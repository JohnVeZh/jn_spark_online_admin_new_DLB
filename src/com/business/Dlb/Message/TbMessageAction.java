package com.business.Dlb.Message;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by qiu on 2017/7/5.
 */
public class TbMessageAction extends BaseAction implements Serializable {

    TbMessageMgr mgr = new TbMessageMgr();

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) {

        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        //分页相关设置
        try {
            int currentPageInt = ParamUtils.getIntParameter(request,
                    "currentPage", 1);
            String strItemsInPage = ParamUtils.getParameter(request,
                    "totalItem", false);
            int itemsInPage = Integer.parseInt((String) SessionUtils
                    .getAttribute(request, "RowCountPerPage"));
            if (strItemsInPage != null) {
                itemsInPage = ParamUtils.getIntParameter(request, "totalItem",
                        15);
            }
            String action = ParamUtils
                    .getParameter(request, "pageAction", true);
            if ("".equals(action))
                action = PageAction.FIRST.toString();

            int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);

            // 接收传值
            String title = ParamUtils.getParameter(request, "titleCondition", false);
            String creatTimeStart = ParamUtils.getParameter(request, "creatTimeStart", false);
            String creatTimeEnd = ParamUtils.getParameter(request, "creatTimeEnd", false);
            String pushTimeStart = ParamUtils.getParameter(request, "pushTimeStart", false);
            String pushTimeEnd = ParamUtils.getParameter(request, "pushTimeEnd", false);
            String createBy = ParamUtils.getParameter(request, "createBy", false);
            // 设置查询条件
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("title", "String", "like", title));
            queryConds.add(new QueryCond("createDate", "String", ">=", creatTimeStart));
            queryConds.add(new QueryCond("createDate", "String", "<=", creatTimeEnd));
            queryConds.add(new QueryCond("pushTime", "String", ">=", pushTimeStart));
            queryConds.add(new QueryCond("pushTime", "String", "<=", pushTimeEnd));
            queryConds.add(new QueryCond("createBy", "String", "like", createBy));

            ListContainer lc = mgr.list("TbMessage", queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);

            request.setAttribute("lc",lc);
            request.setAttribute("ml",lc.getList());

            request.setAttribute("nowDate",new Date());
            request.setAttribute("titleCondition",title);
            request.setAttribute("creatTimeStart",creatTimeStart);
            request.setAttribute("creatTimeEnd",creatTimeEnd);
            request.setAttribute("pushTimeStart",pushTimeStart);
            request.setAttribute("pushTimeEnd",pushTimeEnd);
            request.setAttribute("createBy",createBy);

            return mapping.findForward("list");
        }  catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    public ActionForward preAdd(ActionMapping mapping,ActionForm form,
                             HttpServletRequest request,HttpServletResponse response){


        return mapping.findForward("preAdd");

    }

    public ActionForward add(ActionMapping mapping,ActionForm form,
                             HttpServletRequest request,HttpServletResponse response){
        try {
            //创建一个homework的实体类
            TbMessage message = new TbMessage();
            //接受值
            TbMessageActionForm vo = (TbMessageActionForm) form;
            BeanUtils.copyProperties(message ,vo);

            if (StringUtils.isEmpty(vo.getPushTimeStr())){
                message.setPushTime(new Date());
            }else{
                DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                message.setPushTime(f.parse(vo.getPushTimeStr()));
            }
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if (sessionContainer==null){
                sessionContainer = new SessionContainer();
            }
            message.setCreateBy(sessionContainer.getLoginName());
            message.setCreateDate(new Date());
            message.setTargetType(2);
            message.setPushStatus(0);
            message.setUpdateDate(message.getCreateDate());
            message.setUpdateBy(message.getCreateBy());
            message.setSort(1);
            //添加到数据库
            mgr.add(message);
            //定时任务还是使用极光的api实现定时发送

            request.setAttribute("msg","200");

            return list(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    public ActionForward view(ActionMapping mapping,ActionForm form,
                                   HttpServletRequest request,HttpServletResponse response){
        try {
            String id = ParamUtils.getParameter(request, "id", false);
            TbMessage msg = mgr.view(id);
            request.setAttribute("message", msg);
            return mapping.findForward("view");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }

    }

    public ActionForward preUpdate(ActionMapping mapping,ActionForm form,
                                HttpServletRequest request,HttpServletResponse response){
        try {

            String id = ParamUtils.getParameter(request, "id", false);
            TbMessage msg = mgr.view(id);
            request.setAttribute("message", msg);
            return mapping.findForward("preUpdate");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }

    }
    public ActionForward update(ActionMapping mapping,ActionForm form,
                                HttpServletRequest request,HttpServletResponse response){
        try {

            //接受值
            TbMessageActionForm vo = (TbMessageActionForm) form;


            TbMessage msg = mgr.view(vo.getId());
            BeanUtils.copyProperties(msg ,vo);

            if (StringUtils.isEmpty(vo.getPushTimeStr())){
                msg.setPushTime(new Date());
            }else{
                DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                msg.setPushTime(f.parse(vo.getPushTimeStr()));
            }


            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if (sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            msg.setUpdateDate(new Date());
            msg.setUpdateBy(sessionContainer.getLoginName());
            mgr.add(msg);
            request.setAttribute("msg", 200);

            return list(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    //
    public ActionForward del(ActionMapping mapping,ActionForm form,
                                HttpServletRequest request,HttpServletResponse response){
        try {
            //接受值
            String id = ParamUtils.getParameter(request,"id",true);

//            TbMessage msg = mgr.view(id);
            mgr.deleteById(id);
            return list(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }


}

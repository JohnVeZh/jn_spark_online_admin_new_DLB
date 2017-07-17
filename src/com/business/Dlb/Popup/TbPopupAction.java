package com.business.Dlb.Popup;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;
import com.easecom.system.exception.SystemException;
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
public class TbPopupAction extends BaseAction implements Serializable {

    TbPopupMgr mgr = new TbPopupMgr();

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
            String startTimeStart = ParamUtils.getParameter(request, "startTimeStart", false);
            String endTimeEnd = ParamUtils.getParameter(request, "endTimeEnd", false);
            // 设置查询条件
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("title", "String", "like", title));
            queryConds.add(new QueryCond("createDate", "String", ">=", creatTimeStart));
            queryConds.add(new QueryCond("createDate", "String", "<=", creatTimeEnd));
            queryConds.add(new QueryCond("startTime", "String", ">=", startTimeStart));
            queryConds.add(new QueryCond("endTime", "String", "<=", endTimeEnd));

            ListContainer lc = mgr.list("TbPopup", queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);

            request.setAttribute("lc",lc);
            request.setAttribute("ml",lc.getList());
            request.setAttribute("nowDate",new Date());

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
            TbPopup p = new TbPopup();
            //接受值
            TbPopupActionForm vo = (TbPopupActionForm) form;
            BeanUtils.copyProperties(p ,vo);

            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if (sessionContainer==null){
                sessionContainer = new SessionContainer();
            }
            p.setCreateBy(sessionContainer.getLoginName());
            p.setCreateDate(new Date());
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            p.setStartTime(f.parse(vo.getStartTimeStr()));
            p.setEndTime(f.parse(vo.getEndTimeStr()));
            p.setStatus(1);

            p.setUpdateDate(p.getCreateDate());
            p.setUpdateBy(p.getCreateBy());
            p.setSort(1);
            p.setDelFlag(0);

            request.setAttribute("msg","200");


            if (mgr.count(p.getModule(), vo.getStartTimeStr(), vo.getEndTimeStr(),null)==0){
                //添加到数据库
                mgr.add(p);
                PopupOperResult result = new PopupOperResult();
                result.setSuccess(true);
                JsonUtils.outputJsonResponse(response,result );
                return null;
            }else{
                throw new SystemException("该时间范围内存在有效的弹窗,时间不能重叠");
            }






//            return list(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
//            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
//                    "返回", "javascript:window.history.back()");
//            request.setAttribute("DialogBox", dialog);
//            return mapping.findForward("DialogBox");

            PopupOperResult result = new PopupOperResult();
            result.setSuccess(false);
            result.setMsg(ex.getMessage());
            JsonUtils.outputJsonResponse(response,result );
            return null;
        }
    }


    public ActionForward preUpdate(ActionMapping mapping,ActionForm form,
                                HttpServletRequest request,HttpServletResponse response){
        try {
            String id = ParamUtils.getParameter(request, "id", false);
            TbPopup msg = mgr.view(id);
            request.setAttribute("m", msg);
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
            TbPopupActionForm vo = (TbPopupActionForm) form;


            TbPopup p = mgr.view(vo.getId());
            BeanUtils.copyProperties(p ,vo);

            DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            p.setStartTime(f.parse(vo.getStartTimeStr()));
            p.setEndTime(f.parse(vo.getEndTimeStr()));
            p.setStatus(1);

            p.setUpdateDate(p.getCreateDate());
            p.setUpdateBy(p.getCreateBy());
            p.setSort(1);
            p.setDelFlag(0);


            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if (sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            p.setUpdateDate(new Date());
            p.setUpdateBy(sessionContainer.getLoginName());


            request.setAttribute("msg","200");

            if (mgr.count(p.getModule(), vo.getStartTimeStr(), vo.getEndTimeStr(),p.getId())==0){
                //添加到数据库
                mgr.add(p);
            }else{
                throw new SystemException("该时间范围内存在有效的弹窗,时间不能重叠");
            }
            request.setAttribute("msg", 200);
            PopupOperResult result = new PopupOperResult();
            result.setSuccess(true);
            JsonUtils.outputJsonResponse(response,result );
        } catch (Exception ex) {

//            log.error(ex.getMessage(), ex);
//            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
//                    "返回", "javascript:window.history.back()");
//            request.setAttribute("DialogBox", dialog);
//            return mapping.findForward("DialogBox");

            PopupOperResult result = new PopupOperResult();
            result.setSuccess(false);
            result.setMsg(ex.getMessage());
            JsonUtils.outputJsonResponse(response,result );

        }
        return null;
    }

    //
    public ActionForward del(ActionMapping mapping,ActionForm form,
                                HttpServletRequest request,HttpServletResponse response){
        try {
            //接受值
            String id = ParamUtils.getParameter(request,"id",true);

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
    public ActionForward down(ActionMapping mapping,ActionForm form,
                             HttpServletRequest request,HttpServletResponse response){
        try {
            //接受值
            String id = ParamUtils.getParameter(request,"id",true);

            mgr.down(id);
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

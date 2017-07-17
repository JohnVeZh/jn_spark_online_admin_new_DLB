package com.business.Batch;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;
import com.easecom.system.business.SysConfigMgr;
import com.easecom.system.model.SysConfig;
import com.easecom.system.web.SysConfigForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by liubaibing on 2016/11/24.
 */
public class BatchAction extends BaseAction {

    SysConfigMgr mgr = new SysConfigMgr();

    /**
     * 得到列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) {
        SessionContainer sessionContainer = (SessionContainer) request
                .getSession().getAttribute("SessionContainer");
        if (null == sessionContainer)
            sessionContainer = new SessionContainer();
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
                action = PageAction.CURRENT.toString();

            int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
            String name = ParamUtils.getParameter(request,"searchName");
            // 设置查询条件
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("sc.type", "String", "=", "batch"));
            queryConds.add(new QueryCond("sc.name", "String", "=", name));
            ListContainer lc = mgr.list(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage," order by name desc");
            request.setAttribute("lc", lc);

            return mapping.findForward("list");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    public ActionForward add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        Map map = new HashMap<>();
        try {
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }
            SysConfigForm scf = (SysConfigForm)form;
            SysConfig sc = new SysConfig();
            String id = UUID.randomUUID().toString().replaceAll("-","");
            sc.setId(id);
            sc.setAlias(scf.getName());
            sc.setName(scf.getName());
            sc.setType("batch");
            sc.setValue(scf.getName());
            String result = mgr.add(sc);
            map.put("res",true);
            // 获取ip地址
            String ipaddress = IpAddressUtil.getIpAddr(request);
            //添加操作记录
            Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(),"添加批次,Id:" +id +",批次名称："+scf.getName(), "1", ipaddress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            map.put("res",false);
        }
       JsonUtils.outputJsonResponse(response,map);
        return null;
    }
    public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        Map map = new HashMap<>();
        try {
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }
            SysConfigForm scf = (SysConfigForm)form;
            SysConfig sc = new SysConfig();
            sc.setId(scf.getId());
            sc.setAlias(scf.getName());
            sc.setName(scf.getName());
            sc.setType("batch");
            sc.setValue(scf.getName());
            boolean result = mgr.update(sc);
            map.put("res",result);
            // 获取ip地址
            String ipaddress = IpAddressUtil.getIpAddr(request);
            //添加操作记录
            Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(),"修改批次,Id:" +scf.getId() +",批次名称："+scf.getName(), "1", ipaddress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            map.put("res",false);
        }
        JsonUtils.outputJsonResponse(response,map);
        return null;
    }

    public ActionForward isExists(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        Map map = new HashMap<>();
        SysConfigForm scf = (SysConfigForm) form;
        try {
            List ls = Tool.query("select count(id) res from sys_config where name = '"+scf.getName()+"' and type='batch' ");
            map = (Map)ls.get(0);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            map.put("RES",-1);
        }
        JsonUtils.outputJsonResponse(response,map);
        return null;
    }
    public String isUsed(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        Map map = new HashMap<>();
        SysConfigForm scf = (SysConfigForm) form;
        try {
            List ls = Tool.query("select count(id) res from product where batch_time = '"+scf.getName()+"' ");
            map = (Map)ls.get(0);
            int result =Integer.parseInt(map.get("RES").toString());
            if(result>0){
                map.put("RES",1);//代表图书内有使用该批次码的
            }else{
                ls = Tool.query("select count(id) res from bookcode4 where batch_time = '"+scf.getName()+"' ");
                map = (Map)ls.get(0);
                result =Integer.parseInt(map.get("RES").toString());
                if(result>0){
                    map.put("RES",2);//代表四级激活码内有使用该批次码的
                }else{
                    ls =  mgr.SQLQuery("select count(id) res from bookcode6 where batch_time = '"+scf.getName()+"' ");
                    map = (Map)ls.get(0);
                    result =Integer.parseInt(map.get("RES").toString());
                    if(result>0){
                        map.put("RES",3);//代表六级激活码内有使用该批次码的
                    }else{
                        map.put("RES",0);
                    }
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            map.put("RES",-1);
        }
        JsonUtils.outputJsonResponse(response,map);
        return null;
    }
    public ActionForward realdeleteById(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        try {
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }
            SysConfigForm scf = (SysConfigForm) form;
            boolean bl = Tool.execute("delete from sys_config where id = '"+scf.getId()+"' ");
            // 获取ip地址
            String ipaddress = IpAddressUtil.getIpAddr(request);
            //添加操作记录
            Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(),"删除批次,Id:" +scf.getId()+",批次名称"+scf.getName() , "1", ipaddress);
            return list(mapping, form, request, response);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }
    /**
     * 删除信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward realdelete(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) {

        try {
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            String[] ids = ParamUtils.getParameterValues(request, "id", true);
            String id="";
            for (int i = 0; i < ids.length; i++) {
                if(i == ids.length-1){
                    id +=  "'"+ids[i]+"'";
                }else{
                    id +=  "'"+ids[i]+"',";
                }


            }
            Tool.execute("delete from sys_config where id in ("+id+")");
            // 获取ip地址
            String ipaddress = IpAddressUtil.getIpAddr(request);
            //添加操作记录
            Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(),"删除多条资讯,Id集合:" +id , "1", ipaddress);
            return list(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    public String getBatch(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        List<Map> ls = null;
        try {
            ls = (List<Map>) mgr.SQLQuery("select name from sys_config where type = 'batch' order by name desc");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        JsonUtils.outputJsonArrayResponse(response,ls);
        return null;
    }
}

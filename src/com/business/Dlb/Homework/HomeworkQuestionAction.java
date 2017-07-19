package com.business.Dlb.Homework;

import com.business.v2.question.TbQuestionListening;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;
import com.easecom.system.exception.SystemException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.*;

/**
 * Created by sprke on 2017/7/8.
 */
public class HomeworkQuestionAction extends BaseAction implements Serializable {


    HomeworkQuestionMgr mgr = new HomeworkQuestionMgr();
    HomeworkMgr homeworkMgr = new HomeworkMgr();
    /**
     * 获取作业列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward listenList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response){

        try {
            //分页相关设置
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

            //接收参数
            String execriseId = ParamUtils.getParameter(request,"execriseId",true);
            String questionType = ParamUtils.getParameter(request,"questionType",true);
            String questionName = ParamUtils.getParameter(request,"questionName",true);
            // 设置查询条件
            Collection queryConds = new ArrayList();
            if (!"".equals(execriseId)){
                queryConds.add(new QueryCond("deq.execrise_id", "String", "=", execriseId));
            }
            if (!"".equals(questionName)){
                queryConds.add(new QueryCond("deq.question_name", "String", "like", questionName));
            }
            ListContainer lc = mgr.listentList(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage,questionType);

            request.setAttribute("execriseId",execriseId);

            request.setAttribute("questionName",questionName);
            request.setAttribute("questionType",questionType);
            request.setAttribute("lc",lc);
            String forward = "";
            switch (questionType){
                case "1" : forward = "listenList";
                break;
                case "2" : forward = "readList";
                break;
                case "3" : forward = "translationList";
                break;
                case "4" : forward = "writeList";
                break;
            }
                return mapping.findForward(forward);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 添加作业与题目的关系
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public ActionForward Add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response){
        try{

            //接受参数
            String idVal = ParamUtils.getParameter(request,"idVal",true);
            String nameVal = ParamUtils.getParameter(request,"nameVal",true);
            String sortVal =  ParamUtils.getParameter(request,"sortVal",true);
            String execriseId = ParamUtils.getParameter(request,"execriseId",true);

            String[] questionIds = null;
            String[] questionNames = null;
            String[] sorts = null;
            if (!"".equals(idVal)){
                questionIds = idVal.split(",");
            }
            if (!"".equals(nameVal)){
                questionNames = nameVal.split(";");
            }
            if (!"".equals(sortVal)){
                sorts = sortVal.split(";");
            }

            mgr.add(questionIds,questionNames,sorts,execriseId);
            Map map = new HashMap();
            map.put("result", true);
            JsonUtils.outputJsonResponse(response, map);
            return null;
        }catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }

    }

    /**
     * 预修改修改题目的排序
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward preUpdate(ActionMapping mapping,ActionForm form,
                                   HttpServletRequest request,HttpServletResponse response){
        try{
            SessionContainer sess = (SessionContainer) request.getSession().getAttribute("sessionContainer");
            if(sess == null){
                sess = new SessionContainer();
            }
            String id = ParamUtils.getParameter(request,"id",true);
            String questionType = ParamUtils.getParameter(request,"questionType",true);
            String execriseId = ParamUtils.getParameter(request,"execriseId",true);
            if (!id.equals("")){
                HomeworkQuestion homeworkQuestion = mgr.view(id);
                request.setAttribute("homeworkQuestion",homeworkQuestion);
            }
            request.setAttribute("questionType",questionType);
            request.setAttribute("execriseId",execriseId);
            return mapping.findForward("update");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取添加页面出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 更新排序
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward update(ActionMapping mapping,ActionForm form,
                                HttpServletRequest request,HttpServletResponse response){
        try {

            //接受值
            String id = ParamUtils.getParameter(request,"homeworkQuestionId",true);
            String sort = ParamUtils.getParameter(request,"sort",true);
            String questionType = ParamUtils.getParameter(request,"questionType",true);
            String execriseId = ParamUtils.getParameter(request,"execriseId",true);

            HomeworkQuestion homeworkQuestion = new HomeworkQuestion();
            //根据id获取题目
            if (!"".equals(id)){
               homeworkQuestion =  mgr.view(id);
            }
            homeworkQuestion.setSort(Integer.parseInt(sort));

            mgr.update(homeworkQuestion);
            SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("sessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            return listenList(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 作业和题目解绑
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward delete(ActionMapping mapping,ActionForm form,
                                HttpServletRequest request,HttpServletResponse response){
        try{

            String id = ParamUtils.getParameter(request,"id",true);
            String execriseId = ParamUtils.getParameter(request,"execriseId",true);
            String questionType = ParamUtils.getParameter(request,"questionType",true);

            if (!"".equals(id)){
                HomeworkQuestion homeworkQuestion = mgr.view(id);
                mgr.deleteById(homeworkQuestion);
            }

            return listenList(mapping, form, request, response);

        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 作业与添加题目
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws SystemException
     */
    public ActionForward preAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                HttpServletResponse response) throws SystemException {

        try{
            SessionContainer sess = (SessionContainer) request.getSession().getAttribute("sessionContainer");
            if(sess == null){
                sess = new SessionContainer();
            }
            String execriseId = ParamUtils.getParameter(request,"execriseId",true);
            String questionType = ParamUtils.getParameter(request,"questionType",true);
            if (!"".equals(execriseId)){
                Homework homework = homeworkMgr.view(execriseId);
                request.setAttribute("homework",homework);
            }

            request.setAttribute("questionType",questionType);
            request.setAttribute("execriseId",execriseId);
            return mapping.findForward("add");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取添加页面出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 作业添加题目
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward add(ActionMapping mapping,ActionForm form,
                             HttpServletRequest request,HttpServletResponse response){
        try {

            //接受值
            String execriseId = ParamUtils.getParameter(request,"execriseId",true);
            String questionName1 = ParamUtils.getParameter(request,"questionName1",true);
            String questionId = ParamUtils.getParameter(request,"value",true);
            String sort = ParamUtils.getParameter(request,"sort",true);
            String questionType = ParamUtils.getParameter(request,"questionType",true);

            HomeworkQuestion homeworkQuestion = new HomeworkQuestion();
            homeworkQuestion.setHomeworkId(execriseId);
            homeworkQuestion.setQuestionId(questionId);
            homeworkQuestion.setQuestionName(questionName1);
            homeworkQuestion.setSort(Integer.parseInt(sort));

            mgr.add(homeworkQuestion);

            SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("sessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            return listenList(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }
}

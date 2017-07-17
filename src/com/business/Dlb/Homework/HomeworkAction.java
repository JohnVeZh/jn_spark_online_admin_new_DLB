package com.business.Dlb.Homework;

import com.business.NetworkCourse.NetworkCourse;
import com.business.NetworkCourse.NetworkCourseMgr;
import com.business.NetworkCourseTeacher.NetworkCourseTeacherMgr;
import com.business.NetworkCourseVideo.NetworkCourseVideo;
import com.business.NetworkCourseVideo.NetworkCourseVideoMgr;
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
 * Created by sprke on 2017/7/5.
 */
public class HomeworkAction extends BaseAction implements Serializable {

    HomeworkMgr mgr = new HomeworkMgr();
    NetworkCourseTeacherMgr teacherMgr = new NetworkCourseTeacherMgr();
    NetworkCourseMgr networkCourseMgr = new NetworkCourseMgr();
    NetworkCourseVideoMgr videoMgr = new NetworkCourseVideoMgr();
    HomeworkQuestionMgr homeworkQuestionMgr = new HomeworkQuestionMgr();

    /**
     * 得到列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward courseList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) {

        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
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

            // 接收传值
            String ncNamelike = ParamUtils.getParameter(request, "ncNamelike", false);
            // 设置查询条件
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("ncName", "String", "like", ncNamelike));

            ListContainer lc = mgr.courseList(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);

            List ll = lc.getList();
            List<Map> la = new ArrayList<Map>();
            for (int i = 0; i < ll.size(); i++){
                NetworkCourse networkCourse = null;
                NetworkCourse nc = (NetworkCourse) ll.get(i);

                Map ncMap = new HashMap();
                ncMap.put("ncId",nc.getId());
                ncMap.put("ncImg",nc.getNcImg());
                ncMap.put("ncName",nc.getNcName());
                ncMap.put("ncType",nc.getNcType());
                ncMap.put("ncLevel",nc.getNcLevel());
                ncMap.put("ncLevelType",nc.getNcLevelType());
                ncMap.put("ncLiveTime",nc.getNcLiveTime());
                ncMap.put("ncEndTime",nc.getNcEndTime());

                List<NetworkCourseVideo> videoList = Tool.findListByHql("from NetworkCourseVideo where nvId = '"+nc.getId()+"'");
                List<Map> mList = new ArrayList<Map>();
                for(NetworkCourseVideo video : videoList){
                    Map vMap = new HashMap();
                    vMap.put("videoId",video.getId());
                    vMap.put("ncvName",video.getNcvName());
                    vMap.put("ncvStartTime",video.getNcvStartTime());
                    vMap.put("ncvDuration",video.getNcvDuration());
                    String teacherIdStr = video.getTeacherId();
                    String[] teacherIds = teacherIdStr.split(",");
                    String teacherName = new String();
                    for (int j = 0; j < teacherIds.length; j++){
                        teacherName = teacherMgr.view(teacherIds[j]).getName();
                        teacherName = teacherName + "";
                    }
                    vMap.put("teacher",teacherName);
                    mList.add(vMap);
                }
                ncMap.put("mList",mList);
                la.add(ncMap);
            }
            //回显网课名称
            request.setAttribute("ncNamelike",ncNamelike);
            request.setAttribute("courseList", la);
            request.setAttribute("lc",lc);

            return mapping.findForward("courseList");
        }  catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 预添加功能
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                               HttpServletResponse response) throws SystemException {

        try{
            SessionContainer sess = (SessionContainer) request.getSession().getAttribute("sessionContainer");
            if(sess == null){
                sess = new SessionContainer();
            }
            String videoId = ParamUtils.getParameter(request,"videoId",true);
            String ncId = ParamUtils.getParameter(request,"ncId",true);
            if (!ncId.equals("")){
                NetworkCourse networkCourse = networkCourseMgr.view(ncId);
                NetworkCourseVideo networkCourseVideo = videoMgr.view(videoId);
                request.setAttribute("networkCourse",networkCourse);
                request.setAttribute("networkCourseVideo",networkCourseVideo);
                if (networkCourse.getNcLevel().equals("cet4")){
                    request.setAttribute("section","01");
                }
                if (networkCourse.getNcLevel().equals("cet6")){
                    request.setAttribute("section","02");
                }
            }

            request.setAttribute("videoId",videoId);
            request.setAttribute("ncId",ncId);
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
     * 添加
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
            String section = ParamUtils.getParameter(request,"section",true);
            String questionType = ParamUtils.getParameter(request,"questionType",true);
            String title = ParamUtils.getParameter(request,"title",true);
            String sort = ParamUtils.getParameter(request,"sort",true);
            String displayDate = ParamUtils.getParameter(request,"displayDate",true);
            String courseId = ParamUtils.getParameter(request,"ncId",true);
            String courseCatalogId = ParamUtils.getParameter(request,"videoId",true);
            String id = ParamUtils.getParameter(request,"id",true);

            //创建一个homework的实体类
            Homework homework = new Homework();
            if (!"".equals(id)){
                homework = mgr.view(id);
            }
            homework.setSection(section);
            homework.setQuestionType(Integer.parseInt(questionType));
            homework.setTitle(title);
            homework.setSort(Integer.parseInt(sort));
            homework.setDisplayDate(displayDate);
            homework.setCourseId(courseId);
            homework.setCourseCatalogId(courseCatalogId);

            //添加到数据库
            mgr.add(homework);

            SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("sessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            return homeworkList(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 网课后面的作业列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward homeworkList(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response){
        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
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

            //接受参数
            String courseCatalogId = ParamUtils.getParameter(request,"videoId",true);
            String courseId = ParamUtils.getParameter(request,"ncId",true);
            String titleLike = ParamUtils.getParameter(request,"titleLike",true);

            // 设置查询条件
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("courseCatalogId","String","=",courseCatalogId));
            queryConds.add(new QueryCond("courseId","String","=",courseId));
            queryConds.add(new QueryCond("title", "String", "like", titleLike));

            ListContainer lc = mgr.homeworkList(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);

            List<Homework> list = lc.getList();
            if (!"".equals(courseId)){
                NetworkCourse networkCourse = networkCourseMgr.view(courseId);
                request.setAttribute("ncName",networkCourse.getNcName());
            }
            if (!"".equals(courseCatalogId)){
                NetworkCourseVideo video = videoMgr.view(courseCatalogId);
                request.setAttribute("ncvName",video.getNcvName());
            }

            request.setAttribute("homeworkList",list);
            request.setAttribute("courseId",courseId);
            request.setAttribute("courseCatalogId",courseCatalogId);
            request.setAttribute("titleLike",titleLike);
            request.setAttribute("lc",lc);

            return mapping.findForward("homeworkList");
        }  catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 作业与网课解绑
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

            Homework homework = mgr.view(id);
            mgr.deleteById(homework);

            return homeworkList(mapping, form, request, response);

        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 预修改
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward preUpdate(ActionMapping mapping,ActionForm form,
                                   HttpServletRequest request,HttpServletResponse response){

        SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
        if (null == sessionContainer)
            sessionContainer = new SessionContainer();
        try{

            //接收数据
            String id = ParamUtils.getParameter(request,"id",true);
            Homework homework = null;
            NetworkCourse networkCourse = null;
            if (id != ""){
                //根据id获取到数据
                homework = mgr.view(id);
                //根据网课id获取网课名称
                if (homework != null){
                    String questionId = "";
                    String questionName = "";
                    networkCourse = (NetworkCourse) mgr.getObjectByHql("from NetworkCourse where id = '" + homework.getCourseId() + "'");
                    List<HomeworkQuestion> list = homeworkQuestionMgr.getHQByHomeId(homework.getId());
                    for(HomeworkQuestion hq : list){
                        questionId += hq.getQuestionId()+",";
                        questionName += hq.getQuestionName()+";";
                    }
                    request.setAttribute("questionId",questionId);
                    request.setAttribute("questionName",questionName);
                }
            }


            homework.setDisplayDate((String) homework.getDisplayDate().subSequence(0,homework.getDisplayDate().length()-2));
            request.setAttribute("homework",homework);
            if (networkCourse != null){
                request.setAttribute("ncName",networkCourse.getNcName());
            }else{
                request.setAttribute("ncName","");
            }
            return mapping.findForward("edit");
        }catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取修改页面出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 更新
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
            String id = ParamUtils.getParameter(request,"id",true);
            String section = ParamUtils.getParameter(request,"section",true);
            String questionType = ParamUtils.getParameter(request,"questionType",true);
            String title = ParamUtils.getParameter(request,"title",true);
            String sort = ParamUtils.getParameter(request,"sort",true);
            String displayDate = ParamUtils.getParameter(request,"displayDate",true);
            String questionIds = ParamUtils.getParameter(request,"value",true);
            String questionNames = ParamUtils.getParameter(request,"questionName",true);

            String[] questionId = null;
            String [] questionName = null;
            if (!"".equals(questionIds)){
                questionId = questionIds.split(",");
            }
            if(!"".equals(questionNames)){
                questionName = questionNames.split(";");
            }

            Homework homework = mgr.view(id);
            homework.setSection(section);
            homework.setQuestionType(Integer.parseInt(questionType));
            homework.setTitle(title);
            homework.setSort(Integer.parseInt(sort));
            homework.setDisplayDate(displayDate);

            mgr.update(homework);

            SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("sessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            return homeworkList(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }
}

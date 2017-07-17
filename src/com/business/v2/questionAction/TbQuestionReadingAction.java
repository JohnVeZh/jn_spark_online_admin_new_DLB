package com.business.v2.questionAction;

import com.business.v2.Tools.Reading;
import com.business.v2.Tools.TT;
import com.business.v2.question.TbQuestionReading;
import com.business.v2.question.TbQuestionReadingQuestion;
import com.business.v2.question.TbQuestionReadingQuestionOption;
import com.business.v2.special.TbSpecialCatalog;
import com.business.v2.special.TbSpecialTraining;
import com.easecom.common.framework.hibernate.BaseModel;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by john on 2017/4/24.
 */
public class TbQuestionReadingAction  extends BaseAction {

    TbQuestionReadingMgr mgr = new TbQuestionReadingMgr();
    TbSpecialCatatlogMgr tbSpecialCatatlogMgr = new TbSpecialCatatlogMgr();
    TbSpecialTrainingMgr tbSpecialTrainingMgr = new TbSpecialTrainingMgr();
    TbQuestionReadingQuestionMgr tbQuestionReadingQuestionMgr = new TbQuestionReadingQuestionMgr();
    TbQuestionReadingQuestionOptionMgr tbQuestionReadingQuestionOptionMgr = new TbQuestionReadingQuestionOptionMgr();

    /**
     *  绑定作业时需要的阅读列表
     */
    public ActionForward questionReadList (ActionMapping mapping, ActionForm form,
                                             HttpServletRequest request, HttpServletResponse response) {
        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
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
            String title = ParamUtils.getParameter(request, "title", true);
            // 设置查询条件
            Collection queryConds = new ArrayList();
            if (!"".equals(title)){
                queryConds.add(new QueryCond("title","String","like",title));
            }

            ListContainer lc = mgr.questionReadList(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);
            List<TbQuestionReading> list = lc.getList();

            request.setAttribute("dlbList",list);
            request.setAttribute("title",title);
            request.setAttribute("lc", lc);
            return mapping.findForward("dlbReadList");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 生产功能树
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward treelist(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) {
        try {
            String rootid = "FFFFFF";

            List treelist = null;
            treelist = mgr.getFuntree(rootid);

            request.setAttribute("treelist", treelist);
            request.setAttribute("rootid", rootid);
            return mapping.findForward("tree");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);

            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取功能树时出错", "返回",
                    "javascript:window.history.back()");

            request.setAttribute("DialogBox", dialog);

            return mapping.findForward("DialogBox");
        }
    }

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
        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
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
            String parentid = ParamUtils.getParameter(request, "parentid", true);
            String id = ParamUtils.getParameter(request,"id",true);
            //String loginName = ParamUtils.getParameter(request, "loginName", false);
            // 设置查询条件
            Collection queryConds = new ArrayList();
            if(parentid.equals("") && "".equals(id)){
                queryConds.add(new QueryCond("t1.p_id","String","in","010301,010302,010303,020301,020302,020303"));
                request.setAttribute("flag","0");
            }else if ("".equals(id) && !"".equals(parentid)){
                queryConds.add(new QueryCond("t1.p_id", "String", "=", parentid));
                request.setAttribute("flag","1");
            }else if ("".equals(parentid) && !"".equals(id)){
                queryConds.add(new QueryCond("t1.id", "String", "=", id));
                request.setAttribute("flag","1");
            }
            ListContainer lc = mgr.list(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);
            //request.setAttribute("name", name);
            //request.setAttribute("loginName", loginName);
            request.setAttribute("lc", lc);
            /*if(null==parentid || "".equals(parentid))
                parentid = "FFFFFF";*/
            request.setAttribute("parentid", parentid);
            return mapping.findForward("list");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 下载模板
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public void fileDownload(ActionMapping mapping,ActionForm form,
                             HttpServletRequest request,HttpServletResponse response){

        try {
            String fileName = ParamUtils.getParameter(request,"fileName");
            String filePath = ParamUtils.getParameter(request,"filePath");
            filePath = request.getSession().getServletContext().getRealPath("/") + filePath;
            // 判断文件是否存在
            File file = new File(filePath);
            if (!file.exists()) { // 不存在，返回false
                throw new Exception("没找到该文件，该文件可能已被删除");
            }
            String displayFileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename="
                    + displayFileName);// 不是显示是下载
            BufferedInputStream inputStream = null;
            BufferedOutputStream outputStream = null;
            try {
                inputStream = new BufferedInputStream(new FileInputStream(filePath));
                outputStream = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                while ((bytesRead = inputStream.read(buff, 0, buff.length)) != -1) {
                    outputStream.write(buff, 0, bytesRead);
                }
                outputStream.flush();
            } catch (final IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }  finally {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * 预添加
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward preAdd(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response){
        try{
            String catalogId = ParamUtils.getParameter(request,"id");
            TbSpecialCatalogActionForm catalogActionForm = tbSpecialCatatlogMgr.view(catalogId);
            request.setAttribute("parentid",catalogActionForm.getPId());
            request.setAttribute("id",catalogId);

            //获取订单列表
            String nameStatus = QuestionGetSelelct.getList("select id,name from tb_special_catalog where p_id IN ('0102','0202') order by create_time","name","id");
            if(!"".equals(catalogId)&&null!=catalogId){
                nameStatus=QuestionGetSelelct.getList("select id,name from tb_special_catalog where p_id IN ('0103','0203') order by create_time","name","id",catalogActionForm.getPId());
            }
            request.setAttribute("nameStatus",nameStatus);

            SessionContainer sess = (SessionContainer) request.getSession().getAttribute("sessionContainer");
            if(sess == null){
                sess = new SessionContainer();
            }
            return mapping.findForward("add");
        }catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 添加
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response){

        try {
            String parentid = ParamUtils.getParameter(request,"parentid");
            String id = ParamUtils.getParameter(request,"id");

            //目录
            String name = ParamUtils.getParameter(request,"name");
            String sortOrder = ParamUtils.getParameter(request,"sortOrder");
            String target = ParamUtils.getParameter(request,"target");

            TbSpecialCatalog tbSpecialCatalog = new TbSpecialCatalog();

            tbSpecialCatalog.setCreateTime(new Date());
            tbSpecialCatalog.setPId(parentid);
            String lastId = tbSpecialCatatlogMgr.getLast("id", parentid);
            String s1 = lastId.replaceFirst(parentid, "");
            String newId = parentid +new DecimalFormat("000000").format(Integer.valueOf(s1)+1);
            tbSpecialCatalog.setId(newId);
            tbSpecialCatalog.setName(name);
            tbSpecialCatalog.setSortOrder(Integer.valueOf(sortOrder));
            tbSpecialCatalog.setIsDel("0");


            //题目

            TbSpecialTraining tbSpecialTraining = new TbSpecialTraining();

            String trainingid = UUID.randomUUID().toString().replace("-", "");//题目Id
            String trianingId = UUID.randomUUID().toString().replace("-", "");//trianing_id
            tbSpecialTraining.setId(trainingid);
            tbSpecialTraining.setSection(parentid.substring(0,2));
            tbSpecialTraining.setCatalogId(newId);
            tbSpecialTraining.setTrainingType("2");
            tbSpecialTraining.setTrainingId(trianingId);
            tbSpecialTraining.setSortOrder(1);


            //阅读片段
            String title = ParamUtils.getParameter(request,"title");
            String content = ParamUtils.getParameter(request,"content");
            String translation = ParamUtils.getParameter(request,"translation");

            TbQuestionReading tbQuestionReading = new TbQuestionReading();

            tbQuestionReading.setId(trianingId);
            tbQuestionReading.setTitle(title);
            tbQuestionReading.setContent(content);
            tbQuestionReading.setTranslation(translation);
            tbQuestionReading.setIsDel("0");
            tbQuestionReading.setCreateTime(new Date());
            tbQuestionReading.setTarget(target);

            mgr.addAll(tbSpecialCatalog,tbSpecialTraining,tbQuestionReading);

            SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("sessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }


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
     * 预修改
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward preUpdate(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) {
        SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
        if (null == sessionContainer)
            sessionContainer = new SessionContainer();
        try{
            String ID = ParamUtils.getParameter(request,"id");
            TbSpecialCatalogActionForm catalogActionForm = tbSpecialCatatlogMgr.view(ID);
            String PId = catalogActionForm.getPId();
            String readingId = "";
            List list = Tool.query("select * from tb_question_reading where id = (\n" +
            "select training_id from tb_special_training where  catalog_id = '" + ID + "')");
            if (null != list && list.size()>0 ) {
                readingId = ((Map<String,String>)list.get(0)).get("ID");
            }
            TbQuestionReadingActionForm readingActionForm = mgr.view(readingId);
            String p_id = "";
            if (ID !=null && !"".equals(ID)){
                p_id = ID.substring(0,4);
            }
            //获取订单列表
            /*String nameStatus = Tool.getList("select id,name from tb_special_catalog where p_id = '"+p_id+"' order by create_time","name","id");
            if(!"".equals(ID)&&null!=ID){
                nameStatus=Tool.getList("select id,name from tb_special_catalog where p_id = '"+p_id+"' order by create_time","name","id",PId);
            }*/
            String nameStatus = QuestionGetSelelct.getList("select id,name from tb_special_catalog where p_id = '"+p_id+"' order by create_time","name","id");
            if(!"".equals(ID)&&null!=ID){
                nameStatus=QuestionGetSelelct.getList("select id,name from tb_special_catalog where p_id = '"+p_id+"' order by create_time","name","id",PId);
            }
            request.setAttribute("TbSpecialCatalogActionForm",catalogActionForm);
            request.setAttribute("TbQuestionReadingActionForm",readingActionForm);
            request.setAttribute("nameStatus",nameStatus);
            return mapping.findForward("update");
        }catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取修改页面出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }

    }

    /**
     * 修改
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) {

        try{
            String title = ParamUtils.getParameter(request,"title");
            String name = ParamUtils.getParameter(request,"name");
            String sortOrder = ParamUtils.getParameter(request,"sortOrder");
            String PId = ParamUtils.getParameter(request,"PId");
            String translation = ParamUtils.getParameter(request,"translation");
            String content = ParamUtils.getParameter(request,"content");
            String catalogId = ParamUtils.getParameter(request,"catalogId");
            String readingId = ParamUtils.getParameter(request,"readingId");
            String target = ParamUtils.getParameter(request,"target");

            TbSpecialCatalogActionForm catalogActionForm = tbSpecialCatatlogMgr.view(catalogId);
            catalogActionForm.setName(name);
            catalogActionForm.setSortOrder(Integer.valueOf(sortOrder));
            catalogActionForm.setPId(PId);

            TbQuestionReadingActionForm readingActionForm = mgr.view(readingId);
            readingActionForm.setTitle(title);
            readingActionForm.setContent(content);
            readingActionForm.setTranslation(translation);
            readingActionForm.setTarget(target);

//            tbSpecialCatatlogMgr.update(catalogActionForm);
            mgr.updateCatalogAndRead(readingActionForm,catalogActionForm);

            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }
            String ipaddress = IpAddressUtil.getIpAddr(request);
            String username = "admin";
            Tool.AddLog(sessionContainer.getUserId(), username,
                    "修改阅读:"+readingId, "1", ipaddress);


            return list(mapping,form,request,response);
        }catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }

    }

    /**
     * 删除
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) {

        try{
            String catalogId = ParamUtils.getParameter(request,"id");
            String trainId = "";
            String readingId = "";
            String questionId = "";
            String optionId = "";

            List trainList = Tool.findListByHql("select id from TbSpecialTraining where catalogId = " + catalogId);
            if (null != trainList && trainList.size()>0){
                trainId = (String) trainList.get(0);
                if (null != trainId && !"".equals(trainId)){
                    TbSpecialTrainingActionForm trainingActionForm = tbSpecialTrainingMgr.view(trainId);
                    readingId = trainingActionForm.getTrainingId();
                    if(null != readingId && !readingId.equals("")){
                        List questionList = Tool.findListByHql("select id from TbQuestionReadingQuestion where readingId = '" + readingId + "'");
                        if (null != questionList && questionList.size()>0){
                            questionId = (String) questionList.get(0);
                            if (null != questionId && !"".equals(questionId)){
                                List optionList = Tool.findListByHql("select id from TbQuestionReadingQuestionOption where questionId = '" + questionId + "'");
                                if(null != optionList && optionList.size() > 0){
                                    optionId = (String) optionList.get(0);
                                }
                            }
                        }
                    }
                }
            }

            mgr.deleteAll(catalogId,trainId,readingId,questionId,optionId);
            return list(mapping,form,request,response);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 配题
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward matchSubject(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response){
        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try{

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
            String catalogId = ParamUtils.getParameter(request, "catalogId", true);
            String readingId = "";
            List list = Tool.query("select * from tb_question_reading where id = (\n" +
                    "select training_id from tb_special_training where  catalog_id = '" + catalogId + "')");
            if (null != list && list.size()>0 ) {
                readingId = ((Map<String,String>)list.get(0)).get("ID");
            }else{
                readingId = catalogId;
            }
            //String loginName = ParamUtils.getParameter(request, "loginName", false);
            // 设置查询条件
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("readingId", "String", "=", readingId));
            request.setAttribute("flag","1");
            ListContainer lc = mgr.matchList(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);
            request.setAttribute("lc", lc);

            request.setAttribute("parentid", readingId);
            return mapping.findForward("matchSubject");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 配题预添加
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward preAddSubject(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response){

        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try{
            String readingId = ParamUtils.getParameter(request,"parentid");
            request.setAttribute("readingId",readingId);

            return mapping.findForward("preAddSubject");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }

    }

    /**
     * 配题添加
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward addSubject(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response){

        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try{
            String questionId = UUID.randomUUID().toString().replace("-","");
            String readingId = ParamUtils.getParameter(request,"readingId");
            String title = ParamUtils.getParameter(request,"title");
            String analysis = ParamUtils.getParameter(request,"analysis");
            Integer sortOrder = Integer.valueOf(ParamUtils.getParameter(request,"sortOrder"));

            TbQuestionReadingQuestion question = new TbQuestionReadingQuestion();

            question.setId(questionId);
            question.setReadingId(readingId);
            question.setTitle(title);
            question.setAnalysis(analysis);
            question.setSortOrder(sortOrder);

            tbQuestionReadingQuestionMgr.add(question);

            return list(mapping,form,request,response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }

    }

    /**
     * 配题预修改
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward preUpdateSubject(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response){

        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();

        try{
            String questionId = ParamUtils.getParameter(request,"id");
            TbQuestionReadingQuestionActionForm questionForm = tbQuestionReadingQuestionMgr.view(questionId);
            request.setAttribute("questionForm",questionForm);

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

            // 设置查询条件
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("questionId", "String", "=", questionId));
            ListContainer lc = mgr.optionList(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);
            request.setAttribute("lc", lc);

            return mapping.findForward("preUpdateSubject");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 配题修改
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward updateSubject(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response){

        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();

        try{
            String questionId = ParamUtils.getParameter(request,"id");
            TbQuestionReadingQuestionActionForm questionForm = tbQuestionReadingQuestionMgr.view(questionId);
            questionForm.setTitle(ParamUtils.getParameter(request,"title"));
            questionForm.setSortOrder(Integer.valueOf(ParamUtils.getParameter(request,"sortOrder")));
            questionForm.setAnalysis(ParamUtils.getParameter(request,"analysis"));
            tbQuestionReadingQuestionMgr.update(questionForm);
            return list(mapping,form,request,response);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "修改时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 配题删除
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward deleteSubject(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response){

        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try{
            String id = ParamUtils.getParameter(request,"id");

            tbQuestionReadingQuestionMgr.deleteAll(id);
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
     * 选项预添加
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward preAddOption(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response){

        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try{
            String questionId = ParamUtils.getParameter(request,"questionId");
            request.setAttribute("questionId",questionId);
            return mapping.findForward("preAddOption");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 选项添加
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward addOption(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response){

        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try{
            String optionId = UUID.randomUUID().toString().replace("-", "");
            String questionId = ParamUtils.getParameter(request,"questionId");
            String prefix = ParamUtils.getParameter(request,"prefix");
            String content = ParamUtils.getParameter(request,"content");
            String isAnswer = ParamUtils.getParameter(request,"isAnswer");
            TbQuestionReadingQuestionOption option = new TbQuestionReadingQuestionOption();
            option.setId(optionId);
            option.setQuestionId(questionId);
            option.setPrefix(prefix);
            option.setContent(content);
            option.setIsAnswer(isAnswer);
            tbQuestionReadingQuestionMgr.add(option);
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
     * 选项删除
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward deleteOption(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response){

        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try{
            String optionId = ParamUtils.getParameter(request,"optionId");
            TbQuestionReadingQuestionOptionActionForm optionForm = tbQuestionReadingQuestionOptionMgr.view(optionId);
            TbQuestionReadingQuestionOption option = new TbQuestionReadingQuestionOption();
            BeanUtils.copyProperties(option,optionForm);
            tbQuestionReadingQuestionOptionMgr.delete(option);
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
     * 批量导入
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward ExcelImport(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
        SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
        if (null == sessionContainer)
            sessionContainer = new SessionContainer();
        //获取excle文件
        TbQuestionReadingActionForm readingActionForm = (TbQuestionReadingActionForm) form;
        FormFile file = readingActionForm.getFile();
        InputStream inputStream = file.getInputStream();

        try{
            Reading reading = new Reading();
            Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>>> listMap = reading.readFile(inputStream);
            List<BaseModel> list1 = reading.parseData(listMap);
            TT.save(list1);
            return list(mapping,form, request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
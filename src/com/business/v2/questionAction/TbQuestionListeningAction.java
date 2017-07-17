package com.business.v2.questionAction;

import com.business.v2.Tools.Listening;
import com.business.v2.Tools.TT;
import com.business.v2.question.TbQuestionListening;
import com.business.v2.question.TbQuestionListeningQuestion;
import com.business.v2.question.TbQuestionListeningQuestionOption;
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
 * Created by john on 2017/4/13.
 */
public class TbQuestionListeningAction extends BaseAction {

    TbQuestionListeningMgr mgr = new TbQuestionListeningMgr();
    TbSpecialCatatlogMgr tbSpecialCatatlogMgr = new TbSpecialCatatlogMgr();
    TbSpecialTrainingMgr tbSpecialTrainingMgr = new TbSpecialTrainingMgr();
    TbQuestionListeningQuestionMgr tbQuestionListeningQuestionMgr = new TbQuestionListeningQuestionMgr();
    TbQuestionListeningQuestionOptionMgr tbQuestionListeningQuestionOptionMgr = new TbQuestionListeningQuestionOptionMgr();
    TbQuestionListeningSubtitlesMgr tbQuestionListeningSubtitlesMgr = new TbQuestionListeningSubtitlesMgr();

    /**
     *  绑定作业时需要的听力列表
     */
    public ActionForward questionListenList (ActionMapping mapping, ActionForm form,
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

            ListContainer lc = mgr.questionListenList(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);
            List<TbQuestionListening> list = lc.getList();

            request.setAttribute("dlbList",list);
            request.setAttribute("title",title);
            request.setAttribute("lc", lc);
            return mapping.findForward("dlbListenList");
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
                queryConds.add(new QueryCond("p_id","String","in","010201,010202,010203,020201,020202,020203"));
                request.setAttribute("flag","0");
            }else if("".equals(id) && !"".equals(parentid)){
                queryConds.add(new QueryCond("p_id", "String", "=", parentid));
                request.setAttribute("flag","1");
            }else if ("".equals(parentid) && !"".equals(id)){
                queryConds.add(new QueryCond("id", "String", "=", id));
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
            String PId = "";
            TbSpecialCatalogActionForm catalogActionForm = tbSpecialCatatlogMgr.view(ID);
            TbQuestionListeningActionForm listeningActionForm = null;
            if (catalogActionForm != null){
                PId = catalogActionForm.getPId();
                listeningActionForm = mgr.view(ID);
                String p_id = "";
                if (ID != null && !"".equals(ID)){
                    p_id = ID.substring(0,4);
                }
                String nameStatus = QuestionGetSelelct.getList("select id,name from tb_special_catalog where p_id ='"+p_id+"' order by create_time","name","id");
                if(!"".equals(PId)&&null!=PId){
                    nameStatus=QuestionGetSelelct.getList("select id,name from tb_special_catalog where p_id ='"+p_id+"' order by create_time","name","id",PId);
                }
                request.setAttribute("nameStatus",nameStatus);
            }
            request.setAttribute("TbSpecialCatalogActionForm",catalogActionForm);
            request.setAttribute("TbQuestionListeningActionForm",listeningActionForm);
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
            String audioUrl = ParamUtils.getParameter(request,"audioUrl");
            String sortOrder = ParamUtils.getParameter(request,"sortOrder");
            String PId = ParamUtils.getParameter(request,"PId");
            String tapescripts = ParamUtils.getParameter(request,"tapescripts");
            String translate = ParamUtils.getParameter(request,"translate");
            String id = ParamUtils.getParameter(request,"id");
            String target = ParamUtils.getParameter(request,"target");

            TbSpecialCatalogActionForm catalogActionForm = tbSpecialCatatlogMgr.view(id);
            catalogActionForm.setName(name);
            catalogActionForm.setSortOrder(Integer.valueOf(sortOrder));
            catalogActionForm.setPId(PId);

            TbQuestionListeningActionForm listeningActionForm = mgr.view(id);
            listeningActionForm.setAudioUrl(audioUrl);
            listeningActionForm.setTitle(title);
            listeningActionForm.setTapescripts(tapescripts);
            listeningActionForm.setTranslation(translate);
            listeningActionForm.setTarget(target);

//            tbSpecialCatatlogMgr.update(catalogActionForm);
            mgr.updateCatalogAndListen(listeningActionForm,catalogActionForm);

            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }
            String ipaddress = IpAddressUtil.getIpAddr(request);
            String username = "admin";
            Tool.AddLog(sessionContainer.getUserId(), username,
                    "修改听力:"+id, "1", ipaddress);


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
            String listenId = "";
            String questionId = "";
            String optionId = "";
            String subtitlesId = "";

            List trainList = Tool.findListByHql("select id from TbSpecialTraining where catalogId = " + catalogId);
            if (null != trainList && trainList.size()>0){
                trainId = (String) trainList.get(0);
                if (null != trainId && !"".equals(trainId)){
                    TbSpecialTrainingActionForm trainingActionForm = tbSpecialTrainingMgr.view(trainId);
                    listenId = trainingActionForm.getTrainingId();
                    if(null != listenId && !listenId.equals("")){
                        List questionList = Tool.findListByHql("select id from TbQuestionListeningQuestion where listeningId = '" + listenId + "'");
                        if (null != questionList && questionList.size()>0){
                            questionId = (String) questionList.get(0);
                            if (null != questionId && !"".equals(questionId)){
                                List optionList = Tool.findListByHql("select id from TbQuestionListeningQuestionOption where questionId = '" + questionId + "'");
                                if(null != optionList && optionList.size() > 0){
                                    optionId = (String) optionList.get(0);
                                }
                            }
                        }
                        List subtitlesList = Tool.findListByHql("select id from TbQuestionListeningSubtitles where listeningId = '" + listenId + "'");
                        if (null!= subtitlesList && subtitlesList.size() > 0){
                            if (null != subtitlesList.get(0)){
                                subtitlesId = (String) subtitlesList.get(0);
                            }
                        }
                    }
                }
            }

            mgr.deleteAll(catalogId,trainId,listenId,questionId,optionId,subtitlesId);
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
            String id = ParamUtils.getParameter(request,"id");
            TbSpecialCatalogActionForm catalogActionForm = tbSpecialCatatlogMgr.view(id);
            String PId = "";
            if (catalogActionForm != null){
                PId = catalogActionForm.getPId();
            }
            request.setAttribute("parentid",catalogActionForm.getPId());
            request.setAttribute("id",id);

            //获取订单列表
            String nameStatus = QuestionGetSelelct.getList("select id,name from tb_special_catalog where p_id IN ('0102','0202') order by create_time","name","id");
            if(!"".equals(PId)&&null!=PId){
                nameStatus=QuestionGetSelelct.getList("select id,name from tb_special_catalog where p_id IN ('0102','0202') order by create_time","name","id",PId);
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
            tbSpecialTraining.setTrainingType("1");
            tbSpecialTraining.setTrainingId(trianingId);
            tbSpecialTraining.setSortOrder(1);


            //听力片段
            String title = ParamUtils.getParameter(request,"title");
            String audioUrl = ParamUtils.getParameter(request,"audioUrl");
            String questionQuantity = ParamUtils.getParameter(request,"questionQuantity");
            String tapescripts = ParamUtils.getParameter(request,"tapescripts");
            String translation = ParamUtils.getParameter(request,"translation");

            TbQuestionListening tbQuestionListening = new TbQuestionListening();

            tbQuestionListening.setId(trianingId);
            tbQuestionListening.setTitle(title);
            tbQuestionListening.setAudioUrl(audioUrl);
            tbQuestionListening.setTapescripts(tapescripts);
            tbQuestionListening.setTranslation(translation);
            tbQuestionListening.setIsDel("0");
            tbQuestionListening.setCreateTime(new Date());
            tbQuestionListening.setTarget(target);

            mgr.addAll(tbSpecialCatalog,tbSpecialTraining,tbQuestionListening);

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
            TbQuestionListeningActionForm listenForm = mgr.view(catalogId);
            String listenId = "";
            if (listenForm == null){
                listenId = catalogId;
            }else{
                listenId = listenForm.getId();
            }
            //String loginName = ParamUtils.getParameter(request, "loginName", false);
            // 设置查询条件
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("listeningId", "String", "=", listenId));
            request.setAttribute("flag","1");
            ListContainer lc = mgr.matchList(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);
            request.setAttribute("lc", lc);

            request.setAttribute("parentid", listenId);
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
            TbQuestionListeningQuestionActionForm questionForm = tbQuestionListeningQuestionMgr.view(questionId);
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
            TbQuestionListeningQuestionActionForm questionForm = tbQuestionListeningQuestionMgr.view(questionId);
            questionForm.setTitle(ParamUtils.getParameter(request,"title"));
            questionForm.setSortOrder(Integer.valueOf(ParamUtils.getParameter(request,"sortOrder")));
            questionForm.setAnalysis(ParamUtils.getParameter(request,"analysis"));
            tbQuestionListeningQuestionMgr.update(questionForm);
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

            tbQuestionListeningQuestionMgr.deleteAll(id);
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
            String listeningId = ParamUtils.getParameter(request,"parentid");
            request.setAttribute("listeningId",listeningId);

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
            String listeningId = ParamUtils.getParameter(request,"listeningId");
            String title = ParamUtils.getParameter(request,"title");
            String analysis = ParamUtils.getParameter(request,"analysis");
            Integer sortOrder = Integer.valueOf(ParamUtils.getParameter(request,"sortOrder"));

            TbQuestionListeningQuestion question = new TbQuestionListeningQuestion();

            question.setId(questionId);
            question.setListeningId(listeningId);
            question.setTitle(title);
            question.setAnalysis(analysis);
            question.setSortOrder(sortOrder);

            tbQuestionListeningQuestionMgr.add(question);

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
            TbQuestionListeningQuestionOption option = new TbQuestionListeningQuestionOption();
            option.setId(optionId);
            option.setQuestionId(questionId);
            option.setPrefix(prefix);
            option.setContent(content);
            option.setIsAnswer(isAnswer);
            tbQuestionListeningQuestionMgr.add(option);
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
            TbQuestionListeningQuestionOptionActionForm optionForm = tbQuestionListeningQuestionOptionMgr.view(optionId);
            TbQuestionListeningQuestionOption option = new TbQuestionListeningQuestionOption();
            BeanUtils.copyProperties(option,optionForm);
            tbQuestionListeningQuestionOptionMgr.delete(option);
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
//        Map m = new HashMap();
        //获取excle文件
        TbQuestionListeningActionForm listeningActionForm = (TbQuestionListeningActionForm) form;
        FormFile file = listeningActionForm.getFile();
        InputStream inputStream = file.getInputStream();
//        ArrayList list = new ArrayList();
//        int input = 0;// 导入记数

        try{
            Listening listening = new Listening();
            Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>>> listMap = listening.readFile(inputStream);
            List<BaseModel> list1 = listening.parseData(listMap);
            TT.save(list1);
            return list(mapping,form, request,response);
            /*// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);//第一个工作表
            XSSFRow xssfRow = xssfSheet.getRow(0);//第一行
            String section = "";
            String type = "";
            String name = "";
            String title = "";
            String audioUrl = "";
            String tapescripts ="";
            String translation = "";
            String sortOrder = "";
            String questionQantity = "";
            String sort = "";
            String questionTitle = "";
            String analysis = "";
            String prefix = "";
            String content = "";
            String isAnswer = "";
            String parentid = "";
            String newId = "";
            String trianingId = "";
            String questionId = "";
            // 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
                xssfSheet = xssfWorkbook.getSheetAt(i);
                // 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for (int j = 2; j < xssfSheet.getPhysicalNumberOfRows(); j++){
                    xssfRow = xssfSheet.getRow(j);
                    // 判断是否还存在需要导入的数据
                    if (xssfRow == null){
                        System.out.println("这里已没有数据，在第" + i + "列,第" + j + "行");
                        break;
                    }

                    *//** 将EXCEL中的第 j 行，第一列的值插入到实例中 *//*
                    if (null != xssfRow.getCell((short) 0)){
                        xssfRow.getCell((short) 0).setCellType(Cell.CELL_TYPE_STRING);
                        section = xssfRow.getCell((short) 0).getStringCellValue().trim();
                    }
                    *//** 将EXCEL中的第 j 行，第二列的值插入到实例中 *//*
                    if (null != xssfRow.getCell((short) 1)){
                        xssfRow.getCell((short) 1).setCellType(Cell.CELL_TYPE_STRING);
                        type = xssfRow.getCell((short) 1).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 2)){
                        xssfRow.getCell((short) 2).setCellType(Cell.CELL_TYPE_STRING);
                        name = xssfRow.getCell((short) 2).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 3)){
                        xssfRow.getCell((short) 3).setCellType(Cell.CELL_TYPE_STRING);
                        title = xssfRow.getCell((short) 3).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 4)){
                        xssfRow.getCell((short) 4).setCellType(Cell.CELL_TYPE_STRING);
                        audioUrl = xssfRow.getCell((short) 4).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 5)){
                        xssfRow.getCell((short) 5).setCellType(Cell.CELL_TYPE_STRING);
                        tapescripts = xssfRow.getCell((short) 5).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 6)){
                        xssfRow.getCell((short) 6).setCellType(Cell.CELL_TYPE_STRING);
                        translation = xssfRow.getCell((short) 6).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 7)){
                        xssfRow.getCell((short) 7).setCellType(Cell.CELL_TYPE_STRING);
                        sortOrder = xssfRow.getCell((short) 7).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 8)){
                        xssfRow.getCell((short) 8).setCellType(Cell.CELL_TYPE_STRING);
                        questionQantity = xssfRow.getCell((short) 8).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 9)){
                        xssfRow.getCell((short) 9).setCellType(Cell.CELL_TYPE_STRING);
                        sort = xssfRow.getCell((short) 9).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 10)){
                        xssfRow.getCell((short) 10).setCellType(Cell.CELL_TYPE_STRING);
                        questionTitle = xssfRow.getCell((short) 10).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 11)){
                        xssfRow.getCell((short) 11).setCellType(Cell.CELL_TYPE_STRING);
                        analysis = xssfRow.getCell((short) 11).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 12)){
                        xssfRow.getCell((short) 12).setCellType(Cell.CELL_TYPE_STRING);
                        prefix = xssfRow.getCell((short) 12).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 13)){
                        xssfRow.getCell((short) 13).setCellType(Cell.CELL_TYPE_STRING);
                        content = xssfRow.getCell((short) 13).getStringCellValue().trim();
                    }
                    if (null != xssfRow.getCell((short) 14)){
                        xssfRow.getCell((short) 14).setCellType(Cell.CELL_TYPE_STRING);
                        isAnswer = xssfRow.getCell((short) 14).getStringCellValue().trim();
                    }


                    //目录
                    TbSpecialCatalog tbSpecialCatalog = null;

                    if (null != name & !"".equals(name) & null != type & !"".equals(type) & null != sortOrder & !"".equals(sortOrder)){

                        tbSpecialCatalog = new TbSpecialCatalog();

                        parentid = "0102"+type;
                        tbSpecialCatalog.setCreateTime(new Date());
                        tbSpecialCatalog.setPId(parentid);
                        String lastId = tbSpecialCatatlogMgr.getLast("id", parentid);
                        String s1 = lastId.replaceFirst(parentid, "");
                        newId = parentid +new DecimalFormat("000000").format(Integer.valueOf(s1)+1);
                        tbSpecialCatalog.setId(newId);
                        tbSpecialCatalog.setName(name);
                        tbSpecialCatalog.setSortOrder(Integer.valueOf(sortOrder));
                        tbSpecialCatalog.setIsDel("0");
                    }
                    //题目
                    TbSpecialTraining tbSpecialTraining = null;

                    if (section != title & !"".equals(section)){

                        tbSpecialTraining = new TbSpecialTraining();

                        String trainingid = UUID.randomUUID().toString().replace("-", "");//题目Id
                        trianingId = UUID.randomUUID().toString().replace("-", "");//trianing_id
                        tbSpecialTraining.setId(trainingid);
                        tbSpecialTraining.setSection(parentid.substring(0,2));
                        tbSpecialTraining.setCatalogId(newId);
                        tbSpecialTraining.setTrainingType("1");
                        tbSpecialTraining.setSection(section);
                        tbSpecialTraining.setTrainingId(trianingId);
                        tbSpecialTraining.setSortOrder(1);
                    }
                    //听力片段
                    TbQuestionListening tbQuestionListening = null;

                    if (null != title & !"".equals(title) & null != audioUrl & !"".equals(audioUrl) & null != tapescripts
                            & !"".equals(tapescripts) & null !=questionQantity & !"".equals(questionQantity)){

                        tbQuestionListening = new TbQuestionListening();

                        tbQuestionListening.setId(trianingId);
                        tbQuestionListening.setTitle(title);
                        tbQuestionListening.setAudioUrl(audioUrl);
                        tbQuestionListening.setTapescripts(tapescripts);
                        tbQuestionListening.setTranslation(translation);
                        tbQuestionListening.setQuestionQuantity(Integer.valueOf(questionQantity));
                        tbQuestionListening.setIsDel("0");
                        tbQuestionListening.setCreateTime(new Date());
                    }

                    //听力题目
                    TbQuestionListeningQuestion question = null;

                    if (null != sort & !"".equals(sort) ){

                        question = new TbQuestionListeningQuestion();

                        questionId = UUID.randomUUID().toString().replace("-","");
                        question.setId(questionId);
                        question.setListeningId(trianingId);
                        question.setSortOrder(Integer.valueOf(sort));
                        question.setTitle(questionTitle);
                        question.setAnalysis(analysis);
                    }

                    //听力选项
                    TbQuestionListeningQuestionOption option = null;
                    if (null != prefix & !"".equals(prefix) & null != content & !"".equals(content) & null != isAnswer & !"".equals(isAnswer)){

                        option = new TbQuestionListeningQuestionOption();

                        option.setId(UUID.randomUUID().toString().replace("-",""));
                        option.setQuestionId(questionId);
                        option.setPrefix(prefix);
                        option.setContent(content);
                        option.setIsAnswer(isAnswer);
                    }

                    mgr.addAbove(tbSpecialCatalog,tbSpecialTraining,tbQuestionListening,question,option);

                    //导入成功+1
                    input++;
                }
            }
            m.put("result", true);
            JsonUtils.outputJsonResponse(response, m);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

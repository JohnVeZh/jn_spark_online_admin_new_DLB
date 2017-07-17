package com.business.coupon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.business.NetworkVideo.NetworkVideoMgr;
import com.business.Product.ProductMgr;
import com.business.coupon.dao.CouponCode;
import com.business.coupon.dao.CouponTemplate;
import com.business.coupon.dao.CouponTemplateRelation;
import com.business.coupon.vo.CouponCodeForm;
import com.business.coupon.vo.CouponTemplateForm;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.CommUtil;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.RandomCode;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.StringUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class CouponAction extends BaseAction {
	private CouponMgr mgr = new CouponMgr();
	
	public ActionForward templateList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer = (SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(sessionContainer == null) {
			sessionContainer=new SessionContainer();
		}
		
		try {
            int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
            String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
            int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
            if (strItemsInPage != null) {
                itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
            }
            String action = ParamUtils.getParameter(request, "pageAction", true);
            if ("".equals(action)) {
            	action = PageAction.FIRST.toString();
            }
            int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
            
            String title = ParamUtils.getParameter(request, "title", "");
            String templateType = ParamUtils.getParameter(request, "templateType", "");
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("title", "String", "like", title));
            if(!templateType.equals("") && !templateType.equals("all")){
                queryConds.add(new QueryCond("type", "String", "=", templateType));
            }
            ListContainer lc = mgr.templateList(queryConds, currentPageInt, itemsInPage, action, jumpPage);
            request.setAttribute("lc", lc);
            request.setAttribute("title", title);
            request.setAttribute("templateType", templateType);

            return mapping.findForward("templateList");
		} catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
		}
	}

    /**
     * 优惠码列表展示
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward codeList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        SessionContainer sessionContainer = (SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(sessionContainer == null) {
            sessionContainer=new SessionContainer();
        }
        try {
            int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
            String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
            int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
            if (strItemsInPage != null) {
                itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
            }
            String action = ParamUtils.getParameter(request, "pageAction", true);
            if ("".equals(action)) {
                action = PageAction.FIRST.toString();
            }
            int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);

            String code = ParamUtils.getParameter(request, "code", "");
            String status = ParamUtils.getParameter(request, "status", "");
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("code", "String", "like", code));
            queryConds.add(new QueryCond("status", "String", "=", status));

            List<Map<String,Object>> lm = new ArrayList<>();
            ListContainer lc = mgr.codeList(queryConds, currentPageInt, itemsInPage, action, jumpPage);
            request.setAttribute("lc", lc);
            List<CouponCode> couponCodeList = lc.getList();
            for (CouponCode couponCode : couponCodeList) {
                Map m = new HashMap();
                m.put("id", couponCode.getId());
                m.put("code", couponCode.getCode());
                m.put("status", couponCode.getStatus());
                m.put("effectTime", couponCode.getEffectTime());
                m.put("invalidTime", couponCode.getInvalidTime());
                m.put("createTime", couponCode.getCreateTime());
                lm.add(m);
            }
            request.setAttribute("lm", lm);
            return mapping.findForward("list");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 优惠码添加页面跳转方法
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward preCodeAdd(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) {
        try {
            CouponCodeForm vo = new CouponCodeForm();
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            request.setAttribute("CouponCodeForm", vo);

            return mapping.findForward("add");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 优惠码的添加
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward codeAdd(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //FIXME 后期添加事务以及生成提示
            String template = ParamUtils.getParameter(request, "template", true);
            String codeLength = ParamUtils.getParameter(request, "codeLength", true);
            String codeCount = ParamUtils.getParameter(request, "codeCount", true);
            String effectTime = ParamUtils.getParameter(request, "effect_time", true);
            String invalidTime = ParamUtils.getParameter(request, "invalid_time", true);
            Set<String> st = new HashSet<>();
            String codeStr = "";
            String sql = "";
            String templateRelationSql = "";
            List ls =null;
            int error=0;
            int success = 0;
            if(StringUtils.isNumber(codeCount) && StringUtils.isNumber(codeLength)){
                for(int i= 0;i<i+1;i++){
                    String code = RandomCode.getRandomChar(Integer.parseInt(codeLength));
                    st.add(code);
                    if(st.size() == Integer.parseInt(codeCount)){
                        break;
                    }
                }
                for(String s : st){
                    codeStr +="'"+s+"',";
                }
                codeStr = codeStr.substring(0,codeStr.length()-1);
                ls = mgr.SQLQuery("SELECT id FROM coupon_code where code in ("+codeStr+")");
                for(int i = 0;i<ls.size();i++){
                    Map mp = (Map) ls.get(i);
                    st.remove(mp.get("id").toString());
                    String code = "";
                    while (true){
                        code =  RandomCode.getRandomChar(Integer.parseInt(codeLength));
                        List couponCodeList = mgr.SQLQuery("SELECT code FROM coupon_code where code= '"+code+"'");
                        if(couponCodeList.size()==0) break;
                    }
                    st.add(code);
                }
                sql = " insert into coupon_code(id,code,effect_time,invalid_time,create_time,status) values ";
                templateRelationSql = "insert into coupon_template_relation(id,type,template_id,content_id,create_time) values ";
                String[] templateList=template.split(",");
                for(String s : st){
                    String id = UUID.randomUUID().toString().replaceAll("-","");
                    sql +="('"+id+"','"+s+"','"+effectTime+"','"+invalidTime+"','"+DateUtils.getCurrDateTimeStr()+"',1),";
                    for (String templ : templateList) {
                        String relateId = UUID.randomUUID().toString().replaceAll("-","");
                        templateRelationSql +="('"+relateId+"','4','"+templ+"','"+id+"','"+DateUtils.getCurrDateTimeStr()+"'),";
                    }
                }
                sql = sql.substring(0,sql.length()-1);
                templateRelationSql = templateRelationSql.substring(0,templateRelationSql.length()-1);
                success = mgr.SQLExecute(sql);
                success = mgr.SQLExecute(templateRelationSql);
            }
            map.put("suc",success );
            map.put("res", true);
        } catch (Exception ex) {
            map.put("res",false);
        }
        JsonUtils.outputJsonResponse(response, map);
        return null;
    }

    /**
     * 真实删除优惠码操作
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward realdeleteCode(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response) {

        try {
            String id = ParamUtils.getParameter(request, "id", true);
            mgr.delete(id);
            Tool.execute("delete from coupon_template_relation where content_id='"+id+"' and type =4");
            return codeList(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 优惠码编辑页面跳转
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward preCodeEdit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) {
        try {
            String ID = ParamUtils.getParameter(request, "id", true);

            CouponCodeForm couponCodeForm= mgr.view(ID);
            List<CouponTemplateRelation> templateList = mgr.viewTemplateByContentId(ID);
            String couponTemplateStr = "";
            if(templateList!=null && templateList.size()>0){
                for (CouponTemplateRelation  ouponTemplateRelation: templateList) {
                    couponTemplateStr+=ouponTemplateRelation.getTemplateId()+",";
                }
                couponTemplateStr=couponTemplateStr.substring(0,couponTemplateStr.length()-1);
            }

            request.setAttribute("templateList", couponTemplateStr);
            request.setAttribute("CouponCodeForm", couponCodeForm);

            return mapping.findForward("update");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 优惠码修改
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward codeUpdate(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CouponCodeForm couponCodeForm = (CouponCodeForm) form;
            String effectTime = ParamUtils.getParameter(request, "effect_time", true);
            String invalidTime = ParamUtils.getParameter(request, "invalid_time", true);
            String createTime = ParamUtils.getParameter(request, "create_time", true);
            String template = ParamUtils.getParameter(request,"template",true);
            couponCodeForm.setEffectTime(DateUtils.format(effectTime,"yyyy-MM-dd HH:mm:ss"));
            couponCodeForm.setInvalidTime(DateUtils.format(invalidTime,"yyyy-MM-dd HH:mm:ss"));
            couponCodeForm.setCreateTime(DateUtils.format(createTime,"yyyy-MM-dd HH:mm:ss"));
            //删除relatetion中存留的优惠码信息 再添加
            Tool.execute("delete from coupon_template_relation where content_id='"+couponCodeForm.getId()+"' and type =4");
            String[] templateList = template.split(",");
            String  templateRelationSql = "insert into coupon_template_relation(id,type,template_id,content_id,create_time) values ";
            for (String templateStr : templateList) {
                String relateId = UUID.randomUUID().toString().replaceAll("-","");
                templateRelationSql +="('"+relateId+"','4','"+templateStr+"','"+couponCodeForm.getId()+"','"+DateUtils.getCurrDateTimeStr()+"'),";
            }
            templateRelationSql = templateRelationSql.substring(0,templateRelationSql.length()-1);
            mgr.codeUpdate(couponCodeForm);
            mgr.SQLExecute(templateRelationSql);
            map.put("result", true);
        } catch (Exception ex) {
            map.put("result",false);
        }
        JsonUtils.outputJsonResponse(response, map);
        return null;
    }

    /**
     * 优惠码详情页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward preCodeView(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) {
        try {
            String ID = ParamUtils.getParameter(request, "id", true);
            CouponCodeForm vo= mgr.view(ID);
            List<CouponTemplateRelation> templateList = mgr.viewTemplateByContentId(ID);
            String couponTemplateStr = "";
            if(templateList!=null && templateList.size()>0){
                for (CouponTemplateRelation  ouponTemplateRelation: templateList) {
                    couponTemplateStr+=ouponTemplateRelation.getTemplateId()+",";
                }
                couponTemplateStr=couponTemplateStr.substring(0,couponTemplateStr.length()-1);
            }
            request.setAttribute("templateList", couponTemplateStr);
            request.setAttribute("CouponCodeForm", vo);

            return mapping.findForward("view");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取view面出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 双向选择器模版信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getTemplate4DoubleSelect(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List list = new ArrayList();
        try {
            String title = ParamUtils.getParameter(request, "title", "");
            Collection queryConds = new ArrayList();
            if(CommUtil.isString(title)!=null){
                queryConds.add(new QueryCond("title", "String", "like", title));
            }
            queryConds.add(new QueryCond("type", "String", "=", "2"));
            List<CouponTemplate> lc = mgr.getCouponTemplateNoPage(title, queryConds);
            List formArray = new ArrayList();
            for (CouponTemplate couponTemplate : lc) {
                Map<String,String> stringMap = new HashedMap();
                stringMap.put("key",couponTemplate.getTitle());
                stringMap.put("value",couponTemplate.getId());
                formArray.add(stringMap);
            }
            map.put("fromList",formArray);
            map.put("toList", list);
        } catch (Exception ex) {
            map.put("result",false);
        }
        JsonUtils.outputJsonResponse(response, map);
        return null;
    }
	
    public ActionForward templateView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = ParamUtils.getParameter(request, "id", true);
			request.setAttribute("item", mgr.templateView(id));
			return mapping.findForward("templateView");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取数据时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	    
	public ActionForward templateDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = "admin";
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			
			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				String name = Tool.getValue("select title from coupon_template where id='"+ids[i]+"'");
				Tool.AddLog(sessionContainer.getUserId(), username, "删除优惠券模板,名称:"+name, "1", ipaddress);
				Tool.execute("delete from coupon_template where id = '"+ids[i]+"'");
				Tool.execute("delete from coupon_template_relation where template_id = '"+ids[i]+"'");
			}
			return templateList(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
			"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
    public ActionForward templatePreAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			return mapping.findForward("templateAdd");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
    
    public ActionForward templateAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CouponTemplateForm tb = (CouponTemplateForm) form;
            tb.setCreateTime(new Date());
            tb.setId(UUID.randomUUID().toString().replace("-",""));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tb.setEffectTime(sdf.parse(tb.getEffectTimeStr()));
            tb.setInvalidTime(sdf.parse(tb.getInvalidTimeStr()));
            
            String ipaddress = IpAddressUtil.getIpAddr(request);
            SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            String username = "admin";
            Tool.AddLog(sessionContainer.getUserId(), username, "添加优惠模板,名称:"+tb.getTitle(), "1", ipaddress);
            Tool.testReflect_admin(tb);
            mgr.templateAdd(tb);
            
            map.put("result", true);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            map.put("result",false);
        }

        JsonUtils.outputJsonResponse(response, map);
        return null;
    }
    
    public ActionForward templatePreUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = ParamUtils.getParameter(request, "id", true);
			request.setAttribute("item", mgr.templateView(id));
			return mapping.findForward("templateUpdate");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取数据时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
    
    public ActionForward templateUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CouponTemplateForm tb = (CouponTemplateForm) form;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tb.setEffectTime(sdf.parse(tb.getEffectTimeStr()));
            tb.setInvalidTime(sdf.parse(tb.getInvalidTimeStr()));
            
            String ipaddress = IpAddressUtil.getIpAddr(request);
            SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            String username = "admin";
            Tool.AddLog(sessionContainer.getUserId(), username, "修改优惠模板,名称:"+tb.getTitle(), "1", ipaddress);
            mgr.templateUpdate(tb);
            
            map.put("result", true);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            map.put("result",false);
        }

        JsonUtils.outputJsonResponse(response, map);
        return null;
    }
    
    public ActionForward productBookSelector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		
		ProductMgr pmgr = new ProductMgr();
		try {
			int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
			}
			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			
			// 接收传值
			String nameStr = ParamUtils.getParameter(request, "nameStr", false);
			
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("Product.pName", "String", "like", nameStr));
			queryConds.add(new QueryCond("Product.pIsDel", "number", "=", "0"));
			ListContainer lc = pmgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);
			
			request.setAttribute("lc", lc);
			request.setAttribute("nameStr", nameStr);
			request.setAttribute("srcpage", request.getParameter("srcpage"));
			
			return mapping.findForward("productBookSelector");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
    
    public ActionForward productNetworkSelector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
		sessionContainer=new SessionContainer();
		NetworkVideoMgr nmgr = new NetworkVideoMgr();
		try {
			int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
			}
			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			
			// 接收传值
			String nvNamelike = ParamUtils.getParameter(request, "nvNamelike", false);
			String stateStr = ParamUtils.getParameter(request, "stateStr", false);
			
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("NetworkVideo.networkName", "String", "like", nvNamelike));
			queryConds.add(new QueryCond("NetworkVideo.isFree", "number", "=", "0"));
			queryConds.add(new QueryCond("NetworkVideo.isTeacher", "number", "=", "0"));
			queryConds.add(new QueryCond("NetworkVideo.levelType", "String", "in", "'01', '02', '03'"));
			queryConds.add(new QueryCond("NetworkVideo.networkType", "String", "=", stateStr));
			queryConds.add(new QueryCond("NetworkVideo.isDel", "number", "=", "0"));
			ListContainer lc = nmgr.list(queryConds, currentPageInt,
			itemsInPage, action, jumpPage);
			
			request.setAttribute("lc", lc);
			request.setAttribute("nvNamelike", nvNamelike);
			request.setAttribute("stateStr", stateStr);
			request.setAttribute("srcpage", request.getParameter("srcpage"));
			
			return mapping.findForward("productNetworkSelector");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
    
    public ActionForward templateRelationList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer = (SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(sessionContainer == null) {
			sessionContainer=new SessionContainer();
		}
		
		try {
            int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
            String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
            int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
            if (strItemsInPage != null) {
                itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
            }
            String action = ParamUtils.getParameter(request, "pageAction", true);
            if ("".equals(action)) {
            	action = PageAction.FIRST.toString();
            }
            int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
            
            String templateId = ParamUtils.getParameter(request, "templateId", "");
            String relationType = ParamUtils.getParameter(request, "relationType", "");
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("templateId", "String", "=", templateId));
            if(!relationType.equals("") && !relationType.equals("all")){
                queryConds.add(new QueryCond("type", "String", "=", relationType));
            } else {
                queryConds.add(new QueryCond("type", "String", "!=", "4"));
            }
            ListContainer lc = mgr.templateRelationList(queryConds, currentPageInt, itemsInPage, action, jumpPage);

            List<CouponTemplateRelation> trList = lc.getList();
            List<Map<String, String>> relList = new ArrayList<Map<String, String>>();
            for(CouponTemplateRelation r : trList) {
            	Map<String, String> rel = new HashMap<String, String>();
            	rel.put("id", r.getId());
            	rel.put("type", r.getType());
            	rel.put("contentId", r.getContentId());
            	String contentName = "";
            	if("1".equals(r.getType())) {
            		contentName = Tool.getValue("select t.title from community_post t where t.id='" + r.getContentId() + "'");
            	} else if("2".equals(r.getType())){
                	contentName = Tool.getValue("select p_name from product where id='" + r.getContentId() + "'");
                } else if("3".equals(r.getType())){
                	contentName = Tool.getValue("select network_name from Network_video where id='" + r.getContentId() + "'");
                } else if("5".equals(r.getType()) || "6".equals(r.getType()) || "7".equals(r.getType())){
                	contentName = Tool.getValue("select name from sys_config where id='" + r.getContentId() + "'");
                }
                rel.put("contentName", contentName);
            	relList.add(rel);
            }
            request.setAttribute("lc", lc);
            request.setAttribute("relList", relList);
            request.setAttribute("templateId", templateId);
            request.setAttribute("relationType", relationType);
            request.setAttribute("templateType", Tool.getValue("select type from coupon_template where id='" + templateId + "'"));

            return mapping.findForward("templateRelationList");
		} catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
		}
	}
    
	public ActionForward templateRelationDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				Tool.execute("delete from coupon_template_relation where id = '" +ids[i]+"'");
			}
			return templateRelationList(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
    
    public ActionForward preBindResourceList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer = (SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(sessionContainer == null) {
			sessionContainer=new SessionContainer();
		}
		
		try {
            int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
            String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
            int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
            if (strItemsInPage != null) {
                itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
            }
            String action = ParamUtils.getParameter(request, "pageAction", true);
            if ("".equals(action)) {
            	action = PageAction.FIRST.toString();
            }
            int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
            
            String templateId = ParamUtils.getParameter(request, "templateId", "");
            String relationType = ParamUtils.getParameter(request, "relationType", "");
            String name = ParamUtils.getParameter(request, "name", "");
            
            if(templateId!=null && !"".equals(templateId) && ("1".equals(relationType) || "2".equals(relationType) || "3".equals(relationType) || "5".equals(relationType) || "6".equals(relationType) || "7".equals(relationType))) {
                List<CouponTemplateRelation> relationList = Tool.findListByHql("from CouponTemplateRelation where templateId='"+templateId+"' and type='" + relationType + "'");
                StringBuffer sb = new StringBuffer("''");
                for(CouponTemplateRelation r : relationList) {
                	sb.append(",'").append(r.getContentId()).append("'");
                }
                Collection queryConds = new ArrayList();
                if("1".equals(relationType)) {
                	if(StringUtils.isNotBlank(name)) {
                		queryConds.add(new QueryCond("title", "String", "like", name));
                	}
                    queryConds.add(new QueryCond("isDel", "number", "=", "0"));
                } else if("2".equals(relationType)) {
                	if(StringUtils.isNotBlank(name)) {
                		queryConds.add(new QueryCond("pName", "String", "like", name));
                	}
                    queryConds.add(new QueryCond("pIsDel", "number", "=", "0"));
                } else if("3".equals(relationType)) {
                    if(name!=""&& null!=name){
                        queryConds.add(new QueryCond("networkName", "String", "like", name));
                    }
                } else if("5".equals(relationType) || "6".equals(relationType) || "7".equals(relationType)) {
                    if(name!=""&& null!=name){
                        queryConds.add(new QueryCond("name", "String", "like", name));
                    }
                }
                if(!"''".equals(sb.toString())) {
                	queryConds.add(new QueryCond("id", "String", "not in", sb.toString()));
                }
            	
            	ListContainer lc = mgr.bindResourceList(relationType, queryConds, currentPageInt, itemsInPage, action, jumpPage);
            	request.setAttribute("lc", lc);
            }
            request.setAttribute("name", name);
            request.setAttribute("templateId", templateId);
            request.setAttribute("relationType", relationType);
            
            if("1".equals(relationType)) {
                return mapping.findForward("templateCommunityBinder");
            } else if("2".equals(relationType)) {
                return mapping.findForward("templateBookBinder");
            } else if("3".equals(relationType)) {
                return mapping.findForward("templateNetworkBinder");
            } else if("5".equals(relationType) || "6".equals(relationType) || "7".equals(relationType)) {
            	return mapping.findForward("templateCarouselBinder");
            } else {
            	return null;
            }
		} catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
		}
	}
    
    public ActionForward bindResource(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String templateId = ParamUtils.getParameter(request, "templateId", true);
			String relationType = ParamUtils.getParameter(request, "relationType", true);
			String contentId = ParamUtils.getParameter(request, "contentId", true);
			
			mgr.bindResource(templateId, relationType, Arrays.asList(contentId.split(",")));
			
			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
}

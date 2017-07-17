package com.business.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DictionaryUtils.QiNiu;
import com.easecom.common.util.JsonUtils;
import com.qiniu.util.Auth;

public class ObjectStorageAction extends BaseAction {
    public ActionForward getQiniuToken(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        Auth auth = Auth.create(QiNiu.ACCESS_KEY, QiNiu.SECRET_KEY);
        String upToken = auth.uploadToken(QiNiu.BUCKET, null, QiNiu.EXPIRES, null);
        map.put("uptoken", upToken);

//		response.setHeader("Access-Control-Allow-Origin", "*");
        JsonUtils.outputJsonResponse(response, map);

        return null;
    }
}

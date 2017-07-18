/**
 * 对通过AJAX请求返回的JSON字符串进行解析
 */
AjaxResponseUtils = Class.create();

/**
 * 与服务器端对应的JSON节点的名称常量
 */
AjaxResponseUtils.JSON_ROOT_NODE = "gpms-response";
AjaxResponseUtils.JSON_RESULT_NODE = "result";
AjaxResponseUtils.JSON_REASON_NODE = "reason";
AjaxResponseUtils.JSON_REASON_LOGON_TIMEOUT = "unlogon";
AjaxResponseUtils.JSON_REASON_ACCESS_DENY = "access-deny";
AjaxResponseUtils.JSON_REASON_UNIQUE_CONTRAINT = "unique-contraint";
AjaxResponseUtils.JSON_REASON_BUSINESS_CONTRAINT = "business-contraint";
AjaxResponseUtils.JSON_REASON_SYSTEM_EXCEPTION = "system-exception";
AjaxResponseUtils.JSON_MSG_NODE = "msg";
AjaxResponseUtils.JSON_CUSTOM_CONTENT_NODE = "custom-content";

AjaxResponseUtils.prototype = {

	/*------------------ initializer --------------*/
	
	initialize : function(jsonDoc){
		this._root = null;
		
		if (jsonDoc){
			var jsonObj = jsonDoc.evalJSON();			
			this._root = jsonObj[AjaxResponseUtils.JSON_ROOT_NODE];
			
			if (this._root == null)
				alert("服务器返回了非法的JSON字符串文档");
		}
	},
	
	/*---------------- public methods -------------*/
	_getReason : function(){
		return this._root[AjaxResponseUtils.JSON_REASON_NODE];
	},
	
	_validateReason : function(type){
		
		var reason = this._getReason();
		if(reason != null && reason == type)
			return true;
		
		return false;
	},
	
	/*
	 * 判断是否操作成功
	 */
	isSuccessfully : function(){
		return this._root[AjaxResponseUtils.JSON_RESULT_NODE];
	},
	
	/*
	 * 判断是否未登录
	 */
	isUnLogon : function(){
		return this._validateReason(AjaxResponseUtils.JSON_REASON_LOGON_TIMEOUT);
	},
	
	/*
	 * 判断是否系统异常
	 */
	isSystemException : function(){
		return this._validateReason(AjaxResponseUtils.JSON_REASON_SYSTEM_EXCEPTION);
	},
	
	/*
	 * 判断是否拒绝访问
	 */
	isAccessDeniedException : function() {		
		return this._validateReason(AjaxResponseUtils.JSON_REASON_ACCESS_DENY);
	},
	
	/*
	 * 判断是否违反了业务逻辑约束
	 */
	isBusinessContraintException : function(){
		return this._validateReason(AjaxResponseUtils.JSON_REASON_BUSINESS_CONTRAINT);
	},
	
	/*
	 * 判断是否违反了唯一约束
	 */
	isUniqueContraintException : function(){
		return this._validateReason(AjaxResponseUtils.JSON_REASON_UNIQUE_CONTRAINT);
	},
	
	/*
	 * 取得异常的提示信息
	 */
	getMsg : function(){
		return this._root[AjaxResponseUtils.JSON_MSG_NODE];
	},
	
	/*
	 * 取得自定义对象
	 */
	getCustomContent : function(){
		return this._root[AjaxResponseUtils.JSON_CUSTOM_CONTENT_NODE];
	}
}
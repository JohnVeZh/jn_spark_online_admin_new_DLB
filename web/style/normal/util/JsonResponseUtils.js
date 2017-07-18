JsonResponseUtils = Class.create();

JsonResponseUtils.JSON_ROOT_NODE = "json-response";
JsonResponseUtils.JSON_RESULT_NODE = "result";
JsonResponseUtils.JSON_MSG_NODE = "msg";
JsonResponseUtils.JSON_CUSTOM_CONTENT_NODE = "custom-content";

JsonResponseUtils.prototype = {

	/*------------------ initializer --------------*/
	initialize : function(jsonDoc){
		this._root = null;
		if (jsonDoc){			
			var jsonObj = jsonDoc.evalJSON();			
			this._root = jsonObj[JsonResponseUtils.JSON_ROOT_NODE];
			if (this._root == null){
				alert("invalid jSON data");
			}
		}
	},
	/*---------------- public methods -------------*/
	isSuccessful : function(){
		return this._root[JsonResponseUtils.JSON_RESULT_NODE];
	},
	
	getMsg : function(){
		return this._root[JsonResponseUtils.JSON_MSG_NODE];
	},
	
	getCustomContent : function(){
		return this._root[JsonResponseUtils.JSON_CUSTOM_CONTENT_NODE];
	}
}
var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="detail"]', {
		id:'body',
		resizeType : 1,
		themeType : 'simple',
		allowPreviewEmoticons : false,
		allowFileManager : true, 
		width : "100%",
		height : "80px", 
		newlineTag:"br",
		imageUploadJson : '${pageContext.request.contextPath}/editor/jsp/upload_json.jsp',  
		fileManagerJson : '${pageContext.request.contextPath}/editor/jsp/file_manager_json.jsp',					
		items : [
			  'cut', 'copy', 'paste', '|' ,'image','link', 'unlink'
		]
		});
		editor.sync();
});
	

var editor;
KindEditor.ready(function(K) {
		editor = K.create('textarea[name="detail"]', {
		resizeType : 1,
		themeType : 'simple',
		allowPreviewEmoticons : false,
		allowFileManager : true, 
		width : "100%",
		height : "200px", 
		newlineTag:"br",
		imageUploadJson : '${pageContext.request.contextPath}/editor/jsp/upload_json.jsp',  
		fileManagerJson : '${pageContext.request.contextPath}/editor/jsp/file_manager_json.jsp',					
		items : [
			'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
			'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
			'anchor', 'link', 'unlink', '|', 'about'
		]
	});
	editor.sync();
});

var editor1;
KindEditor.ready(function(K) {
		editor1 = K.create('textarea[name="content"]', {
		resizeType : 1,
		themeType : 'simple',
		allowPreviewEmoticons : false,
		allowFileManager : true, 
		width : "100%",
		height : "200px", 
		newlineTag:"br",
		imageUploadJson : '${pageContext.request.contextPath}/editor/jsp/upload_json.jsp',  
		fileManagerJson : '${pageContext.request.contextPath}/editor/jsp/file_manager_json.jsp',					
		items : [
			'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
			'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
			'anchor', 'link', 'unlink', '|', 'about'
		]
	});
	editor1.sync();
});

	

/**
 * 需引入以下js文件：
 * <script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
 * <script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/plupload.full.min.js"></script>
 * <script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/i18n/zh_CN.js"></script>
 * <script type="text/javascript" src="<%=path%>/deboeditor/dist/js/qiniu.js"></script>
 * <script type="text/javascript" src="<%=path%>/js/common/jquery.qiniu.js"></script>
 * 
 * 参数配置如下：
 * config.containerId
 * config.dragId
 * config.backInputId	回填表单ID，必选
 * config.backShowId
 */
var path = "";
$.fn.extend({
	upload : function(config) {
		if(typeof config === "string") {
			var backInputId = config;
			config = {};
			config.backInputId = backInputId;
		}
		
		var buttonId = $(this).attr("id");
		if(!buttonId) {
			buttonId = "_qn_up_id_" + Math.random();
			$(this).attr("id", buttonId);
		}
		var maxFileSize = "100mb";
		if(config.maxFileSize){
            maxFileSize = config.maxFileSize;
		}

		
		Qiniu.uploader({
			runtimes: 'html5,flash,html4',
			browse_button: buttonId,
			uptoken_url: path + '/business/common/storage.do?act=getQiniuToken',
			domain: 'http://oanafcpi7.bkt.clouddn.com/',
			container: config.containerId,
			max_file_size: maxFileSize,
			flash_swf_url: '../../deboeditor/dist/js/plupload/Moxie.swf',
			filters: {
				mime_types: [
					{title: "图片文件", extensions: "jpg,gif,png,bmp"}
				]
			},
            unique_names: true, //生成唯一文件名
            max_retries: 0,
			dragdrop: true,
			drop_element: config.dragId,
			chunk_size: '4mb',
			auto_start: true,
			init: {
				'FilesAdded': function (up, files) {
					plupload.each(files, function (file) {
						
					});
				},
				'BeforeUpload': function (up, file) {
					
				},
				'UploadProgress': function (up, file) {
					
				},
				'FileUploaded': function (up, file, info) {
					var domain = up.getOption('domain');
					var res = $.parseJSON(info);
					var sourceLink = domain + res.key;
					$("#" + config.backInputId).val(sourceLink);
					$("#" + config.backShowId).html("<img src='"+sourceLink+"' style='width: 80px;'/>");
				},
				'Error': function (up, err, errTip) {
					
				},
				'UploadComplete': function () {
					
				}

			}
		});
	}
});
ProcessingHint = {};
ProcessingHint._loadingMaskId = "__widget_processing_loading_mask__";
ProcessingHint._loadingId = "__widget_processing_loading__";
ProcessingHint.showProcessing = function(loadingImg){
	document.write('<div id="' + ProcessingHint._loadingMaskId + '" class="process-loading-mask" style=""></div>' +
			'		<div id="' + ProcessingHint._loadingId +'" class="process-loading">' +
			'			<div class="process-loading-indicator">' +
			'				<img src="' + loadingImg + '" width="32" height="32" style="margin-right:8px;" align="absmiddle"/>Loading...' +
			'		    </div>' +
			'	   </div>');
}

ProcessingHint.close = function(){	
	var loading = document.getElementById(ProcessingHint._loadingId);
	var loadingMask = document.getElementById(ProcessingHint._loadingMaskId);
	document.body.removeChild(loading);
	document.body.removeChild(loadingMask);
}
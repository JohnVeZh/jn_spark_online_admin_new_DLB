var loadingImg = contextPath+'/style/normal/loading/images/loading.gif';
	var iTimerID = "";
	
	function removeLoading(){
	  var version = navigator.appVersion;
	  var pos = version.indexOf("MSIE ");
	  if (pos < 0)
	  {
	  		window.clearInterval(iTimerID);
			ProcessingHint.close();
	  }else{
	  	if(document.readyState == "complete"){
			window.clearInterval(iTimerID);
			ProcessingHint.close();
		}
	  }
	}
	ProcessingHint.showProcessing(loadingImg);
	iTimerID = window.setInterval(removeLoading, 1000);
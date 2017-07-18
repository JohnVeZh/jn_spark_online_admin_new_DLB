var oldID = null;
function switchItem(newID) {
         if(oldID != null) {
                 oldID.style.color = "#cccccc";
         }

         newID.style.color = "#FFFF99";
         oldID = newID;
}
var headerPic = new Array(1);
headerPic[0] = new Image();
headerPic[0].src = "images/icon5.gif";
headerPic[1] = new Image();
headerPic[1].src = "images/icon6.gif";
function switchHeader() {
	if (top.document.getElementById("set1").rows == "85,*") {
		top.document.getElementById("set1").rows = "0,*";
		document.headerPic.src = headerPic[1].src;
	} else {
		top.document.getElementById("set1").rows = "85,*";
		document.headerPic.src = headerPic[0].src;
	}
}

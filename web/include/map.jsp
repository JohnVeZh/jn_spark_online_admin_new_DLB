<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
	在适当的位置添加这段代码，然后把本文件放置在页面的最下面即可自动生成一个地图页面和一个搜索框，一个按钮，及生成相应的属性
 	<div id="allmap">
	<div id="l-map"></div>
   	<div id="r-result"></div>
  	</div>
  	<input type="text" id="search"></input>
  	<input type="button" value="搜索" onclick="map_search(document.getElementById('search').value);"></input>


	页头需要引入的数据
	<style type="text/css">
	#allmap{width:900px; height:400px;}
	#l-map{height:100%;width:73%;float:left;border-right:2px solid #bcbcbc;}
	#r-result{height:100%;width:25%;float:left;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
	
	
	提供的回调函数
	function map_callback(mapx, mapy){}
--%>
	<script type="text/javascript">
	
		// 新建一个地图
		var map = new BMap.Map("l-map");
		// 设置地图中心地点
		map.centerAndZoom(new BMap.Point(mapx, mapy), zoom);
		
		map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
		map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT}));    // 添加左下角比例控件
	
		var marker1 = new BMap.Marker(new BMap.Point(mapx, mapy));  	// 创建标注
		marker1.enableDragging();
		// 标注拖曳事件
		marker1.addEventListener("dragend", function(e){    
		     map_callback(e.point.lng, e.point.lat, map.getZoom());
		}) 
		map.addOverlay(marker1);              							 // 将标注添加到地图中
	
		// 地图的单击过程
		function mapClick(e){
			 map.removeOverlay(marker1);  
			 marker1 = new BMap.Marker(e.point);  
			 marker1.enableDragging()
			 // 标注拖曳事件
			 marker1.addEventListener("dragend", function(e){    
			     map_callback(e.point.lng, e.point.lat, map.getZoom());
			 }) 	
			 map.addOverlay(marker1);              	
			 map_callback(e.point.lng, e.point.lat, map.getZoom());
		}
		// 添加鼠标单击事件
		map.addEventListener("click", mapClick);
		
		// 搜索实例
		var local = new BMap.LocalSearch(map, {
			  renderOptions: {
			    map: map,
			    panel : "r-result",
			    autoViewport: true,
			    selectFirstResult: false
			  }
			});
			
		
		// 搜索过程
		function map_search(location){
			local.search(location,  map.getBounds());
		}
	</script>


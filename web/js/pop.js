// JavaScript Document
$(document).ready(function(){
 $(".imgtext").hide();
 $(".zzsc").hover(function(){
  $(".imgtext",this).slideToggle(500);
 });
});
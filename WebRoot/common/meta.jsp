<meta http-equiv="Content-Type" content="text/html;charset=GBK"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script src="<c:out value="${pageScope.staticURL}"/>/js/autocheckform.js" type="text/javascript"></script>
<script src="<c:out value="${pageScope.staticURL}"/>/js/checkform.js" type="text/javascript"></script>
<script src="<c:out value="${pageScope.staticURL}"/>/js/jquery/jquery.js" type="text/javascript"></script>
<script src="<c:out value="${pageScope.staticURL}"/>/js/jquery/effects.core.js" type="text/javascript"></script>
<script src="<c:out value="${pageScope.staticURL}"/>/js/jquery/effects.highlight.js" type="text/javascript"></script>
<script src="<c:out value="${pageScope.staticURL}"/>/js/loading.js" type="text/javascript"></script>

<script src="<c:out value="${pageScope.staticURL}"/>/js/common.js" type="text/javascript"></script>
<script type="text/javascript">
	var $j = jQuery.noConflict();
	//begin loading
	showGifLoading('<c:out value="${pageScope.staticURL}"/>/images/loader.gif');

	$j(function() {
		var head = document.getElementsByTagName("head").item(0);
		var script = document.createElement("script");
		script.src = "<c:out value="${pageScope.staticURL}"/>/js/calendar/WdatePicker.js";
		head.appendChild(script);
		//end loading
		closeGifLoading();
		//onkeydown();
		//oncontextmenu();
	});
  
</script>
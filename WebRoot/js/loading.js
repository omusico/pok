	/*
	 * Begin 进度条
	 */
	var disabledImageZone;
	function showGifLoading(url_img) {
		if (window.parent !== null) {
			if (window.parent.name !== null && window.parent.name == "rightframe") {
		 		if (window.parent.document.getElementById("TypeList") !== null) {
		 			var tag_common_jsp = window.parent;
		 			var w = tag_common_jsp.document.body.offsetWidth;
		 			var h = tag_common_jsp.document.body.offsetHeight;
					disabledImageZone = tag_common_jsp.document.createElement('div');
					disabledImageZone.setAttribute('id', 'disabledImageZone');
					disabledImageZone.style.position = "absolute";
					disabledImageZone.style.zIndex = "1000";
					disabledImageZone.style.left = "0px";
					disabledImageZone.style.top = "0px";
					disabledImageZone.style.width = "100%";
					disabledImageZone.style.height = "100%";
					disabledImageZone.style.backgroundColor = "#FFFFFF";
					var imageZone = tag_common_jsp.document.createElement('img');
					imageZone.setAttribute('id','imageZone');
					imageZone.setAttribute('src', url_img);
					imageZone.style.position = "absolute";
					imageZone.style.top = h/2.5;
					imageZone.style.right = w/2;
					disabledImageZone.appendChild(imageZone);
					tag_common_jsp.document.body.appendChild(disabledImageZone);
		 		}
		 	}
		}
	}
	
	function showGifAffixLoading(url_img) {
		var w = window.document.body.offsetWidth;
		var h = window.document.body.offsetHeight;
		disabledImageZone = window.document.createElement('div');
		disabledImageZone.setAttribute('id', 'disabledImageZone');
		disabledImageZone.style.position = "absolute";
		disabledImageZone.style.zIndex = "1000";
		disabledImageZone.style.left = "0px";
		disabledImageZone.style.top = "0px";
		disabledImageZone.style.width = "100%";
		disabledImageZone.style.height = "100%";
		disabledImageZone.style.backgroundColor = "#F2F2F2";
		var imageZone = window.document.createElement('img');
		imageZone.setAttribute('id','imageZone');
		imageZone.setAttribute('src', url_img);
		imageZone.style.position = "absolute";
		imageZone.style.top = h/2;
		imageZone.style.right = w/2;
		disabledImageZone.appendChild(imageZone);
		window.document.body.appendChild(disabledImageZone);
	}

   /*
	* End 进度条
	*/
	function closeGifLoading() {
		if (disabledImageZone != null) {
			setTimeout("disabledImageZone.style.visibility = 'hidden';", 500);
		}
	}
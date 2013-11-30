
function wagerPlusComShow(selText,selValue,wagerNumText){//组选复式，胆拖（添加）共用,往pokertext中添加号码
  var pokerText = document.all("pokertext");
  var wagerEachNumText=document.all("wagereachnumtext");
  
  pokerText.length = pokerText.length+1;
  wagerEachNumText.length=wagerEachNumText.length+1;
  
  pokerText[pokerText.length-1].value = selValue;
  pokerText[pokerText.length-1].text = selText;
  
  wagerEachNumText[wagerEachNumText.length-1].value=wagerNumText;
  wagerEachNumText[wagerEachNumText.length-1].text=wagerNumText+"注";

}

function wagerPlusComTotal(singWagerNum){
  var totalWager=parseInt(document.all("showtotalwager").innerText);
  totalWager=totalWager+singWagerNum;
  document.all("showtotalwager").innerText=totalWager;
  document.all("showtotalmoney").innerText=numbertomoney(totalWager*2);
  
}


function pokerTextRemove(event){
	var pokerText = document.all("pokertext");
	var wagerEachNumText=document.all("wagereachnumtext");
	var indexSel;
	if(pokerText.selectedIndex>=0){
		indexSel = pokerText.selectedIndex;//get the index selected.
		wagerEachNumText.options[indexSel].selected = true;
		if(event.name=="butremove"){
		  var totalWager=parseInt(document.all("showtotalwager").innerText);
		  totalWager=totalWager-wagerEachNumText[indexSel].value;
		  document.all("showtotalwager").innerText=totalWager
		  document.all("showtotalmoney").innerText=numbertomoney(totalWager*2);
		  pokerText.options.remove(indexSel);
		  wagerEachNumText.options.remove(indexSel);
		  if(indexSel==pokerText.options.length){
			indexSel = indexSel-1;
		  }
		  if(indexSel!=-1){
		    pokerText.options[indexSel].selected = true;
		    wagerEachNumText.options[indexSel].selected = true;
		  }
		  getStrWager();
		}
	 }
	else{
	  alert("请先选择要删除的一行");
	  return;
	}
}

function wagerEmpty(){
  document.all("pokertext").length = 0;
  document.all("wagereachnumtext").length = 0;
  document.all("showtotalwager").innerText=0;
  document.all("showtotalmoney").innerText=numbertomoney(0);
  getStrWager();
}

function getStrWager(){
   var pokerText = document.all("pokertext");
   //var wagTotBef=document.all("wagtotbef");
   var wagerTotal = document.all("wagertotal");
   //wagTotBef.value="";
   wagerTotal.value = "";
   
   if(pokerText.length==0){
       //wagTotBef.value="";
	   wagerTotal.value = "";
   }else{
	  for(var i=0;i<pokerText.length;i++){
	    /* if(wagTotBef.value!=""){
	     wagTotBef.value=wagTotBef.value+"$";
	    }*/
	    var strvalue=pokerText[i].text+"g"+pokerText[i].value;
	    if(wagerTotal.value!=""){
	     wagerTotal.value=wagerTotal.value+"f";
	    }
	    //wagTotBef.value=wagTotBef.value+pokerText[i].text;
		wagerTotal.value=wagerTotal.value+strvalue;
	  }
   }
   
   document.all("wagernum").value=document.all("showtotalwager").innerText;
   //document.all("test").innerHTML=wagerTotal.value;
}

var arrTextForPla=new Array();
var arrQuanForPla=new Array();
//组6，3复式
function mulCho(numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }

  var arPlTy=new Array("hundred");
  wagerPlaGetText(arPlTy);
  wagCal();
}
//组6，3和值
function totalChoPla(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("hundred");
  wagerPlaGetText(arPlTy);
  document.all("showwagertext").innerText=arrTextForPla[0];
  var wagerNum = 0;
  if(arrTextForPla[0]!=""){
    var arrText = arrTextForPla[0].split(",");
    for (var i=0; i<arrText.length; i++){
      var intText=parseInt(arrText[i]);
      if(document.all("playtype").value=="组6"){
        wagerNum = wagerNum+getParTotGrSix(intText);
      }
	  if(document.all("playtype").value=="组3"){
        wagerNum = wagerNum+getParTotGrThr(intText);
      }
    }
  }
  
  document.all("showtotalwager").innerText=wagerNum;
  document.all("showtotalmoney").innerText=numbertomoney(wagerNum*2);
  document.all("wagertotal").value=document.all("showwagertext").innerText;
  document.all("wagernum").value=document.all("showtotalwager").innerText;
}

function wagCal(){
  var strPlayType=document.all("playtype").value;
  var arrRowOne = arrTextForPla[0].split(",");
  var numPok=arrRowOne.length;
  var totalQuan;
  if(strPlayType=="组6"){
	  if(numPok<3){
	    totalQuan=0;
	  }else{
	    totalQuan=numPok*(numPok-1)*(numPok-2)/6;
	  }
  }
  if(strPlayType=="组3"){
	  if(numPok<2){
	    totalQuan=0;
	  }else{
	    totalQuan=numPok*(numPok-1);
	  }
  }
  document.all("showsinglewager").innerText = totalQuan;
  document.all("showsinglemoney").innerText = numbertomoney(totalQuan*2);
}

function wagerPlaGetText(arrPlaType){//公共com,gr6
  
  for(var i=0;i<arrPlaType.length;i++)
  {
    var arrNumForPla=document.all(arrPlaType[i]);
    arrQuanForPla[i]=0;
    arrTextForPla[i]="";
    for(var j=0;j<arrNumForPla.length;j++){
	  if(arrNumForPla[j].className=="plach")
	  {
          arrQuanForPla[i]=arrQuanForPla[i]+1;
          if(arrTextForPla[i]!=""){
            arrTextForPla[i]=arrTextForPla[i]+",";
          }
          arrTextForPla[i]=arrTextForPla[i]+arrNumForPla[j].innerText;
	  }
    }
  }
}

function wagerPlus(){
  if(parseInt(document.all("showsinglewager").innerText)==0){
    alert("至少选择一注");
	return;
  }
  var textCombined=arrTextForPla[0];
  var showSingleWagerText=document.all("showsinglewager").innerText;
  wagerPlusComShow(textCombined,showSingleWagerText);
  wagerPlusComTotal(parseInt(showSingleWagerText));
  var arrFig=new Array("hundred");
  wagerPlusComRest(arrFig,"plare");
  getStrWager();
}
function wagerPlusComShow(text,wagerNumText){//组选与任选（添加）共用,往pokertext中添加号码
  var pokerText = document.all("pokertext");
  var wagerEachNumText=document.all("wagereachnumtext");
  
  pokerText.length = pokerText.length+1;
  wagerEachNumText.length=wagerEachNumText.length+1;
  
  pokerText[pokerText.length-1].value = text;
  pokerText[pokerText.length-1].text = text;
  
  wagerEachNumText[wagerEachNumText.length-1].value=parseInt(wagerNumText);
  wagerEachNumText[wagerEachNumText.length-1].text=wagerNumText+"注";

}

function wagerPlusComTotal(singWagerNum){
  var totalWager=parseInt(document.all("showtotalwager").innerText);
  totalWager=totalWager+singWagerNum;
  document.all("showtotalwager").innerText=totalWager;
  document.all("showtotalmoney").innerText=numbertomoney(totalWager*2);
  
}
function wagerPlusComRest(arrFigureTemp,reStr){
  document.all("showsinglewager").innerText = 0;
  document.all("showsinglemoney").innerText = numbertomoney(0);

  for(var i=0;i<arrFigureTemp.length;i++){
    var figType = document.all(arrFigureTemp[i]);
    for(var j=0;j<figType.length;j++){
      if(reStr!="plare"){
        figType[j].className=arrFigureTemp[i]+"re";
      }else{
        figType[j].className="plare";
      }
    }
  }
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

function wagerEmpty()
{
	document.all("pokertext").length = 0;
	document.all("wagereachnumtext").length = 0;
    document.all("showtotalwager").innerText=0;
    document.all("showtotalmoney").innerText=numbertomoney(0);
	getStrWager();
}

function getStrWager(){
   var pokerText = document.all("pokertext");
   var wagerTotal = document.all("wagertotal");
   wagerTotal.value = "";
   if(pokerText.length==0){
	   wagerTotal.value = "";
   }else{
	  for(var i=0;i<pokerText.length;i++){
	    if(wagerTotal.value!=""){
	     wagerTotal.value=wagerTotal.value+"$";
	    }
		wagerTotal.value=wagerTotal.value+pokerText[i].value;
	  }
   }
   
   document.all("wagernum").value=document.all("showtotalwager").innerText;
}



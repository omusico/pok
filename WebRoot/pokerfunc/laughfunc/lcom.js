var arrTextForPla=new Array();
var arrQuanForPla=new Array();
//直选
//复式
function choosePla(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("hundred","ten","one");
  wagerPlaGetText(arPlTy);
  wagerPlaCalEach();
}

//单式
function choosePlaSing(figure,numb){
  if(numb.className=="plach"){
    numb.className="plare";
  }else{
    var objFigure=document.all(figure);
    for(var i=0;i<objFigure.length;i++){
      if(objFigure[i].innerText!=numb.innerText){
        objFigure[i].className="plare";
      }else{
        objFigure[i].className="plach";
      }
    }
  }
  var arPlTy=new Array("hundred","ten","one");
  wagerPlaGetText(arPlTy);
  wagerPlaCalEach();
}
//和值
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
	  wagerNum = wagerNum+getParTot(intText);
    }
  }
  
  document.all("showtotalwager").innerText=wagerNum;
  document.all("showtotalmoney").innerText=numbertomoney(wagerNum*2);
  document.all("wagertotal").value=document.all("showwagertext").innerText;
  document.all("wagernum").value=document.all("showtotalwager").innerText;
}
//包号
function SameChoPla(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("hundred");
  wagerPlaGetText(arPlTy);
 // alert("--11--");
  document.all("showwagertext").innerText=arrTextForPla[0];
  arrQuanForPla[1]=arrQuanForPla[0];
  arrQuanForPla[2]=arrQuanForPla[1];
  var totQuan=arrQuanForPla[0]*arrQuanForPla[1]*arrQuanForPla[2];
  //alert("--22--totQuan="+totQuan+","+arrQuanForPla[0]+","+arrQuanForPla[1]+","+arrQuanForPla[2]);

  document.all("showtotalwager").innerText = totQuan;
  document.all("showtotalmoney").innerText = numbertomoney(totQuan*2);
  document.all("wagertotal").value=document.all("showwagertext").innerText;
  document.all("wagernum").value=document.all("showtotalwager").innerText;
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

function wagerPlaCalEach(){
  var totalQuan=0;
  totalQuan=arrQuanForPla[0]*arrQuanForPla[1]*arrQuanForPla[2];
  document.all("showsinglewager").innerText = totalQuan;
  document.all("showsinglemoney").innerText = numbertomoney(totalQuan*2);
}

function wagerPlus(){
  if(parseInt(document.all("showsinglewager").innerText)==0){
    alert("至少选择一注");
	return;
  }
  var playMode=document.all("playmode");
  if(playMode.value=="复式"){
    if(arrQuanForPla[0]<=1 && arrQuanForPla[1]<=1 && arrQuanForPla[2]<=1){
      alert("复式投注，其中一个位数至少选择两个号码");
      return;
    }
  }
  var textCombined=arrTextForPla[0]+"|"+arrTextForPla[1]+"|"+arrTextForPla[2];
  var showSingleWagerText=document.all("showsinglewager").innerText;
  wagerPlusComShow(textCombined,showSingleWagerText);
  wagerPlusComTotal(parseInt(showSingleWagerText));
  var arrFig=new Array("hundred","ten","one");
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
  clearRadio();
}

function clearRadio(){
  var radioMethod=document.all("method");
  for (var i=0;i<radioMethod.length;i++){
    radioMethod[i].checked=false;
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

function chPlTog(numb){
  var figureChosen=document.all("place");
  var arrFigure=new Array("hundred","ten","one");
  for(var i=0;i<arrFigure.length;i++){
    if(figureChosen.value==arrFigure[i]){
      var figType = document.all(arrFigure[i]);
      for(var j=0;j<figType.length;j++){
        if(numb.value=="all"){
          figType[j].className="plach";
        }
        if(numb.value=="major"){
           if(j>=5){
             figType[j].className="plach";
           }else{
             figType[j].className="plare";
           }
         }
        if(numb.value=="minor"){
           if(j<5){
             figType[j].className="plach";
           }else{
             figType[j].className="plare";
           }
         }
         if(numb.value=="odd"){
           if(j%2==1){
             figType[j].className="plach";
           }else{
             figType[j].className="plare";
           }
         }
         if(numb.value=="even"){
           if(j%2==0){
             figType[j].className="plach";
           }else{
             figType[j].className="plare";
           }
         }
         if(numb.value=="empty"){
           figType[j].className="plare";
         }
       }
    }
  }
  
  wagerPlaGetText(arrFigure);
  wagerPlaCalEach();
}

function randSelect(num)//组选与任选（机选）共用
{
	for(var i=0;i<num;i++)
	{
		var textRanTemp = randSelectOneCom();
		wagerPlusComShow(textRanTemp,"1");
	}
	wagerPlusComTotal(num);
	
    getStrWager();
}

function randSelectOneCom(){
  var arrRanNumFig=new Array();
  for(var i=0;i<3;i++){
    arrRanNumFig[i]=generRandomNum(0,9);
  }
  var ranNumFig=arrRanNumFig[0] + "|" + arrRanNumFig[1] + "|" + arrRanNumFig[2];
  return ranNumFig;
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


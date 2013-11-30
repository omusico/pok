var arrTextForPlay=new Array();
var arrQuanForPlay=new Array();
//直选


//复式玩法
function chooseFushiPlay(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("bai","shi","ge");
  wagerPlayGetText(arPlTy);
  wagerPlayCalEach();
}


//单式玩法
function chooseDanshiPlay(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("bai","shi","ge");
  wagerPlayGetText(arPlTy);
  wagerPlayCalEach();
}

/*
//复式
function choosePlay(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("bai","shi","ge");
  wagerPlayGetText(arPlTy);
  wagerPlayCalEach();
}

//单式
function choosePlaySing(figure,numb){
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
  var arPlTy=new Array("bai","shi","ge");
  wagerPlayGetText(arPlTy);
  wagerPlayCalEach();
}
//和值

function totalChoPlay(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("hundred");
  wagerPlayGetText(arPlTy);
  document.all("showwagertext").innerText=arrTextForPlay[0];
  var wagerNum = 0;
  if(arrTextForPlay[0]!=""){
    var arrText = arrTextForPlay[0].split(",");
    for (var i=0; i<arrText.length; i++){
      var intText=parseInt(arrText[i]);
	  wagerNum = wagerNum+getParTot(intText);
    }
  }
  
  document.all("curIssueTzNum").innerText=wagerNum;
  document.all("curIssueTzMoney").innerText=numbertomoney(wagerNum*2);
  document.all("wagertotal").value=document.all("showwagertext").innerText;
  document.all("wagernum").value=document.all("curIssueTzNum").innerText;
}
//包号
function SameChoPlay(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("hundred");
  wagerPlayGetText(arPlTy);
 // alert("--11--");
  document.all("showwagertext").innerText=arrTextForPlay[0];
  arrQuanForPlay[1]=arrQuanForPlay[0];
  arrQuanForPlay[2]=arrQuanForPlay[1];
  var totQuan=arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2];
  //alert("--22--totQuan="+totQuan+","+arrQuanForPlay[0]+","+arrQuanForPlay[1]+","+arrQuanForPlay[2]);

  document.all("curIssueTzNum").innerText = totQuan;
  document.all("curIssueTzMoney").innerText = numbertomoney(totQuan*2);
  document.all("wagertotal").value=document.all("showwagertext").innerText;
  document.all("wagernum").value=document.all("curIssueTzNum").innerText;
}
*/

function wagerPlayGetText(arrPlayType){//公共com,gr6
  
  for(var i=0;i<arrPlayType.length;i++)
  {
    var arrNumForPlay=document.all(arrPlayType[i]);
    arrQuanForPlay[i]=0;
    arrTextForPlay[i]="";
    for(var j=0;j<arrNumForPlay.length;j++){
		//	alert("!11111111111111111");

	  if(arrNumForPlay[j].className=="plach")
	  {
          arrQuanForPlay[i]=arrQuanForPlay[i]+1;
          if(arrTextForPlay[i]!=""){
           // arrTextForPlay[i]=arrTextForPlay[i]+",";
            arrTextForPlay[i]=arrTextForPlay[i];//alter 2010-08-27,原来一个位置上的多个数字是以“，”隔开，现在改为不用分隔符

          }
          arrTextForPlay[i]=arrTextForPlay[i]+arrNumForPlay[j].innerText;
	  }
    }
  }
}
/**
 *计算注数
 */
function wagerPlayCalEach(){
  var totalQuan=0;
  totalQuan=arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2];
  document.all("clickTzNum").innerText = totalQuan;
  document.all("clickTzMoney").innerText = numbertomoney(totalQuan*2);
}
/**
 *点击"添加按钮"时动作
 */
function wagerPlus(){
  if(parseInt(document.all("clickTzNum").innerText)==0){
    alert("至少选择一注");
	return;
  }
  var playMode=document.all("playMode");
  if(playMode.value=="fushi"){
    if(arrQuanForPlay[0]<=1 && arrQuanForPlay[1]<=1 && arrQuanForPlay[2]<=1 ){
      alert("复式投注，其中一个位数至少选择两个号码");
      return;
    }
  }
// var textCombined=arrTextForPlay[0]+"|"+arrTextForPlay[1]+"|"+arrTextForPlay[2]+"|"+arrTextForPlay[3]+"|"+arrTextForPlay[4];
  var textCombined=arrTextForPlay[0]+","+arrTextForPlay[1]+","+arrTextForPlay[2];//alter 2010-08-27，原来的分隔符是"|",现改为","
  var clickTzNumText=document.all("clickTzNum").innerText;
  wagerPlusComShow(textCombined,clickTzNumText);
  wagerPlusComTotal(parseInt(clickTzNumText));
  var arrFig=new Array("bai","shi","ge");
  wagerPlusComRest(arrFig,"plare");
  getStrWager();
}


function wagerPlusComShow(text,wagerNumText){//组选与任选（添加）共用,往pokertext中添加号码
  var touzhuInfoList = document.all("touzhuInfoList");
  var touzhuNumList=document.all("touzhuNumList");
  
  touzhuInfoList.length = touzhuInfoList.length+1;
  touzhuNumList.length=touzhuNumList.length+1;
  
  touzhuInfoList[touzhuInfoList.length-1].value = text;
  touzhuInfoList[touzhuInfoList.length-1].text = text;
  
  touzhuNumList[touzhuNumList.length-1].value=parseInt(wagerNumText);
  touzhuNumList[touzhuNumList.length-1].text=wagerNumText+"注";

}

function wagerPlusComTotal(singWagerNum){
  var totalWager=parseInt(document.all("curIssueTzNum").innerText);
  totalWager=totalWager+singWagerNum;
  document.all("curIssueTzNum").innerText=totalWager;
  document.all("curIssueTzMoney").innerText=numbertomoney(totalWager*2);
  
}

function wagerPlusComRest(arrFigureTemp,reStr){
  document.all("clickTzNum").innerText = 0;
  document.all("clickTzMoney").innerText = numbertomoney(0);

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
  if(radioMethod != null){//在组合方式时，没有method对象，因此加上判断
	  for (var i=0;i<radioMethod.length;i++){
		radioMethod[i].checked=false;
	  }
  }
}


function sscTextRemove(event){
	var touzhuInfoList = document.all("touzhuInfoList");
	var touzhuNumList=document.all("touzhuNumList");
	var indexSel;
	if(touzhuInfoList.selectedIndex>=0){
		indexSel = touzhuInfoList.selectedIndex;//get the index selected.
		touzhuNumList.options[indexSel].selected = true;
		if(event.name=="buttonRemove"){
		
		  var totalWager=parseInt(document.all("curIssueTzNum").innerText);
		  totalWager=totalWager-touzhuNumList[indexSel].value;
		  document.all("curIssueTzNum").innerText=totalWager
		  document.all("curIssueTzMoney").innerText=numbertomoney(totalWager*2);
		  
		  
		  touzhuInfoList.options.remove(indexSel);
		  touzhuNumList.options.remove(indexSel);
		  if(indexSel==touzhuInfoList.options.length){
			indexSel = indexSel-1;
		  }
		  if(indexSel!=-1){
		    touzhuInfoList.options[indexSel].selected = true;
		    touzhuNumList.options[indexSel].selected = true;
		  }
		  
		  getStrWager();
		}
	 }
	else{
	  alert("请先选择要删除的一行");
	  return;
	}
}

/**
 *进行”全 大 小 奇 偶 空 "的选择
 */
function choosePlayByRadio(numb){
  var figureChosen=document.all("place");
  var arrFigure=new Array("bai","shi","ge");
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
  
  wagerPlayGetText(arrFigure);
  wagerPlayCalEach();
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
  var ranNumFig=arrRanNumFig[0] + "," + arrRanNumFig[1] + "," + arrRanNumFig[2];
  return ranNumFig;
}

function wagerEmpty()
{
	document.all("touzhuInfoList").length = 0;
	document.all("touzhuNumList").length = 0;
    document.all("curIssueTzNum").innerText=0;
    document.all("curIssueTzMoney").innerText=numbertomoney(0);
	getStrWager();
}
/**
 *对投注的数量进行文本显示
 */
function getStrWager(){
   var touzhuInfoList = document.all("touzhuInfoList");
   var wagerTotal = document.all("wagertotal");
   wagerTotal.value = "";
   if(touzhuInfoList.length==0){
	   wagerTotal.value = "";
   }else{
	  for(var i=0;i<touzhuInfoList.length;i++){
	    if(wagerTotal.value!=""){
	     wagerTotal.value=wagerTotal.value+"$";
	    }
		wagerTotal.value=wagerTotal.value+touzhuInfoList[i].value;
	  }
   }
   
   document.all("wagernum").value=document.all("curIssueTzNum").innerText;

}

//追投时，得到相应的投注数及相应的期数,格式："期号|倍数$期号|倍数"
function getZhuitouIssueInfo(){
  var frmZhuitou=parent.document.frames['frameZhuitou'];
  var strIssueTimes="";
  
  var zhuitouIssueLen=frmZhuitou.document.all("zhuitouIssueLen").value;
  for (var i=0;i<zhuitouIssueLen;i++){
    var str="isscheck"+i;
    var objIssCheck=frmZhuitou.document.all(str);
    if(objIssCheck.checked==true){
      var strIssTd="isstd"+i;
      var issueTdVal=frmZhuitou.document.all(strIssTd).innerText;
      var strTimesText="timestext"+i;
      var timesInputVal=frmZhuitou.document.all(strTimesText).value
       var oneIssueTimes=issueTdVal+"|"+timesInputVal;
      if(strIssueTimes!=""){
        strIssueTimes=strIssueTimes+"$";
      }
      strIssueTimes=strIssueTimes+oneIssueTimes;
    }
  }
  return strIssueTimes;
}

/**
 * 单式添加时，对手写数据的验证及操作
 *ljy 2010-08-31
 */
function danshiCheckData(objName,len){
	var txt = $("#" + objName).attr("value");
	if (txt==""){
		alert("号码输入框不能为空！");
		return;
	}
	var aa = txt.split(/\s*\n\s*/g);
	var l = aa.length;
	if (l==0){
		return alert("号码输入框格式有误，请检查！");
	}else if (l>300){
		return alert("输入号码最多300行！");
	}else{
		var msg = "";
		var b = false;//ssc.name=="三星组选单式";
		//var c = ssc.name=='二星组选单式';
		for (var i=0;i<l;i++){
		  if(!(/^\d+$/.test(aa[i]))){
			alert("第"+(i+1)+"行号码格式有误，请重新检查修改。\n提示：内容只能为数字。");
			return;
		  }
		  
		  aa[i] = aa[i].match(/\d/g);
		 // alert(aa[i].length);
		  if(aa[i].length != len){
			  alert("第"+(i+1)+"行号码有误,必须为" + len +"位数");
			  return;
		  }
		  /**暂时先不用校验
		  if( aa[i][0] == aa[i][1]){
				msg = (aa[i]+"为对子，二星组选单式不支持该类型，谢谢。");
		  }else if(b&&/^(\d),?\1,?\1$/.test(aa[i])){
				msg = "您好，3个号码不能完全相同，请重新检查修改！";
		  }else if(b&&!ssc.chkAsc(s)){
				msg = "您好，号码没按升序排序，请重新检查修改！";
		  }else continue;
		  */	


		}
		
	}
	var infoList = document.getElementById("touzhuInfoList").options;
	var numList = document.getElementById("touzhuNumList").options;
	for (var i=0,l=aa.length; i<l; i++){
		infoList.add(new Option(aa[i],aa[i]));	
		numList.add(new Option("1注","1"));
	}
	//设置总注数
	$("#curIssueTzNum").attr("innerHTML",l);
	$("#curIssueTzMoney").attr("innerHTML",eval(l*2));
	getStrWager();
	//清空原来的
	$("#" + objName).attr("value","");
}


/**
 *三星直选-组合
 *2010-08-31
 */
function chooseSanxingZhixuznZuhePlay(figure,numb){
  if(numb.className=="plare"){
	numb.className="plach";
  }else{
	 numb.className="plare";
  }
  var arPlTy=new Array("ge");
  wagerPlayGetText(arPlTy);
  sanxingZhixuanZuheCal();
}


/**
 *三星直选--组合
 *2010-08-31
 */

function sanxingZhixuanZuheCal(){
  var strPlayType=document.all("playType").value;
  var strPlayMode=document.all("playMode").value;
 // var arrRowOne = arrTextForPlay[0].split(",");
  var arrRowOne = arrTextForPlay[0].split("");
 // alert(strPlayType);
  //alert(arrRowOne);
  var numPok=arrRowOne.length;
 // alert(arrRowOne);
  var totalQuan;
  if(strPlayType=="组6"){
	  if(numPok<3){
	    totalQuan=0;
	  }else{
	    totalQuan=numPok*(numPok-1)*(numPok-2)/6;
	  }
  }
  if(strPlayType=="sanxing" && strPlayMode == "zuheFushi"){
	//  alert(strPlayType);
	  if(numPok<3){
	    totalQuan=0;
	  }else{
	    totalQuan=numPok*(numPok-2)*(numPok-1);
	  }
  }
 // document.all("showsinglewager").innerText = totalQuan;
  //document.all("showsinglemoney").innerText = numbertomoney(totalQuan*2);
    document.all("clickTzNum").innerText = totalQuan;
  document.all("clickTzMoney").innerText = numbertomoney(totalQuan*2);

}

/**
 *三星直选-组合复式--点击"添加按钮"时动作
 2010-08-31
 */
function wagerPlusOfSxZhixuanZuhe(){
  if(parseInt(document.all("clickTzNum").innerText)==0){
    alert("至少选择一注");
	return;
  }
  var playMode=document.all("playMode");
  if(playMode.value=="fushi"){
    if(arrQuanForPlay[0]<=1 && arrQuanForPlay[1]<=1 && arrQuanForPlay[2]<=1 ){
      alert("复式投注，其中一个位数至少选择两个号码");
      return;
    }
  }
// var textCombined=arrTextForPlay[0]+"|"+arrTextForPlay[1]+"|"+arrTextForPlay[2]+"|"+arrTextForPlay[3]+"|"+arrTextForPlay[4];
  var textCombined=arrTextForPlay[0];//alter 2010-08-27，原来的分隔符是"|",现改为","
  //组合复式的时候，只有"123",现在要把它变为"1,2,3"的形式
  var aryTextCombined = textCombined.split("");
  var tempText = "";
  for(i=0; i < aryTextCombined.length; i++){
		tempText = tempText + aryTextCombined[i] + ",";
  }
  if(tempText.length != 0){
	  tempText = tempText.substring(0,tempText.length-1);
  }

  var clickTzNumText=document.all("clickTzNum").innerText;
  wagerPlusComShow(tempText,clickTzNumText);
  wagerPlusComTotal(parseInt(clickTzNumText));
  var arrFig=new Array("ge");
 // alert(arrFig);
  wagerPlusComRest(arrFig,"plare");
 getStrWager();
}

var arrTextForPlay=new Array();
var arrQuanForPlay=new Array();
 

//复式玩法
function chooseFushiPlay(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("ge");
  wagerPlayGetText(arPlTy);
  wagerPlayCalEach();
}



//初始化数组
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
           arrTextForPlay[i]=arrTextForPlay[i]+",";
            //arrTextForPlay[i]=arrTextForPlay[i];//alter 2010-08-27,原来一个位置上的多个数字是以“，”隔开，现在改为不用分隔符

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
  var tempNum =   arrTextForPlay[0].split(",");
  var tempLen = tempNum.length;
  // alert(tempNum+";;;"+tempLen);
  if(tempLen < 5){
	  totalQuan = 0;
  }else{
	totalQuan=arrQuanForPlay[0]*(arrQuanForPlay[0]-1)*(arrQuanForPlay[0]-2)*(arrQuanForPlay[0]-3)*(arrQuanForPlay[0]-4)/120;
  }

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
   var textCombined=arrTextForPlay[0];//alter 2010-08-27，原来的分隔符是"|",现改为","
  var clickTzNumText=document.all("clickTzNum").innerText;
  wagerPlusComShow(textCombined,clickTzNumText);
  wagerPlusComTotal(parseInt(clickTzNumText));
  var arrFig=new Array("ge");
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
  //var figureChosen=document.all("place");
  var figureChosen = "ge";
  var arrFigure=new Array("ge");
  for(var i=0;i<arrFigure.length;i++){
    if(figureChosen==arrFigure[i]){
      var figType = document.all(arrFigure[i]);
      for(var j=0;j<figType.length;j++){
        if(numb.value=="all"){
          figType[j].className="plach";
        }
        if(numb.value=="major"){
           if(j>=10){
             figType[j].className="plach";
           }else{
             figType[j].className="plare";
           }
         }
        if(numb.value=="minor"){
           if(j<10){
             figType[j].className="plach";
           }else{
             figType[j].className="plare";
           }
         }
         if(numb.value=="odd"){
           if(j%2==0){
             figType[j].className="plach";
           }else{
             figType[j].className="plare";
           }
         }
         if(numb.value=="even"){
           if(j%2==1){
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

//组选与任选（机选）共用
function randSelect(num)
{
	for(var i=0;i<num;i++)
	{
		var textRanTemp = randSelectOneCom();
		wagerPlusComShow(textRanTemp,"1");
	}
	wagerPlusComTotal(num);
	
    getStrWager();
}
//机选1注
function randSelectOneCom(){
     var min = 1;
  var max = 20;
  var count = 5;
  var a=[],b=[],mm=Math; 
  count=mm.min(count,max); 
  while(b.length<count) { 
	  var c=mm.round(mm.random()*(max-min))+min; 
	  if(undefined==a[c]) {
		  b.push(a[c]=c);
	  }
  } 
  return b.sort(function(a,b){return a>b?1:-1}); 

}
//胆拖 ----------机选--------------
function jixuanRandSelect(num)
{
	for(var i=0;i<num;i++)
	{
		var textRanTemp = jixuanRandSelectOneCom();
		wagerPlusComShow(textRanTemp,"1");
	}
	wagerPlusComTotal(num);
	
    getStrWager();
}
//-----胆拖-机选-----------
function jixuanRandSelectOneCom(){
  var min = 1;
  var max = 11;
  var count = 5;
  var a=[],b=[],mm=Math; 
  count=mm.min(count,max); 
  while(b.length<count) { 
	  var c=mm.round(mm.random()*(max-min))+min; 
	  if(undefined==a[c]) {
		  b.push(a[c]=c);
	  }
  } 
  var aryStr =  b.sort(function(a,b){return a>b?1:-1}); 
 
  var str = "";
  if(aryStr.length > 1){
	str = "胆"+aryStr[0] + "-拖";
	var temp = "";
	for(var i = 1; i < aryStr.length; i++){
		temp = temp + aryStr[i] + ",";
	}
	if(temp != ""){
		temp = temp.substring(0,temp.length-1);
	}
	str = str + temp;
  }
  
  return str;
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
 *胆拖--选择
 */
function chooseDantuoPlay(weizhi,selectedNum){
		
	
	//如果当前点击的号码为选择状态，则将其置为未选中状态,同时置为
	if(selectedNum.className=="plach"){
		selectedNum.className="plare";
		
		wagerPlayGetText(new Array("shi","ge"));		
		xuanwuDantuoCal()
		return;
	}

	var objWeizhi = document.getElementsByName(weizhi);
	var selectedCount = 0;
	for(i=0;i<objWeizhi.length;i++){
		if(objWeizhi[i].className=="plach"){
			selectedCount = selectedCount+1;
			
		}
	}
	//胆码只能选择1个
	if(weizhi == "shi" && selectedCount > 3){
		alert("胆码个数不能超过4个");
		return;
	}else{
		 if(selectedNum.className=="plare"){
			selectedNum.className="plach";
		 }else{
			 selectedNum.className="plare";
		 }

		 //同时将不同数位上相同的数置为未选中
		 var tempWeizhi = "";
		 if(weizhi == "shi"){
			 tempWeizhi = "ge";
		 }else if(weizhi == "ge"){
			 tempWeizhi = "shi";
		 }
		
		 var objTempWeizhi = document.getElementsByName(tempWeizhi);
		 for(i=0;i<objTempWeizhi.length;i++){
			if( objTempWeizhi[i].innerText == selectedNum.innerText){
				if(objTempWeizhi[i].className=="plach"){
					objTempWeizhi[i].className="plare";
				}
			}
		}
	}


	wagerPlayGetText(new Array("shi","ge"));

   // wagerPlayGetText(arPlTy);
	xuanwuDantuoCal()
}


/**
 *任选3--胆拖--选择
 *2010-09-09
 */

function xuanwuDantuoCal(){
		
	//alert(arrTextForPlay[0]+";;;;"+arrTextForPlay[1]);	
	var arrRowOne = new Array();
	var arrRowTwo = new Array();
	
	var rowOneLen = 0;
	var rowTwoLen = 0;

	if(arrTextForPlay[0] != null && arrTextForPlay[0] != ""){
		arrRowOne = arrTextForPlay[0].split(",");
		rowOneLen = arrRowOne.length;

	}
	
	if(arrTextForPlay[1] != null && arrTextForPlay[1] != ""){
		arrRowTwo = arrTextForPlay[1].split(",");
		rowTwoLen = arrRowTwo.length;
	}
//	alert("one="+rowOneLen + ",two="+rowTwoLen);
	    
	var totalQuan = 0;
	if((rowOneLen<1 || rowTwoLen <1) && (rowOneLen+rowTwoLen < 5) ){
		totalQuan=0;
	}else{
		if(rowOneLen == 1){
			totalQuan = rowTwoLen * (rowTwoLen-1) * (rowTwoLen-2) * (rowTwoLen-3)/24;//如果胆码选择的是1个，则投注注数为拖胆中任意4个的组合
		}else if(rowOneLen == 2){
			totalQuan = rowTwoLen * (rowTwoLen-1) * (rowTwoLen-2)/6;//如果胆码选择的是2个，则投注注数为拖胆中任意3个的组合
		}else if(rowOneLen == 3){
			totalQuan = rowTwoLen * (rowTwoLen-1)/2;//如果胆码选择的是3个，则投注注数为拖胆中任意2个的组合
		}else if(rowOneLen == 4){
			totalQuan = rowTwoLen;//如果胆码选择的是4个，则投注注数为拖胆的个数
		}
	}

	document.all("clickTzNum").innerText = totalQuan;
	document.all("clickTzMoney").innerText = numbertomoney(totalQuan*2);

}

//任选3--胆拖--添加按钮
function xuanerDantuoPlug(){
	if(parseInt(document.all("clickTzNum").innerText)==0){
	alert("至少选择一注");
	return;
	}	

	//判断选择的状态
	var objBaodanRadio = document.getElementsByName("btBaodan");
	var radioVal = "";
	for(i=0;i<objBaodanRadio.length;i++){
	  if(objBaodanRadio[i].checked){
		radioVal = objBaodanRadio[i].value;
	  }
	}
	
	tempText = "胆" + arrTextForPlay[0] + "-拖"  + arrTextForPlay[1];

	var clickTzNumText=document.all("clickTzNum").innerText;
	wagerPlusComShow(tempText,clickTzNumText);
	wagerPlusComTotal(parseInt(clickTzNumText));
	//var arrFig=new Array("ge");
	// alert(arrFig);
	
	wagerPlusComRest(new Array("shi","ge"),"plare");
    
	getStrWager();
}
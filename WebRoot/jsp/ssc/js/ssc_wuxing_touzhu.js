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
  var arPlTy=new Array("wan","qian","bai","shi","ge");
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
  var arPlTy=new Array("wan","qian","bai","shi","ge");
  wagerPlayGetText(arPlTy);
  wagerPlayCalEach();
}

 
function wagerPlayGetText(arrPlayType){//公共com,gr6
  
  for(var i=0;i<arrPlayType.length;i++)
  {
    var arrNumForPlay=document.all(arrPlayType[i]);
    arrQuanForPlay[i]=0;
    arrTextForPlay[i]="";
    for(var j=0;j<arrNumForPlay.length;j++){
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
  totalQuan=arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
 
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
  var playMode=document.all("playmode");
  if(playMode.value=="复式"){
    if(arrQuanForPlay[0]<=1 && arrQuanForPlay[1]<=1 && arrQuanForPlay[2]<=1 && arrQuanForPlay[3]<=1 && arrQuanForPlay[4]<=1){
      alert("复式投注，其中一个位数至少选择两个号码");
      return;
    }
  }
// var textCombined=arrTextForPlay[0]+"|"+arrTextForPlay[1]+"|"+arrTextForPlay[2]+"|"+arrTextForPlay[3]+"|"+arrTextForPlay[4];
  var textCombined=arrTextForPlay[0]+","+arrTextForPlay[1]+","+arrTextForPlay[2]+","+arrTextForPlay[3]+","+arrTextForPlay[4];//alter 2010-08-27，原来的分隔符是"|",现改为","
  var clickTzNumText=document.all("clickTzNum").innerText;
  wagerPlusComShow(textCombined,clickTzNumText);
  wagerPlusComTotal(parseInt(clickTzNumText));
  var arrFig=new Array("wan","qian","bai","shi","ge");
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
  for (var i=0;i<radioMethod.length;i++){
    radioMethod[i].checked=false;
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
  var arrFigure=new Array("wan","qian","bai","shi","ge");
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
  for(var i=0;i<5;i++){
    arrRanNumFig[i]=generRandomNum(0,9);
  }
  var ranNumFig=arrRanNumFig[0] + "," + arrRanNumFig[1] + "," + arrRanNumFig[2]+ "," + arrRanNumFig[3]+ "," + arrRanNumFig[4];
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
	
//五星组120
function chooseZu120Play(weizhi,selectObj,val){
 
	//如果是进行“选中”的操作
	if(selectObj.className=="plare"){
		//得到当前位置的数值
		var typeAry=new Array("wan","qian","bai","shi","ge");
		var repeatFlag = false;
		for(var i = 0; i < typeAry.length;i++){
			var tempType = typeAry[i];
			//进行对其他位置的行数量进行扫描，判断是否有与当前选择的号码相同的
			if(tempType != weizhi){
				var objTemp = document.getElementsByName(tempType);
				for(j=0;j<objTemp.length;j++){
					if(objTemp[j].className=="plach"){
						var tempVal = objTemp[j].innerText;
						//alert(tempVal);
						if(tempVal == val){							
							repeatFlag = true;
							break;
						}
					}
				}
			}
		}
		if(repeatFlag){
			alert("五星组选一百二十不能有重复数字!");
			return;
		}else{
			 selectObj.className="plach";
		}
	}else{
		 selectObj.className="plare";
	}

    var arPlTy=new Array("wan","qian","bai","shi","ge");
	wagerPlayGetText(arPlTy);
	wagerPlayCalEach();
}
//五星组选---通用
function chooseZuxuanPlay(weizhi,selectObj,val,curType){
	var typeAry = new Array();
	var alertStr = "";
	if(curType=="zu120"){
		typeAry=new Array("wan","qian","bai","shi","ge");
		alertStr = "五星组选一百二十不能有重复数字!";
	}
	if(curType=="zu60"){
		typeAry=new Array("qian","bai","shi","ge");
		alertStr = "五星组选六十不能重复选择!";
	}

	if(curType=="zu30"){
		typeAry=new Array("bai","shi","ge");
		alertStr = "五星组选三十不能重复选择!";
	}
	if(curType=="zu20"){
		typeAry=new Array("bai","shi","ge");
		alertStr = "五星组选二十不能重复选择!";
	}
	if(curType=="zu10"){
		typeAry=new Array("shi","ge");
		alertStr = "五星组选十不能重复选择!";
	}
	
	if(curType=="zu5"){
		typeAry=new Array("shi","ge");
		alertStr = "五星组选五不能重复选择!";
	}
	if(curType=="zuchong2"){
		typeAry=new Array("ge");
		alertStr = "五星重号全包不能重复选择!";
	}
	if(curType=="zuchong3"){
		typeAry=new Array("ge");
		alertStr = "五星三重号全包不能重复选择!";
	}
	
	if(curType=="zuchong4"){
		typeAry=new Array("ge");
		alertStr = "五星四重号全包不能重复选择!";
	}
	//如果是进行“选中”的操作
	if(selectObj.className=="plare"){
		//得到当前位置的数值

		var repeatFlag = false;
		for(var i = 0; i < typeAry.length;i++){
			var tempType = typeAry[i];
			//进行对其他位置的行数量进行扫描，判断是否有与当前选择的号码相同的
			if(tempType != weizhi){
				var objTemp = document.getElementsByName(tempType);
				for(j=0;j<objTemp.length;j++){
					if(objTemp[j].className=="plach"){
						var tempVal = objTemp[j].innerText;
						//alert(tempVal);
						if(tempVal == val){							
							repeatFlag = true;
							break;
						}
					}
				}
			}
		}
		if(repeatFlag){
			alert(alertStr);
			return;
		}else{
			 selectObj.className="plach";
		}
	}else{
		 selectObj.className="plare";
	}

 	wagerPlayGetText(typeAry);
	 
	//由此开始计算注数
	var totalQuan=0;
	 
	if(curType=="zu120"){
		 totalQuan=arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
	}
	if(curType=="zu60"){
		totalQuan=arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3];
	}
	if(curType=="zu30"){
		totalQuan=arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2];
	}
	if(curType=="zu20"){
		totalQuan=arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2];
	} 
	 
	if(curType=="zu10"){
		totalQuan=arrQuanForPlay[0]*arrQuanForPlay[1];
	} 
	if(curType=="zu5"){
		totalQuan=arrQuanForPlay[0]*arrQuanForPlay[1];
	}
	if(curType=="zuchong2"){
		totalQuan=arrQuanForPlay[0];
	}
	if(curType=="zuchong3"){
		totalQuan=arrQuanForPlay[0];
	}
	
	if(curType=="zuchong4"){
		totalQuan=arrQuanForPlay[0];
	}
	 document.all("clickTzNum").innerText = totalQuan;
	 document.all("clickTzMoney").innerText = numbertomoney(totalQuan*2);
	 //注数计算 end
}

function chooseZu60Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zu60");

}
function chooseZu30Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zu30");

}
function chooseZu20Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zu20");

}
function chooseZu10Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zu10");

}

function chooseZu5Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zu5");

}
//重号全包
function chooseZuChong2Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zuchong2");

}
//三重号全包选择
function chooseZuChong3Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zuchong3");

}
//四重号全包选择
function chooseZuChong4Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zuchong4");

}
/**
 *点击"添加按钮"时动作
 */
function wagerZuxuanPlus(selectType){
  if(parseInt(document.all("clickTzNum").innerText)==0){
    alert("至少选择一注");
	return;
  }
  var playMode=document.all("playmode");
 
  var textCombined="";
  var arrFig;
  if(selectType=="zu60"){
		textCombined = arrTextForPlay[0]+","+arrTextForPlay[1]+","+arrTextForPlay[2]+","+arrTextForPlay[3];
		arrFig = new Array("qian","bai","shi","ge");
  }
   if(selectType=="zu30"){
		textCombined = arrTextForPlay[0]+","+arrTextForPlay[1]+","+arrTextForPlay[2];
		arrFig = new Array("bai","shi","ge");
  }
   if(selectType=="zu20"){
		textCombined = arrTextForPlay[0]+","+arrTextForPlay[1]+","+arrTextForPlay[2];
		arrFig = new Array("bai","shi","ge");
  }
  if(selectType=="zu10"){
		textCombined = arrTextForPlay[0]+","+arrTextForPlay[1];
		arrFig = new Array("shi","ge");
  }
  if(selectType=="zu5"){
		textCombined = arrTextForPlay[0]+","+arrTextForPlay[1];
		arrFig = new Array("shi","ge");
  }
  if(selectType=="zuchong2"){
		textCombined = arrTextForPlay[0];
		arrFig = new Array("ge");
  }
    if(selectType=="zuchong3"){
		textCombined = arrTextForPlay[0];
		arrFig = new Array("ge");
  }
  //alert(selectType);
  if(selectType=="zuchong4"){
		textCombined = arrTextForPlay[0];
		arrFig = new Array("ge");
  }
  var clickTzNumText=document.all("clickTzNum").innerText;
  wagerPlusComShow(textCombined,clickTzNumText);
  wagerPlusComTotal(parseInt(clickTzNumText));
  wagerPlusComRest(arrFig,"plare");
  getStrWager();
}
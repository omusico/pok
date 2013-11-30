var arrTextForFig=new Array();
var arrQuanForFig=new Array();



function wagerComFreeCalcu(){
  var arrFigType=new Array("spade","heart","club","diamond");
  var arrFreeFigStr=new Array("one","two","three","four");
  for (var i=0;i<arrFigType.length;i++){
    var selTextFigTemp="seltext"+arrFigType[i];
    document.all(selTextFigTemp).innerHTML=arrTextForFig[i];
    var freeFigTemp="free"+arrFreeFigStr[i];
    document.all(freeFigTemp).innerHTML=calcuWagQuan(arrFreeFigStr[i],arrQuanForFig[0],arrQuanForFig[1],arrQuanForFig[2],arrQuanForFig[3]);
	//alert( document.all(freeFigTemp).innerHTML);
    if(arrTextForFig[i]==""){
      arrTextForFig[i]="=";
    }
  }
  var strFreeOne=document.all("freeone").innerHTML;
  var strFreeTwo=document.all("freetwo").innerHTML;
  var strFreeThree=document.all("freethree").innerHTML;
  var strFreeFour=document.all("freefour").innerHTML;

  document.all("selpokertext").innerHTML=arrTextForFig[0]+"|"+arrTextForFig[1]+"|"+arrTextForFig[2]+"|"+arrTextForFig[3];
  document.all("showtotalwager").innerText=parseInt(strFreeOne)+parseInt(strFreeTwo)+parseInt(strFreeThree)+parseInt(strFreeFour);
  document.all("showtotalmoney").innerText=numbertomoney(parseInt(document.all("showtotalwager").innerText)*2);
  
  document.all("wagernum").value=document.all("showtotalwager").innerText;
  document.all("wagertotal").value=document.all("selpokertext").innerHTML;
}
function choPokGrFourCalcu(){
  var modeMark=parent.document.all("modechosenmark");
  var arrFigType=new Array("rowone","rowtwo");
  choPokerGrGetText(arrFigType);
  var totalQuan;
  if(arrTextForFig[0]=="" || arrTextForFig[1]==""){
    totalQuan=0;
  }else{
    var arrRowOne = arrTextForFig[0].split(",");
    var arrRowTwo = arrTextForFig[1].split(",");
    totalQuan=0;
    var textCombinGr="";
    for(var i=0;i<arrRowOne.length;i++){
      for(var j=0;j<arrRowTwo.length;j++){
        if(arrRowOne[i]!=arrRowTwo[j]){
          totalQuan++;//add one wager
          var textTemp=arrRowOne[i]+","+arrRowTwo[j]+","+arrRowTwo[j]+","+arrRowTwo[j];
          textTemp = SortNum(textTemp.replace(/A/g,"1").replace(/J/g,"11").replace(/Q/g,"12").replace(/K/g,"13")).join(",");
		  textTemp = textTemp.replace(/11/g,"J").replace(/12/g,"Q").replace(/13/g,"K").replace(/1/g,"A").replace(/A0/g,"10");
          if(textCombinGr!=""){
	        textCombinGr = textCombinGr + "$";
          }
          textCombinGr = textCombinGr + textTemp;
        }
      }
    }
    if(modeMark.value!="compgrou"){
      document.all("textcomgrhidden").value=textCombinGr;
    }
  }
  return totalQuan;
}

function choPokGrFour(){
  choPokerGrShowText(choPokGrFourCalcu());
}
function choPokGrSixCalcu(){
  var modeMark=parent.document.all("modechosenmark");
  var arrFigType=new Array("rowone","rowtwo");
  choPokerGrGetText(arrFigType);
  var totalQuan;
  if(arrTextForFig[0]=="" || arrTextForFig[1]==""){
    totalQuan=0;
  }else{
    var arrRowOne = arrTextForFig[0].split(",");
    var arrRowTwo = arrTextForFig[1].split(",");
    totalQuan=0;
    var textCombinGr="";
    for(var i=0;i<arrRowOne.length;i++){
      for(var j=0;j<arrRowTwo.length;j++){
        if(arrRowOne[i]!=arrRowTwo[j]){
          var textTemp = arrRowOne[i]+","+arrRowOne[i]+","+arrRowTwo[j]+","+arrRowTwo[j];
		  textTemp = SortNum(textTemp.replace(/A/g,"1").replace(/J/g,"11").replace(/Q/g,"12").replace(/K/g,"13")).join(",");
		  textTemp = textTemp.replace(/11/g,"J").replace(/12/g,"Q").replace(/13/g,"K").replace(/1/g,"A").replace(/A0/g,"10");
		  if(textCombinGr.indexOf(textTemp)==-1){
		    totalQuan++;
		    if(textCombinGr!=""){
		      textCombinGr = textCombinGr + "$";
		    }
		    textCombinGr = textCombinGr + textTemp;
		  }
        }
      }
    }
    if(modeMark.value!="compgrou"){
      document.all("textcomgrhidden").value=textCombinGr;
    }
  }
  return totalQuan;
}
function choPokGrSix(){

  choPokerGrShowText(choPokGrSixCalcu());
}
function choPokGrTwelveCalcu(){
  var modeMark=parent.document.all("modechosenmark");
  var arrFigType=new Array("rowone","rowtwo","rowthree");
  choPokerGrGetText(arrFigType);
  var totalQuan;
  if(arrTextForFig[0]=="" || arrTextForFig[1]=="" ||arrTextForFig[2]==""){
    totalQuan=0;
  }else{
    var arrRowOne = arrTextForFig[0].split(",");
    var arrRowTwo = arrTextForFig[1].split(",");
    var arrRowThree = arrTextForFig[2].split(",");
    totalQuan=0;
    var textCombinGr="";
    for(var i=0;i<arrRowOne.length;i++){
      for(var j=0;j<arrRowTwo.length;j++){
        for(var k=0;k<arrRowThree.length;k++){
          var strTemp = (arrRowOne[i]+","+arrRowTwo[j]+","+arrRowThree[k]+","+arrRowThree[k]).replace(/A/g,"1").replace(/J/g,"11").replace(/Q/g,"12").replace(/K/g,"13");
		  strTemp = strTemp.split(",").sort(SortFunction_Int).join(",");
		  strTemp = strTemp.replace(/11/g,"J").replace(/12/g,"Q").replace(/13/g,"K").replace(/1/g,"A").replace(/A0/g,"10");
		  if(arrRowOne[i]!=arrRowTwo[j] && arrRowOne[i]!=arrRowThree[k] && arrRowTwo[j]!=arrRowThree[k] && (textCombinGr).indexOf(strTemp)<0){
		    if(textCombinGr!=""){
			  textCombinGr = textCombinGr + "$";
			}
			textCombinGr = textCombinGr + strTemp;
			totalQuan++;
		  }
        }
      }
    }
    if(modeMark.value!="compgrou"){
      document.all("textcomgrhidden").value=textCombinGr;
    }
  }
  return totalQuan;
}
function choPokGrTwelve(){

  choPokerGrShowText(choPokGrTwelveCalcu());
}
function choPokGrTwenFourCalcu(){
  var modeMark=parent.document.all("modechosenmark");
  var arrFigType=new Array("rowone","rowtwo","rowthree","rowfour");
  choPokerGrGetText(arrFigType);
  
  var totalQuan;
  if(arrTextForFig[0]=="" || arrTextForFig[1]=="" ||arrTextForFig[2]==""||arrTextForFig[3]==""){
    totalQuan=0;
  }else{
    var arrRowOne = arrTextForFig[0].split(",");
    var arrRowTwo = arrTextForFig[1].split(",");
    var arrRowThree = arrTextForFig[2].split(",");
    var arrRowFour = arrTextForFig[3].split(",");
    totalQuan=0;
    var textCombinGr="";
    for(var i=0;i<arrRowOne.length;i++){
      for(var j=0;j<arrRowTwo.length;j++){
        for(var k=0;k<arrRowThree.length;k++){
          for(var l=0;l<arrRowFour.length;l++){
            var strTemp = (arrRowOne[i]+","+arrRowTwo[j]+","+arrRowThree[k]+","+arrRowFour[l]).replace(/A/g,"1").replace(/J/g,"11").replace(/Q/g,"12").replace(/K/g,"13");
		    strTemp = strTemp.split(",").sort(SortFunction_Int).join(",");
		    strTemp = strTemp.replace(/11/g,"J").replace(/12/g,"Q").replace(/13/g,"K").replace(/1/g,"A").replace(/A0/g,"10");
		    if(arrRowOne[i]!=arrRowTwo[j] && arrRowOne[i]!=arrRowThree[k] && arrRowOne[i]!=arrRowFour[l]){
		      if(arrRowTwo[j]!=arrRowThree[k] && arrRowTwo[j]!=arrRowFour[l]){
		        if(arrRowThree[k]!=arrRowFour[l] && (textCombinGr).indexOf(strTemp)<0){
		          if(textCombinGr!=""){
		  	        textCombinGr = textCombinGr + "$";
			      }
			      textCombinGr = textCombinGr + strTemp;
			      totalQuan++;
			    }
			  }
		    }
		  }
        }
      }
    }
    if(modeMark.value!="compgrou"){
      document.all("textcomgrhidden").value=textCombinGr;
    }
  }
  return totalQuan;
}
function choPokGrTwenFour(){
  choPokerGrShowText(choPokGrTwenFourCalcu());
}

function choPokGr(figure,numb){
  var modeMark=parent.document.all("modechosenmark");
  if(modeMark.value=="gr4"){
    if(figure=="rowone"||figure=="rowtwo"){
      choPokGrCla(numb);
    }
    choPokGrFour();
  }
  if(modeMark.value=="gr6"){
    if(figure=="rowone"||figure=="rowtwo"){
      choPokGrCla(numb);
    }
    choPokGrSix();
  }
  if(modeMark.value=="gr12"){
    if(figure=="rowone"||figure=="rowtwo"||figure=="rowthree"){
      choPokGrCla(numb);
    }
    choPokGrTwelve();
  }
  if(modeMark.value=="gr24"){
    if(figure=="rowone"||figure=="rowtwo"||figure=="rowthree"||figure=="rowfour"){
      choPokGrCla(numb);
    }
    choPokGrTwenFour();
  }

}

function choPokGrCla(numbTemp){
 if(numbTemp.className=="voidch"){
   numbTemp.className="voidre";
 }else{
   var rowName=numbTemp.id;
   var objRow=document.all(rowName);
   for(var i=0;i<objRow.length;i++){
     objRow[i].className="voidre";
   }
   numbTemp.className="voidch";
 }
}

function choPokerGrGetText(arrFigTypeTemp){
  for(var i=0;i<arrFigTypeTemp.length;i++)
  {
    var arrPokerForFig=document.all(arrFigTypeTemp[i]);//get 13 pokers for row one
    var str="voidch";//得到row one的str为voidch
    arrTextForFig[i]="";//row one的text为空*****每次点击都置空********
    for(var j=0;j<arrPokerForFig.length;j++){//循环row one的13张
	  if(arrPokerForFig[j].className==str)//if each poker get the className as "spadech".
	  {
          if(arrTextForFig[i]!=""){
            arrTextForFig[i]=arrTextForFig[i]+",";
          }
          arrTextForFig[i]=arrTextForFig[i]+arrPokerForFig[j].innerText;//spade's text string add each poker's text.
	  }
    }
  }
}

function choPokerGrShowText(totalQuanTemp){
  document.all("showsinglewager").innerText = totalQuanTemp;
  document.all("showsinglemoney").innerText = numbertomoney(totalQuanTemp*2);
}
function choPokGrTog(numb){
  var figureChosen=document.all("figure");
  var arrFigure=new Array("rowone","rowtwo","rowthree");
  for(var i=0;i<arrFigure.length;i++){
    if(figureChosen.value==arrFigure[i]){
      var figType = document.all(arrFigure[i]);
      for(var j=0;j<figType.length;j++){
        if(numb.value=="all"){
          figType[j].className="voidch";
        }
        if(numb.value=="major"){
           if(j>=6){
             figType[j].className="voidch";
           }else{
             figType[j].className="voidre";
           }
         }
        if(numb.value=="minor"){
           if(j<6){
             figType[j].className="voidch";
           }else{
             figType[j].className="voidre";
           }
         }
         if(numb.value=="odd"){
           if(j%2==0){
             figType[j].className="voidch";
           }else{
             figType[j].className="voidre";
           }
         }
         if(numb.value=="even"){
           if(j%2==1){
             figType[j].className="voidch";
           }else{
             figType[j].className="voidre";
           }
         }
         if(numb.value=="empty"){
           figType[j].className="voidre";
         }
       }
    }
  }
  
  var modeMark=parent.document.all("modechosenmark");
  if(modeMark.value=="gr4"){
    choPokGrFour();
  }
  if(modeMark.value=="gr6"){
    choPokGrSix();
  }
  if(modeMark.value=="gr12"){
    choPokGrTwelve();
  }
  if(modeMark.value=="gr24"){
    choPokGrTwenFour();
  }
  
}
function choosePoker(figure,numb){
  //点击时，改变显示的样式
  var figureReStr=figure+"re";
  var figureChStr=figure+"ch";
  var modeMark=parent.document.all("modechosenmark");
//  alert("modeMark="+modeMark.value+",figureReStr="+figureReStr+",figureChStr="+figureChStr);
  if(modeMark.value=="multi"||modeMark.value==""){
    if(numb.className==figureReStr){
    numb.className=figure+"ch";
    }else{
    numb.className=figure+"re";
    }
  }
  if(modeMark.value=="sing"){
    if(numb.className==figureChStr){
      numb.className=figure+"re";
    }else{
      var objFigure=document.all(figure);
      for(var i=0;i<objFigure.length;i++){
        if(objFigure[i].innerText!=numb.innerText){
          objFigure[i].className=figure+"re";
        }else{
          objFigure[i].className=figure+"ch";
        }
      }
    }
  }
  //alert("111");
  wagerFree();
}

function choosePokerTog(numb){
  var figureChosen=document.all("figure");
  var arrFigure=new Array("spade","heart","club","diamond");
  var modeMark=parent.document.all("modechosenmark");
  for(var i=0;i<arrFigure.length;i++){
    if(figureChosen.value==arrFigure[i]){
      var figType = document.all(arrFigure[i]);
      for(var j=0;j<figType.length;j++){
        if(numb.value=="all"){
          figType[j].className=arrFigure[i]+"ch";
        }
        if(numb.value=="major"){
           if(j>=6){
             figType[j].className=arrFigure[i]+"ch";
           }else{
             figType[j].className=arrFigure[i]+"re";
           }
         }
        if(numb.value=="minor"){
           if(j<6){
             figType[j].className=arrFigure[i]+"ch";
           }else{
             figType[j].className=arrFigure[i]+"re";
           }
         }
         if(numb.value=="odd"){
           if(j%2==0){
             figType[j].className=arrFigure[i]+"ch";
           }else{
             figType[j].className=arrFigure[i]+"re";
           }
         }
         if(numb.value=="even"){
           if(j%2==1){
             figType[j].className=arrFigure[i]+"ch";
           }else{
             figType[j].className=arrFigure[i]+"re";
           }
         }
         if(numb.value=="empty"){
           figType[j].className=arrFigure[i]+"re";
         }
       }
    }
  }
  wagerFree();
}
function wagerFree(){
  var typeMark=parent.document.all("typechosenmark");
 // alert("typeMark="+typeMark.value);
  wagerFreeGetText();
  if(typeMark.value!="compound"){
    wagerFreeCalcu();
  }else{
    wagerComFreeCalcu();
  }
}
//计算点击时，各个花色的poker的数量
function wagerFreeGetText(){
  var arrFigType=new Array("spade","heart","club","diamond");
  for(var i=0;i<arrFigType.length;i++)
  {
    var arrPokerForFig=document.all(arrFigType[i]);//get 13 pokers for sapde
    var str=arrFigType[i]+"ch";//得到spade的str为spadech
    arrQuanForFig[i]=0;//spade的quantity为0
    arrTextForFig[i]="";//spade的text为空
    for(var j=0;j<arrPokerForFig.length;j++){//循环spade的13张
	  //alert(arrPokerForFig[j].className+",str="+str);
	  if(arrPokerForFig[j].className==str)//if each poker get the className as "spadech".
	  {
          arrQuanForFig[i]=arrQuanForFig[i]+1;// add 1 to spade's quantity
          if(arrTextForFig[i]!=""){
            arrTextForFig[i]=arrTextForFig[i]+",";
          }
          arrTextForFig[i]=arrTextForFig[i]+arrPokerForFig[j].innerText;//spade's text string add each poker's text.
	  }
    }
  }
}
function wagerFreeCalcu(){
  var totalQuan=0;
  var typeMark=parent.document.all("typechosenmark");
  var arrType=new Array("one","two","three","four");
  for (var i=0;i<arrType.length;i++){
    var strtyp=arrType[i];
    if(typeMark.value==strtyp){
     totalQuan=calcuWagQuan(strtyp,arrQuanForFig[0],arrQuanForFig[1],arrQuanForFig[2],arrQuanForFig[3]);
    }
  }
  
  document.all("showsinglewager").innerText = totalQuan;
  document.all("showsinglemoney").innerText = numbertomoney(totalQuan*2);
}


function wagerPlus(){
  var typeMark=parent.document.all("typechosenmark");
  var modeMark=parent.document.all("modechosenmark");
  var arrType=new Array("one","two","three","four");
  var arrTypeNum=new Array("1","2","3","4");
  if(parseInt(document.all("showsinglewager").innerText)<=0){
    for(var i=0;i<arrType.length;i++){
      var strty=arrType[i];
      if(typeMark.value==strty){
        var st="至少需要选出"+arrTypeNum[i]+"种花色";
		alert(st);
		return;
	  }
    }
  }
  
  if(modeMark.value=="multi"){
    if(arrQuanForFig[0]<=1 && arrQuanForFig[1]<=1 && arrQuanForFig[2]<=1 && arrQuanForFig[3]<=1){
      alert("复式投注，其中一个花色至少选两个号码");
      return;
    }
  }
  for(var i=0;i<arrTextForFig.length;i++){
    if(arrTextForFig[i]==""){
      arrTextForFig[i]="=";
    }

  }
  var textCombined=arrTextForFig[0]+"|"+arrTextForFig[1]+"|"+arrTextForFig[2]+"|"+arrTextForFig[3];
  var showSingleWagerText=document.all("showsinglewager").innerText;
  wagerPlusComShow(textCombined,showSingleWagerText);
  wagerPlusComTotal(parseInt(showSingleWagerText));
  var arrFig=new Array("spade","heart","club","diamond");
  wagerPlusComRest(arrFig,"0");
  clearRadio();
  getStrWager();
}
function wagerPlusGr()
{

  if(parseInt(document.all("showsinglewager").innerText)<=0){
	alert("最少选择一注号码");
	return;
  }
  var hiddenTextCombinGr=document.all("textcomgrhidden").value;
  document.all("textcomgrhidden").value="";
  var modeMark=parent.document.all("modechosenmark");
  if(modeMark.value!="gr24"){
    var arrTextCombGr = hiddenTextCombinGr.split("$");
    for(var i=0;i<arrTextCombGr.length;i++){
       wagerPlusComShow(arrTextCombGr[i],"1");
    }
  }else{
    var strGr=document.all("showsinglewager").innerText;
    wagerPlusComShow(hiddenTextCombinGr,strGr);
  }
  
  wagerPlusComTotal(parseInt(document.all("showsinglewager").innerText));
  var arrFig=new Array("rowone","rowtwo","rowthree","rowfour");
  wagerPlusComRest(arrFig,"voidre");
  getStrWager();
}

function wagerPlusComShow(text,wagerNumText){//任选添加用,往pokertext中添加号码
  var pokerText = document.all("pokertext");
  var wagerEachNumText=document.all("wagereachnumtext");
  
  pokerText.length = pokerText.length+1;
  wagerEachNumText.length=wagerEachNumText.length+1;
  
  pokerText[pokerText.length-1].value = text;
  pokerText[pokerText.length-1].text = text;
  
  wagerEachNumText[wagerEachNumText.length-1].value=parseInt(wagerNumText);
  wagerEachNumText[wagerEachNumText.length-1].text=wagerNumText+"注";
  
}
function wagerPlusComTotal(singWagerNum){//组选与任选（添加）共用,改变“总注数”“总金额”中的值   
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
      if(reStr!="voidre"){
        figType[j].className=arrFigureTemp[i]+"re";
      }else{
        figType[j].className="voidre";
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

function calcuWagQuan(type,Q1,Q2,Q3,Q4)
{
  if(type=="four"){
    return Q1*Q2*Q3*Q4;
  }
  if(type=="three"){
    return Q1*Q2*Q3 + Q1*Q2*Q4 + Q1*Q3*Q4 + Q2*Q3*Q4;
  }
  if(type=="two"){
    return Q1*Q2 + Q1*Q3 + Q1*Q4 + Q2*Q3 + Q2*Q4 + Q3*Q4;
  }
  if(type=="one"){
    return Q1+Q2+Q3+Q4;
  }
	
}

function getStrWager()
{
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

function randSelect(num)//组选与任选（机选）共用
{
  var modeMark=parent.document.all("modechosenmark");
  var typeMark=parent.document.all("typechosenmark");
	for(var i=0;i<num;i++)
	{
		var textRanTemp;
		if(typeMark.value!="fourgr"){
		  textRanTemp = randSelectOneCom(typeMark,modeMark);
		}else{
		  textRanTemp = randSelectOneGro(typeMark,modeMark);
		}
		wagerPlusComShow(textRanTemp,"1");
	}
	wagerPlusComTotal(num);
	
    getStrWager();
}
function randSelectOneGro(typeMarkTemp,modeMarkTemp){
  var arrRanNumFig=new Array();
  
  if(modeMarkTemp.value=="gr24"){
    arrRanNumFig[0]=generRandomNum(1,13);
    arrRanNumFig[1]=generRandomNum(1,13);
    while(arrRanNumFig[1]==arrRanNumFig[0]){
      arrRanNumFig[1]=generRandomNum(1,13);
    }
    arrRanNumFig[2]=generRandomNum(1,13);
    while(arrRanNumFig[2]==arrRanNumFig[0] || arrRanNumFig[2]==arrRanNumFig[1]){
      arrRanNumFig[2]=generRandomNum(1,13);
    }
    arrRanNumFig[3]=generRandomNum(1,13);
    while(arrRanNumFig[3]==arrRanNumFig[0] || arrRanNumFig[3]==arrRanNumFig[1]||arrRanNumFig[3]==arrRanNumFig[2]){
      arrRanNumFig[3]=generRandomNum(1,13);
    }
  }
  if(modeMarkTemp.value=="gr12"){
    arrRanNumFig[0]=generRandomNum(1,13);
    arrRanNumFig[1]=generRandomNum(1,13);
    while(arrRanNumFig[1]==arrRanNumFig[0]){
      arrRanNumFig[1]=generRandomNum(1,13);
    }
    arrRanNumFig[2]=generRandomNum(1,13);
    while(arrRanNumFig[2]==arrRanNumFig[0] || arrRanNumFig[2]==arrRanNumFig[1]){
      arrRanNumFig[2]=generRandomNum(1,13);
    }
    arrRanNumFig[3]=arrRanNumFig[2];
  }
  if(modeMarkTemp.value=="gr6"){
    arrRanNumFig[0]=generRandomNum(1,13);
    arrRanNumFig[2]=generRandomNum(1,13);
    while(arrRanNumFig[2]==arrRanNumFig[0]){
      arrRanNumFig[2]=generRandomNum(1,13);
    }
    arrRanNumFig[1]=arrRanNumFig[0];
    arrRanNumFig[3]=arrRanNumFig[2];
  }
  if(modeMarkTemp.value=="gr4"){
    arrRanNumFig[0]=generRandomNum(1,13);
    arrRanNumFig[1]=generRandomNum(1,13);
    while(arrRanNumFig[1]==arrRanNumFig[0]){
      arrRanNumFig[1]=generRandomNum(1,13);
    }
    arrRanNumFig[2]=arrRanNumFig[1];
    arrRanNumFig[3]=arrRanNumFig[1];
  }

  var ranNumFig = arrRanNumFig[0] + "," + arrRanNumFig[1] + "," + arrRanNumFig[2] + "," + arrRanNumFig[3];
  ranNumFig = ranNumFig.replace(/11/g,"J").replace(/12/g,"Q").replace(/13/g,"K").replace(/1/g,"A").replace(/A0/g,"10");
  return ranNumFig;
}
function randSelectOneCom(typeMarkTemp,modeMarkTemp)
{
  var arrRanNumFig=new Array();

  if(typeMarkTemp.value=="one"){
    var locate1=generRandomNum(0,3);
    for(var i=0;i<4;i++){
      if(i==locate1){
        arrRanNumFig[i]=generRandomNum(1,13);
      }else{
        arrRanNumFig[i]="=";
      }
    }
  }
  if(typeMarkTemp.value=="two"){
    var locate1=generRandomNum(0,3);
    var locate2=generRandomNum(0,3);
    while(locate2==locate1){
      locate2=generRandomNum(0,3);
    }  
    for(var i=0;i<4;i++){
      if(i==locate1){
        arrRanNumFig[i]=generRandomNum(1,13);
      }else if(i==locate2){
        arrRanNumFig[i]=generRandomNum(1,13);
      }else{
        arrRanNumFig[i]="=";
      }
    }
  }
  if(typeMarkTemp.value=="three"){
    var locate1=generRandomNum(0,3);
    for(var i=0;i<4;i++){
      if(i!=locate1){
        arrRanNumFig[i]=generRandomNum(1,13);
      }else{
        arrRanNumFig[i]="=";
      }
    }
  }
  if(typeMarkTemp.value=="four"){
    for(var i=0;i<4;i++){
      arrRanNumFig[i]=generRandomNum(1,13);
    }
  }
  var ranNumFig = arrRanNumFig[0] + "|" + arrRanNumFig[1] + "|" + arrRanNumFig[2] + "|" + arrRanNumFig[3];
  ranNumFig = ranNumFig.replace(/11/g,"J").replace(/12/g,"Q").replace(/13/g,"K").replace(/1/g,"A").replace(/A0/g,"10");
  return ranNumFig;
}
function clearRadio(){
  var radioMethod=document.all("method");
  for (var i=0;i<radioMethod.length;i++){
    radioMethod[i].checked=false;
  }
}
function deleteAll(){
  var arrFigure=new Array("spade","heart","club","diamond");
  for(var i=0;i<arrFigure.length;i++){
    var figType = document.all(arrFigure[i]);
    for(var j=0;j<figType.length;j++){
       figType[j].className=arrFigure[i]+"re";
    }
  }
  wagerFree();
}
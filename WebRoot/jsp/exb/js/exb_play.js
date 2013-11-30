var objFrameTouzhu;
var objGetIssueNumber;
var objIssueType;

//从lauplay中摘取
 
function playLoad(){
	//alert("ssc_play.js ");
    objFrameTouzhu = document.frames['frameTouzhu'];
	if(objFrameTouzhu.document.readyState=='complete'){
      getTopIssNum();
	}
}

function getTopIssNum()
{
	//alert("hidMarkiss="+document.all("hidmarkiss").value);
    objFrameTouzhu.document.all("markiss").value=document.all("hidmarkiss").value;
	//alert(document.all("hidmarkiss").value);
    objFrameTouzhu.document.all("getissuenumber").value=document.all("hidmulissvalue").value;
    objFrameTouzhu.document.all("issuetype").value=document.all("hidissuetype").value;
    objFrameTouzhu.document.all("selling").value=document.all("hidselling").value;
    setTimeout("getTopIssNum()",1000);
	return;
}



/*
function playLoad(){
    objFrameTouzhu = document.frames['frameTouzhu'];
	if(objFrameTouzhu.document.readyState=='complete'){
	  tranValue();
	  enableComponent();
      getTopIssNum();
	}
}

function getTopIssNum()
{
    objFrameTouzhu.document.all("markiss").value=document.all("hidmarkiss").value;
    objFrameTouzhu.document.all("getissuenumber").value=document.all("hidmulissvalue").value;
    objFrameTouzhu.document.all("issuetype").value=document.all("hidissuetype").value;
    objFrameTouzhu.document.all("selling").value=document.all("hidselling").value;
    setTimeout("getTopIssNum()",1000);
	return;
}
*/
function tranValue(){
    var typePar=parent.document.all("typechosenmark");
    var modePar=parent.document.all("modechosenmark");
    var typePlay=objFrameTouzhu.document.all("playtype");
    var modePlay=objFrameTouzhu.document.all("playmode");
 	 
    typePlay.value=typePar.value;
    modePlay.value=modePar.value;
      var arrPlayTypeStr=new Array("wuxingTx","wuxing","sixing","sanxingZhixuan","sanxingZuxuan","erxingZhixuan","erxingZuxuan","yixing","zuhe","daxiaoDs");
	  var arrPlayTypeStrChin=new Array("五星通选","五星","四星","三星直选","三星组选","二星直选","二星组选","一星","组合","大小单双");
	  for(var i=0;i<arrPlayTypeStr.length;i++){
	    if(typePlay.value==arrPlayTypeStr[i]){
	      typePlay.value=arrPlayTypeStrChin[i];
	    }
	  }

	  var arrplayModeStr=new Array("multi","sing","gr4","gr6","gr12","gr24","total","diff");
	  var arrplayModeStrChin=new Array("复式","单式","组4","组6","组12","组24","和值","跨度");
	  for(var i=0;i<arrplayModeStr.length;i++){
	    if(modePlay.value==arrplayModeStr[i]){
	      modePlay.value=arrplayModeStrChin[i];
	    }
	  }
	  
}

function enableComponent(){
  var typeMark=parent.document.all("typechosenmark");
  var modeMark=parent.document.all("modechosenmark");
  if(modeMark.value=="multi"){
    objFrameTouzhu.document.all("tdchosentog").disabled=false;
    objFrameTouzhu.document.all("randone").disabled=true;
    objFrameTouzhu.document.all("randfive").disabled=true;
    objFrameTouzhu.document.all("randten").disabled=true;
  }
  if(modeMark.value=="sing"){
    objFrameTouzhu.document.all("tdchosentog").disabled=true;
  }
  if(typeMark.value=="fourgr"){
    //var arrFigureSele=objFrameTouzhu.document.all("figure");
    if(modeMark.value=="gr4"){
      objFrameTouzhu.document.all("rowtwotdword").innerHTML="<font color=red>第二排为重号选择区</font>";
      objFrameTouzhu.document.all("rowthreetd").disabled=true;
      objFrameTouzhu.document.all("rowfourtd").disabled=true;
      //arrFigureSele.options.remove(2);
    }
    if(modeMark.value=="gr6"){
      objFrameTouzhu.document.all("rowonetdword").innerHTML="<font color=red>第一排为重号选择区</font>";
      objFrameTouzhu.document.all("rowtwotdword").innerHTML="<font color=red>第二排为重号选择区</font>";
      objFrameTouzhu.document.all("rowthreetd").disabled=true;
      objFrameTouzhu.document.all("rowfourtd").disabled=true;
      //arrFigureSele.options.remove(2);
    }
    if(modeMark.value=="gr12"){
      objFrameTouzhu.document.all("rowtwotdword").innerHTML="第二排";
      objFrameTouzhu.document.all("rowthreetdword").innerHTML="<font color=red>第三排为重号选择区</font>";
      objFrameTouzhu.document.all("rowfourtd").disabled=true;
    }
    if(modeMark.value=="gr24"){
    objFrameTouzhu.document.all("rowfourtdword").innerHTML="第四排";
      //objFrameTouzhu.document.all("rowtwotd").disabled=true;
     //objFrameTouzhu.document.all("rowthreetd").disabled=true;
      //objFrameTouzhu.document.all("rowfourtd").disabled=true;
      //arrFigureSele.options.remove(2);
      //arrFigureSele.options.remove(1);
    }
    
  }
}

var framePlay;
var objGetIssueNumber;
var objIssueType;

function playLoad(){
    framePlay = document.frames['fraplay'];
	if(framePlay.document.readyState=='complete'){
	  tranValue();
	  enableComponent();
      getTopIssNum();
	}
}

function getTopIssNum()
{
    framePlay.document.all("markiss").value=document.all("hidmarkiss").value;
    framePlay.document.all("getissuenumber").value=document.all("hidmulissvalue").value;
    framePlay.document.all("issuetype").value=document.all("hidissuetype").value;
    framePlay.document.all("selling").value=document.all("hidselling").value;
    setTimeout("getTopIssNum()",1000);
	return;
}
function tranValue(){
    var typePar=parent.document.all("typechosenmark");
    var modePar=parent.document.all("modechosenmark");
    var typePlay=framePlay.document.all("playtype");
    var modePlay=framePlay.document.all("playmode");
    typePlay.value=typePar.value;
    modePlay.value=modePar.value;
      var arrPlayTypeStr=new Array("one","two","three","four","fourgr","grmulti","grdantuo","compound");
	  var arrPlayTypeStrChin=new Array("任选一","任选二","任选三","选四直选","组选单式","组选复式","组选胆拖","组合投注");
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
    framePlay.document.all("tdchosentog").disabled=false;
    framePlay.document.all("randone").disabled=true;
    framePlay.document.all("randfive").disabled=true;
    framePlay.document.all("randten").disabled=true;
  }
  if(modeMark.value=="sing"){
    framePlay.document.all("tdchosentog").disabled=true;
  }
  if(typeMark.value=="fourgr"){
    //var arrFigureSele=framePlay.document.all("figure");
    if(modeMark.value=="gr4"){
      framePlay.document.all("rowtwotdword").innerHTML="<font color=red>第二排为重号选择区</font>";
      framePlay.document.all("rowthreetd").disabled=true;
      framePlay.document.all("rowfourtd").disabled=true;
      //arrFigureSele.options.remove(2);
    }
    if(modeMark.value=="gr6"){
      framePlay.document.all("rowonetdword").innerHTML="<font color=red>第一排为重号选择区</font>";
      framePlay.document.all("rowtwotdword").innerHTML="<font color=red>第二排为重号选择区</font>";
      framePlay.document.all("rowthreetd").disabled=true;
      framePlay.document.all("rowfourtd").disabled=true;
      //arrFigureSele.options.remove(2);
    }
    if(modeMark.value=="gr12"){
      framePlay.document.all("rowtwotdword").innerHTML="第二排";
      framePlay.document.all("rowthreetdword").innerHTML="<font color=red>第三排为重号选择区</font>";
      framePlay.document.all("rowfourtd").disabled=true;
    }
    if(modeMark.value=="gr24"){
    framePlay.document.all("rowfourtdword").innerHTML="第四排";
      //framePlay.document.all("rowtwotd").disabled=true;
     //framePlay.document.all("rowthreetd").disabled=true;
      //framePlay.document.all("rowfourtd").disabled=true;
      //arrFigureSele.options.remove(2);
      //arrFigureSele.options.remove(1);
    }
    
  }
}

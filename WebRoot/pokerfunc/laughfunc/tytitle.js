var fraPlay;
var fraMode;
function changeType(strMode,strPlay,type){
  fraPlay=parent.document.frames['fraplay'];
  fraMode=parent.document.frames['framodtit'];
  fraPlay.location.href="/lott/laugh/play/"+strPlay+".jsp";
  fraMode.location.href="/lott/laugh/mode/"+strMode+".jsp";
  var objCom=document.all("com");
  var objGrSix=document.all("grsix");
  var objGrThr=document.all("grthr");
  //var objGrSin=document.all("grsin");
  var objFrOn=document.all("fron");
  var objFrTw=document.all("frtw");
  var arrBut=new Array(objCom,objGrSix,objGrThr,objFrOn,objFrTw);
  comExch(arrBut,type,"typechosen","typeready");
  parent.document.frames['framulissue'].location.href="/servlet/servmulissue?play=laugh";
}

function chMode(strPlay){
  fraPlay=parent.document.frames['fraplay'];
  fraPlay.location.href="/lott/laugh/play/"+strPlay+".jsp";  
  parent.document.frames['framulissue'].location.href="/servlet/servmulissue?play=laugh";
}

function comExch(arr,obj,strCho,strRe){
  for(var i=0;i<arr.length;i++){
    arr[i].className=strRe;
    arr[i].style.color="black";
  }
  obj.className=strCho;
  obj.style.color="white";
}
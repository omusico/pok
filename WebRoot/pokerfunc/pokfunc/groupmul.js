var textForNum="";
var strWag="";

function grMulCho(numb){
  if(numb.className=="voidre"){
    numb.className="voidch";
  }else{
    numb.className="voidre";
  }

  var arrGrMulRow=document.all("grmulrow");
  textForNum="";
  for(var j=0;j<arrGrMulRow.length;j++){
    if(arrGrMulRow[j].className=="voidch"){
      if(textForNum!=""){
        textForNum=textForNum+",";
      }
      textForNum=textForNum+arrGrMulRow[j].innerText;
    }
  }

  wagNumCal();
  
}

function wagNumCal(){
  var strPlayMode=document.all("playmode").value;
  if(strPlayMode=="组4"){
	  strWag=getWagQuanGrFour();
  }
  if(strPlayMode=="组6"){
	  strWag=getWagQuanGrSix();
  }
  if(strPlayMode=="组12"){
	  strWag=getWagQuanGrTwel();
  }
  if(strPlayMode=="组24"){
	  strWag=getWagQuanGrTwenFour();
  }
  var totalQuan=0;
  if(strWag==""){
    totalQuan=0;
  }else{
    var arrStrWag=strWag.split("$");
    totalQuan=arrStrWag.length;
  }
  document.all("showsinglewager").innerText = totalQuan;
  document.all("showsinglemoney").innerText = numbertomoney(totalQuan*2);
}
/*
function getParQuanGrFour(num){
  var partot=new Array(14);
  partot[0]=0;
  partot[1]=0;
  partot[2]=2;
  partot[3]=6;
  partot[4]=12;
  partot[5]=20;
  partot[6]=30;
  partot[7]=42;
  partot[8]=56;
  partot[9]=72;
  partot[10]=90;
  partot[11]=110;
  partot[12]=132;
  partot[13]=156;
  
  var numValue=partot[num];
  return numValue;
}

function getParQuanGrSix(num){
  var partot=new Array(14);
  partot[0]=0;
  partot[1]=0;
  partot[2]=1;
  partot[3]=3;
  partot[4]=6;
  partot[5]=10;
  partot[6]=15;
  partot[7]=21;
  partot[8]=28;
  partot[9]=36;
  partot[10]=45;
  partot[11]=55;
  partot[12]=66;
  partot[13]=78;
  
  var numValue=partot[num];
  return numValue;
}

function getParQuanGrTwel(num){
  var partot=new Array(14);
  partot[0]=0;
  partot[1]=0;
  partot[2]=0;
  partot[3]=3;
  partot[4]=12;
  partot[5]=30;
  partot[6]=60;
  partot[7]=105;
  partot[8]=168;
  partot[9]=252;
  partot[10]=360;
  partot[11]=495;
  partot[12]=660;
  partot[13]=858;
  
  var numValue=partot[num];
  return numValue;
}

function getParQuanGrTwenFour(num){
  var partot=new Array(14);
  partot[0]=0;
  partot[1]=0;
  partot[2]=0;
  partot[3]=0;
  partot[4]=1;
  partot[5]=5;
  partot[6]=15;
  partot[7]=35;
  partot[8]=70;
  partot[9]=126;
  partot[10]=210;
  partot[11]=330;
  partot[12]=495;
  partot[13]=715;
  
  var numValue=partot[num];
  return numValue;
}
*/
function wagerPlus(){
  var strPlayMode=document.all("playmode").value;
  var arrText = textForNum.split(",");
  if(strPlayMode=="组4"){
    if(arrText.length<3){
      alert("须选择3个或3个以上的号码进行投注");
      return;
    }
  }
  if(strPlayMode=="组6"){ 
    if(arrText.length<3){
      alert("须选择3个或3个以上的号码进行投注");
      return;
    }
  }
  if(strPlayMode=="组12"){ 
    if(arrText.length<4){
      alert("须选择4个或4个以上的号码进行投注");
      return;
    }
  }
  if(strPlayMode=="组24"){ 
    if(arrText.length<5){
      alert("须选择5个或5个以上的号码进行投注");
      return;
    }
  }

  var showSingleWagerText=document.all("showsinglewager").innerText;
  wagerPlusComShow(textForNum,strWag,strWag.split("$").length);
  wagerPlusComTotal(parseInt(showSingleWagerText));
  wagerPlusComRest();
  getStrWager();
}

function wagerPlusComRest(){
  document.all("showsinglewager").innerText = 0;
  document.all("showsinglemoney").innerText = numbertomoney(0);
  var objGrMulRow = document.all("grmulrow");
  for(var j=0;j<objGrMulRow.length;j++){
    objGrMulRow[j].className="voidre";
  }
}


function getWagQuanGrFour(){
  var arrTextForNum = textForNum.split(",");
  var arrLen=arrTextForNum.length;
  var totalQuan=0;
  var textCombinGr="";
  for(var i=0;i<arrLen;i++){
    var numOne=arrTextForNum[i];
    var numTwo="";
    for(var j=0;j<arrLen;j++){
      if(arrTextForNum[j]!=arrTextForNum[i]){
        numTwo=arrTextForNum[j];
        var textTemp=numOne+","+numOne+","+numOne+","+numTwo;
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
  return textCombinGr;
}


function getWagQuanGrSix(){
  var arrTextForNum = textForNum.split(",");
  var arrLen=arrTextForNum.length;
  var totalQuan=0;
  var textCombinGr="";
  for(var i=0;i<arrLen;i++){
    var numOne=arrTextForNum[i];
    var numTwo="";
    for(var j=0;j<arrLen;j++){
      if(arrTextForNum[j]!=arrTextForNum[i]){
        numTwo=arrTextForNum[j];
        var textTemp=numOne+","+numOne+","+numTwo+","+numTwo;
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
  return textCombinGr;
}


function getWagQuanGrTwel(){
  var arrTextForNum = textForNum.split(",");
  var arrLen=arrTextForNum.length;
  var totalQuan=0;
  var textCombinGr="";
  for(var i=0;i<arrLen;i++){
    var numOne=arrTextForNum[i];
    var numTwo="";
    for(var j=0;j<arrLen;j++){
      if(arrTextForNum[j]!=arrTextForNum[i]){
        numTwo=arrTextForNum[j];
        var numThr="";
        for(var k=0;k<arrLen;k++){
          if(arrTextForNum[k]!=arrTextForNum[i] && arrTextForNum[k]!=arrTextForNum[j]){
            numThr=arrTextForNum[k];
	        var textTemp=numOne+","+numOne+","+numTwo+","+numThr;
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
    }
  }
  return textCombinGr;
}

function getWagQuanGrTwenFour(){
  var arrTextForNum = textForNum.split(",");
  var arrLen=arrTextForNum.length;
  var totalQuan=0;
  var textCombinGr="";
  for(var i=0;i<arrLen;i++){
    var numOne=arrTextForNum[i];
    var numTwo="";
    for(var j=0;j<arrLen;j++){
      if(arrTextForNum[j]!=arrTextForNum[i]){
        numTwo=arrTextForNum[j];
        var numThr="";
        for(var k=0;k<arrLen;k++){
          if(arrTextForNum[k]!=arrTextForNum[i] && arrTextForNum[k]!=arrTextForNum[j]){
            numThr=arrTextForNum[k];
            var numFour="";
            for(var n=0;n<arrLen;n++){
              if(arrTextForNum[n]!=arrTextForNum[i] && arrTextForNum[n]!=arrTextForNum[j] && arrTextForNum[n]!=arrTextForNum[k]){
		        numFour=arrTextForNum[n];
		        var textTemp=numOne+","+numTwo+","+numThr+","+numFour;
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
	    }
      }
    }
  }
  return textCombinGr;
}

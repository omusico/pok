var textForDan="";
var textForTuo="";
var strWag="";

function grDanCho(numb){
  var strPlayMode=document.all("playmode").value;
  var arrGrDanRow=document.all("grdanrow");
  var arrTextForTuo=textForTuo.split(",");
  
  if(numb.className=="voidre"){
    for(var i=0;i<arrTextForTuo.length;i++){
      if(numb.innerText==arrTextForTuo[i]){
        alert("���������벻��һ��");
        return;
      }
    }
    var quan=0;
	for(var j=0;j<arrGrDanRow.length;j++){
	  if(arrGrDanRow[j].className=="voidch"){
	    quan++;
	  }
	}
	if(strPlayMode=="��4"){
	  if(quan<1){
	    numb.className="voidch";
	  }else{
	    alert("���ѡȡ1��������Ϊ����");
	    return;
	  }
    }
    if(strPlayMode=="��6"){
  	  if(quan<1){
	    numb.className="voidch";
	  }else{
	    alert("���ѡȡ1��������Ϊ����");
	    return;
	  }
    }
    if(strPlayMode=="��12"){
	  if(quan<2){
	    numb.className="voidch";
	  }else{
	    alert("���ѡȡ2��������Ϊ����");
	    return;
	  }
    }
    if(strPlayMode=="��24"){
  	  if(quan<3){
	    numb.className="voidch";
	  }else{
	    alert("���ѡȡ3��������Ϊ����");
	    return;
	  }
    }
  }else{
    numb.className="voidre";
  }
  
  textForDan="";
  for(var j=0;j<arrGrDanRow.length;j++){
    if(arrGrDanRow[j].className=="voidch"){
      if(textForDan!=""){
        textForDan=textForDan+",";
      }
      textForDan=textForDan+arrGrDanRow[j].innerText;
    }
  }
  wagNumCal();
}

function grTuoCho(numb){
  var arrTextForDan=textForDan.split(",");
  var arrGrTuoRow=document.all("grtuorow");
  if(numb.className=="voidre"){
    for(var i=0;i<arrTextForDan.length;i++){
      if(numb.innerText==arrTextForDan[i]){
        alert("���������벻��һ��");
        return;
      }
    }
    numb.className="voidch"; 
  }else{
    numb.className="voidre";
  }
  textForTuo="";
  for(var j=0;j<arrGrTuoRow.length;j++){
    if(arrGrTuoRow[j].className=="voidch"){
      if(textForTuo!=""){
        textForTuo=textForTuo+",";
      }
      textForTuo=textForTuo+arrGrTuoRow[j].innerText;
    }
  }
  wagNumCal();
}


function wagNumCal(){
  var strPlayMode=document.all("playmode").value;
  if(strPlayMode=="��4"){
    strWag=getStrWagGrFour();
  }
  if(strPlayMode=="��6"){
	strWag=getStrWagGrSix();
  }
  if(strPlayMode=="��12"){
	strWag=getStrWagGrTwel();
  }
  if(strPlayMode=="��24"){
	strWag=getStrWagGrTwenFour(); 
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
  
  //document.all("test").innerHTML=strWag;
}

function getStrWagGrFour(){  
  var textForDanTuo=textForDan+","+textForTuo;
  var arrDanTuo=textForDanTuo.split(",");
  var intDanTuoLen=arrDanTuo.length;
  var textCombinGr="";
  if(textForDan=="" || textForTuo=="" || intDanTuoLen<2){
    textCombinGr="";
  }else{
	  for(var i=0;i<intDanTuoLen;i++){
	    var numOne=arrDanTuo[i];
	    var numTwo="";
	    for(var j=0;j<intDanTuoLen;j++){
	      if(arrDanTuo[j]!=arrDanTuo[i]){
	        numTwo=arrDanTuo[j];
	        var textTemp=numOne+","+numOne+","+numOne+","+numTwo;
	        textTemp = pokGrSort(textTemp);
	        var hasDan=isDanInText(textForDan,textTemp);
	        if(hasDan=="true"){
	          textCombinGr=textConn(textCombinGr,textTemp);
			}
	      }
	    }
	  }
  }
  return textCombinGr;
}


function getStrWagGrSix(){
  var textForDanTuo=textForDan+","+textForTuo;
  var arrDanTuo=textForDanTuo.split(",");
  var intDanTuoLen=arrDanTuo.length;
  var textCombinGr="";
  if(textForDan=="" || textForTuo=="" || intDanTuoLen<2){
    textCombinGr="";
  }else{
	  for(var i=0;i<intDanTuoLen;i++){
	    var numOne=arrDanTuo[i];
	    var numTwo="";
	    for(var j=0;j<intDanTuoLen;j++){
	      if(arrDanTuo[j]!=arrDanTuo[i]){
	        numTwo=arrDanTuo[j];
	        var textTemp=numOne+","+numOne+","+numTwo+","+numTwo;
	        textTemp = pokGrSort(textTemp);
	        var hasDan=isDanInText(textForDan,textTemp);
	        if(hasDan=="true"){
	          textCombinGr=textConn(textCombinGr,textTemp);
			}
	      }
	    }
	  }
  }
  return textCombinGr;
}

function getStrWagGrTwel(){
  var textForDanTuo=textForDan+","+textForTuo;
  var arrDanTuo=textForDanTuo.split(",");
  var intDanTuoLen=arrDanTuo.length;
  var textCombinGr="";
  if(textForDan=="" || textForTuo=="" || intDanTuoLen<3){
    textCombinGr="";
  }else{
	  for(var i=0;i<intDanTuoLen;i++){
	    var numOne=arrDanTuo[i];
	    var numTwo="";
	    for(var j=0;j<intDanTuoLen;j++){
	      if(arrDanTuo[j]!=arrDanTuo[i]){
	        numTwo=arrDanTuo[j];
	        var numThr="";
	        for (var k=0;k<intDanTuoLen;k++){
	          if(arrDanTuo[k]!=arrDanTuo[i] && arrDanTuo[k]!=arrDanTuo[j]){
	            numThr=arrDanTuo[k];
	            var textTemp=numOne+","+numOne+","+numTwo+","+numThr;
	            textTemp = pokGrSort(textTemp);
		        var hasDan=isDanInText(textForDan,textTemp);
		        if(hasDan=="true"){
		          textCombinGr=textConn(textCombinGr,textTemp);
				}
	          }
	        }
	      }
	    }
	  }
  }
  return textCombinGr;
}

function getStrWagGrTwenFour(){
  var textForDanTuo=textForDan+","+textForTuo;
  var arrDanTuo=textForDanTuo.split(",");
  var intDanTuoLen=arrDanTuo.length;
  var textCombinGr="";
  if(textForDan=="" || textForTuo=="" || intDanTuoLen<4){
    textCombinGr="";
  }else{
	  for(var i=0;i<intDanTuoLen;i++){
	    var numOne=arrDanTuo[i];
	    var numTwo="";
	    for(var j=0;j<intDanTuoLen;j++){
	      if(arrDanTuo[j]!=arrDanTuo[i]){
	        numTwo=arrDanTuo[j];
	        var numThr="";
	        for (var k=0;k<intDanTuoLen;k++){
	          if(arrDanTuo[k]!=arrDanTuo[i] && arrDanTuo[k]!=arrDanTuo[j]){
	            numThr=arrDanTuo[k];
	            var numFour="";
	            for (var n=0;n<intDanTuoLen;n++){
	              if(arrDanTuo[n]!=arrDanTuo[i] && arrDanTuo[n]!=arrDanTuo[j] && arrDanTuo[n]!=arrDanTuo[k]){
	                numFour=arrDanTuo[n];
	                var textTemp=numOne+","+numTwo+","+numThr+","+numFour;
	                textTemp = pokGrSort(textTemp);
			        var hasDan=isDanInText(textForDan,textTemp);
			        if(hasDan=="true"){
			          textCombinGr=textConn(textCombinGr,textTemp);
					}
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

function pokGrSort(text){
  var retText="";
  retText = SortNum(text.replace(/A/g,"1").replace(/J/g,"11").replace(/Q/g,"12").replace(/K/g,"13")).join(",");
  retText = retText.replace(/11/g,"J").replace(/12/g,"Q").replace(/13/g,"K").replace(/1/g,"A").replace(/A0/g,"10");
  return retText;
}

function isDanInText(danText,text){
  var arrDanText=danText.split(",");
  var isDan="true";
  if(danText==""){
    isDan="false";
  }else{
    for(var i=0;i<arrDanText.length;i++){
      var strDan=arrDanText[i];
      if(text.indexOf(strDan)==-1){
        isDan="false";
      }
    }
  }
  return isDan;
}

function textConn(textTot,textSing){
  var textTotTemp=textTot;
  if(textTotTemp.indexOf(textSing)==-1){
    if(textTotTemp!=""){
      textTotTemp = textTotTemp + "$";
    }
    textTotTemp = textTotTemp + textSing;
  }
  return textTotTemp;
}

function wagerPlus(){
  var strPlayMode=document.all("playmode").value;
  var arrTextForDan=textForDan.split(",");
  var arrTextForTuo=textForTuo.split(",");
  var intDanLen=arrTextForDan.length;
  var intTuoLen=arrTextForTuo.length;
  var totLen=intDanLen+intTuoLen;
  if(strPlayMode=="��4"){
    if(totLen<3){
      alert("������������֮�ͱ�����ڻ����3������");
      return;
    }
    
  }
  if(strPlayMode=="��6"){
    if(totLen<3){
      alert("������������֮�ͱ�����ڻ����3������");
      return;
    }
  }
  if(strPlayMode=="��12"){
    if(totLen<4){
      alert("������������֮�ͱ�����ڻ����4������");
      return;
    }
  }
  if(strPlayMode=="��24"){
    if(totLen<5){
      alert("������������֮�ͱ�����ڻ����5������");
      return;
    }
  }
  
  var showSingleWagerText=document.all("showsinglewager").innerText;
  var strText="��"+textForDan+"-"+"��"+textForTuo;
  wagerPlusComShow(strText,strWag,strWag.split("$").length);
  wagerPlusComTotal(parseInt(showSingleWagerText));
  wagerPlusComRest();
  getStrWager();
}

function wagerPlusComRest(){
  document.all("showsinglewager").innerText = 0;
  document.all("showsinglemoney").innerText = numbertomoney(0);
  var objGrDanRow = document.all("grdanrow");
  var objGrTuoRow = document.all("grtuorow");
  for(var j=0;j<objGrDanRow.length;j++){
    objGrDanRow[j].className="voidre";
  }
  for(var j=0;j<objGrTuoRow.length;j++){
    objGrTuoRow[j].className="voidre";
  }
  textForDan="";
  textForTuo="";
}


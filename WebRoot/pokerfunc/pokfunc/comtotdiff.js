var textForNumTot="";


function ChoTot(numb){
  if(numb.className=="totre"){
    numb.className="totch";
  }else{
    numb.className="totre";
  }
  var arrNumTot=document.all("numtot");
  textForNumTot="";
  for(var j=0;j<arrNumTot.length;j++){
    if(arrNumTot[j].className=="totch"){
       if(textForNumTot!=""){
         textForNumTot=textForNumTot+",";
       }
       textForNumTot=textForNumTot+arrNumTot[j].innerText;
    }
  }
}

function wagerPlus(){
  var strPlayMode=document.all("playmode").value;
  var arrRadioComp=document.all("comp");
  var compType;
  for (var i=0;i<arrRadioComp.length;i++){
    if(arrRadioComp[i].checked==true){
      compType=arrRadioComp[i].value;
    }
  }
  if(textForNumTot==""){
    alert("至少选择一个"+strPlayMode);
    return;
  }
  var arrTextForTot=textForNumTot.split(",");
  var intWagTot=0;
  for(var i=0;i<arrTextForTot.length;i++){
    var strWag="";
    var valSel=arrTextForTot[i];//如：3,4,5,6
    var strNumSeq="";
    if(strPlayMode=="和值"){
      strNumSeq=getParTot(valSel);
    }
    if(strPlayMode=="跨度"){
      strNumSeq=getParDiff(valSel);//如：A,4|2,5|3,6|...
    }
    var arrGro=strNumSeq.split("|");
    for(var j=0;j<arrGro.length;j++){
      var arrNumOfGro=arrGro[j].split(",");
      var strOneWag=getStrWag(arrNumOfGro[0],arrNumOfGro[1],compType);
      if(strWag!=""){
        strWag=strWag+"$";
      }
      strWag=strWag+strOneWag;
    }
    var strText= getCompDes(compType)+","+valSel;
    wagerPlusShow(strText,strWag,strWag.split("$").length);
    intWagTot=intWagTot+arrGro.length;
  }
  wagTotShow(intWagTot);
  wagerPlusRest();
  getStrWager();
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

function wagerEmpty(){
  document.all("pokertext").length = 0;
  document.all("wagereachnumtext").length = 0;
  document.all("showtotalwager").innerText=0;
  document.all("showtotalmoney").innerText=numbertomoney(0);
  getStrWager();
}

function wagerPlusShow(selText,selValue,wagerNumText){
  var pokerText = document.all("pokertext");
  var wagerEachNumText=document.all("wagereachnumtext");
  
  pokerText.length = pokerText.length+1;
  wagerEachNumText.length=wagerEachNumText.length+1;
  
  pokerText[pokerText.length-1].value = selValue;
  pokerText[pokerText.length-1].text =selText;
  
  wagerEachNumText[wagerEachNumText.length-1].value=wagerNumText;
  wagerEachNumText[wagerEachNumText.length-1].text=wagerNumText+"注";
}

function wagTotShow(singWagerNum){
  var totalWager=parseInt(document.all("showtotalwager").innerText);
  totalWager=totalWager+singWagerNum;
  document.all("showtotalwager").innerText=totalWager;
  document.all("showtotalmoney").innerText=numbertomoney(totalWager*2);
}

function wagerPlusRest(){
  textForNumTot="";
  var arrNumTot = document.all("numtot");
  for(var i=0;i<arrNumTot.length;i++){
    arrNumTot[i].className="totre";
  }
  
  var arrRadioComp=document.all("comp");
  for (var i=0;i<arrRadioComp.length;i++){
    if(arrRadioComp[i].value=="sh"){
      arrRadioComp[i].checked=true;
    }else{
      arrRadioComp[i].checked=false;
    }
  }

}

function getCompDes(compT){
  var strVal;
  if(compT=="sh"){
    strVal="黑桃-红桃";
  }
  if(compT=="sc"){
    strVal="黑桃-梅花";
  }
  if(compT=="sd"){
    strVal="黑桃-方块";
  }
  if(compT=="hc"){
    strVal="红桃-梅花";
  }
  if(compT=="hd"){
    strVal="红桃-方块";
  }
  if(compT=="cd"){
    strVal="梅花-方块";
  }
  return strVal;
}
function getStrWag(numFir,numSec,compT){
  var strVal="";
  if(compT=="sh"){
    strVal=numFir+"|"+numSec+"|"+"="+"|"+"=";
  }
  if(compT=="sc"){
    strVal=numFir+"|"+"="+"|"+numSec+"|"+"=";
  }
  if(compT=="sd"){
    strVal=numFir+"|"+"="+"|"+"="+"|"+numSec;
  }
  if(compT=="hc"){
    strVal="="+"|"+numFir+"|"+numSec+"|"+"=";
  }
  if(compT=="hd"){
    strVal="="+"|"+numFir+"|"+"="+"|"+numSec;
  }
  if(compT=="cd"){
    strVal="="+"|"+"="+"|"+numFir+"|"+numSec;
  }
  return strVal;
}
function getParTot(num){
  var partot=new Array(27);
  partot[0]="";
  partot[1]="";
  partot[2]="A,A";
  partot[3]="A,2|2,A";
  partot[4]="A,3|2,2|3,A";
  partot[5]="A,4|2,3|3,2|4,A";
  partot[6]="A,5|2,4|3,3|4,2|5,A";
  partot[7]="A,6|2,5|3,4|4,3|5,2|6,A";
  partot[8]="A,7|2,6|3,5|4,4|5,3|6,2|7,A";
  partot[9]="A,8|2,7|3,6|4,5|5,4|6,3|7,2|8,A";
  partot[10]="A,9|2,8|3,7|4,6|5,5|6,4|7,3|8,2|9,A";
  partot[11]="A,10|2,9|3,8|4,7|5,6|6,5|7,4|8,3|9,2|10,A";
  partot[12]="A,J|2,10|3,9|4,8|5,7|6,6|7,5|8,4|9,3|10,2|J,A";
  partot[13]="A,Q|2,J|3,10|4,9|5,8|6,7|7,6|8,5|9,4|10,3|J,2|Q,A";
  partot[14]="A,K|2,Q|3,J|4,10|5,9|6,8|7,7|8,6|9,5|10,4|J,3|Q,2|K,A";
  partot[15]="2,K|3,Q|4,J|5,10|6,9|7,8|8,7|9,6|10,5|J,4|Q,3|K,2";
  partot[16]="3,K|4,Q|5,J|6,10|7,9|8,8|9,7|10,6|J,5|Q,4|K,3";
  partot[17]="4,K|5,Q|6,J|7,10|8,9|9,8|10,7|J,6|Q,5|K,4";
  partot[18]="5,K|6,Q|7,J|8,10|9,9|10,8|J,7|Q,6|K,5";
  partot[19]="6,K|7,Q|8,J|9,10|10,9|J,8|Q,7|K,6";
  partot[20]="7,K|8,Q|9,J|10,10|J,9|Q,8|K,7";
  partot[21]="8,K|9,Q|10,J|J,10|Q,9|K,8";
  partot[22]="9,K|10,Q|J,J|Q,10|K,9";
  partot[23]="10,K|J,Q|Q,J|K,10";
  partot[24]="J,K|Q,Q|K,J";
  partot[25]="Q,K|K,Q";
  partot[26]="K,K";
  
  var numValue=partot[num];
  return numValue;
}

function getParDiff(num){
  var partot=new Array(13);
  partot[0]="A,A|2,2|3,3|4,4|5,5|6,6|7,7|8,8|9,9|10,10|J,J|Q,Q|K,K";
  partot[1]="A,2|2,3|3,4|4,5|5,6|6,7|7,8|8,9|9,10|10,J|J,Q|Q,K|K,Q|Q,J|J,10|10,9|9,8|8,7|7,6|6,5|5,4|4,3|3,2|2,A";
  partot[2]="A,3|2,4|3,5|4,6|5,7|6,8|7,9|8,10|9,J|10,Q|J,K|K,J|Q,10|J,9|10,8|9,7|8,6|7,5|6,4|5,3|4,2|3,A";
  partot[3]="A,4|2,5|3,6|4,7|5,8|6,9|7,10|8,J|9,Q|10,K|K,10|Q,9|J,8|10,7|9,6|8,5|7,4|6,3|5,2|4,A";
  partot[4]="A,5|2,6|3,7|4,8|5,9|6,10|7,J|8,Q|9,K|K,9|Q,8|J,7|10,6|9,5|8,4|7,3|6,2|5,A";
  partot[5]="A,6|2,7|3,8|4,9|5,10|6,J|7,Q|8,K|K,8|Q,7|J,6|10,5|9,4|8,3|7,2|6,A";
  partot[6]="A,7|2,8|3,9|4,10|5,J|6,Q|7,K|K,7|Q,6|J,5|10,4|9,3|8,2|7,A";
  partot[7]="A,8|2,9|3,10|4,J|5,Q|6,K|K,6|Q,5|J,4|10,3|9,2|8,A";
  partot[8]="A,9|2,10|3,J|4,Q|5,K|K,5|Q,4|J,3|10,2|9,A";
  partot[9]="A,10|2,J|3,Q|4,K|K,4|Q,3|J,2|10,A";
  partot[10]="A,J|2,Q|3,K|K,3|Q,2|J,A";
  partot[11]="A,Q|2,K|K,2|Q,A";
  partot[12]="A,K|K,A";
  
  var numValue=partot[num];
  return numValue;
}

function getStrWager(){
   var pokerText = document.all("pokertext");
   //var wagTotBef=document.all("wagtotbef");
   var wagerTotal = document.all("wagertotal");
   //wagTotBef.value="";
   wagerTotal.value = "";
   
   if(pokerText.length==0){
       //wagTotBef.value="";
	   wagerTotal.value = "";
   }else{
	  for(var i=0;i<pokerText.length;i++){
	    /* if(wagTotBef.value!=""){
	     wagTotBef.value=wagTotBef.value+"$";
	    }*/
	    var strvalue=pokerText[i].text+"g"+pokerText[i].value;
	    if(wagerTotal.value!=""){
	     wagerTotal.value=wagerTotal.value+"f";
	    }
	    //wagTotBef.value=wagTotBef.value+pokerText[i].text;
		wagerTotal.value=wagerTotal.value+strvalue;
	  }
   }
   
   document.all("wagernum").value=document.all("showtotalwager").innerText;
   //document.all("test").innerHTML=wagerTotal.value;测试区
}
var arrTextForPlay=new Array();
var arrQuanForPlay=new Array();//��¼ÿ��λ���ϵ�ѡ���˼���

//��ʼ��Ͷע��ע������Ӧ�ĺ���
function wagerPlayGetText(arrPlayType){
   for(var i=0;i<arrPlayType.length;i++){
		var arrNumForPlay=document.all(arrPlayType[i]);
		arrQuanForPlay[i]=0;
		arrTextForPlay[i]="";
		for(var j=0;j<arrNumForPlay.length;j++){
			  if(arrNumForPlay[j].className=="plach") {
				  arrQuanForPlay[i]=arrQuanForPlay[i]+1;
				  if(arrTextForPlay[i]!=""){
					  arrTextForPlay[i]=arrTextForPlay[i];//alter 2010-08-27,ԭ��һ��λ���ϵĶ���������ԡ��������������ڸ�Ϊ���÷ָ���
				  }
				  arrTextForPlay[i]=arrTextForPlay[i]+arrNumForPlay[j].innerText;
			  }
		}	
   }
}
/**
 *����ע��
 */
function wagerPlayCalEach(){
  var totalQuan=0;

  var playType=document.getElementById("playType");
  var typeName=document.getElementById("typeName");
 
  var flag = true;
   for(i=0;i<arrTextForPlay.length;i++){
	   if(arrTextForPlay[i] == "" || arrTextForPlay[i]==null){
		   flag=false;
	   }
   }
   if(flag){
	   var wuxingCount = 0;
	   var sixingCount = 0;
	   var sanxingCount = 0;
	   var erxingCount = 0;
	   var yixingCount = 0;

	   if(playType.value == "wuxing" && typeName.value=="zuhe"){
		  wuxingCount = arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];//alter 2010-08-27��ԭ���ķָ�����"|",�ָ�Ϊ","
		  sixingCount = arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
		  sanxingCount = arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
	      erxingCount = arrQuanForPlay[3]*arrQuanForPlay[4];
	      yixingCount = arrQuanForPlay[4];
	   }
	   if(playType.value == "sixing" && typeName.value=="zuhe"){
		  sixingCount = arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3];
		  sanxingCount = arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3];
		  erxingCount = arrQuanForPlay[2]*arrQuanForPlay[3];
		  yixingCount = arrQuanForPlay[3];
	   }
	   if(playType.value == "sanxing" && typeName.value=="zuhe"){
		  sanxingCount = arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2];
		  erxingCount = arrQuanForPlay[1]*arrQuanForPlay[2];
		  yixingCount = arrQuanForPlay[2];

	   }
	   if(playType.value == "erxing" && typeName.value=="zuhe"){
		  erxingCount = arrQuanForPlay[0]*arrQuanForPlay[1];
		  yixingCount = arrQuanForPlay[1];
	   }
	   totalQuan = wuxingCount + sixingCount + sanxingCount + erxingCount + yixingCount;
   }

  document.all("clickTzNum").innerText = totalQuan;
  document.all("clickTzMoney").innerText = numbertomoney(totalQuan*2);
}
/**
 *���"��Ӱ�ť"ʱ����
 */
function wagerPlus(){
  if(parseInt(document.all("clickTzNum").innerText)==0){
    alert("����ѡ��һע");
	return;
  }
  var playType=document.getElementById("playType");
  var typeName=document.getElementById("typeName").value;
  var playMode=document.all("playMode");
 /*
  if(playMode.value=="fushi"){
    if(arrQuanForPlay[0]<=1 && arrQuanForPlay[1]<=1 && arrQuanForPlay[2]<=1 ){
      alert("��ʽͶע������һ��λ������ѡ����������");
      return;
    }
  }
  */
// var textCombined=arrTextForPlay[0]+"|"+arrTextForPlay[1]+"|"+arrTextForPlay[2]+"|"+arrTextForPlay[3]+"|"+arrTextForPlay[4];
  var textCombined="";
 
  if(playType.value == "wuxing" && typeName == "zuhe"){
	  textCombined = arrTextForPlay[0]+","+arrTextForPlay[1]+","+arrTextForPlay[2]+","+arrTextForPlay[3]+","+arrTextForPlay[4];//alter 2010-08-27��ԭ���ķָ�����"|",�ָ�Ϊ","
  }else if(playType.value == "sixing" && typeName == "zuhe"){
	  textCombined = arrTextForPlay[0]+","+arrTextForPlay[1]+","+arrTextForPlay[2]+","+arrTextForPlay[3];
  }else if(playType.value == "sanxing" && typeName == "zuhe"){
	  textCombined = arrTextForPlay[0]+","+arrTextForPlay[1]+","+arrTextForPlay[2];
  }else if(playType.value == "erxing" && typeName == "zuhe"){
	  textCombined = arrTextForPlay[0]+","+arrTextForPlay[1];
  }
  var clickTzNumText=document.all("clickTzNum").innerText;
  wagerPlusComShow(textCombined,clickTzNumText);
  wagerPlusComTotal(parseInt(clickTzNumText));

   //var arrFig=new Array("bai","shi","ge");
  if(playType.value == "wuxing" && typeName == "zuhe"){
	  wagerPlusComRest(new Array("wan","qian","bai","shi","ge"),"plare");
  }else if(playType.value == "sixing" && typeName == "zuhe"){
	  wagerPlusComRest(new Array("qian","bai","shi","ge"),"plare");
  }else if(playType.value == "sanxing" && typeName == "zuhe"){
	  wagerPlusComRest(new Array("bai","shi","ge"),"plare");
  }else if(playType.value == "erxing" && typeName == "zuhe"){
	  wagerPlusComRest(new Array("shi","ge"),"plare");
  }
  getStrWager();
}


function wagerPlusComShow(text,wagerNumText){//��ѡ����ѡ����ӣ�����,��pokertext����Ӻ���
  var touzhuInfoList = document.all("touzhuInfoList");
  var touzhuNumList=document.all("touzhuNumList");
  
  touzhuInfoList.length = touzhuInfoList.length+1;
  touzhuNumList.length=touzhuNumList.length+1;
  
  touzhuInfoList[touzhuInfoList.length-1].value = text;
  touzhuInfoList[touzhuInfoList.length-1].text = text;
  
  touzhuNumList[touzhuNumList.length-1].value=parseInt(wagerNumText);
  touzhuNumList[touzhuNumList.length-1].text=wagerNumText+"ע";

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
  if(radioMethod != null){//����Ϸ�ʽʱ��û��method������˼����ж�
	  for (var i=0;i<radioMethod.length;i++){
		radioMethod[i].checked=false;
	  }
  }
}

/**tzTypeNum��Ͷע���͵�����;������ϣ�5��������ϣ�4......*/

function sscTextRemove(obj,tzTypeNumVal){
	var touzhuInfoList = document.all("touzhuInfoList");
	var touzhuNumList=document.all("touzhuNumList");
	var indexSel;
	if(touzhuInfoList.selectedIndex>=0){
		indexSel = touzhuInfoList.selectedIndex;//get the index selected.
		touzhuNumList.options[indexSel].selected = true;
		if(obj.name=="buttonRemove"){
		
		  var totalWager=parseInt(document.all("curIssueTzNum").innerText);
		  var delNum = (parseInt(touzhuNumList[indexSel].value))*(parseInt(tzTypeNumVal));

 		  //totalWager=totalWager-(parseInt(touzhuNumList[indexSel].value))*(parseInt(tzTypeNum));
		  totalWager=totalWager-delNum;
		  document.all("curIssueTzNum").innerText=totalWager;
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
	  alert("����ѡ��Ҫɾ����һ��");
	  return;
	}
}


function randSelect(num,typeNum)//��ѡ����ѡ����ѡ������
{
	//alert(num + "," + typeNum);
	for(var i=0;i<num;i++)
	{
		var textRanTemp = randSelectOneCom(typeNum);
		wagerPlusComShow(textRanTemp,"1");
	}
	//wagerPlusComTotal(num);
	
   // getStrWager();
   	var touzhuInfoList = document.all("touzhuInfoList");

   	var totalQuan=(touzhuInfoList.length)*typeNum;
	//alert(totalQuan);
    document.all("curIssueTzNum").innerText = totalQuan;
	//alert(document.all("clickTzNum").innerText);
	document.all("curIssueTzMoney").innerText = numbertomoney(totalQuan*2);
}

function randSelectOneCom(typeNum){
  var arrRanNumFig=new Array();
  for(var i=0;i<typeNum;i++){
    arrRanNumFig[i]=generRandomNum(0,9);
  }
  //	alert(arrRanNumFig[i]);

 // var ranNumFig=arrRanNumFig[0] + "," + arrRanNumFig[1] + "," + arrRanNumFig[2] + "," + arrRanNumFig[3] + "," + arrRanNumFig[4];
  var ranNumFig = "";
  for(var i = 0; i < arrRanNumFig.length; i++){
 	 ranNumFig = ranNumFig + arrRanNumFig[i] + ",";
  }
  if(ranNumFig != null && ranNumFig != ""){
	  ranNumFig = ranNumFig.substring(0,ranNumFig.length-1);
  }
  return ranNumFig;
}

//-----------����--��ʽ ��ѡ---------------
function wuxingFushiRandSelect(num)
{
	for(var i=0;i<num;i++)
	{
		var textRanTemp = randSelectOneCom();
		wagerPlusComShow(textRanTemp,"1");
	}
	//wagerPlusComTotal(num);
	
    //getStrWager();
	var touzhuInfoList = document.all("touzhuInfoList");
	//alert(touzhuInfoList.length);
	var totalQuan=(touzhuInfoList.length)*5;
	//alert(totalQuan);
    document.all("curIssueTzNum").innerText = totalQuan;
	//alert(document.all("clickTzNum").innerText);
	document.all("curIssueTzMoney").innerText = numbertomoney(totalQuan*2);
 }
//----------------��ʽ ��ѡ ѡ������һ��-----------
function wuxingFushiRandSelectOneCom(){
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
 *��Ͷע�����������ı���ʾ
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

//׷Ͷʱ���õ���Ӧ��Ͷע������Ӧ������,��ʽ��"�ں�|����$�ں�|����"
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

////////////////////////////ͨ�÷���/////////
/**
 *���С�ȫ �� С �� ż �� "��ѡ��
 */
function choosePlayByRadio(numb){
  var figureChosen=document.all("place");
  var playType = document.getElementById("playType");
  var typeName = document.getElementById("typeName");
  var arrFigure = new Array();
  if(playType.value =="wuxing" && typeName.value=="zuhe"){
		arrFigure =new Array("wan","qian","bai","shi","ge");
  }else if(playType.value == "sixing"  && typeName.value=="zuhe"){
		arrFigure =new Array("qian","bai","shi","ge");
  }else if(playType.value == "sanxing"  && typeName.value=="zuhe"){
		arrFigure =new Array("bai","shi","ge");
  }else if(playType.value == "erxing"  && typeName.value=="zuhe"){
		arrFigure =new Array("shi","ge");
  }

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


///////////////////////////////////////////////���//////////////


//---------------------���-����-�淨------------------------
function chooseZuheWxPlay(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("wan","qian","bai","shi","ge");
  wagerPlayGetText(arPlTy);
  zuheWuxingCal();
}

/**
 *���--������ѡ,�������ǣ����ǣ����ǣ����ǣ�һ������
 *2010-09-10
 */
function zuheWuxingCal(){
		
   var arrRowOne = arrTextForPlay[0];
   //var arrRowOne = arrTextForPlay.length;
	//alert(arrTextForPlay[0]+"::"+arrTextForPlay[1]+"::"+arrTextForPlay[2]+"::"+arrTextForPlay[3]+"::"+arrTextForPlay[4]);
   var totalQuan = 0;
  // if(arrTextForPlay[0]!="" || arrTextForPlay[1]!="" || arrTextForPlay[2]!="" || arrTextForPlay[3]!="" || arrTextForPlay[4]!=""){

   var flag = true;
   for(i=0;i<arrTextForPlay.length;i++){
	   if(arrTextForPlay[i] == "" || arrTextForPlay[i]==null){
		   flag=false;
	   }
   }
   if(flag){
	   var wuxingCount = arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
	   //var sixingCount = arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
	   //var sanxingCount = arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
	   //var erxingCount = arrQuanForPlay[3]*arrQuanForPlay[4];
	   //var yixingCount = arrQuanForPlay[4];
	  // alert(wuxingCount+":"+sixingCount+":"+sanxingCount+":"+erxingCount+":"+yixingCount);
	   //totalQuan = wuxingCount + sixingCount + sanxingCount + erxingCount + yixingCount;
	   totalQuan = wuxingCount*5;
   }
   document.all("clickTzNum").innerText = totalQuan;
	document.all("clickTzMoney").innerText = numbertomoney(totalQuan*2);

}

//---------------���-����-�淨-----------------
function chooseZuheSixingPlay(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("qian","bai","shi","ge");
  wagerPlayGetText(arPlTy);
  zuheSixingCal();
}

/**
 *���--������ѡ,�������ǣ����ǣ����ǣ�һ�� 4��
 *2010-09-10
 */
function zuheSixingCal(){
		
   var arrRowOne = arrTextForPlay[0];
   //var arrRowOne = arrTextForPlay.length;
	//alert(arrRowOne);
	var totalQuan = 0;
  // var wuxingCount = arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
   var sixingCount = arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3];
  // var sanxingCount = arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3];
 //  var erxingCount = arrQuanForPlay[2]*arrQuanForPlay[3];
  // var yixingCount = arrQuanForPlay[3];

 // alert("wuxing="+wuxingCount + ",sixing="+sixingCount + ",sanxing="+sanxingCount + ",erxing="+erxingCount);
   
  
 //  totalQuan = sixingCount + sanxingCount + erxingCount + yixingCount;

	totalQuan=sixingCount*4;
   document.all("clickTzNum").innerText = totalQuan;
	document.all("clickTzMoney").innerText = numbertomoney(totalQuan*2);

}

//-------------------------------���-����-�淨-----------------
function chooseZuheSanxingPlay(figure,numb){
  if(numb.className=="plare"){
  numb.className="plach";
  }else{
  numb.className="plare";
  }
  var arPlTy=new Array("bai","shi","ge");
  wagerPlayGetText(arPlTy);
  zuheSanxingCal();
}

/**
 *���--������ѡ,�������ǣ����ǣ�һ�� 3��
 *2010-09-10
 */
function zuheSanxingCal(){
		
   var arrRowOne = arrTextForPlay[0];
   //var arrRowOne = arrTextForPlay.length;
	//alert(arrRowOne);
	var totalQuan = 0;
  // var wuxingCount = arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
   var sanxingCount = arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2];
   //var erxingCount = arrQuanForPlay[1]*arrQuanForPlay[2];
   //var yixingCount = arrQuanForPlay[2];

   //totalQuan = sanxingCount + erxingCount + yixingCount;
   totalQuan = sanxingCount*3;

   document.all("clickTzNum").innerText = totalQuan;
	document.all("clickTzMoney").innerText = numbertomoney(totalQuan*2);

}

//-------------------------------���-����-�淨-----------------
function chooseZuheErxingPlay(figure,numb){
  if(numb.className=="plare"){
	 numb.className="plach";
  }else{
	 numb.className="plare";
  }
  var arPlTy=new Array("shi","ge");
  wagerPlayGetText(arPlTy);
  zuheErxingCal();
}

/**
 *���--������ѡ,�������ǣ�һ�� 3��
 *2010-09-10
 */
function zuheErxingCal(){
		
   var arrRowOne = arrTextForPlay[0];
   //var arrRowOne = arrTextForPlay.length;
	//alert(arrRowOne);
	var totalQuan = 0;
  // var wuxingCount = arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
   var erxingCount = arrQuanForPlay[0]*arrQuanForPlay[1];
  // var yixingCount = arrQuanForPlay[1];
   
   //totalQuan = erxingCount + yixingCount;
	totalQuan = erxingCount * 2;
   document.all("clickTzNum").innerText = totalQuan;
	document.all("clickTzMoney").innerText = numbertomoney(totalQuan*2);

}
var arrTextForPlay=new Array();
var arrQuanForPlay=new Array();
//ֱѡ


//��ʽ�淨
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

//��ʽ�淨
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

 
function wagerPlayGetText(arrPlayType){//����com,gr6
  
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
  totalQuan=arrQuanForPlay[0]*arrQuanForPlay[1]*arrQuanForPlay[2]*arrQuanForPlay[3]*arrQuanForPlay[4];
 
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
  var playMode=document.all("playmode");
  if(playMode.value=="��ʽ"){
    if(arrQuanForPlay[0]<=1 && arrQuanForPlay[1]<=1 && arrQuanForPlay[2]<=1 && arrQuanForPlay[3]<=1 && arrQuanForPlay[4]<=1){
      alert("��ʽͶע������һ��λ������ѡ����������");
      return;
    }
  }
// var textCombined=arrTextForPlay[0]+"|"+arrTextForPlay[1]+"|"+arrTextForPlay[2]+"|"+arrTextForPlay[3]+"|"+arrTextForPlay[4];
  var textCombined=arrTextForPlay[0]+","+arrTextForPlay[1]+","+arrTextForPlay[2]+","+arrTextForPlay[3]+","+arrTextForPlay[4];//alter 2010-08-27��ԭ���ķָ�����"|",�ָ�Ϊ","
  var clickTzNumText=document.all("clickTzNum").innerText;
  wagerPlusComShow(textCombined,clickTzNumText);
  wagerPlusComTotal(parseInt(clickTzNumText));
  var arrFig=new Array("wan","qian","bai","shi","ge");
  wagerPlusComRest(arrFig,"plare");
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
	  alert("����ѡ��Ҫɾ����һ��");
	  return;
	}
}

/**
 *���С�ȫ �� С �� ż �� "��ѡ��
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

function randSelect(num)//��ѡ����ѡ����ѡ������
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

/**
 * ��ʽ���ʱ������д���ݵ���֤������
 *ljy 2010-08-31
 */
function danshiCheckData(objName,len){
	var txt = $("#" + objName).attr("value");
	if (txt==""){
		alert("�����������Ϊ�գ�");
		return;
	}
	var aa = txt.split(/\s*\n\s*/g);
	var l = aa.length;
	if (l==0){
		return alert("����������ʽ�������飡");
	}else if (l>300){
		return alert("����������300�У�");
	}else{
		var msg = "";
		var b = false;//ssc.name=="������ѡ��ʽ";
		//var c = ssc.name=='������ѡ��ʽ';
		for (var i=0;i<l;i++){
		  if(!(/^\d+$/.test(aa[i]))){
			alert("��"+(i+1)+"�к����ʽ���������¼���޸ġ�\n��ʾ������ֻ��Ϊ���֡�");
			return;
		  }
		  
		  aa[i] = aa[i].match(/\d/g);
		 // alert(aa[i].length);
		  if(aa[i].length != len){
			  alert("��"+(i+1)+"�к�������,����Ϊ" + len +"λ��");
			  return;
		  }
		  /**��ʱ�Ȳ���У��
		  if( aa[i][0] == aa[i][1]){
				msg = (aa[i]+"Ϊ���ӣ�������ѡ��ʽ��֧�ָ����ͣ�лл��");
		  }else if(b&&/^(\d),?\1,?\1$/.test(aa[i])){
				msg = "���ã�3�����벻����ȫ��ͬ�������¼���޸ģ�";
		  }else if(b&&!ssc.chkAsc(s)){
				msg = "���ã�����û���������������¼���޸ģ�";
		  }else continue;
		  */	


		}
		
	}
	var infoList = document.getElementById("touzhuInfoList").options;
	var numList = document.getElementById("touzhuNumList").options;
	for (var i=0,l=aa.length; i<l; i++){
		infoList.add(new Option(aa[i],aa[i]));	
		numList.add(new Option("1ע","1"));
	}
	//������ע��
	$("#curIssueTzNum").attr("innerHTML",l);
	$("#curIssueTzMoney").attr("innerHTML",eval(l*2));
	getStrWager();
	//���ԭ����
	$("#" + objName).attr("value","");
}
	
//������120
function chooseZu120Play(weizhi,selectObj,val){
 
	//����ǽ��С�ѡ�С��Ĳ���
	if(selectObj.className=="plare"){
		//�õ���ǰλ�õ���ֵ
		var typeAry=new Array("wan","qian","bai","shi","ge");
		var repeatFlag = false;
		for(var i = 0; i < typeAry.length;i++){
			var tempType = typeAry[i];
			//���ж�����λ�õ�����������ɨ�裬�ж��Ƿ����뵱ǰѡ��ĺ�����ͬ��
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
			alert("������ѡһ�ٶ�ʮ�������ظ�����!");
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
//������ѡ---ͨ��
function chooseZuxuanPlay(weizhi,selectObj,val,curType){
	var typeAry = new Array();
	var alertStr = "";
	if(curType=="zu120"){
		typeAry=new Array("wan","qian","bai","shi","ge");
		alertStr = "������ѡһ�ٶ�ʮ�������ظ�����!";
	}
	if(curType=="zu60"){
		typeAry=new Array("qian","bai","shi","ge");
		alertStr = "������ѡ��ʮ�����ظ�ѡ��!";
	}

	if(curType=="zu30"){
		typeAry=new Array("bai","shi","ge");
		alertStr = "������ѡ��ʮ�����ظ�ѡ��!";
	}
	if(curType=="zu20"){
		typeAry=new Array("bai","shi","ge");
		alertStr = "������ѡ��ʮ�����ظ�ѡ��!";
	}
	if(curType=="zu10"){
		typeAry=new Array("shi","ge");
		alertStr = "������ѡʮ�����ظ�ѡ��!";
	}
	
	if(curType=="zu5"){
		typeAry=new Array("shi","ge");
		alertStr = "������ѡ�岻���ظ�ѡ��!";
	}
	if(curType=="zuchong2"){
		typeAry=new Array("ge");
		alertStr = "�����غ�ȫ�������ظ�ѡ��!";
	}
	if(curType=="zuchong3"){
		typeAry=new Array("ge");
		alertStr = "�������غ�ȫ�������ظ�ѡ��!";
	}
	
	if(curType=="zuchong4"){
		typeAry=new Array("ge");
		alertStr = "�������غ�ȫ�������ظ�ѡ��!";
	}
	//����ǽ��С�ѡ�С��Ĳ���
	if(selectObj.className=="plare"){
		//�õ���ǰλ�õ���ֵ

		var repeatFlag = false;
		for(var i = 0; i < typeAry.length;i++){
			var tempType = typeAry[i];
			//���ж�����λ�õ�����������ɨ�裬�ж��Ƿ����뵱ǰѡ��ĺ�����ͬ��
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
	 
	//�ɴ˿�ʼ����ע��
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
	 //ע������ end
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
//�غ�ȫ��
function chooseZuChong2Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zuchong2");

}
//���غ�ȫ��ѡ��
function chooseZuChong3Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zuchong3");

}
//���غ�ȫ��ѡ��
function chooseZuChong4Play(weizhi,selectObj,val){
	chooseZuxuanPlay(weizhi,selectObj,val,"zuchong4");

}
/**
 *���"��Ӱ�ť"ʱ����
 */
function wagerZuxuanPlus(selectType){
  if(parseInt(document.all("clickTzNum").innerText)==0){
    alert("����ѡ��һע");
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
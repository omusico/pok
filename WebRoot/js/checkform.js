//��������chksafe
//���ܽ��ܣ�����Ƿ���"'",'\\',"/"
//����˵����Ҫ�����ַ���
//����ֵ��0���� 1������
function chksafe(a){
	fibdn = new Array ("\'" ,"\\", "��", ",", ";", "/","\&","$","~","!","`");
	i=fibdn.length;
	j=a.length;
	for (ii=0;ii<i;ii++){
		for (jj=0;jj<j;jj++){
			temp1=a.charAt(jj);
			temp2=fibdn[ii];
			if (temp1==temp2)
			{ return 0; }
		}
	}
	return 1;
}

//��������chkspc
//���ܽ��ܣ�����Ƿ��пո�
//����˵����Ҫ�����ַ���
//����ֵ��0���� 1������
function chkspc(a){
	var i=a.length;
	var j = 0;
	var k = 0;
	while (k<i){
		if ((a.charAt(k) == " ")||(a.charAt(k)=="��")){
	  		j = j+1;
	  	}
	  	k = k+1;
	}
	if (j>0){
		return 0;
	}
	return 1;
}

//������ַ���a���Ƿ����b
function isContainChar(a,b)
{
var i=a.length;
var j = 0;
var k = 0;
while (k<i){
  if (a.charAt(k) == b){
   j=1;
   break;
  }
  k = k+1;
}
return j;
}


//��������chkemail
//���ܽ��ܣ�����Ƿ�ΪEmail Address
//����˵����Ҫ�����ַ���
//����ֵ��0������ 1����
function chkemail(inputString){
	var pattern = /^([\.a-zA-Z0-9_-]){3,}@([a-zA-Z0-9_-]){1,}(\.([a-zA-Z0-9]){1,}){1,}$/;
	if(!pattern.test(inputString)){
		return 0;
	}
	return 1;
}

//opt1 С�� opt2 ����
//��opt2Ϊ1ʱ���num�Ƿ��Ǹ���
//��opt1Ϊ1ʱ���num�Ƿ���С��
//����1����ȷ�ģ�0�Ǵ����
function chknbr(num,opt1,opt2){
	var i=num.length;
	var staus;
	//staus���ڼ�¼.�ĸ���
	status=0;
	if ((opt2!=1) && (num.charAt(0)=='-')){
		//alert("You have enter a invalid number.");
		return 0;
	}
	//�����һλΪ.ʱ����
	if (num.charAt(i-1)=='.'){
	//alert("You have enter a invalid number.");
	return 0;
	}

for (j=0;j<i;j++)
{
if (num.charAt(j)=='.')
{
status++;
}
if (status>1)
{
//alert("You have enter a invalid number.");
return 0;
}
if (num.charAt(j)<'0' || num.charAt(j)>'9' )
{
if (((opt1==0) || (num.charAt(j)!='.')) && (j!=0))
{
//alert("You have enter a invalid number.");
return 0;
}
}
}
return 1;
}

//��������chkdate
//���ܽ��ܣ�����Ƿ�Ϊ����
//����˵����Ҫ�����ַ���
//����ֵ��0���������� 1��������
function chkdate(datestr){
	var lthdatestr
	if (datestr != "")
		lthdatestr= datestr.length ;
	else
		lthdatestr=0;
	var tmpy="";
	var tmpm="";
	var tmpd="";
	var status;
	status=0;
	if (lthdatestr== 0) return 0

	for (i=0;i<lthdatestr;i++){
		if (datestr.charAt(i)== '-'){
			status++;
		}if (status>2){
			return 0;
		}if ((status==0) && (datestr.charAt(i)!='-')){
			tmpy=tmpy+datestr.charAt(i)
		}if ((status==1) && (datestr.charAt(i)!='-')){
			tmpm=tmpm+datestr.charAt(i)
		}if ((status==2) && (datestr.charAt(i)!='-')){
			tmpd=tmpd+datestr.charAt(i)
		}
	}
	year=new String (tmpy);
	month=new String (tmpm);
	day=new String (tmpd)
	if ((tmpy.length!=4) || (tmpm.length>2) || (tmpd.length>2)){
		return 0;
	}if (!((1<=month) && (12>=month) && (31>=day) && (1<=day)) ){
		return 0;
	}if (!((year % 4)==0) && (month==2) && (day==29)){
		return 0;
	}if ((month<=7) && ((month % 2)==0) && (day>=31)){
		return 0;
	}if ((month>=8) && ((month % 2)==1) && (day>=31)){
		return 0;
	}if ((month==2) && (day==30)){
		return 0;
	}
	return 1;
}

//��������fucPWDchk
//���ܽ��ܣ�����Ƿ��з����ֻ���ĸ
//����˵����Ҫ�����ַ���
//����ֵ��0������ 1��ȫ��Ϊ���ֻ���ĸ
function fucPWDchk(str)
{
var strSource ="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
var ch;
var i;
var temp;

for (i=0;i<=(str.length-1);i++)
{

ch = str.charAt(i);
temp = strSource.indexOf(ch);
if (temp==-1)
{
return 0;
}
}
if (strSource.indexOf(ch)==-1)
{
return 0;
}
else
{
return 1;
}
}

function jtrim(str)
{ while (str.charAt(0)==" ")
{str=str.substr(1);}
while (str.charAt(str.length-1)==" ")
{str=str.substr(0,str.length-1);}
return(str);
}

//���˫����С��,������Ҳ��ͨ�����
function fucCheckDouble(str)
{
    var i = str.indexOf(".");
    var iStr,fStr;//�������ֺ�С������
    //���һλ����С����Ҳ����ͨ�����
    if(i==str.length-1) return 1;
    //����������С�����ָ�ʽ
    if(i!=-1){
      iStr = str.substring(0,i);
      fStr = str.substring(i+1);
      if(fucCheckNUM(iStr)==1 && fucCheckNUM(fStr)==1)
        return 1;
      else
        return 0;
    }
    else {
      if(fucCheckNUM(str)==1)
        return 1;
      else
        return 0;
    }
}

//��������fucCheckInt
//���ܽ��ܣ�����Ƿ�Ϊ����
//����˵����Ҫ��������
//����ֵ��1Ϊ�����֣�0Ϊ��������
function fucCheckInt(NUM){
var i,j,strTemp;
strTemp="0123456789";
if ( NUM.length== 0)
return 0
for (i=0;i<NUM.length;i++)
{
j=strTemp.indexOf(NUM.charAt(i));
if (j==-1)
{
//˵�����ַ���������
return 0;
}
}
if (NUM.charAt(0)=="0"&NUM.size>1)return 0;//˵����һ����ĸ��0
//˵��������
return 1;
}


//��������fucCheckNUM
//���ܽ��ܣ�����Ƿ�Ϊ����
//����˵����Ҫ��������
//����ֵ��1Ϊ�����֣�0Ϊ��������
function fucCheckNUM(NUM){
    var i,j,strTemp;
    strTemp="0123456789";
    if ( NUM.length== 0)
        return 0
        for (i=0;i<NUM.length;i++){
            j=strTemp.indexOf(NUM.charAt(i));
            if (j==-1){
                return 0;//˵�����ַ���������
            }
        }
        return 1;//˵��������
}

//��������fucCheckPostcode
//���ܽ��ܣ�����Ƿ�Ϊ�ʱ�
//����˵����Ҫ��������
//����ֵ��1Ϊ���ʱ࣬0Ϊ�����ʱ�
function fucCheckPostcode(postCode)
{
    var i,j;
    if (fucCheckNUM(postCode)==0)
        return 0;
    if (postCode.length!=6)
        return 0;
//˵��������
    return 1;
}

//��������fucCheckTEL
//���ܽ��ܣ�����Ƿ�Ϊ�绰����
//����˵����Ҫ�����ַ���
//����ֵ��1Ϊ�ǺϷ���0Ϊ���Ϸ�
function fucCheckTEL(TEL)
{
    var i,j,strTemp;
    strTemp="0123456789-()# ";
    for (i=0;i<TEL.length;i++)
    {
        j=strTemp.indexOf(TEL.charAt(i));
        if (j==-1)
        {
//˵�����ַ����Ϸ�
            return 0;
        }
    }
//˵���Ϸ�
    return 1;
}

//��������fucCheckLength
//���ܽ��ܣ�����ַ����ĳ���
//����˵����Ҫ�����ַ���
//����ֵ������ֵ
function fucCheckLength(strTemp)
{
var i,sum;
sum=0;
for(i=0;i<strTemp.length;i++)
{
if ((strTemp.charCodeAt(i)>=0) && (strTemp.charCodeAt(i)<=255))
sum=sum+1;
else
sum=sum+2;
}
return sum;
}




/***********************
** ������:��ǿ
** �ա���:2002-7-18
** �޸���:
** �ա���:
** �衡��:���ú���
** �桡��:V1.0
******************************************************************/



/*****************************************************************
** ����  ��: isBlank
** �䡡  ��: value      ��Ҫ�������ַ���
**������
** ��     ����true�ǿգ�false�ǲ�Ϊ��
** ��������:���ֵ�Ƿ�Ϊ��
** �޸�  ��:
** �ա�  ��:
*****************************************************************/
function Is_Null(value)
{
	var j=0;
	var Text="";
	Text=value;
	if(Text.length)
	{
		for(var i=0;i<Text.length;i++){
		if(Text.charAt(i)!=" ")
		j=j+1;}

		if(j==0){
		Ret=true;
		}else{
		Ret=false;}
	}
	else
		Ret=true;
	return(Ret);
}

// -----------------------------------------------------------------------------------
// ���������ڶ�sString�ַ������пո�س�
// -----------------------------------------------------------------------------------
function JsTrim(sString){
	var sTemp="";
	sTemp=JsRTrim(JsLTrim(sString));
	return sTemp;
}


// -----------------------------------------------------------------------------------
// ���������ڶ�sString�ַ������к�ո�س�
// -----------------------------------------------------------------------------------
function JsRTrim(sString){
	var sStr,i,sResult = "",sTemp = "" ;
 	if (sString.length == 0) { return "" ;} 	// ����sString�ǿմ�
	sStr = sString.split("");
	for (i = sStr.length - 1 ; i >= 0 ; i --){  // ���ַ������е���
		sResult = sResult + sStr[i];
	}
	sTemp = JsLTrim(sResult) ; 					// �����ַ���ǰ�ո�س�
	if (sTemp == "") { return "" ; }
	sStr = sTemp.split("");
	sResult = "" ;
	for (i = sStr.length - 1 ; i >= 0 ; i--){ // �����������ַ����ٽ��е���
		sResult = sResult + sStr[i];
	}
	return sResult;
}


// -----------------------------------------------------------------------------------
//���������ڶ�sString�ַ�������ǰ�ո�س�
// -----------------------------------------------------------------------------------
function JsLTrim(sString){
	var sStr,i,iStart,sResult = "";
	sStr = sString.split("");
	iStart = -1 ;
	for (i = 0 ; i < sStr.length ; i++){
		if (sStr[i] != " "){
			iStart = i;
			break;
		}
	}
	if (iStart == -1) { return "" ;}    //��ʾsString�е������ַ����ǿո�,�򷵻ؿմ�
	else { return sString.substring(iStart) ;}
}

//��������checkStringCompose
//���ܽ��ܣ����InputString�Ƿ���ComposeString���
//����˵����Ҫ�����ַ���
//����ֵ��1Ϊ����ComposeString��ɣ�0Ϊ������ComposeString���
function checkStringCompose(InputString,ComposeString)
{
    var i,j;
    if ( InputString.length==0)
        return 0
    for (i=0;i<InputString.length;i++)
    {
        j=ComposeString.indexOf(InputString.charAt(i));
        if (j==-1)
        {
            return 0;//˵�����ַ�������ComposeString�е��ַ����
        }
    }
    //˵������ComposeString�е��ַ����
    return 1;
}

//��������checkDigitBit
//���ܽ��ܣ����InputString�ɼ�λС��
//����˵����Ҫ�����ַ���
//����ֵ��С��λ��
function checkDigitBit(InputString)
{
  var i,j;
  j=0;
  //����ַ������������ֺ�С������ɵ��򷵻�-1
  if(checkStringCompose(InputString,'0123456789.')==0){
    return -1;
  }
  else{
    for (i=0;i<InputString.length;i++){
      if(InputString.charAt(i)=='.')
        j++;
      //���С����ĸ���>1���򷵻�-1
      if (j>1){
        return -1;
      }
    }
    //����ַ����������򷵻�0
    if(checkStringCompose(InputString,'0123456789')==1){
      return 0;
    }
    else{
      i=InputString.lastIndexOf(".");
      i=InputString.length-i-1;
      return i;
    }
  }
}

//��������checkMoney
//���ܽ��ܣ����InputString�Ƿ���Ǯ��
//����˵����Ҫ�����ַ���
//����ֵ���Ƿ���1,���Ƿ���-1
function checkMoney(InputString)
{
  var i,j,k;
  j=0;
  k=-2;
  //����ַ������������ֺ�С������ɵ��򷵻�-1
  if(checkStringCompose(InputString,'0123456789.')==0){
    return -1;
  }
  else{
    for (i=0;i<InputString.length;i++){
      if(InputString.charAt(i)=='.'){
        j++;
        k++;
      }
      if(k!=-2)k++;
      //���С����ĸ���>1���򷵻�-1
      if (j>1){
        return -1;
      }
      //����С��λ��>2
      if(k>2){
        return -1;
      }
    }
    return 1;
  }
}

//һ���ж����ڴ�С��sDate������ʼʱ�䣬eDate�������ʱ�䣬���eDate����sDate������true
//author:xiqiang
function opinionDate(sDate,eDate){
      startDate=sDate;
      endDate=eDate;
          startMark1=startDate.indexOf("-");
          startYear=startDate.substring(0,startMark1);
          startDate=startDate.substring(startMark1+1,startDate.length);
          startMark2=startDate.indexOf("-");
          startMonth=startDate.substring(0,startMark2);
          startDate=startDate.substring(startMark2+1,startDate.length);
          startDay=startDate;

          endMark1=endDate.indexOf("-");
          endYear=endDate.substring(0,endMark1);
          endDate=endDate.substring(endMark1+1,endDate.length);
          endMark2=endDate.indexOf("-");
          endMonth=endDate.substring(0,endMark2);
          endDate=endDate.substring(endMark2+1,endDate.length);
          endDay=endDate;

          if(startMonth.substring(0,1)==0){
             startMonth = startMonth.substring(1,2);
          }
          if(endMonth.substring(0,1)==0){
             endMonth = endMonth.substring(1,2);
          }
          if(startDay.substring(0,1)==0){
            startDay = startDay.substring(1,2);
          }
          if(endDay.substring(0,1)==0){
            endDay = endDay.substring(1,2);
          }
          if(parseInt(endYear)<parseInt(startYear)){
                 return false
          }else if((parseInt(endYear)==parseInt(startYear))){

                 if(parseInt(startMonth)<parseInt(endMonth)){
                     return true;
                }else if(parseInt(startMonth)>parseInt(endMonth)){
                   return false;
                }else if(parseInt(startMonth)==parseInt(endMonth)){
                  if(parseInt(startDay)>parseInt(endDay)){
                    return false;

                  }
             }
          }

        return true;

}

//��������checkFileExt
//���ܽ��ܣ�����ļ���չ��
//����˵����Ҫ�����ַ���
//����ֵ���ļ���չ��,��չ������ʱ����error
/* Edited By Ty, 2003-03-26
 * �޸�ԭ�򣺰ѵõ�����չ��ǿ��ת����Сд��
 */
function checkFileExt(InputString)
{
    var i;
    var tmpString;
    //���Ϊ���ַ����򷵻�error
    if(InputString==null)
        return 0;
    else{
        //���Ϊ�ַ�������<4�򷵻�error
        if (InputString.length<=4)
            return 0;
        else{
            i=InputString.lastIndexOf(".");
              tmpString=InputString.substring(i+1,InputString.length);
            i=tmpString.length;
            if(i==3||i==4){
               return tmpString.toLowerCase();
            }
            else{
               return 0;
            }
        }
    }
}

//�ж�һ���ַ����Ƿ���Ӣ��+�������
function isEnglish(inputString){
	var regexp=/^[a-zA-Z0-9_-]+$/;
	if(!regexp.test(inputString)){
		return 0;
	}
	return 1;
}

function isPicture(picpath){
   if(checkFileExt(picpath)=="gif") return 1;
   else if(checkFileExt(picpath)=="bmp") return 1;
   else if(checkFileExt(picpath)=="jpg") return 1;
   else return 0;
}

//���С��,������Ҳ��ͨ�����,���ҿ����Ǹ���
//return:1 �Ϸ� 0 ���Ϸ�
function fucCheckFloat(str)
{
 	var iStr,fStr;//�������ֺ�С������
	if(str.indexOf("-")!=-1){
		if(str.indexOf("-")!=0){
		  return 0;
		}else{
		  str = str.substring(1);//����
		}	  		
	}	 
 if(str.indexOf(".")!=-1){//��С��
	  iStr = str.substring(0,str.indexOf("."));
    fStr = str.substring(str.indexOf(".")+1);   
    if(fucCheckNUM(iStr)==1 && fStr.length<=2 && (fucCheckNUM(fStr)==1 || fStr.length==0)){
        return 1;
    }else{
        return 0;
    }
	}else{
	  if(fucCheckNUM(str)==1){
		   return 1;
		}else{
		   return 0;
		}
	}	
}

/**
 * ����ʽ���С��
 * 
 * @param str ��Ҫ�����ַ���
 * @param integerDigits ��������λ��
 * @param decimalDigits С������λ��
 * @return �Ϸ�����1�����Ϸ�������ȷ�ĸ�ʽ
 */
function checkFormatDouble(str, integerDigits, decimalDigits) {
	var format;
	var iFormat = "";
	var dFormat = "";
	
	for (i = 0; i < integerDigits; i++) {
		iFormat += "0";
	}
	
	for (d = 0; d < decimalDigits; d++) {
		dFormat += "0";
	}
	
	format = iFormat + "." + dFormat;
	
	if (fucCheckDouble(str) == 1) {
		if (str.indexOf(".") == -1) {
			str += ".";
		}
		
		var i = str.indexOf(".");
		var iStr = str.substring(0,i);
      	var dStr = str.substring(i+1);
      	
		var iLength = iStr.length;
		var dLength = dStr.length;
		
		if (iLength <= integerDigits && dLength <= decimalDigits) {
			return 1;
		} else {
			return format;
		}
	} else {
		return format;
	}
}


function validateForm(str) {
	var funs = str.split(";");
	for(var i = 0 ; i < funs.length ; ++i){
		if(funs[i] != "" && eval(funs[i]) == 0){
			return false;
		}
	}
	return true;
}

function chkStringOnSubmit(name, ispc, isnull, Max, Min, b) {
	var elements = document.getElementsByName(name);
	for(var i = 0 ; i < elements.length ; ++i){
		if(_chkString(elements[i].value, ispc, isnull, Max, Min, b) == 0)return 0;
	}
	return 1;
}
function _chkString(inputString, ispc, isnull, Max, Min, b) {
	var a = inputString;
	if (isnull) {
		if (Is_Null(a)) {
			alert(b);
			return 0;
		}
	}
	if (ispc) {
		var c = chkspc(a);
		if (c == 0) {
			alert(b);
			return 0;
		}
	}
	var length = fucCheckLength(a);
	if ((Min <= length) && (length <= Max)) {
		return 1;
	} else {
		alert(b);
		return 0;
	}
	return 1;
}

//函数名：chksafe
//功能介绍：检查是否含有"'",'\\',"/"
//参数说明：要检查的字符串
//返回值：0：是 1：不是
function chksafe(a){
	fibdn = new Array ("\'" ,"\\", "、", ",", ";", "/","\&","$","~","!","`");
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

//函数名：chkspc
//功能介绍：检查是否含有空格
//参数说明：要检查的字符串
//返回值：0：是 1：不是
function chkspc(a){
	var i=a.length;
	var j = 0;
	var k = 0;
	while (k<i){
		if ((a.charAt(k) == " ")||(a.charAt(k)=="　")){
	  		j = j+1;
	  	}
	  	k = k+1;
	}
	if (j>0){
		return 0;
	}
	return 1;
}

//检查在字符串a中是否包含b
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


//函数名：chkemail
//功能介绍：检查是否为Email Address
//参数说明：要检查的字符串
//返回值：0：不是 1：是
function chkemail(inputString){
	var pattern = /^([\.a-zA-Z0-9_-]){3,}@([a-zA-Z0-9_-]){1,}(\.([a-zA-Z0-9]){1,}){1,}$/;
	if(!pattern.test(inputString)){
		return 0;
	}
	return 1;
}

//opt1 小数 opt2 负数
//当opt2为1时检查num是否是负数
//当opt1为1时检查num是否是小数
//返回1是正确的，0是错误的
function chknbr(num,opt1,opt2){
	var i=num.length;
	var staus;
	//staus用于记录.的个数
	status=0;
	if ((opt2!=1) && (num.charAt(0)=='-')){
		//alert("You have enter a invalid number.");
		return 0;
	}
	//当最后一位为.时出错
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

//函数名：chkdate
//功能介绍：检查是否为日期
//参数说明：要检查的字符串
//返回值：0：不是日期 1：是日期
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

//函数名：fucPWDchk
//功能介绍：检查是否含有非数字或字母
//参数说明：要检查的字符串
//返回值：0：含有 1：全部为数字或字母
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

//检测双精度小数,是整数也可通过检测
function fucCheckDouble(str)
{
    var i = str.indexOf(".");
    var iStr,fStr;//整数部分和小数部分
    //最后一位数是小数点也可以通过检测
    if(i==str.length-1) return 1;
    //分析整数核小数部分格式
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

//函数名：fucCheckInt
//功能介绍：检查是否为数字
//参数说明：要检查的数字
//返回值：1为是数字，0为不是数字
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
//说明有字符不是数字
return 0;
}
}
if (NUM.charAt(0)=="0"&NUM.size>1)return 0;//说明第一个字母是0
//说明是数字
return 1;
}


//函数名：fucCheckNUM
//功能介绍：检查是否为数字
//参数说明：要检查的数字
//返回值：1为是数字，0为不是数字
function fucCheckNUM(NUM){
    var i,j,strTemp;
    strTemp="0123456789";
    if ( NUM.length== 0)
        return 0
        for (i=0;i<NUM.length;i++){
            j=strTemp.indexOf(NUM.charAt(i));
            if (j==-1){
                return 0;//说明有字符不是数字
            }
        }
        return 1;//说明是数字
}

//函数名：fucCheckPostcode
//功能介绍：检查是否为邮编
//参数说明：要检查的数字
//返回值：1为是邮编，0为不是邮编
function fucCheckPostcode(postCode)
{
    var i,j;
    if (fucCheckNUM(postCode)==0)
        return 0;
    if (postCode.length!=6)
        return 0;
//说明是数字
    return 1;
}

//函数名：fucCheckTEL
//功能介绍：检查是否为电话号码
//参数说明：要检查的字符串
//返回值：1为是合法，0为不合法
function fucCheckTEL(TEL)
{
    var i,j,strTemp;
    strTemp="0123456789-()# ";
    for (i=0;i<TEL.length;i++)
    {
        j=strTemp.indexOf(TEL.charAt(i));
        if (j==-1)
        {
//说明有字符不合法
            return 0;
        }
    }
//说明合法
    return 1;
}

//函数名：fucCheckLength
//功能介绍：检查字符串的长度
//参数说明：要检查的字符串
//返回值：长度值
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
** 创建人:奚强
** 日　期:2002-7-18
** 修改人:
** 日　期:
** 描　述:公用函数
** 版　本:V1.0
******************************************************************/



/*****************************************************************
** 函数  名: isBlank
** 输　  入: value      需要操作的字符串
**　　　
** 输     出：true是空，false是不为空
** 功能描述:检查值是否为空
** 修改  人:
** 日　  期:
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
// 本函数用于对sString字符串进行空格截除
// -----------------------------------------------------------------------------------
function JsTrim(sString){
	var sTemp="";
	sTemp=JsRTrim(JsLTrim(sString));
	return sTemp;
}


// -----------------------------------------------------------------------------------
// 本函数用于对sString字符串进行后空格截除
// -----------------------------------------------------------------------------------
function JsRTrim(sString){
	var sStr,i,sResult = "",sTemp = "" ;
 	if (sString.length == 0) { return "" ;} 	// 参数sString是空串
	sStr = sString.split("");
	for (i = sStr.length - 1 ; i >= 0 ; i --){  // 将字符串进行倒序
		sResult = sResult + sStr[i];
	}
	sTemp = JsLTrim(sResult) ; 					// 进行字符串前空格截除
	if (sTemp == "") { return "" ; }
	sStr = sTemp.split("");
	sResult = "" ;
	for (i = sStr.length - 1 ; i >= 0 ; i--){ // 将经处理后的字符串再进行倒序
		sResult = sResult + sStr[i];
	}
	return sResult;
}


// -----------------------------------------------------------------------------------
//本函数用于对sString字符串进行前空格截除
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
	if (iStart == -1) { return "" ;}    //表示sString中的所有字符均是空格,则返回空串
	else { return sString.substring(iStart) ;}
}

//函数名：checkStringCompose
//功能介绍：检查InputString是否由ComposeString组成
//参数说明：要检查的字符串
//返回值：1为是由ComposeString组成，0为不是由ComposeString组成
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
            return 0;//说明有字符不是由ComposeString中的字符组成
        }
    }
    //说明是由ComposeString中的字符组成
    return 1;
}

//函数名：checkDigitBit
//功能介绍：检查InputString由几位小数
//参数说明：要检查的字符串
//返回值：小数位数
function checkDigitBit(InputString)
{
  var i,j;
  j=0;
  //如果字符串不是由数字和小数点组成的则返回-1
  if(checkStringCompose(InputString,'0123456789.')==0){
    return -1;
  }
  else{
    for (i=0;i<InputString.length;i++){
      if(InputString.charAt(i)=='.')
        j++;
      //如果小数点的个数>1个则返回-1
      if (j>1){
        return -1;
      }
    }
    //如果字符串是整数则返回0
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

//函数名：checkMoney
//功能介绍：检查InputString是否是钱数
//参数说明：要检查的字符串
//返回值：是返回1,不是返回-1
function checkMoney(InputString)
{
  var i,j,k;
  j=0;
  k=-2;
  //如果字符串不是由数字和小数点组成的则返回-1
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
      //如果小数点的个数>1个则返回-1
      if (j>1){
        return -1;
      }
      //如是小数位数>2
      if(k>2){
        return -1;
      }
    }
    return 1;
  }
}

//一个判断日期大小，sDate代表起始时间，eDate代表结束时间，如果eDate大于sDate，返回true
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

//函数名：checkFileExt
//功能介绍：检查文件扩展名
//参数说明：要检查的字符串
//返回值：文件扩展名,扩展名有误时返回error
/* Edited By Ty, 2003-03-26
 * 修改原因：把得到的扩展名强制转换成小写。
 */
function checkFileExt(InputString)
{
    var i;
    var tmpString;
    //如果为空字符串则返回error
    if(InputString==null)
        return 0;
    else{
        //如果为字符串长度<4则返回error
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

//判断一个字符串是否是英文+数字组成
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

//检测小数,是整数也可通过检测,并且可以是负数
//return:1 合法 0 不合法
function fucCheckFloat(str)
{
 	var iStr,fStr;//整数部分和小数部分
	if(str.indexOf("-")!=-1){
		if(str.indexOf("-")!=0){
		  return 0;
		}else{
		  str = str.substring(1);//正数
		}	  		
	}	 
 if(str.indexOf(".")!=-1){//有小数
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
 * 按格式检查小数
 * 
 * @param str 需要检查的字符串
 * @param integerDigits 整数部分位数
 * @param decimalDigits 小数部分位数
 * @return 合法返回1，不合法返回正确的格式
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

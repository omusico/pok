function parseNumTotalCom(num){
  var arrHundred=new Array(0,1,2,3,4,5,6,7,8,9);
  var arrTen=new Array(0,1,2,3,4,5,6,7,8,9);
  var arrOne=new Array(0,1,2,3,4,5,6,7,8,9);
  var count=0;
  for(var i=0;i<arrHundred.length;i++){
    for(var j=0;j<arrTen.length;j++){
      for(var k=0;k<arrOne.length;k++){
        var value=arrHundred[i]+arrTen[j]+arrOne[k];
        if(value==num){
          count++;
        }
      }
    }
  }
  return count;
}

function parseNumTotalGrSix(num){
  var arrHundred=new Array(0,1,2,3,4,5,6,7,8,9);
  var arrTen=new Array(0,1,2,3,4,5,6,7,8,9);
  var arrOne=new Array(0,1,2,3,4,5,6,7,8,9);
  var count=0;
  var wholeStrNum="";
  for(var i=0;i<arrHundred.length;i++){
    for(var j=0;j<arrTen.length;j++){
      for(var k=0;k<arrOne.length;k++){
        if(arrHundred[i]!=arrTen[j] && arrTen[j]!=arrOne[k] && arrHundred[i]!=arrOne[k]){
	        var value=arrHundred[i]+arrTen[j]+arrOne[k];
	        if(value==num){
	          var strNum=SortNum(arrHundred[i]+","+arrTen[j]+","+arrOne[k]);
	          if(wholeStrNum.indexOf(strNum)==-1){
	            count++;
	            if(wholeStrNum!=""){
		          wholeStrNum = wholeStrNum + "$";
		        }
		        wholeStrNum = wholeStrNum + strNum;
	          }
	          
	        }
        }
      }
    }
  }
  return count;
}
function parseNumTotalGrThr(num){
  var arrHundred=new Array(0,1,2,3,4,5,6,7,8,9);
  var arrTen=new Array(0,1,2,3,4,5,6,7,8,9);
  var arrOne=new Array(0,1,2,3,4,5,6,7,8,9);
  var count=0;
  var wholeStrNum="";
  for(var i=0;i<arrHundred.length;i++){
    for(var j=0;j<arrTen.length;j++){
      for(var k=0;k<arrOne.length;k++){
        if(arrHundred[i]==arrTen[j] && arrHundred[i]!=arrOne[k] || arrHundred[i]==arrOne[k] && arrHundred[i]!=arrTen[j] || arrTen[j]==arrOne[k] && arrTen[j]!=arrHundred[i]){
	        var value=arrHundred[i]+arrTen[j]+arrOne[k];
	        if(value==num){
	          var strNum=SortNum(arrHundred[i]+","+arrTen[j]+","+arrOne[k]);
	          if(wholeStrNum.indexOf(strNum)==-1){
	            count++;
	            if(wholeStrNum!=""){
		          wholeStrNum = wholeStrNum + "$";
		        }
		        wholeStrNum = wholeStrNum + strNum;
	          }
	          
	        }
        }
      }
    }
  }
  return count;
}

function mapTran(mark,arrMark,arrReal){
  var real="";
  for(var i=0; i<arrMark.length; i++){
    if(mark==arrMark[i]){
      real=arrReal[i];
    }
  }
  return real;
}

function formatNum(intValue){
  var strValue="";
  if(intValue<10){
    strValue="0"+intValue;
  }else{
    strValue=intValue;
  }
  return strValue;
}

function formatNumThr(intValue){
  var strValue="";
  if(intValue<10){
    strValue="00"+intValue;
  }else if(intValue<100){
    strValue="0"+intValue;
  }else{
    strValue=intValue;
  }
  return strValue;
}


function generRandomNum(min,max)
{
	return Math.round((max-min)*Math.random()+min);
}


function numbertomoney(zhushustr)
{
    var str="￥"+zhushustr+".00";
    return str;
}
function SortNum(codes)
{
	var arrCodes = codes.split(",");
	for(var i=0;i<arrCodes.length;i++)
	{
		var chg;
		var exchange = false;
		var startIndex=arrCodes.length-1;
		for(var j=startIndex;j>i;j--)
		{
			if(parseInt(arrCodes[j])<parseInt(arrCodes[j-1]))
			{
				chg = arrCodes[j];
				arrCodes[j] = arrCodes[j-1];
				arrCodes[j-1] = chg;
				exchange = true;
			}
		}
		if(!exchange)
		{
			return arrCodes;
		}
	}
	return arrCodes;
}
function SortFunction_Int(a,b)
{
	if(parseInt(a)<parseInt(b))
	{
		return -1;
	}
	else if(parseInt(a)==parseInt(b))
	{
		return 0;
	}
	else
	{
		return 1;
	}
	//负值和等值不变，正值需要交换位置(当函数返回值为1的时候就交换两个数组项的顺序，否则就不交换)循环比较
}
/*function SortFunction_Intt(a,b)
{
	return (parseInt(b)-parseInt(a));
}*/
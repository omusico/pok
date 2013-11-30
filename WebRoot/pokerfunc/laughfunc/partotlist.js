///¸£²Ê3DµÄÍ¶×¢ÕóÁÐ
function getParTot(num){

var partot=new Array(28);
partot[0]=1;
partot[1]=3;
partot[2]=6;
partot[3]=10;
partot[4]=15;
partot[5]=21;
partot[6]=28;
partot[7]=36;
partot[8]=45;
partot[9]=55;
partot[10]=63;
partot[11]=69;
partot[12]=73;
partot[13]=75;
partot[14]=75;
partot[15]=73;
partot[16]=69;
partot[17]=63;
partot[18]=55;
partot[19]=45;
partot[20]=36;
partot[21]=28;
partot[22]=21;
partot[23]=15;
partot[24]=10;
partot[25]=6;
partot[26]=3;
partot[27]=1;

var numValue=partot[num];
return numValue;
}

function getParTotGrSix(num){
    var partot=new Array(27);
    partot[0]=0;
    partot[1]=0;
    partot[2]=0;
    
	partot[3]=1;
	partot[4]=1;
	partot[5]=2;
	partot[6]=3;
	partot[7]=4;
	partot[8]=5;
	partot[9]=7;
	partot[10]=8;
	partot[11]=9;
	partot[12]=10;
	partot[13]=10;
	partot[14]=10;
	partot[15]=10;
	partot[16]=9;
	partot[17]=8;
	partot[18]=7;
	partot[19]=5;
	partot[20]=4;
	partot[21]=3;
	partot[22]=2;
	partot[23]=1;
	partot[24]=1;
	
	partot[25]=0;
	partot[26]=0;

	var numValue=partot[num];
	return numValue;
}

function getParTotGrThr(num){
    var partot=new Array(27);
    partot[0]=0;
	partot[1]=1;
	partot[2]=2;
	
	partot[3]=1;
	partot[4]=3;
	partot[5]=3;
	partot[6]=3;
	partot[7]=4;
	partot[8]=5;
	partot[9]=4;
	partot[10]=5;
	partot[11]=5;
	partot[12]=4;
	partot[13]=5;
	partot[14]=5;
	partot[15]=4;
	partot[16]=5;
	partot[17]=5;
	partot[18]=4;
	partot[19]=5;
	partot[20]=4;
	partot[21]=3;
	partot[22]=3;
	partot[23]=3;
	partot[24]=1;
	
	partot[25]=2;
	partot[26]=1;

	var numValue=partot[num];
	return numValue;
}


function getParTotGr(numb){
	var numValue=getParTotGrThr(numb)+getParTotGrSix(numb);
	return numValue;
}
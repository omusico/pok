var framePlay;

function playLoad(){
    framePlay = document.frames['fraplay'];
	if(framePlay.document.readyState=='complete'){
      getTopIssNum();
	}
}

function getTopIssNum()
{
    framePlay.document.all("markiss").value=document.all("hidmarkiss").value;
	//alert(document.all("hidmarkiss").value);
    framePlay.document.all("getissuenumber").value=document.all("hidmulissvalue").value;
    framePlay.document.all("issuetype").value=document.all("hidissuetype").value;
    framePlay.document.all("selling").value=document.all("hidselling").value;
    setTimeout("getTopIssNum()",1000);
	return;
}
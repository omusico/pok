<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ดํฮ๓าณ</title>
<script language="javascript">
function remind(){
  var str=document.all("remind");
  alert(str.value);
  window.close();
}
</script>
</head>
<body onLoad=remind()>
  <input type="hidden" name="remind" value="<%=(String)session.getAttribute("mes") %>">
</body>
</html>
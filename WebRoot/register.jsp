<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��������-��ƱͶע��</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pktrade/pk/dg/public.js" type="text/javascript"></script>
<script src="/pktrade/pk/dg/onload.js" type="text/javascript"></script>

<style type="text/css">
.image1 a:link,.image1 a:visited { color:#00F; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 li { float:left; color:#FFF;margin:0 20px}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 
.image3 { background:url(/pkimages/mainimages/layout6.gif);}
.image3 a:link,.image3 a:visited { margin:0px; text-decoration:none;}
.image4 { background:url(/pkimages/mainimages/regimage4.gif);}
.image6 { background:url(/pkimages/mainimages/regimage6.gif) repeat-y;}
</style>
</head>

<body>

<table border=0 cellpadding="0" cellspacing="0">
   <tr>
      <td>
         <iframe name="fratitle" id="fratitle" frameborder="0" width="920" height="220"  scrolling="no" src="/title.jsp"></iframe>
      </td> 
   </tr>
</table>


<table align=center width=910 border=0 class="image6">
<tr>
<td> 
    <form name="formregister" action="/servlet/servregister" method="post">
      <table align=center width=700 border="0" bordercolor="#FFDBC4" cellpadding="3" cellspacing="0">
        <jsp:useBean id="reginforemind" type="beanpac.RegRemindInfo" scope="session" />
        <tr>
         <td><br>
         </td>
        </tr>
        <tr align="center">
          <td height="30" colspan="2" style="color:#FF0000;font-weight:bold "><font size=3 bold>�� Ա ע ��</font></td>
        </tr>
        <tr align="left">
          <td height="30" colspan="2"  >˵����ע���Ա��ɵ�½����ƽ̨���д���������</td>
        </tr>
        <tr align="left">
          <td height="30" colspan="2"><font color="red"> ���´��Ǻ�(*)������Ϊ������Ŀ��</font></td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >�û�����<font color="red">(*)</font></td>
          <td align="left" >
           <input type="text" name="username" size="16" value="">
           [<font color=red>ע������޸ģ�</font>]
           <font color=red>
            <jsp:getProperty name="reginforemind" property="userName"/>
           </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >Ͷע���룺<font color="red">(*)</font></td>
          <td align="left" >
          <input type="password" name="password" size="16" value="">
          <font color=red>
           <jsp:getProperty name="reginforemind" property="passWord"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >ȷ�����룺<font color="red">(*)</font></td>
          <td align="left" >
          <input type="password" name="confirm" size="16" value="">
          <font color=red>
           <jsp:getProperty name="reginforemind" property="confirmPass"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >��֤�룺<font color="red">(*)</font></td>
          <td align="left" >
          <input type="text" name="valcode" size="16" value="">
          <img border=0 src="/servlet/valimage">
          <font color=red>
           <jsp:getProperty name="reginforemind" property="valCode"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >�Ա�<font color="red">(*)</font></td>
          <td align="left" >
          <input type="radio" name="sex" size="16" value="��" checked>��
          <input type="radio" name="sex" value="Ů" size="16">Ů
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >��ʵ������<font color="red">(*)</font></td>
          <td align="left" >
            <input type="text" name="realname" size="16" value="">
            [����������������<font color=red>ע������޸ģ�</font>]
            <font color=red>
             <jsp:getProperty name="reginforemind" property="realName"/><br>
            </font>
              <font color="blue">����������һ����Ҫ���ݣ��������п��Ļ�������������д����ʵ�����������޷��������֡�</font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >���֤���룺<font color="red">(*)</font></td>
          <td align="left" >
            <input type="text" name="idcardnumber" size="16" value="">
            [������Ҫ���ݣ�������15λ����18λ�ĺ��롣<font color=red>ע������޸ģ�</font>]
            <font color=red>
             <jsp:getProperty name="reginforemind" property="idNum"/>
            </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >E-mail��<font color="red">(*)</font></td>
          <td align="left" >
          <input type="text" name="email" size="16" value="">
          [����д���ĳ������䣬����������վ֪ͨ]
          <font color=red>
           <jsp:getProperty name="reginforemind" property="email"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >��ϵ�绰��<font color="red">(*)</font></td>
          <td align="left" >
          <input type="text" name="contactphone" size="16" value="">
          [��Ҫ��ϵ��ʽ ��ʽ������-�绰����]
          <font color=red>
           <jsp:getProperty name="reginforemind" property="conPhone"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >�ֻ����룺<font color="red">(*)</font></td>
          <td align="left" >
          <input type="text" name="mobilephone" size="16" value="">
          [��Ҫ��ϵ��ʽ]
          <font color=red>
           <jsp:getProperty name="reginforemind" property="cellPhone"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >��ϵ��ַ��</td>
          <td align="left" >
          <input type="text" name="address" size="16" value="">
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >MSN��</td>
          <td align="left" >
          <input type="text" name="msnnum" size="16" value="">
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >QQ��</td>
          <td align="left" >
          <input type="text" name="qqnum" size="16" value="">
          </td>
        </tr>
        <tr>
          <td align=center colspan=2>
            <table border=0 width=100%>
	             <tr>
		              <td width=60% align="right">
		                <input type="submit" name="submit" value="�ύע��">
		              </td>
		              <td align="left">
		               <font color=red>
		                 <jsp:getProperty name="reginforemind" property="sucMes"/>
		               </font>
		              </td>
	              </tr>
              </table>
           </td>
        </tr>
      </table>
    </form>
    </td>
    </tr>
    <tr>
     <td class="image3"><br>
     </td>
    </tr>
    </table>
</body>
</html>
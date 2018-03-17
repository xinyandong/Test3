<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String basePath = request.getScheme()+"://"
        +request.getServerName()+":"+request.getServerPort()+
        request.getContextPath()+"/";		
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/import.js"></script>
<title>数据导入</title>
</head>
<body>
   <form id="addForm">
      <p>
                 请选择要导入的客户资料:<input type="file" name="info" id="info"/>
      </p>
      <p>
         <input type="button" value="上传" />
         <input type="reset" value="清除" />
      </p>
   </form>
   <div id="message" style="color:red;"></div>
</body>
</html>
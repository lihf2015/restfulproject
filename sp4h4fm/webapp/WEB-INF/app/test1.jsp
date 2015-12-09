<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=request.getSession().getAttribute("userId")==null?"":request.getSession().getAttribute("userId").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>LOGIN</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<%=basePath%>plugins/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    
  </head>
  
  <body>
  <div class="container">
<h1 class="page-header">User Login</h1>
<%=user %>
<form action="<%=basePath%>person" method="post">
<input type="hidden" name="_method" value="patch" />
<input type="submit"/>


</form>
 </div> 
    <script src="<%=basePath%>plugins/jquery/jquery-1.10.2.js"></script>
    <script src="<%=basePath%>plugins/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>

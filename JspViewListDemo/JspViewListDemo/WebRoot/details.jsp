<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import = "dao.ItemsDAO,entity.Items" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'details.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<h1>商品详情</h1>
  	
  	<hr>
  	
  	<%
  		request.setCharacterEncoding("utf-8");
  		//out.println(request.getParameter("id"));
  	 %> 
  	 <center>
    	<table width = "750" height = "60" cellpadding = "0" cellspacing="0" border = "0">
    		<tr>
    			
    			<%
    				ItemsDAO itemDao = new ItemsDAO();
    				Items item = itemDao.getItemsByIdA(Integer.parseInt(request.getParameter("id")));
    			 	if(item!=null){
    			 %>
    			<td width="70%" valign = "top">
    				<table>
    					<tr>
    						<td rowspan = "4"><img src="images/<%=item.getPicture() %>" width="200" height="160" ></td>
    					</tr>
    					<tr><b><%=item.getName() %></b></tr>
    					<tr><b><%=item.getCity() %></b></tr>
    					<tr><b><%=item.getPrice() %></b></tr>
    				</table>
    			</td>
    			<% } %>
    			<%
    				String list = "";
    				//从客户端获得cookie集合
    				Cookie[] cookies = request.getCookies();
    				//遍历cookie集合
    				if(cookies!=null&&cookies.length>0){
	    				for(Cookie c : cookies){
	    					if(c.getName().equals("ListViewCookie")){
	    						list = c.getValue();
	    					}
	    				}
    				}
    				list+=request.getParameter("id")+",";
    				//如果浏览记录超过1000条，清零
    				String[] arr = list.split(",");
    				if(arr!=null&&arr.length>0){
    					if(arr.length>=1000)list="";
    				}
    				Cookie cookie = new Cookie("ListViewCookie",list);
    				response.addCookie(cookie);
    			 %>
    			<!-- 浏览过的商品 -->
    			<td width = "30%" bgcolor="#EEE" align = "center">
    				<br>
    				<b>您浏览过的商品</b><br>
    				<!-- 循环开始 -->
    				<%
    					ArrayList<Items> itemList = itemDao.getViewList(list);
    					if(itemList!=null&&itemList.size()>0){
    						for(Items itemm:itemList){
    							
    				 %>
    				<div>
    					<dl>
    						<dt>
    							<a href="details.jsp?id=<%=itemm.getId()%>"><img src = "images/<%=itemm.getPicture() %>" width="120" height="60"/></a>
    						</dt>
    						<dd><%=itemm.getName() %></dd>
    						<dd><%=itemm.getCity() %></dd>
    					</dl>
    				</div>
    				<%
    						}
    					}
    				 %>
    				<!-- 循环结束 -->
    			</td>
    		</tr>
    	
    	</table>
    
    </center>
  </body>
</html>

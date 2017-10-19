<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="
    	de.profil.*,
    	de.profil.beans.*,
    	java.util.*"
%>

<div class="header">
	<h1 class="brand-logo"><a href="<%=request.getContextPath()%>/index.jsp"><%=Config.ESHOP_NAME %></a></h1>
 
	<span class="shopping-cart"><a href="<%=request.getContextPath()%>/content/warenkorb.jsp"><i class="fa fa-shopping-cart"></i></a></span>
	<div class="clear-fix"></div>
</div>
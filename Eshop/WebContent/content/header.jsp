<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="
    	de.profil.*,
    	de.profil.beans.*,
    	java.util.*"
%>

<%

OrderBean ob = new OrderBean(session, request);
ob.initializeOrder();

String rootPath = request.getContextPath();

%>

<div class="header">

	<div class="container">
	
		<h1 class="brand-logo"><a href="<%=rootPath%>/index.jsp"><%=Config.ESHOP_NAME %></a></h1>
	 
	 	<form class="shopping-cart" action="<%=rootPath %>/content/warenkorb.jsp">
	 	
	 		<button class="button button-medium button-neutral">
	 			 <i class="fa fa-shopping-cart"></i>
	 			 <span>Zum Warenkorb</span>		
	 		</button>
	 	
	 	</form>
		
		<div class="clear-fix"></div>
		
		<%=ob.productCounter("cart-count") %>
		
		<div class="clear-fix"></div>
	
	</div>
	
</div>
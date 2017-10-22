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
	
		<div class="brand">
			<h1 class="brand-logo"><a href="<%=rootPath%>/index.jsp"><%=Config.ESHOP_NAME %></a></h1>
			<h2 class="brand-slogan">Teuer macht lustig!</h2>
		</div>
	 
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

<div class="navbar">

	<div class="container">
	
		<ul class="nav-items">
			<li><a href="<%=rootPath %>/index.jsp" >Startseite</a></li>
			<li><a href="<%=rootPath %>/content/zahlungsarten.jsp" >Zahlung und Lieferung</a></li>
			<li><a href="<%=rootPath %>/content/impressum.jsp" >Impressum</a></li>
		</ul>
	
		<div class="clear-fix"></div>
	
	</div>

</div>
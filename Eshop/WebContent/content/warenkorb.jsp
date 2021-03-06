<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import=
    	"de.profil.*,
    	de.profil.beans.*,
    	javax.sql.DataSource, 
    	java.util.*"
    
    %>
    
<%

	OrderBean orderBean = new OrderBean(session, request);
	orderBean.initializeOrder();
	orderBean.addProductsFromRequest();
	orderBean.removeProductFromRequest();
	
	orderBean.saveOrder();
	
	Order order = orderBean.getOrder();
	
	String nextButtonDisabled = order == null || order.getAllProducts().length <= 0 ? " disabled " : "";
		

%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Fira+Sans:400,700" rel="stylesheet">

<title><%=Config.ESHOP_NAME %></title>
</head>
<body>
<%@include file="header.jsp" %>

	<div class="container">
	
		<div class="content">
		
			<h1>Ihr Warenkorb</h1>
		
			<%=orderBean.listShoppingCartItems("cart-positions") %>
			
			<%=orderBean.shoppingCartSummary("cart-summary", "cart-summary-info", "cart-total-info") %>
			
			<div class="clear-fix"></div>
			
			<div class="justified-buttons">
			
		  		<form action="../index.jsp">
		  			<button class="button button-medium button-default">Zur Produktauswahl</button>  		
		  		</form>
		  		
		  		<form action="order_customer.jsp">
		  			<button class="button button-medium button-positive" <%=nextButtonDisabled %>>Weiter</button>  		
		  		</form>
			
			</div>
		
		</div>

  </div>	
  	
</body>
</html>
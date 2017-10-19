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
	
	orderBean.saveOrder();
	
	Order order = orderBean.getOrder();
	Product[] products = order.getAllProducts();
		

%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title><%=Config.ESHOP_NAME %></title>
</head>
<body>
<%@include file="header.jsp" %>

<div id="container">
	
		<h1>Warenkorb</h1>
		
		<%=orderBean.listShoppingCartItems() %>

  		<a href="../index.jsp"><button type="button">Weiter Einkaufen</button></a>
  		<a href="order_customer.jsp"><button type="button">Bestellen</button></a>
  		
  </div>		
  </body>
</html>
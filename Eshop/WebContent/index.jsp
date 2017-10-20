<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="
    	de.profil.*,
    	de.profil.beans.*,
    	javax.sql.DataSource, 
    	java.util.*"
%>

<%

// Starten der Session und laden des evtl bereits bestehenden Order-Objekts
OrderBean orderBean = new OrderBean(session, request);
orderBean.initializeOrder();
orderBean.saveOrder();


EshopDatabaseAccessor dbAccess = new EshopDatabaseAccessor();

List<Product> products = dbAccess.fetchJoined(() -> new Product());

List<Bundle> bundles = dbAccess.fetchJoined(() -> new Bundle());

for(Bundle b : bundles) {
	
	b.fetchProducts(dbAccess);
	products.add(b);
	
}

ProductBean pb = new ProductBean(products);

String dbErrorMessage = "";

String prodTest = "";

if(products != null) {
	
	for(Product p : products)
		prodTest += p.getName() + "<br>";
	
}
else {
	
	dbErrorMessage = "Es ist ein Fehler bei der Verbindung zur Datenbank aufgetreten.";
	
}



%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title><%=Config.ESHOP_NAME %></title>
</head>
<body>

<%@include file="content/header.jsp" %>

<div class="nav">
	<a href="index.jsp">Startseite</a>
	<a href="content/warenkorb.jsp">Warenkorb</a>
	<a href="content/zahlungsarten.jsp">Zahlungsarten</a>
	<a href="content/impressum.jsp">Impressum</a>
</div>

<div id="container">
	
	<p><%=dbErrorMessage %></p>
		
	<div>
	
	<%=pb.htmlProductsView() %>
	
	</div>

	<a href="content/warenkorb.jsp"><button type="button">zum Warenkorb</button></a>
	
	
	

</div>

</body>
</html>
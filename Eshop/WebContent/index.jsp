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

List<Product> products = dbAccess.fetchJoined(() -> new Product(), "active=1");

String productsError = products == null ? "Es ist ein Fehler beim Laden der Produkte aufgetreten. Sorry! :(" : "";

ProductBean productBean = new ProductBean(products);

List<Bundle> bundles = dbAccess.fetchJoined(() -> new Bundle(), "active=1");
List<Product> bundleProducts = new ArrayList<Product>();

String bundleError = "Es ist ein Fehler beim Laden der Bundles aufgetreten. Tut uns schrecklich leid! :(";

if(bundles != null) {
	
	bundleError = "";
	
	for(Bundle b : bundles) {
		
		b.fetchProducts(dbAccess);
		bundleProducts.add(b);
		
	}
	
}

ProductBean bundleBean = new ProductBean(bundleProducts);

String noPackagingAndShippingValue = String.format("%.2f EUR", Order.NO_PACKAGING_AND_SHIPPING_VALUE);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Fira+Sans:400,700" rel="stylesheet">

<title><%=Config.ESHOP_NAME %></title>
</head>
<body>

<%@include file="content/header.jsp" %>

<div class="container">
	
	<h4>Keine Versand- und Verpackungskosten ab einem Bestellwert von <%=noPackagingAndShippingValue %>!</h4>
	
	<div class="products-overview">
	
		<h2>Aktuelle Sonderangebote:</h2>
	
		<p><%=bundleError %></p>
		
		<%=bundleBean.htmlProductsOverview("img", "content product-details", "row", "cell") %> 
	
	</div>
		
	<div class="products-overview">
	
		<h2>Unsere Produkte:</h2>
	
		<p><%=productsError %></p>
		
		<%=productBean.htmlProductsOverview("img", "content product-details", "row", "cell") %> 
	
	</div>

</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="
    	de.profil.*,
    	de.profil.beans.*,
    	javax.sql.DataSource, 
    	java.util.*"
%>

<%


EshopDatabaseAccessor dbAccess = new EshopDatabaseAccessor();

List<Country> countries = dbAccess.fetch(() -> new Country());
List<Payment> payments = dbAccess.fetchJoined(() -> new Payment(), "pay_countries.country_code='DE'");
List<Product> products = dbAccess.fetchJoined(() -> new Product());

List<Bundle> bundles = dbAccess.fetchJoined(() -> new Bundle());

for(Bundle b : bundles) {
	
	b.fetchProducts(dbAccess);
	products.add(b);
	
}

ProductBean pb = new ProductBean(products);

String dbErrorMessage = "";

Country[] countryArr = countries.toArray(new Country[0]);
Arrays.sort(countryArr, (x, y) -> x.getName().compareTo(y.getName()));

Payment[] paymentArr = payments.toArray(new Payment[0]);
Arrays.sort(paymentArr, (x, y) -> x.getId() - y.getId());

SelectBoxBuilder countryBean = new SelectBoxBuilder(countryArr);
SelectBoxBuilder paymentBean = new SelectBoxBuilder(paymentArr);

String prodTest = "";

if(countries != null && payments != null && products != null) {
	
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

<div id="container">
	
	<p><%=dbErrorMessage %>
		<div class="nav">
			<a href="index.jsp">Startseite</a>
			<a href="content/warenkorb.jsp">Warenkorb</a><tab>
			<a href="content/kundendaten.jsp">Kundendaten</a><tab>
			<a href="content/zahlungsarten.jsp">Zahlungsarten</a><tab>
			<a href="content/uebersicht.jsp">Übersicht</a><tab>
			<a href="content/impressum.jsp">Impressum</a>
		</div>
		
	<h3>Wir beliefern folgende europäische Länder:</h3>
	
	<%=countryBean.htmlSelect("country", 1, "countries") %>
	
	<h3>In Deutschland bieten wir folgende Zahlungsmethoden an:</h3>
	
	<%=paymentBean.htmlSelect("payment", 1, "payments") %>
	
	<h3>Hier unsere Produktauswahl:</h3>
	<div>
	<%=pb.htmlProductsView() %>
	
	</div>
	<br>
	<br>
	<br>
	<button type="button">zum Warenkorb</button>
	
	
	

</div>

</body>
</html>
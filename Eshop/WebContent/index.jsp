<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import=
    	"net.yetibyte.snowstorm.*, 
    	de.profil.*,
    	de.profil.beans.*,
    	javax.sql.DataSource, 
    	java.util.*"
%>

<%

// Bin jetzt auch dabei!
// Kevin ist dabei!

DataSource dataSource = Config.getDataSource();
DatabaseAccessor dbAccess = new DatabaseAccessor(dataSource);

List<Country> countries = dbAccess.fetch(() -> new Country());
List<Payment> payments = dbAccess.fetchJoined(() -> new Payment(), "pay_countries.country_code='DE'");
List<Product> products = dbAccess.fetchJoined(() -> new Product());

List<Bundle> bundles = dbAccess.fetchJoined(() -> new Bundle());

for(Bundle b : bundles) {
	
	b.fetchProducts(dbAccess);
	products.add(b);
	
}

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
<title><%=Config.ESHOP_NAME %></title>
</head>
<body>

<div id="container">
	
	<div class="header">
		<h1>Willkommen bei <%=Config.ESHOP_NAME %></h1>
		<h2>Bei uns nur das Beste!</h2>
	</div>

	<p><%=dbErrorMessage %>
	
	
		<div class="nav">
			<a href="warenkorb.jsp">Warenkorb</a>
			<a href="kundendaten.jsp">Kundendaten</a>
			<a href="zahlungsarten.jsp">Zahlungsarten</a>
			<a href="uebersicht.jsp">Übersicht</a>
		</div>
	
	
	
	<h3>Wir beliefern folgende europäische Länder:</h3>
	
	<%=countryBean.htmlSelect("country", 1, "countries") %>
	
	<h3>In Deutschland bieten wir folgende Zahlungsmethoden an:</h3>
	
	<%=paymentBean.htmlSelect("payment", 1, "payments") %>
	
	<h3>Hier unsere Produktauswahl:</h3>
	
	<p><%=prodTest %></p>
	
	
	

</div>

</body>
</html>
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

DataSource dataSource = Config.getDataSource();
DatabaseAccessor dbAccess = new DatabaseAccessor(dataSource);

List<Country> countries = dbAccess.fetch(() -> new Country());
List<Payment> payments = dbAccess.fetchJoined(() -> new Payment(), "pay_countries.country_code='DE'", null);

String dbErrorMessage = "";

CountryBean countryBean = new CountryBean(countries);
PaymentBean paymentBean = new PaymentBean(payments);

if(countries != null && payments != null) {
	
	// Stuff
	
}
else {
	
	dbErrorMessage = "Es ist ein Fehler bei der Verbindung zur Datenbank aufgereten.";
	
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
	
	<h3>Wir beliefern folgende europ�ische L�nder:</h3>
	
	<%=countryBean.htmlSelect("country", 1, "Bitte ausw�hlen...") %>
	
	<h3>In Deutschland bieten wir folgende Zahlungsmethoden an:</h3>
	
	<%=paymentBean.htmlSelect("payment", 1, "Zahlung w�hlen...", null, null) %>
	

</div>

</body>
</html>
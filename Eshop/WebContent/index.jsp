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

String dbErrorMessage = "";

CountryBean countryBean = new CountryBean(countries);

if(countries != null) {
	
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
<title>K. Adam's Online Store</title>
</head>
<body>

<h1>Willkommen bei <%=Config.ESHOP_NAME %></h1>

<h2>Wir beliefern folgende europäische Länder:</h2>

<p><%=dbErrorMessage %>

<%=countryBean.htmlSelect("country", 1, "Bitte auswählen...") %>

</body>
</html>
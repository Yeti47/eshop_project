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
	
	EshopDatabaseAccessor dbAccess = new EshopDatabaseAccessor();

	List<Country> countries = dbAccess.fetch(() -> new Country());
	
	String countriesDbError = "";
	String dbErrorVisibilty = "error-hidden";

	Country[] countryArr = null;
	
	if(countries != null) {
		
		countryArr = countries.toArray(new Country[0]);
		Arrays.sort(countryArr, (x, y) -> x.getName().compareTo(y.getName()));
				
	}
	else {
		
		countriesDbError = "Fehler beim Laden der verf¸gbaren L‰nder.";
		dbErrorVisibilty = "";
		
	}
	
	SelectBoxBuilder countryBuilder = new SelectBoxBuilder(countryArr);
	countryBuilder.setDefaultText("Bitte ausw‰hlen...");
	countryBuilder.setDefaultValue("-1");
	

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

<%@include file="../content/header.jsp" %>

<form id="customer-form" action="order_customer.jsp" method="post">

	<div class="form-group">
	
		<h2>Ihre Anschrift:</h2>
		
		<label class="label-medium" for="title">Anrede:</label>
		<select id="title" name="title">
			<option value="Frau">Frau</option>
			<option value="Frau">Herr</option>
			<option value="Frau">Firma</option>
		</select>
		<br>
		
		<label class="label-medium" for="firstname">Vorname:</label>
		<input type="text" id="firstname" name="firstnmae"/>
		<br>
		
		<label class="label-medium" for="lastname">Name:</label>
		<input type="text" id="lastname" name="lastname"/>
		<br>
		
		<label class="label-medium" for="street">Straﬂe:</label>
		<input type="text" id="street" name="street"/>
		<br>
		
		<label class="label-medium" for="houseno">Hausnummer:</label>
		<input type="text" id="houseno" name="houseno"/>
		<br>
		
		<label class="label-medium" for="postcode">PLZ:</label>
		<input type="text" id="postcode" name="postcode"/>
		<br>
		
		<div class="error-db <%=dbErrorVisibilty %>">
			<%=countriesDbError %>
		</div>
		
		<label class="label-medium" for="country">Land:</label>
		
		<%=countryBuilder.htmlSelect("country", 1, "country", "country") %>

	</div>

	

</form>

</body>
</html>
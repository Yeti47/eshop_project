<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import=
    	"de.profil.*,
    	de.profil.beans.*,
    	javax.sql.DataSource, 
    	java.util.*"
%>

<%
	
	//Starten der Session und laden des evtl bereits bestehenden Order-Objekts
	OrderBean orderBean = new OrderBean(session, request);
	orderBean.initializeOrder();
	orderBean.saveOrder();
	
	Customer customer = orderBean.getOrder().getCustomer();
	
	// Wenn noch kein Kunde angelegt wurde, zurück auf die Startseite
	if(customer == null || customer.getAddress() == null) {
		
		pageContext.forward("warenkorb.jsp");
		
		
	}
	
	Address address = customer.getAddress();

	EshopDatabaseAccessor dbAccess = new EshopDatabaseAccessor();
	dbAccess.setSelectDistinct(true);	
	
	String countriesDbError = "";
	String dbErrorVisibilty = "error-hidden";

	List<Payment> payments = dbAccess.fetchJoined(() -> new Payment());
	Payment[] paymentArr = null;
	
	if(payments == null) {
		
		countriesDbError = "Fehler beim Laden der verfügbaren Länder.";
		dbErrorVisibilty = "";
		
	}
	
	SelectBoxBuilder paymentBuilder = new SelectBoxBuilder(paymentArr);
	paymentBuilder.setDefaultText("Bitte auswählen...");
	paymentBuilder.setDefaultValue("NONE");

	Receiver receiver = new Receiver();
	
	String action = WebUtility.getNonNullParam(request, "action");

	if(action.equals("send2")) {
		
		
		
	}

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

	<div class="content-right">
	
		<h1>Schritt 2</h1>
	
		<form id="customer-form" action="order_customer.jsp" method="post">
	
		<div class="form-group">
		
			<h2>Ihre Rechnungsanschrift:</h2>
			
			<label class="label-medium" for="title">Anrede:</label>
			<input type="text" id="title" name="title" value="<%=customer.getTitle().toString() %>" readonly>
			<br>
			
			<label class="label-medium" for="firstname">Vorname:</label>
			<input type="text" id="firstname" name="firstname" value="<%=customer.getFirstname() %>" readonly/>
			<br>
			
			<label class="label-medium" for="lastname">Name:</label>
			<input type="text" id="lastname" name="lastname" value="<%=customer.getName() %>" readonly/>
			<br>
			
			<label class="label-medium" for="street">Straße:</label>
			<input type="text" id="street" name="street" value="<%=address.getStreet() %>" readonly/>
			<br>
			
			<label class="label-medium" for="houseno">Hausnummer:</label>
			<input type="text" id="houseno" name="houseno" value="<%=address.getHouseNumber() %>" readonly/>
			<br>
			
			<label class="label-medium" for="postcode">PLZ:</label>
			<input type="text" id="postcode" name="postcode" value="<%=address.getPostCode() %>" readonly/>
			<br>
			
			<label class="label-medium" for="city">Ort:</label>
			<input type="text" id="city" name="city" value="<%=address.getCity() %>" readonly/>
			<br>

			<label class="label-medium" for="country">Land:</label>
			<input type="text" id="title" name="title" value="<%=address.getCountry().getName() %>" readonly>
			<br>
			
			<label class="label-medium" for="email">E-Mail:</label>
			<input type="text" id="email" name="email" value="<%=customer.getEmail() %>" readonly/>
			<br>

		</div>
		
		<div class="form-group">
		
			<a href="order_customer.jsp" class="button">Zurück</a>
			<button type="submit" name="action" value="send2">Weiter</button>
		
		</div>
	
	</form>
	
	</div>

</div>


</body>
</html>
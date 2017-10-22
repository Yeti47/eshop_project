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

	EshopDatabaseAccessor dbAccess = new EshopDatabaseAccessor();
	dbAccess.setSelectDistinct(true);	
	
	String countriesDbError = "";
	String countryErrorVisibilty = "error-hidden";
	
	List<Country> countries = dbAccess.fetch(() -> new Country());

	Country[] countryArr = null;
	
	if(countries != null) {
		
		countryArr = countries.toArray(new Country[0]);
		Arrays.sort(countryArr, (x, y) -> x.getName().compareTo(y.getName()));
				
	}
	else {
		
		countriesDbError = "Fehler beim Laden der verfügbaren Länder.";
		countryErrorVisibilty = "";
		
	}
	
	
	SelectBoxBuilder countryBuilder = new SelectBoxBuilder(countryArr);
	countryBuilder.setDefaultText("Bitte auswählen...");
	countryBuilder.setDefaultValue("NONE");

	Receiver receiver = new Receiver();
	Address deliveryAddress = new Address();
	
	ReceiverForm receiverForm = new ReceiverForm(request);
	
	String action = WebUtility.getNonNullParam(request, "action2");

	if(action.equals("next")) {
		
		receiverForm.retrieveReceiverFromRequest(countryArr, customer);
		receiver = receiverForm.getReceiver();
		deliveryAddress = receiver.getAddress();
		
		countryBuilder.setPreSelectedItem(WebUtility.getNonNullParam(request, "d_country"));
		
		boolean isValid = receiverForm.validateReceiver(customer);
		
		if(isValid) {
			
			orderBean.getOrder().setCustomer(customer);
			orderBean.getOrder().setReceiver(receiver);
			orderBean.saveOrder();
			pageContext.forward("order_pay.jsp");
			
		}	
		
	}
	else if (action.equals("previous")) {
		
		pageContext.forward("order_customer.jsp");
		
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

<div class="container">

	<div class="content">
	
		<h1>Ihre Lieferanschrift</h1>
	
		<form id="receiver-form" action="order_receiver.jsp" method="post">
		
		<div class="form-group">
			
			<label class="label-medium" for="d_title">Anrede:</label>
			<%=receiverForm.titleSelectBox("d_title", "d_title") %>
			<br>
			
			<label class="label-medium" for="d_firstname">Vorname:</label>
			<input type="text" id="d_firstname" name="d_firstname" value="<%=receiver.getFirstname() %>"/>
			<span class="form-error"><%=receiverForm.getErrFirstName() %></span>
			<br>
			
			<label class="label-medium" for="d_lastname">Name:</label>
			<input type="text" id="d_lastname" name="d_lastname" value="<%=receiver.getName() %>"/>
			<span class="form-error"><%=receiverForm.getErrName() %></span>
			<br>
			
			<label class="label-medium" for="d_street">Straße:</label>
			<input type="text" id="d_street" name="d_street" value="<%=deliveryAddress.getStreet() %>"/>
			<span class="form-error"><%=receiverForm.getErrStreet() %></span>
			<br>
			
			<label class="label-medium" for="d_houseno">Hausnummer:</label>
			<input type="text" id="d_houseno" name="d_houseno" value="<%=deliveryAddress.getHouseNumber() %>"/>
			<span class="form-error"><%=receiverForm.getErrHouseNumber() %></span>
			<br>
			
			<label class="label-medium" for="d_postcode">PLZ:</label>
			<input type="text" id="d_postcode" name="d_postcode" value="<%=deliveryAddress.getPostCode() %>"/>
			<span class="form-error"><%=receiverForm.getErrPostCode() %></span>
			<br>
			
			<label class="label-medium" for="d_city">Ort:</label>
			<input type="text" id="d_city" name="d_city" value="<%=deliveryAddress.getCity() %>"/>
			<span class="form-error"><%=receiverForm.getErrCity() %></span>
			<br>
			
			<div class="error-db <%=countryErrorVisibilty %>">
				<%=countriesDbError %>
			</div>
			
			<label class="label-medium" for="d_country">Land:</label>
			
			<%=countryBuilder.htmlSelect("d_country", 1, "country", "d_country") %>
			<span class="form-error"><%=receiverForm.getErrCountry() %></span>
			<br>
			
			<label class="label-medium" for="d_phone">Telefon:</label>
			<input type="text" id="d_phone" name="d_phone" value="<%=customer.getPhone() %>"/>
			<span class="form-error"><%=receiverForm.getErrPhone() %></span>
			<br>
		
		
		</div>
		
		<div class="form-group">
		
			<div class="justified-buttons">
	
			  	<button class="button button-medium button-default" name="action2" value="previous">Zurück</button>  		
			  	<button class="button button-medium button-positive" name="action2" value="next">Weiter</button>  		
				
			</div>
		
		</div>
	
	</form>
	
	</div>

</div>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import=
    	"de.profil.*,
    	de.profil.beans.*,
    	java.util.*"
%>

<%
	
	//Starten der Session und laden des evtl bereits bestehenden Order-Objekts
	OrderBean orderBean = new OrderBean(session, request);
	orderBean.initializeOrder();
	orderBean.saveOrder();
	
	Order order = orderBean.getOrder();

	EshopDatabaseAccessor dbAccess = new EshopDatabaseAccessor();
	dbAccess.setSelectDistinct(true);	

	List<Country> countries = dbAccess.fetchJoined(() -> new Country());
	
	String countriesDbError = "";
	String dbErrorVisibilty = "error-hidden";

	Country[] countryArr = null;
	
	if(countries != null) {
		
		countryArr = countries.toArray(new Country[0]);
		Arrays.sort(countryArr, (x, y) -> x.getName().compareTo(y.getName()));
				
	}
	else {
		
		countriesDbError = "Fehler beim Laden der verf�gbaren L�nder.";
		dbErrorVisibilty = "";
		
	}
	
	SelectBoxBuilder countryBuilder = new SelectBoxBuilder(countryArr);
	countryBuilder.setDefaultText("Bitte ausw�hlen...");
	countryBuilder.setDefaultValue("NONE");
	
	// Wenn schon ein Kunden-Objekt in der Order besteht und in diesem ein Land hinterlegt ist, setze die Vorauswahl der Select-Box auf dieses Land
	if(order.getCustomer() != null && order.getCustomer().getAddress() != null && order.getCustomer().getAddress().getCountry() != null)
		countryBuilder.setPreSelectedItem(order.getCustomer().getAddress().getCountry().getCode());
	
	CustomerForm customerForm = new CustomerForm(request);

	Customer customer = order.getCustomer() != null ? order.getCustomer() : customerForm.getCustomer();
	Address address = customer.getAddress();
	
	String action = WebUtility.getNonNullParam(request, "action1");
	
	String deliveryChecked = "";
	
	if(action.equals("next")) {
		
		customerForm.retrieveCustomerFromRequest(countryArr);
		customer = customerForm.getCustomer();
		address = customer.getAddress();
		
		countryBuilder.setPreSelectedItem(WebUtility.getNonNullParam(request, "country"));
		
		deliveryChecked = WebUtility.getNonNullParam(request, "delivery");
		
		boolean isValid = customerForm.validateCustomer();
		
		if(isValid) {
			
			orderBean.getOrder().setCustomer(customer);
			orderBean.saveOrder();
			
			if(deliveryChecked.equals("checked"))
				pageContext.forward("order_receiver.jsp");
			else
				pageContext.forward("order_paym.jsp");
			
		}	
		
	}
	else if (action.equals("previous")) {
		
		pageContext.forward("warenkorb.jsp");
		
	}
	
	// Output
	String outFirstName = WebUtility.escapeHtmlChars(customer.getFirstname());
	String outLastName = WebUtility.escapeHtmlChars(customer.getName());
	String outStreet =  WebUtility.escapeHtmlChars(address.getStreet());
	String outHouseNumber =  WebUtility.escapeHtmlChars(address.getHouseNumber());
	String outPostcode =  WebUtility.escapeHtmlChars(address.getPostCode());
	String outCity =  WebUtility.escapeHtmlChars(address.getCity());
	String outEmail =  WebUtility.escapeHtmlChars(customer.getEmail());
	String outPhone =  WebUtility.escapeHtmlChars(customer.getPhone());

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Fira+Sans:400,700" rel="stylesheet">

<title><%=Config.ESHOP_NAME %></title>
</head>
<body>

<%@include file="header.jsp" %>

<div class="container">

	<div class="content">
	
		<h2>Ihre Rechnungsanschrift:</h2>
	
		<form id="customer-form" action="order_customer.jsp" method="post">
		
		<div class="form-group">
			
			<label class="label-medium" for="title">Anrede:</label>
			<%=customerForm.titleSelectBox("title", "title") %>
			<br>
			
			<label class="label-medium" for="firstname">Vorname:</label>
			<input type="text" id="firstname" name="firstname" value="<%=outFirstName %>"/>
			<span class="form-error"><%=customerForm.getErrFirstName() %></span>
			<br>
			
			<label class="label-medium" for="lastname">Name:</label>
			<input type="text" id="lastname" name="lastname" value="<%=outLastName %>"/>
			<span class="form-error"><%=customerForm.getErrName() %></span>
			<br>
			
			<label class="label-medium" for="street">Stra�e:</label>
			<input type="text" id="street" name="street" value="<%=outStreet %>"/>
			<span class="form-error"><%=customerForm.getErrStreet() %></span>
			<br>
			
			<label class="label-medium" for="houseno">Hausnummer:</label>
			<input type="text" id="houseno" name="houseno" value="<%=outHouseNumber %>"/>
			<span class="form-error"><%=customerForm.getErrHouseNumber() %></span>
			<br>
			
			<label class="label-medium" for="postcode">PLZ:</label>
			<input type="text" id="postcode" name="postcode" value="<%=outPostcode %>"/>
			<span class="form-error"><%=customerForm.getErrPostCode() %></span>
			<br>
			
			<label class="label-medium" for="city">Ort:</label>
			<input type="text" id="city" name="city" value="<%=outCity %>"/>
			<span class="form-error"><%=customerForm.getErrCity() %></span>
			<br>
			
			<div class="error-db <%=dbErrorVisibilty %>">
				<%=countriesDbError %>
			</div>
			
			<label class="label-medium" for="country">Land:</label>
			
			<%=countryBuilder.htmlSelect("country", 1, "country", "country") %>
			<span class="form-error"><%=customerForm.getErrCountry() %></span>
			<br>
			
			<label class="label-medium" for="email">E-Mail:</label>
			<input type="text" id="email" name="email" value="<%=outEmail %>"/>
			<span class="form-error"><%=customerForm.getErrEmail() %></span>
			<br>
			
			<label class="label-medium" for="phone">Telefon:</label>
			<input type="text" id="phone" name="phone" value="<%=outPhone %>"/>
			<span class="form-error"><%=customerForm.getErrPhone() %></span>
			<br>
			
			<label class="label-large" for="delivery">Ich m�chte eine abweichende Lieferanschrift angeben:</label>
			<input type="checkbox" id="delivery" name="delivery" value="checked" <%=deliveryChecked %>/>
			<br>
	
		</div>
		
		<div class="form-group">
		
			<div class="justified-buttons">
	
			  	<button class="button button-medium button-default" name="action1" value="previous">Zur�ck</button>  		
				<button class="button button-medium button-positive" name="action1" value="next">Weiter</button>  		
				
			</div>
		
		</div>

	</form>
	
	</div>

</div>


</body>
</html>
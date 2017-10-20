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
		
		countriesDbError = "Fehler beim Laden der verfügbaren Länder.";
		dbErrorVisibilty = "";
		
	}
	
	SelectBoxBuilder countryBuilder = new SelectBoxBuilder(countryArr);
	countryBuilder.setDefaultText("Bitte auswählen...");
	countryBuilder.setDefaultValue("NONE");
	
	Customer customer = new Customer();
	Address address = new Address();
	
	CustomerForm customerForm = new CustomerForm(customer);
	
	String action = WebUtility.getNonNullParam(request, "action");
	
	String deliveryChecked = "";
	
	if(action.equals("send")) {
		
		String countryCode = WebUtility.getNonNullParam(request, "country");
		
		customer.setTitleByString(WebUtility.getNonNullParam(request, "title"));
		customer.setFirstname(WebUtility.getNonNullParam(request, "firstname"));
		customer.setName(WebUtility.getNonNullParam(request, "lastname"));
		customer.setEmail(WebUtility.getNonNullParam(request, "email"));
		customer.setPhone(WebUtility.getNonNullParam(request, "phone"));
		
		address.setStreet(WebUtility.getNonNullParam(request, "street"));
		address.setHouseNumber(WebUtility.getNonNullParam(request, "houseno"));
		address.setPostCode(WebUtility.getNonNullParam(request, "postcode"));
		address.setCity(WebUtility.getNonNullParam(request, "city"));
		
		for(Country c : countries) {
			
			if(c.getCode().equals(countryCode))
				address.setCountry(c);
			
		}
		
		countryBuilder.setPreSelectedItem(WebUtility.getNonNullParam(request, "country"));
	
		customer.setAddress(address);
		
		deliveryChecked = WebUtility.getNonNullParam(request, "delivery");
		
		customerForm.setDeliveryAddressChecked(!"".equals(deliveryChecked));
		
		boolean isValid = customerForm.validateCustomer();
		
		if(isValid) {
			
			pageContext.forward("order_payment.jsp");
			
		}
		
		
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

	<div class="content-left">
	
	</div>
	
	<div class="content-right">
	
		<form id="customer-form" action="order_customer.jsp" method="post">
	
		<div class="form-group">
		
			<h2>Ihre Anschrift:</h2>
			
			<label class="label-medium" for="title">Anrede:</label>
			<%=customerForm.titleSelectBox("title", "title") %>
			<br>
			
			<label class="label-medium" for="firstname">Vorname:</label>
			<input type="text" id="firstname" name="firstname" value="<%=customer.getFirstname() %>"/>
			<span class="form-error"><%=customerForm.getErrFirstName() %></span>
			<br>
			
			<label class="label-medium" for="lastname">Name:</label>
			<input type="text" id="lastname" name="lastname" value="<%=customer.getName() %>"/>
			<span class="form-error"><%=customerForm.getErrName() %></span>
			<br>
			
			<label class="label-medium" for="street">Straße:</label>
			<input type="text" id="street" name="street" value="<%=address.getStreet() %>"/>
			<span class="form-error"><%=customerForm.getErrStreet() %></span>
			<br>
			
			<label class="label-medium" for="houseno">Hausnummer:</label>
			<input type="text" id="houseno" name="houseno" value="<%=address.getHouseNumber() %>"/>
			<span class="form-error"><%=customerForm.getErrHouseNumber() %></span>
			<br>
			
			<label class="label-medium" for="postcode">PLZ:</label>
			<input type="text" id="postcode" name="postcode" value="<%=address.getPostCode() %>"/>
			<span class="form-error"><%=customerForm.getErrPostCode() %></span>
			<br>
			
			<label class="label-medium" for="city">Ort:</label>
			<input type="text" id="city" name="city" value="<%=address.getCity() %>"/>
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
			<input type="text" id="email" name="email" value="<%=customer.getEmail() %>"/>
			<span class="form-error"><%=customerForm.getErrEmail() %></span>
			<br>
			
			<label class="label-medium" for="phone">Telefon:</label>
			<input type="text" id="phone" name="phone" value="<%=customer.getPhone() %>"/>
			<span class="form-error"><%=customerForm.getErrPhone() %></span>
			<br>
			
			<label class="label-large" for="delivery">Ich möchte eine abweichende Lieferanschrift angeben:</label>
			<input type="checkbox" id="delivery" name="delivery" value="checked" <%=deliveryChecked %>/>
			<br>
	
		</div>
		
		<div class="form-group">
		
			<button type="submit" name="action" value="send">Weiter</button>
		
		</div>
	
	</form>
	
	</div>

</div>


</body>
</html>
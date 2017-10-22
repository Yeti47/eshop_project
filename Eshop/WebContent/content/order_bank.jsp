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
	
	Customer customer = order.getCustomer();
	
	// Wenn noch kein Kunde angelegt wurde, zurück auf die Startseite
	if(customer == null || customer.getAddress() == null || customer.getAddress().getCountry() == null || order.getPayment() == null) {
		
		pageContext.forward("warenkorb.jsp");
		
	}
	
	Bank bankDetails = new Bank();
	
	String action = WebUtility.getNonNullParam(request, "action4");

	if(action.equals("next")) {
		
		
	}
	else if(action.equals("previous")) {
		
		pageContext.forward("order_paym.jsp");
		
	}
	
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
	
		<h1>Ihre Bankverbindung:</h1>
	
		<form id="payment-form" action="order_bank.jsp" method="post">
		
		<div class="form-group">
		
			<p>Da Sie sich für eine Zahlung per Bankeinzug entschieden haben, teilen Sie uns bitte Ihre Kontodaten mit.</p>
		
			<label class="label-medium" for="bankname">Bankname:</label>
			<input type="text" id="bankname" name="bankname" value="<%=bankDetails.getName() %>"/>
			<br>
			
			<label class="label-medium" for="owner">Kontoinhaber:</label>
			<input type="text" id="owner" name="owner" value="<%=bankDetails.getOwner() %>"/>
			<br>
			
			<label class="label-medium" for="iban">IBAN:</label>
			<input type="text" id="iban" name="iban" value="<%=bankDetails.getIban() %>"/>
			<br>
			
			<label class="label-medium" for="bic">BIC:</label>
			<input type="text" id="bic" name="bic" value="<%=bankDetails.getBic() %>"/>
			<br>
		
		</div>
		
		<div class="form-group">
		
			<div class="justified-buttons">
	
			  	<button class="button button-medium button-default" name="action4" value="previous">Zurück</button>  		
			  	<button class="button button-medium button-positive" name="action4" value="next">Weiter</button>  		
				
			</div>
		
		</div>
	
	</form>
	
	</div>

</div>


</body>
</html>
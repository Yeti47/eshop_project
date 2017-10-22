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
	if(customer == null || customer.getAddress() == null || customer.getAddress().getCountry() == null) {
		
		pageContext.forward("warenkorb.jsp");
		
	}
	
	Country country = customer.getAddress().getCountry();
	String countryName = country.getName();
	String countryCode = country.getCode();

	EshopDatabaseAccessor dbAccess = new EshopDatabaseAccessor();
	dbAccess.setSelectDistinct(true);	
	
	String paymentError = "";
	String paymentDbError = "";
	String paymentErrorVisibilty = "error-hidden";
	
	String deliveryChecked = WebUtility.getNonNullParam(request, "delivery");
	
	List<Payment> payments = dbAccess.fetchJoined(() -> new Payment(), "country_code=?", new String[] { countryCode });

	Payment[] paymentArr = null;
	
	if(payments != null) {
		
		paymentArr = payments.toArray(new Payment[0]);
		Arrays.sort(paymentArr, (x, y) -> x.getName().compareTo(y.getName()));
				
	}
	else {
		
		paymentDbError = "Fehler beim Laden der verfügbaren Zahlungsmethoden.";
		paymentErrorVisibilty = "";
		
	}
	
	PaymentBean paymentBean = new PaymentBean(paymentArr);

	String action = WebUtility.getNonNullParam(request, "action3");

	if(action.equals("next")) {
		
		String chosenPayment = WebUtility.getNonNullParam(request, "payment");
		
		if(!chosenPayment.equals("")) {
			
			for(Payment p : payments) {
				
				if(p.getName().equals(chosenPayment)) {
					
					order.setPayment(p);
					orderBean.saveOrder();
					
					// Wenn Bankeinzug gewählt wurde (id=2), weiterleiten zur Eingabe der Bankdaten, ansonsten diesen Schritt überspringen
					if(p.getId() == 2)
						pageContext.forward("order_bank.jsp");
					else
						pageContext.forward("order_overview.jsp");
					
				}
				
			}
			
		}
		else {
			
			paymentError = "Bitte wählen Sie eine Zahlungsart.";
			
		}
		
	}
	else if (action.equals("previous")) {
		
		if(deliveryChecked.equals("checked"))
			pageContext.forward("order_receiver.jsp");
		else
			pageContext.forward("order_customer.jsp");
		
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
	
		<h1>Wählen Sie eine Zahlungsart:</h1>
	
		<form id="payment-form" action="order_paym.jsp" method="post">
		
		<input type="hidden" id="delivery" name="delivery" value="<%=deliveryChecked %>"/>
		
		<div class="form-group">
			
			<h4>Für Ihre Rechnungsaddresse in <%=countryName %> können wir folgende Zahlungsmethoden anbieten:</h4>
		
			<%=paymentBean.htmlRadioButtons("payment-container", "payment-section", "payment") %>
			<br>
			<span class="form-error"><%=paymentError %></span>
		
		</div>
		
		<div class="form-group">
		
			<div class="justified-buttons">
	
			  	<button class="button button-medium button-default" name="action3" value="previous">Zurück</button>  		
			  	<button class="button button-medium button-positive" name="action3" value="next">Weiter</button>  		
				
			</div>
		
		</div>
	
	</form>
	
	</div>

</div>


</body>
</html>
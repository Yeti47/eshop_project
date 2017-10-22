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
	
	// Wenn noch irgendwelche Daten fehlen, abbrechen und zurück zum Warenkorb
	if(customer == null || customer.getAddress() == null ||  
		customer.getAddress().getCountry() == null || order.getPayment() == null ||
		(order.getPayment().getId() == 2 && customer.getBank() == null)) {
		
		pageContext.forward("warenkorb.jsp");
		
	}
	
	String errorMessage = "";
	
	String action = WebUtility.getNonNullParam(request, "action5");
	
	if(action.equals("order")) {
		
		if(writeOrderToDatabase(order)) {
			
			pageContext.forward("order_confirmation.jsp");
			
		}
		else {
			
			errorMessage = "Leider ist bei der Übermittlung Ihrer Bestellung ein Fehler aufgetreten.";
			
		}
		
	}
	else if(action.equals("previous")) {
		
		pageContext.forward("order_paym.jsp");
		
	}
	
	ReceiverFormatter customerFormatter = new ReceiverFormatter(customer);
	ReceiverFormatter receiverFormatter = new ReceiverFormatter(order.getFinalReceiver());
	
	String totalPackaging = String.format("%.2f EUR", order.getTotalPackagingFee());
	String totalProducts = String.format("%.2f EUR", order.getTotalProductsPrice());
	String shippingFee = String.format("%.2f EUR", order.getFinalReceiver().getAddress().getCountry().getShippingFee());
	String paymentFee = String.format("%.2f EUR", order.getPayment().getFee());
	
	String total = String.format("%.2f EUR", order.getFinalValue());


%>

<%!

	private boolean writeOrderToDatabase(Order order) {
		
		if(order == null)
			return false;
	
		EshopDatabaseAccessor dbAccess = new EshopDatabaseAccessor();
		
		Customer customer = order.getCustomer();
		Address invoiceAddr = customer.getAddress();
		
		if(!dbAccess.insert(invoiceAddr))
			return false;
		
		Integer addrSeq = dbAccess.fetchCurrentSequenceValue("ADDR_SEQ");
		
		if(addrSeq == null)
			return false;
		
		invoiceAddr.setId(addrSeq);
		
		if(!dbAccess.insert(customer))
			return false;
		
		Integer customSeq = dbAccess.fetchCurrentSequenceValue("CUST_SEQ");
		
		if(customSeq == null)
			return false;
		
		customer.setCustom_id(customSeq);
		Receiver receiver = order.getReceiver();
		
		if(receiver != null) {
			
			Address deliveryAddr = receiver.getAddress();
			
			if(deliveryAddr == null)
				return false;
			
			if(!dbAccess.insert(deliveryAddr))
				return false;
			
			addrSeq = dbAccess.fetchCurrentSequenceValue("ADDR_SEQ");
			
			if(addrSeq == null)
				return false;
			
			deliveryAddr.setId(addrSeq);	
			receiver.setCustom_id(customSeq);
			
			if(!dbAccess.insert(receiver))
				return false;
			
			Integer recId = dbAccess.fetchCurrentSequenceValue("CUST_SEQ");
			
			if(recId == null)
				return false;
			
			receiver.setRecId(recId);
			
		}
		
		// Bei Bankeinzug
		if(customer.getBank() != null) {
			
			customer.getBank().setCustId(customer.getCustom_id());
			
			if(!dbAccess.insert(customer.getBank())) {
				return false;
			}
			
		}
		
		if(!dbAccess.insert(order))
			return false;
		
		Integer orderSeq = dbAccess.fetchCurrentSequenceValue("ORDER_SEQ");
		
		if(orderSeq == null)
			return false;
		
		order.setId(orderSeq);

		return order.insertPositionsIntoDatabase(dbAccess);
		
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
	
		<span class="form-error"><%=errorMessage %></span>
	
		<h1>Bestellübersicht:</h1>
		
		<h3>Bitte prüfen Sie vor Abschluss Ihrer Bestellung noch einmal alle Eingaben!</h3>
	
		<div class="details-container">
			<h4>Rechnungsadresse:</h4>
			<%=customerFormatter.htmlList("input-details") %>
		</div>
		<div class="details-container">
			<h4>Lieferanschrift:</h4>
			<%=receiverFormatter.htmlList("input-details") %>	
		</div>
		<div class="details-container">
			<h4>Zahlungsdetails:</h4>
			<%=orderBean.htmlPaymentDetails("input-details") %>	
		</div>
		<div class="details-container">
			<h4>Kontakt:</h4>
			<ul class="input-details">
				<li>E-Mail: <%=customer.getEmail() %></li>
				<li>Tel.: <%=customer.getPhone() %></li>
			</ul>
		</div>	
	
		<%=orderBean.orderOverview("cart-positions") %>
		
		<div class="order-summary">
			
			<div class="order-summary-row">
				<div class="order-summary-col">
					Summe Artikel:
				</div>
				<div class="order-summary-col">
					<%=totalProducts %>
				</div>
			</div>
			
			<div class="order-summary-row">
				<div class="order-summary-col">
					Summe Verpackung:
				</div>
				<div class="order-summary-col">
					<%=totalPackaging %>
				</div>
			</div>
			
			<div class="order-summary-row">
				<div class="order-summary-col">
					Liefergebühr:
				</div>
				<div class="order-summary-col">
					<%=shippingFee %>
				</div>
			</div>
			
			<div class="order-summary-row">
				<div class="order-summary-col">
					Gebühr Zahlungsabwicklung:
				</div>
				<div class="order-summary-col">
					<%=paymentFee %>
				</div>
			</div>
			
			<div class="order-summary-row summary-total">
				<div class="order-summary-col">
					Ihr Rechnungsbetrag:
				</div>
				<div class="order-summary-col">
					<%=total %>
				</div>
			</div>
		
		</div>
		
		<div class="clear-fix"></div>
	
		<form id="order-confirm-form" action="order_overview.jsp" method="post">

		<div class="form-group">
		
			<div class="justified-buttons">
	
			  	<button class="button button-medium button-default" name="action5" value="previous">Zurück</button>  		
			  	<button class="button button-medium button-neutral" name="action5" value="order">Jetzt bestellen!</button>  		
				
			</div>
		
		</div>
	
	</form>
	
	</div>

</div>

</body>
</html>
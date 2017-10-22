<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import=
    	"de.profil.*,
    	de.profil.beans.*,
    	java.util.*"
%>

<%

	// Lösche das Order-Objekt aus der Session
	OrderBean orderBean = new OrderBean(session, request);
	orderBean.initializeOrder();
	int orderId = orderBean.getOrder().getId();
	orderBean.destroyOrder();
	
	// Wenn noch keine Order in der DB angelegt wurde, hat sich der User wohl verlaufen. Zurück zum Warenkorb.
	if(orderId <= 0) {
		
		pageContext.forward("warenkorb.jsp");
		
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
	
		<h1>Vielen Dank für Ihre Bestellung!</h1>
		
		<p>
		Ihre Bestellung mit der Nummer <%=orderId %> ist bei uns eingegangen und wird bearbeitet, sobald wir Lust dazu haben.
		Prüfen Sie bitte Ihr E-Mail-Postfach. Dort finden Sie noch einmal eine schriftliche Bestätigung über Ihre Bestellung.
		</p>
		
		<p>Wir hoffen sehr, dass wir Ihnen bald wieder etwas Geld abnehmen dürfen!</p>
		
		<form action="../index.jsp">
			<button class="button button-medium button-default">Zurück zum Shop</button>		
		</form>
		
	
	</div>

</div>

</body>
</html>
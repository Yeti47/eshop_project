<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import=
    	"de.profil.*,
    	de.profil.beans.*,
    	java.util.*"
%>
    	
<%

	EshopDatabaseAccessor dbAccess = new EshopDatabaseAccessor();
	dbAccess.setSelectDistinct(true);	
	
	List<Payment> payments = dbAccess.fetch(() -> new Payment(true));
	
	PaymentBean paymentBean = new PaymentBean();
	
	if(payments != null) {
		
		Payment[] paymentArr = payments.toArray(new Payment[0]);
		Arrays.sort(paymentArr, (x, y) -> x.getName().compareTo(y.getName()));
		paymentBean.setPayments(paymentArr);
		
	}
	
	List<Country> countries = dbAccess.fetch(() -> new Country());
	
	CountryBean countryBean = new CountryBean();
	
	if(countries != null) {
		
		Country[] countryArr = countries.toArray(new Country[0]);
		Arrays.sort(countryArr, (x, y) -> x.getName().compareTo(y.getName()));
		countryBean.setCountries(countryArr);
		
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
		
			<h1>Zahlungsarten</h1>
			
			<p>Grundsätzlich bieten wir Ihnen folgenden Zahlungsmethoden an:</p>
			
			<%=paymentBean.htmlList("payment-list") %>
			
			<p>
				Beachten Sie jedoch, dass die endgültige Auswahl an Zahlungsmethoden von Ihrer Rechnungsanschrift abhängt. <br>
				Leider können wir nicht gewährleisten, dass jede der oben genannten Zahlungsarten in jedem Land verfügbar ist. <br>
				Wir bitten um Ihr Verständnis!
			</p>
			
			<h1>Lieferung</h1>
			
			<p>Derzeit liefern wir in folgende europäische Länder:</p>
			
			<%=countryBean.htmlTable("country-table") %>
			
			<p>Beachten Sie bitte die Gebühr, welche für eine Lieferung in das jeweilige Land berechnet wird.</p>
		
		</div>
		
			
	</div>	
	
</body>
</html>
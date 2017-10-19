<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
         import=
    	"net.yetibyte.snowstorm.*, 
    	de.profil.*,
    	de.profil.beans.*,
    	javax.sql.DataSource, 
    	java.util.*"%>
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
	
			
		<div class="nav">
			<a href="../index.jsp">Startseite</a>
			<a href="warenkorb.jsp">Warenkorb</a>
			<a href="kundendaten.jsp">Kundendaten</a>
			<a href="zahlungsarten.jsp">Zahlungsarten</a>
			<a href="uebersicht.jsp">Übersicht</a>
			<a href="impressum.jsp">Impressum</a>
		</div>
	<h1>Impressum</h1>
	<p>K.Adam's Electronics GmbH<br>
		Mordsgaudistr. 96 - D. 30167 Hannover<br>     
		mob.: +49.172-12345678<br>
		verantw.: Alle<br>
		IHK Hannover, St.-Nr. 18/404/30167<br></p>

	<p>Texte und Bilder dieser Seiten unterliegen dem Urheberrecht. Der Inhalt darf ohne schriftliche Genehmigung weder bearbeitet, übersetzt, vervielfältigt oder verbreitet, noch sonst gewerblich auf Druckmedien oder elektronischem Wege vertrieben werden. Wir übernehmen keine Haftung für die Inhalte externer Links, für deren Inhalt sind ausschließlich deren Betreiber verantwortlich.</p>
	</div>
</body>
</html>
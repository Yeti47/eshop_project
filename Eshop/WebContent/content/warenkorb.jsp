<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
        import=
    	"net.yetibyte.snowstorm.*, 
    	de.profil.*,
    	de.profil.beans.*,
    	javax.sql.DataSource, 
    	java.util.*"
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
		</div>
	
		<div>
    	<h2>Sonstiges</h2>
    		<ul>
      			<li><a href="content/impressum.jsp">Impressum</a></li>
          	</ul>
  		</div>
  </div>		
  </body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import=
    	"net.yetibyte.snowstorm.*, 
    	de.profil.*,
    	de.profil.beans.*,
    	javax.sql.DataSource, 
    	java.util.*"

%><%

//Starten der Session und laden des evtl bereits bestehenden Order-Objekts
OrderBean orderBean = new OrderBean(session, request);
orderBean.initializeOrder();
orderBean.saveOrder();

// Bin jetzt auch dabei!
// Kevin ist dabei!

DataSource dataSource = Config.getDataSource();
DatabaseAccessor dbAccess = new DatabaseAccessor(dataSource);

List<Product> products = dbAccess.fetchJoined(() -> new Product());
List<Bundle> 	bundles  = dbAccess.fetchJoined(() -> new Bundle());

for(Bundle b : bundles) {
	products.add(b);
}

ProductBean pb = new ProductBean(products);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title><%=Config.ESHOP_NAME %></title>
</head>
<body>

<%@include file="content/header.jsp" %>

<div id="container">

	<%=pb.htmlProductsView() %>
	
</div>

</body>
</html>
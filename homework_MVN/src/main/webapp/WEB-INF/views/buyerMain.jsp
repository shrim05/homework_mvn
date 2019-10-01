<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/buyerMain.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath}";
</script>
<meta charset="UTF-8">
<title>Buyer CRUD</title>
</head>

<%-- <jsp:include page="/buyer/contents.jsp" /> --%>
<%-- <jsp:include page="/buyer/footer.jsp" /> --%>
<c:import url="/buyer/contents.jsp"></c:import>
<c:import url="/buyer/footer.jsp"></c:import>
</body>
<script src="${pageContext.request.contextPath}/js/buyer.js">

</script>
</html>
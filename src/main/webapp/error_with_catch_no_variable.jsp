<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" import="java.util.*"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/tlds/include.tld" prefix="inc"%>

<html>
<head></head>
<body>

<h2>BEFORE</h2>
<%
request.setAttribute("attrib1", "attrib1 original value");
pageContext.setAttribute("ATTRIB FROM INDEX PAGE", "attrib_page value", PageContext.PAGE_SCOPE);
pageContext.setAttribute("ATTRIB FROM INDEX REQUEST", "attrib_request value", PageContext.REQUEST_SCOPE);
pageContext.setAttribute("ATTRIB FROM INDEX SESSION", "attrib_session value", PageContext.SESSION_SCOPE);
%>
<%@ include file="include/values.jspf"%>


<hr />


<p>Including error.jsp without var</p>
<inc:include page="include/error.jsp">
    <inc:param name="param1" value="param1value" />
    <inc:param name="param2" value="param2value" />
    <inc:attrib name="attrib1" value="attrib1value" />
    <inc:attrib name="attrib2" value="attrib2value" />
    <inc:catch>
           This include threw an error, so is being rolled up.
           We don't know the error caught because we didn't specify a variable
    </inc:catch>
</inc:include>

<hr />
 
<h2>AFTER</h2>
<%@ include file="include/values.jspf" %>


</body>
</html>

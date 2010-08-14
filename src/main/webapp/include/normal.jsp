<%@ page isELIgnored="false"%>
<!-- this one does not throw an error -->
<h2>This is normal.jsp</h2>
<%
pageContext.setAttribute("ATTRIB FROM NORMAL PAGE", "attrib_page value", PageContext.PAGE_SCOPE);
pageContext.setAttribute("ATTRIB FROM NORMAL REQUEST", "attrib_request value", PageContext.REQUEST_SCOPE);
pageContext.setAttribute("ATTRIB FROM NORMAL SESSION", "attrib_session value", PageContext.SESSION_SCOPE);
%>

<%@ include file="values.jspf" %>

AND
<ul>
    <li>param1=${param.param1}</li>
    <li>param2=${param.param2}</li>
    <li>attrib1=${attrib1}</li>
    <li>attrib2=${attrib2}</li>
</ul>


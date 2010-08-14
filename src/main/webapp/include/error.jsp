<!--  this one throws an error -->
<!-- this one does not throw an error -->
<p>This is error.jsp</p>
<h1><b>WE SHOULD NEVER SEE THIS TEXT SINCE IT SHOULD GET ROLLED UP</b></h1>
<%
if(true) {
    throw new RuntimeException("this is the exception");
}
%>

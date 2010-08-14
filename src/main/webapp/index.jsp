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

<h2>no_error.jsp</h2>
<iframe src="no_error.jsp?bob=joe" width="100%" height="300">no iframes available</iframe>

<hr/>

<h2>error_with_catch.jsp</h2>
<iframe src="error_with_catch.jsp?bob=joe" width="100%" height="300">no iframes available</iframe>

<hr/>

<h2>error_with_catch_no_variable.jsp</h2>
<iframe src="error_with_catch_no_variable.jsp?bob=joe" width="100%" height="300">no iframes available</iframe>

<hr/>

<h2>error_no_catch.jsp</h2>
<iframe src="error_no_catch.jsp?bob=joe" width="100%" height="300">no iframes available</iframe>


</body>
</html>

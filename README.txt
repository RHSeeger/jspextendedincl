This is, at least for now, a proof of concept of an extended jsp:include command, allowing the user to 
pass in both parameters (String) and attributes (Object), as well as take actions when there is an 
Exception (specifically, Throwable) thrown in the act of including the specified file. This allows for 
a clean, concise way of "rolling up" an included module if it fails to "work".

Sample code:
<inc:include page="normal.jsp">
    <inc:param name="param1" value="param1value" />
    <inc:param name="param2" value="param2value" />
    <inc:attrib name="attrib1" value="${attrib1value}" />
    <inc:attrib name="attrib2" value="${attrib2value}" />
    <inc:catch var="ex">
           This block was not rolled up because there wasn't an error. 
           Should never see this, but just in case, the exception was: ${ex.message}
    </inc:catch>
</inc:include>

License information can be found in LICENSE.txt. That being said, it's a BSD license, so should allow
anyone to use the code however they need to.

Robert Seeger
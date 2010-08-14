package net.rkseeger.taglib.include;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import javax.swing.DebugGraphics;

import org.apache.commons.lang.StringUtils;

//import org.apache.catalina.core.

// http://www.docjar.com/docs/api/org/apache/jasper/runtime/JspRuntimeLibrary.html
// http://www.docjar.com/docs/api/org/apache/jasper/runtime/JspRuntimeLibrary.html
// http://www.docjar.com/html/api/org/apache/catalina/core/ApplicationDispatcher.java.html

// http://www.informit.com/articles/article.aspx?p=26119&seqNum=9
// http://onjava.com/pub/a/onjava/2001/01/18/jsptags.html?page=2

public class IncludeTag extends TagSupport {
    boolean evaluated = false;
    boolean flush = false;
    String page = "zomg";
    Map<String,String> parameters = new HashMap<String,String>();
    Map<String,Object> attributes = new HashMap<String,Object>();
    
    public void setFlush(boolean value){
        flush = value;
    }

    public boolean getFlush(){
        return(flush);
    }
    
    public void setPage(String page) {
        this.page = page;
    }
    public String getPage() {
        return page;
    }
    
    boolean getEvaluated() {
        return evaluated;
    }
    
    void addParameter(String key, String value) {
        parameters.put(key,value);
    }
    void addAttribute(String key, Object value) {
        attributes.put(key,value);
    }
    
    Throwable evaluate() {
        // Create a passthrough wrapper
        ServletRequestWrapper request = new HttpServletRequestWrapper((HttpServletRequest)pageContext.getRequest()) {
            // The default behavior of this method is to call getAttribute(String name) on the wrapped request object.
            @Override
            public java.lang.Object getAttribute(java.lang.String name) {
                if(attributes.containsKey(name)) {
                    return attributes.get(name);
                } else {
                    return super.getAttribute(name);
                }
            }
            // The default behavior of this method is to return getAttributeNames() on the wrapped request object.
            @Override
            public java.util.Enumeration getAttributeNames() {
                Set<String> names = new HashSet<String>();
                Enumeration<String> e = super.getAttributeNames();
                while(e.hasMoreElements()) {
                    names.add(e.nextElement());
                }
                names.addAll(attributes.keySet());
                return Collections.enumeration(names);
            }
            
            // The default behavior of this method is to return setAttribute(String name, Object o) on the wrapped request object.
            @Override public void setAttribute(java.lang.String name, java.lang.Object o) {
                if(attributes.containsKey(name)) {
                    attributes.remove(name);
                }
                super.setAttribute(name, o);
            }
            
        };
        ServletResponse response = pageContext.getResponse();
        JspWriter out = pageContext.getOut();

        List<String> queries = new ArrayList<String>();
        for(Map.Entry<String,String> entry : parameters.entrySet()) {
            String key = org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(entry.getKey(), request.getCharacterEncoding());
            String value = org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(entry.getValue(), request.getCharacterEncoding());
            queries.add(key + "=" + value);
        }

        try {
            org.apache.jasper.runtime.JspRuntimeLibrary.include(
                    request, response, 
                    page + (page.indexOf('?') > 0 ? '&' : '?') + StringUtils.join(queries, "&"),
                    out, false);
        } catch(Exception ex) {
            evaluated = true;
            Utilities.debug(pageContext, "found exception: " + ex.getMessage());
            return ex;
        }
        
        evaluated = true;
        return null;
    }
     
    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_INCLUDE;
    }
    
    @Override
    public int doEndTag() throws JspException {
        if(!evaluated) {
            Utilities.debug(pageContext, "Calling " + this.toString() + " from include");
            Throwable ex = evaluate();
            if(ex != null) {
                throw new JspException(ex);
            }
        }
        reset();
        return EVAL_PAGE;
    }

    void reset() {
        evaluated = false;
        flush = false;
        page = null;
        parameters = new HashMap<String,String>();
        attributes = new HashMap<String,Object>();
    }
    
    @Override
    public void release() {
        super.release();
        Utilities.debug(pageContext, "Running release on " + this.toString());
        reset();
    }
    
}

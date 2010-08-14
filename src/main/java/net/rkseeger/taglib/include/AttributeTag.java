package net.rkseeger.taglib.include;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class AttributeTag extends TagSupport {
    String name;
    Object value;
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setValue(Object value) {
        this.value = value;
    }
    public Object getValue() {
        return value;
    }
    

    @Override
    public int doStartTag() throws JspException {
//        try {
//            pageContext.getOut().println("<p>AttributeTag:doStartTag</p>");
//        } catch(IOException ex) {
//            throw new RuntimeException(ex);
//        }

        IncludeTag ancestorTag = (IncludeTag)findAncestorWithClass(this, IncludeTag.class);
        if(ancestorTag == null) {
            throw new JspException("attribute tag must be inside include tag");
        }
        if(ancestorTag.getEvaluated()) {
            Utilities.debug(pageContext, ancestorTag.toString() + " is evaluated already");
            throw new JspException("attribute tag must be before catch tag");
        } else {
            Utilities.debug(pageContext, ancestorTag.toString() + " is NOT evaluated already");
        }
        ancestorTag.addAttribute(getName(), getValue());
        name = null;
        value = null;
        
        return SKIP_BODY;

    }
    
}

package net.rkseeger.taglib.include;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class CatchTag extends BodyTagSupport {
//    PageContext context = null;
    String var = null;
    
    public void setVar(String var) {
        this.var = var;
    }
    public String getVar() {
        return var;
    }

    @Override
    public int doStartTag() throws JspTagException {
        IncludeTag ancestorTag = (IncludeTag)findAncestorWithClass(this, IncludeTag.class);
        if(ancestorTag == null) {
            throw new JspTagException("catch tag must be inside include tag");
        }
        Utilities.debug(pageContext, "Calling " + ancestorTag.toString() + " from catch");
        Throwable ex = ancestorTag.evaluate();
        if(ex != null) {
            Utilities.debug(pageContext, "there was an exception");
            if(var != null) {
                Utilities.debug(pageContext, "Setting " + var + " to " + ex);
                pageContext.setAttribute(var, ex);
            } else {
                Utilities.debug(pageContext, "There was no var to set");
            }
            return EVAL_BODY_INCLUDE;
            //return EVAL_BODY_BUFFERED;
            //return(EVAL_BODY_TAG);
        } else {
            Utilities.debug(pageContext, "there was not an exception");
            return SKIP_BODY;
        }
    }

    void reset() {
        var = null;
    }
    
    @Override
    public void release() {
        super.release();
        reset();
    }

    @Override
    public int doEndTag() throws JspException {
        reset();
        //return SKIP_PAGE;
        return EVAL_PAGE;
    }
        
    // ======================
//    public static class CatchTEI extends TagExtraInfo {
//        @Override
//        public VariableInfo[] getVariableInfo(TagData data) {
//            VariableInfo[]  scriptVars = new VariableInfo[1];
//
//            // We are telling the constructor  not to create
//            // a new variable since we will define it in our JSP
//            // loopCounter will be available in the body and
//            // remainder of the JSP
//            System.out.println("using var of '" + data.getAttributeString("var") + "'");
////            throw new RuntimeException("using var of '" + data.getAttributeString("var") + "'");
//            scriptVars[0]  = new VariableInfo(data.getAttributeString("var"),
//                    "java.lang.Throwable",
//                    false,
//                    VariableInfo.NESTED);
//              
//            return scriptVars;
//        }
//    }
    
}

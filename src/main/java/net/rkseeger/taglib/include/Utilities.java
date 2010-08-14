package net.rkseeger.taglib.include;

import java.io.IOException;

import javax.servlet.jsp.PageContext;

public class Utilities {
    static final boolean DEBUG = false;
    
    static void debug(PageContext pageContext, String message) {
        if(!DEBUG) return;
        try {
            pageContext.getOut().println("<p>" + message + "</p>");
            System.out.println(message);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

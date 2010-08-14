package net.rkseeger.taglib.include;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class CatchTEI extends TagExtraInfo {
    @Override
    public VariableInfo[] getVariableInfo(TagData data) {
        String varName = data.getAttributeString("var");
        if(varName == null || varName.trim().length() == 0) {
            return new VariableInfo[0];
        }
        
        VariableInfo[]  scriptVars = new VariableInfo[1];
        scriptVars[0]  = new VariableInfo(
                data.getAttributeString("var"),
                "java.lang.Throwable",
                true,
                VariableInfo.NESTED);
          
        return scriptVars;
    }
}

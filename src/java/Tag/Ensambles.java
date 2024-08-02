package Tag;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Ensambles extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("<p>Hola mundo</p>");
        } catch (IOException ex) {
            Logger.getLogger(Ensambles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}

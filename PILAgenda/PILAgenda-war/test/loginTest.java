
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author soleneantoine
 */
public class loginTest {
    
    @Test
    public void testPageLogin() throws IOException{
        WebClient webClient = new WebClient();
        HtmlPage currentPage = webClient.getPage("http://localhost:8080/PILAgenda-war/faces/login.xhtml");
        assertEquals("Login", currentPage.getTitleText());
    }
    
    @Test
    public void testFormLoginOk() throws IOException{
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage("http://localhost:8080/PILAgenda-war/faces/login.xhtml");
        final HtmlForm form = page.getFormByName("j_idt9");
        final HtmlSubmitInput button = form.getInputByName("j_idt9:formLogin:btnLogin");
        final HtmlTextInput textFieldLogin = form.getInputByName("j_idt9:formLogin:j_idt20");
        final HtmlPasswordInput textFieldPasswd = form.getInputByName("j_idt9:formLogin:j_idt22");
        textFieldLogin.setValueAttribute("admin");
        textFieldPasswd.setValueAttribute("admin");
        final HtmlPage page2 = button.click();
        assertEquals("Accueil", page2.getTitleText());
    }
    
    @Test
    public void testFormLoginKo() throws IOException{
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage("http://localhost:8080/PILAgenda-war/faces/login.xhtml");
        final HtmlForm form = page.getFormByName("j_idt9");
        final HtmlSubmitInput button = form.getInputByName("j_idt9:formLogin:btnLogin");
        final HtmlTextInput textFieldLogin = form.getInputByName("j_idt9:formLogin:j_idt20");
        final HtmlPasswordInput textFieldPasswd = form.getInputByName("j_idt9:formLogin:j_idt22");
        textFieldLogin.setValueAttribute("admin");
        textFieldPasswd.setValueAttribute("adminfs");
        final HtmlPage page2 = button.click();
        assertEquals("Login", page2.getTitleText());
    }
    

}

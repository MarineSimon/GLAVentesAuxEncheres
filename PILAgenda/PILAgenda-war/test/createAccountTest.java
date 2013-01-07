/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author soleneantoine
 */
public class createAccountTest {
    
    /*aucun champs de rempli*/
@Test
    public void testFormCreateAccountKo1() throws IOException{
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage("http://localhost:8080/PILAgenda-war/faces/createAccount.xhtml");
        final HtmlForm form = page.getFormByName("j_idt9");
        final HtmlSubmitInput button = form.getInputByName("j_idt9:account:saveButton");
        final HtmlPage page2 = button.click();
        assertEquals("Création compte", page2.getTitleText());
    } 

    /*pas les mêmes mots de passe*/
@Test
    public void testFormCreateAccountKo2() throws IOException{
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage("http://localhost:8080/PILAgenda-war/faces/createAccount.xhtml");
        final HtmlForm form = page.getFormByName("j_idt9");
        final HtmlTextInput textFieldMail = form.getInputByName("j_idt9:account:j_idt24");
        final HtmlPasswordInput textFieldPasswd = form.getInputByName("j_idt9:account:pwd");
        final HtmlPasswordInput textFieldPasswd2 = form.getInputByName("j_idt9:account:pwdc");
        
        textFieldMail.setValueAttribute("test2.test2@gmail.com");
        textFieldPasswd.setValueAttribute("admin");
        textFieldPasswd2.setValueAttribute("admin2");
        
        final HtmlSubmitInput button = form.getInputByName("j_idt9:account:saveButton");
        final HtmlPage page2 = button.click();
        assertEquals("Création compte", page2.getTitleText());
    } 
    
@Test
    public void testFormCreateAccountOk() throws IOException{
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage("http://localhost:8080/PILAgenda-war/faces/createAccount.xhtml");
        final HtmlForm form = page.getFormByName("j_idt9");
        final HtmlTextInput textFieldMail = form.getInputByName("j_idt9:account:j_idt24");
        final HtmlPasswordInput textFieldPasswd = form.getInputByName("j_idt9:account:pwd");
        final HtmlPasswordInput textFieldPasswd2 = form.getInputByName("j_idt9:account:pwdc");
        
        textFieldMail.setValueAttribute("test1.test1@gmail.com");
        textFieldPasswd.setValueAttribute("admin");
        textFieldPasswd2.setValueAttribute("admin");
        
        final HtmlSubmitInput button = form.getInputByName("j_idt9:account:saveButton");
        final HtmlPage page2 = button.click();
        assertEquals("Login", page2.getTitleText());
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.ArticleBeanLocal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import library.EnchereBeanInterface;
import org.primefaces.context.RequestContext;
import persistence.Article;
import persistence.UserEnchere;

/**
 *
 * @author Swann
 */
@Named(value = "enchereBean")
@RequestScoped
public class EnchereBean {
    @EJB
    private ArticleBeanLocal articleBeanLocal;
    @EJB
    private EnchereBeanInterface enchereBeanLocal;
    
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public EnchereBean() {
    }
    
    public String addEnchere(Article a) {
        if (this.amount <= this.articleBeanLocal.getActualPrice(a)){
            FacesContext.getCurrentInstance().addMessage("encherire:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Montant d'enchère insuffisant.",""));
            return null;
        }
        else {
            Calendar date = new GregorianCalendar();
            this.enchereBeanLocal.addEnchere(date, amount, a, this.getUserConnected());
            RequestContext.getCurrentInstance().update("entete");
            FacesContext.getCurrentInstance().addMessage("encherire:messages", new FacesMessage(FacesMessage.SEVERITY_INFO,"Enchère prise en compte !",""));
            return null;
        }
    }
    
    public UserEnchere getUserConnected() {
        LoginBean log = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
        return log.getUserConnected();
    }
}

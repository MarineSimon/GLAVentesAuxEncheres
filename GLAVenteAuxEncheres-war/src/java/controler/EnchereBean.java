/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.ArticleBeanLocal;
import business.EnchereBeanLocal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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
import persistence.Enchere;
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
    private static final String STATEFUL_ENCHERE_BEAN_KEY = "STATEFUL_ENCHERE_BEAN_KEY";

    private double amount;
    private Article selectedArticle;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Article getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(Article selectedArticle) {
        this.selectedArticle = selectedArticle;
    }

    public EnchereBean() {
    }
    
    public List<Article> getRunningBill(UserEnchere userConnected) throws ServletException {
        this.enchereBeanLocal = this.getStatefulBean();
        return this.enchereBeanLocal.getRunningBill(userConnected);
    }

    
    public String addEnchere(Article a) {
        if (this.amount <= this.articleBeanLocal.getActualPrice(a)){
            FacesContext.getCurrentInstance().addMessage("encherire:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Montant d'enchère insuffisant.",""));
            return null;
        }
        else {
            Calendar date = new GregorianCalendar();
            //System.out.println("Utilisateur : "+this.getUserConnected().);
            this.enchereBeanLocal.addEnchere(date, amount, a, this.getUserConnected());
            RequestContext.getCurrentInstance().update("j_idt9:entete");
            FacesContext.getCurrentInstance().addMessage("encherire:messages", new FacesMessage(FacesMessage.SEVERITY_INFO,"Enchère prise en compte !",""));
            return null;
        }
    }
    
    public UserEnchere getUserConnected() {
        LoginBean log = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
        return log.getUserConnected();
    }
    
    private EnchereBeanLocal getStatefulBean() throws ServletException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        EnchereBeanLocal enchereBean = (EnchereBeanLocal) httpSession.getAttribute(STATEFUL_ENCHERE_BEAN_KEY);
        if (enchereBean == null) {
            try {
                InitialContext ic = new InitialContext();
                enchereBean = (EnchereBeanLocal) ic.lookup("java:global/GLAVenteAuxEncheres/GLAVenteAuxEncheres-ejb/EnchereBeanLocal!business.EnchereBeanLocal");
                httpSession.setAttribute(STATEFUL_ENCHERE_BEAN_KEY, enchereBean);
            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }
        return enchereBean;
    }
    
    public void removeEnchereArticle(Article a){
        enchereBeanLocal.removeEnchereArticle(this.getUserConnected(), a);
        RequestContext.getCurrentInstance().update("j_idt9:j_idt23:vueEncheresCompte");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt23:notifications");
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
    }
    
    public boolean haveUserEnchere(Article a){
        boolean result = false;
        if (a != null && this.getUserConnected() != null){
            result =enchereBeanLocal.haveUserEnchere(this.getUserConnected(),a);
        }
        return result;
        
    }
    
    public Enchere userLastEnchere(Article a){
        Enchere result = null;
        if(this.getUserConnected() != null && a != null){
            result = enchereBeanLocal.getUserLastEnchere(this.getUserConnected(),a);
        }
         return result;
    }
    
}

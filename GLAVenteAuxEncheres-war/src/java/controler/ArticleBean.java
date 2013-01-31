/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.ArticleBeanLocal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import library.ArticleBeanInterface;
import org.primefaces.context.RequestContext;
import persistence.Article;

/**
 *
 * @author Marine
 */

@Named(value = "articleBean")
@RequestScoped
public class ArticleBean {
    @EJB
    private ArticleBeanInterface articleLocal; 
    
    private String keywords = "";
    private List<Article> displayedArticles = new ArrayList<Article>();
    
    private static final String STATEFUL_ARTICLE_BEAN_KEY = "STATEFUL_ARTICLE_BEAN_KEY";

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<Article> getDisplayedArticles() {
        if (this.displayedArticles.isEmpty() && this.keywords.isEmpty())
            return this.getCriticalsArticles();
        else 
            return displayedArticles;
    }

    public void setDisplayedArticles(List<Article> displayedArticles) {
        this.displayedArticles = displayedArticles;
    }
    
    public List<Article> getCriticalsArticles() {
        return articleLocal.getCriticalsArticles();
    }
    
    public List<Article> getArticlesInPromotion() {
        return articleLocal.getArticlesInPromotion();
    }

    public String getActualPrice(Article a){
        return articleLocal.getActualPrice(a)+" €";
    }

    public Article getSelectedArticle() throws ServletException {
        ArticleBeanLocal articleBean = getStatefulBean();
        return articleBean.getSelectedArticle();
    }

    public void setSelectedArticle(Article selectedArticle) throws ServletException {
        ArticleBeanLocal articleBean = getStatefulBean();
        articleBean.setSelectedArticle(selectedArticle);
    }
    
    public String getReminingTime(Article a){
        Calendar endTime = a.getEndDate();
        Calendar actualTime = new GregorianCalendar();
        
        long diff = Math.abs(endTime.getTimeInMillis() - actualTime.getTimeInMillis());
        long numberOfDay = (long)(diff/(86400000))+1;
        
        return numberOfDay+" jours";
    }
    
    private ArticleBeanLocal getStatefulBean() throws ServletException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        ArticleBeanLocal articleBean = (ArticleBeanLocal) httpSession.getAttribute(STATEFUL_ARTICLE_BEAN_KEY);
        if (articleBean == null) {
            try {
                InitialContext ic = new InitialContext();
                articleBean = (ArticleBeanLocal) ic.lookup("java:global/GLAVenteAuxEncheres/GLAVenteAuxEncheres-ejb/ArticleBeanLocal!business.ArticleBeanLocal");
                httpSession.setAttribute(STATEFUL_ARTICLE_BEAN_KEY, articleBean);
            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }
        return articleBean;
    }
    
    public void searchArticle(){
        List<Article> searchList = this.articleLocal.search(this.keywords);
        this.displayedArticles = searchList;
        System.out.println("Taille de la liste : "+this.displayedArticles.size());
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
    }
    
    public void resetDisplayArticles(){
        this.displayedArticles = this.getCriticalsArticles();
        this.keywords = "";
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt18:searchValue");
    }
}

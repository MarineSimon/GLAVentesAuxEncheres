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
import persistence.Category;
import persistence.SubCategory;
import persistence.UserEnchere;

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
    private int category = 0;
    private int subCategory = 0;
    private List<Article> displayedArticles = new ArrayList<Article>();
    
    private static final String STATEFUL_ARTICLE_BEAN_KEY = "STATEFUL_ARTICLE_BEAN_KEY";

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }
    
    public List<Article> getDisplayedArticles() {
        if (this.displayedArticles.isEmpty() && this.keywords.isEmpty())
            return this.getCriticalsArticles();
        else 
            return displayedArticles;
    }
    
    public List<Article> getDisplayedArticlesByUser(UserEnchere userConnected) throws ServletException {
        this.articleLocal = this.getStatefulBean();
        return this.articleLocal.getCriticalsArticles(userConnected);
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
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
    }
    
    public void resetDisplayArticles(){
        this.displayedArticles = this.getCriticalsArticles();
        this.keywords = "";
        this.category = 0;
        this.subCategory = 0;
        RequestContext.getCurrentInstance().update("j_idt9:viewCategory");
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt18:searchValue");
    }
    
    public List<Category> getAllCategory() {
        List<Category> result = this.articleLocal.getAllCategory();
        return result;
    }
    
    public List<SubCategory> getAllSubCategory() {
        List<SubCategory> result = this.articleLocal.getAllSubCategory(this.category);
        return result;
    }
    
    public void searchArticleByCategory() {
        List<Article> searchList = this.articleLocal.searchArticleByCategory(this.category);
        this.displayedArticles = searchList;  
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
    }
    
    public void searchArticleBySubCategory() {
        List<Article> searchList = this.articleLocal.searchArticleBySubCategory(this.subCategory);
        this.displayedArticles = searchList;
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
    }
}

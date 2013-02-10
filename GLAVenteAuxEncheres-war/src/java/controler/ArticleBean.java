/*
 * To change this template, choose Tools | Templates
w * and open the template in the editor.
 */
package controler;

import business.ArticleBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
public class ArticleBean implements Serializable{
    @EJB
    private ArticleBeanInterface articleLocal; 
    
    private String keywords = "";
    private int category = 0;
    private int subCategory = 0;
    private String name;
    private String description;
    private double prixInitial;
    private Date finEnchere;
    private String photo ="";
    private String destination="resources/pictures/articles";
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

    public ArticleBeanInterface getArticleLocal() {
        return articleLocal;
    }

    public void setArticleLocal(ArticleBeanInterface articleLocal) {
        this.articleLocal = articleLocal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(double prixInitial) {
        this.prixInitial = prixInitial;
    }

    public Date getFinEnchere() {
        return finEnchere;
    }

    public void setFinEnchere(Date finEnchere) {
        this.finEnchere = finEnchere;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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
        
        long diff = endTime.getTimeInMillis() - actualTime.getTimeInMillis();
        if (diff < 0) {
            diff=0;
        }
        
        Calendar remaining = new GregorianCalendar();
        remaining.setTimeInMillis(diff);
        remaining.add(Calendar.HOUR_OF_DAY, -1);
        return (remaining.get(Calendar.DAY_OF_MONTH)-1)+"j "+remaining.get(Calendar.HOUR_OF_DAY)+"h "+remaining.get(Calendar.MINUTE)+"m "+remaining.get(Calendar.SECOND)+"s ";
        
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
        System.out.println("Search 1");
        List<Article> searchList = this.articleLocal.search(this.keywords, this.category, this.subCategory);
        System.out.println("Search 2");
        this.displayedArticles = searchList;
        System.out.println("Search 3");
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
        System.out.println("Search 4");
    }
    
    public void resetDisplayArticles(){
        this.displayedArticles = this.getCriticalsArticles();
        this.keywords = "";
        this.category = 0;
        this.subCategory = 0;
        RequestContext.getCurrentInstance().update("j_idt9:viewCategory");
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt24:searchValue");
    }
    
    public List<Category> getAllCategory() {
        List<Category> result = this.articleLocal.getAllCategory(this.category,this.subCategory);
        return result;
    }
    
    public List<SubCategory> getAllSubCategory() {
        List<SubCategory> result = this.articleLocal.getAllSubCategory(this.category,this.subCategory);
        return result;
    }
    
    public UserEnchere getUserConnected() {
        LoginBean log = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
        return log.getUserConnected();
    }
    
    public boolean isUserArticle(Article a){
        boolean result = false;
        if (this.getUserConnected() != null && a != null){
            result = a.getOwner().getId() == this.getUserConnected().getId();
        }
        return result;
    }
    
    public void removeArticle(Article a){
        articleLocal.removeArticle(a, this.getUserConnected());
        RequestContext.getCurrentInstance().update("j_idt9:j_idt23:vueEncheresCompte");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt23:notifications");
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt23:vueArticlesCompte");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt23:vueEncheresCompte");
        
    }
    
    public String addArticle(){  
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.finEnchere);
        Article a = new Article(this.name,this.description, this.prixInitial, cal , ""); // A CHANGER POUR LA PICTURE !!!
        a.setOwner(this.getUserConnected());
        SubCategory s;
        s = articleLocal.getSubCategory(subCategory);
        a.setSubCategory(s);
        articleLocal.addArticle(a);
        return "backToViewAccount";
    }
    
    public boolean displaySubCategory(){
        return (this.category == 0);
    }
    
    
}

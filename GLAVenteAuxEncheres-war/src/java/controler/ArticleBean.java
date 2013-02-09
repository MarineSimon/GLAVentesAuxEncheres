/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.ArticleBeanLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import library.ArticleBeanInterface;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
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
    private String name;
    private String description;
    private double prixInitial;
    private int sousCategorie;
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

    public int getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(int sousCategorie) {
        this.sousCategorie = sousCategorie;
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
        s = articleLocal.getSubCategory(sousCategorie);
        a.setSubCategory(s);
        articleLocal.addArticle(a);
        return "backToViewAccount";
    }
    
    public void upload(FileUploadEvent event) { 
        System.out.println("test");
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void copyFile(String fileName, InputStream in) {
           try {
             
             
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination + fileName));
             
                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
             
                in.close();
                out.flush();
                out.close();
             
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
    
    
}

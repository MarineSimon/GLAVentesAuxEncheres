/*
 * To change this template, choose Tools | Templates
w * and open the template in the editor.
 */
package controler;

import business.ArticleBeanLocal;
import business.InitBaseSingleton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Timer;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
import org.primefaces.model.UploadedFile;
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
@ManagedBean

public class ArticleBean implements Serializable {
    @EJB
    private ArticleBeanInterface articleLocal; 
    
    @EJB
    private InitBaseSingleton singleton;
    
    
    private String keywords = "";
    private int category = 0;
    private int subCategory = 0;
    private int maxPrice = 0;
    private int minPrice = 0;
    private String name;
    private String description;
    private double prixInitial;
    private Date finEnchere;
    private String photo ="";
    private String destination="/Users/soleneantoine/NetBeansProjects/GLAVentesAuxEncheres/GLAVenteAuxEncheres-war/web/resources/pictures/articles/";
    private List<Article> displayedArticles = new ArrayList<Article>();
    private UploadedFile file;  
    
    private static final String STATEFUL_ARTICLE_BEAN_KEY = "STATEFUL_ARTICLE_BEAN_KEY";

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }
    
    public UploadedFile getFile() {  
        return file;  
    }  
  
    public void setFile(UploadedFile file) {  
        this.file = file;  
    } 
    
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
        if (this.keywords.isEmpty() && this.category == 0 && this.subCategory == 0 && this.maxPrice == 0 && this.minPrice ==0)
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
    
    public int getActualPriceInt(Article a){
        return (int)articleLocal.getActualPrice(a);
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
        Calendar remaining = singleton.getRemainingTime(a);
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
        int borneInf = (this.minPrice == 0)?0:this.minPrice;
        int borneSup = (this.maxPrice == 0)?Integer.MAX_VALUE:this.maxPrice;
        if (borneSup < borneInf)
            FacesContext.getCurrentInstance().addMessage("j_idt9:error", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur : Prix maximum inférieur au prix minimum ...",""));
        else {
            List<Article> searchList = this.articleLocal.search(this.keywords, this.category, this.subCategory);
            ArrayList<Article> result = new ArrayList<Article>();
            for (Article a : searchList) { 
                if ((this.getActualPriceInt(a) >= borneInf) && (this.getActualPriceInt(a) <= borneSup)) {
                    result.add(a);
                }
            }
            this.displayedArticles = result;
        }
    }
    
    public String resetDisplayArticles(){
        this.displayedArticles = this.getCriticalsArticles();
        this.keywords = "";
        this.category = 0;
        this.subCategory = 0;
        this.maxPrice = 0;
        this.minPrice = 0;
        RequestContext.getCurrentInstance().update("j_idt9:j_idt24:j_idt28");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt24:sousCateg");
        RequestContext.getCurrentInstance().update("j_idt9:articles_dg");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt24:searchValue");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt24:minPrice");
        RequestContext.getCurrentInstance().update("j_idt9:j_idt24:maxPrice");
        return null;
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
    
    public String addArticle() throws ServletException{  
        Calendar today = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.finEnchere);
        if (cal.before(today)) {
            FacesContext.getCurrentInstance().addMessage("j_idt9:formAddEvent:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"date de fin avant la date de début.",""));
            return null;
        }
        else {
            Article a = new Article(this.name,this.description, this.prixInitial, cal , this.getStatefulBean().getPicture());
            a.setOwner(this.getUserConnected());
            SubCategory s;
            s = articleLocal.getSubCategory(subCategory);
            a.setSubCategory(s);
            articleLocal.addArticle(a);
            
            singleton.addTimerToArticle(a);
            return "backToViewAccount";
        }  
    }
    
    public void upload(FileUploadEvent event) throws ClassNotFoundException, ServletException { 
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void copyFile(String fileName, InputStream in) throws ClassNotFoundException, ServletException {
           try {
               Class class1 = Class.forName("controler.ArticleBean");
               URL url = class1.getResource("");
               String str[]=url.getPath().split("GLAVentesAuxEncheres");
               String path = str[0]+"GLAVentesAuxEncheres/GLAVenteAuxEncheres-war/web/resources/pictures/articles/"+fileName;
               String pathPhoto[]=path.split("web/");
               this.getStatefulBean().setPicture(pathPhoto[1]);
                // write the inputStream to a FileOutputStream
               OutputStream out = new FileOutputStream(new File(path));
             
                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                in.close();
                out.flush();
                out.close();
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
    
    public boolean displaySubCategory(){
        return (this.category == 0);
    }
    
    public boolean noResult() {
        boolean b1 = this.displayedArticles.isEmpty() && !this.keywords.isEmpty();
        boolean b2 = this.keywords.isEmpty() && this.category != 0 && this.subCategory != 0 && this.displayedArticles.isEmpty();
        boolean b3 = this.keywords.isEmpty() && this.category == 0 && this.subCategory != 0 && this.displayedArticles.isEmpty();
        boolean b4 = this.keywords.isEmpty() && this.category != 0 && this.subCategory == 0 && this.displayedArticles.isEmpty();
        boolean b5 = this.minPrice == 0 && this.maxPrice == 0 && this.displayedArticles.isEmpty();
        boolean b6 = this.minPrice != 0 && this.maxPrice == 0 && this.displayedArticles.isEmpty();
        boolean b7 = this.minPrice == 0 && this.maxPrice != 0 && this.displayedArticles.isEmpty();
        boolean b8 = this.minPrice != 0 && this.maxPrice != 0 && this.displayedArticles.isEmpty();
        return (b1 || b2 || b3 || b4 || b5 || b6 || b7 || b8);
    }
    
    public ArrayList<Integer> minList(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(5);
        for (int i = 1; i < 10; i++) 
            result.add(i, 10*i);
        for (int j=1; j<10;j++)
            result.add(9+j, 100*j);
        for (int k=1; k<10;k++)
            result.add(18+k, 1000*k);
        for (int l=1; l<10; l++)
            result.add(27+l, 10000*l);
        return result;
    }
    
    public ArrayList<Integer> maxList(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(5);
        for (int i = 1; i < 10; i++) 
            result.add(i, 10*i);
        for (int j=1; j<10;j++)
            result.add(9+j, 100*j);
        for (int k=1; k<10;k++)
            result.add(18+k, 1000*k);
        for (int l=1; l<10; l++)
            result.add(27+l, 10000*l);
        return result;
    }
}

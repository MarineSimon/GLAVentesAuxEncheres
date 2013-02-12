package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import library.ArticleBeanInterface;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import persistence.Article;
import persistence.Category;
import persistence.Enchere;
import persistence.Notification;
import persistence.Promotion;
import persistence.SubCategory;
import persistence.UserEnchere;
//import org.atmosphere.cpr.AsyncSupportListenerAdapter;


/**
 *
 * @author Marine
 */
@Stateful(name="ArticleBeanLocal")
@LocalBean
public class ArticleBeanLocal implements ArticleBeanInterface{
    
    @PersistenceContext(unitName="GLAVenteAuxEncheres-PU")
    private EntityManager em;
    
    private Article selectedArticle;
    private String picture;

    public Article getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(Article selectedArticle) {
        this.selectedArticle = selectedArticle;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    
    
    @Override
    public List<Article> getCriticalsArticles() {
        List<Article> result = new ArrayList<Article>();
        Query query = em.createNamedQuery("Article.findArticles");
        try {
             List<Article> articles = (List<Article>) query.getResultList();
             for (int i = 0; i < articles.size(); i++) {
                 if (articles.get(i).getEndDate().after(new GregorianCalendar())){
                     result.add(articles.get(i));
                 }
            }
        } catch (NoResultException e){
            return null;
        }
        
        return result;
    }
    
    @Override
    public List<Article> getCriticalsArticles(UserEnchere user) {
        List<Article> result = new ArrayList<Article>();
        Query query = em.createNamedQuery("Article.findArticlesByUser");
        query.setParameter(1, user.getId());
        try {
             List<Article> articles = (List<Article>) query.getResultList();
             for (int i = 0; i < articles.size(); i++) {
                    result.add(articles.get(i));
            }
        } catch (NoResultException e){
            return null;
        }
        
        return result;
    }
    

    @Override
    public double getActualPrice(Article a) {
        Query query = em.createNamedQuery("Article.findLastEnchereByArticles");
        double amount = a.getInitialPrice();
        query.setParameter(1, a.getId());
        List<Enchere> encheres = (List<Enchere>) query.getResultList();
        if (!encheres.isEmpty()){
            amount = encheres.get(encheres.size()-1).getAmount();
        }
        return amount;
    }

    @Override
    public List<Article> getArticlesInPromotion() {
        List<Article> result = new ArrayList<Article>();
        Query query = em.createNamedQuery("Promotion.findAllPromotions");
        
        try {
             List<Promotion> promotions = (List<Promotion>) query.getResultList();
             for (int i = 0; i < promotions.size(); i++) {
                 for (int j = 0; j < promotions.get(i).getArticles().size(); j++) {
                     if (!result.contains(promotions.get(i).getArticles().get(j))){
                         if (promotions.get(i).getArticles().get(j).getEndDate().after(new GregorianCalendar())){
                             result.add(promotions.get(i).getArticles().get(j));
                         }
                         
                     }
                     
                 }
                
            }
        } catch (NoResultException e){
            return null;
        }
        
        return result;
    }

    @Override
    public List<Article> search(String keywords, int category, int subCategory) {
        List<Article> articles;
        List<Article> result = new ArrayList<Article>();
        Query query;
        if (category == 0 && subCategory == 0) {
            query = em.createNamedQuery("Article.searchArticles");
            query.setParameter(1, "%"+keywords+"%");
            query.setParameter(2, "%"+keywords+"%");
        }else {
            if (subCategory == 0) {
                /* cas ou on a seulement une categorie */
                if (keywords.isEmpty()) {
                    query = em.createNamedQuery("Article.searchArticleByCategory");
                    query.setParameter(1, category);
                } else {
                    query = em.createNamedQuery("Article.searchArticleByCategoryWithKeywords");
                    query.setParameter(1, "%"+keywords+"%");
                    query.setParameter(2, "%"+keywords+"%");
                    query.setParameter(3, category);
                }  
            }
            else {
                if (keywords.isEmpty()) {
                    query = em.createNamedQuery("Article.searchArticleBySubCategory");
                    query.setParameter(1, subCategory);
                } else {
                    query = em.createNamedQuery("Article.searchArticleBySubCategoryWithKeywords");
                    query.setParameter(1, "%"+keywords+"%");
                    query.setParameter(2, "%"+keywords+"%");
                    query.setParameter(3, subCategory);
                }           
            }
        }


        try {
            articles = (List<Article>) query.getResultList();
            for (Article a : articles) {
                 if (a.getEndDate().after(new GregorianCalendar())){
                    result.add(a);
                }
            }
        } catch (NoResultException e){
            return null;
        }
        return result;
    }

    @Override
    public List<Category> getAllCategory(int category,int subCategory) {
        List<Category> listCategory = new ArrayList<Category>();
        Query query;
        query = em.createNamedQuery("Category.findAll");

        try {
             listCategory = (List<Category>) query.getResultList();
        } catch (NoResultException e){
            return null;
        }
        return listCategory;
    }
    
   @Override
    public List<SubCategory> getAllSubCategory(int category,int subCategory) {
        List<SubCategory> listSubCategory = new ArrayList<SubCategory>();
        Query query;
        if (category == 0) {
            query = em.createNamedQuery("SubCategory.findAll");
        } else {
            query = em.createNamedQuery("SubCategory.searchByCategory");
            query.setParameter(1, category);
        }
        
        try {
             listSubCategory = (List<SubCategory>) query.getResultList();
        } catch (NoResultException e){
            return null;
        }
        return listSubCategory;
    }

    @Override
    public void removeArticle(Article a, UserEnchere user) {
        List<Enchere> result = new ArrayList<Enchere>();
        Query query = em.createNamedQuery("Enchere.getRunningBillArticle");
        query.setParameter(1, a.getId());
        List<Enchere> encheres = (List<Enchere>) query.getResultList();
        result.addAll(encheres);
        
        Notification n = new Notification("L'article "+a.getName()+" a été retiré de la vente");
        for (int i = 0; i < encheres.size(); i++) {
            UserEnchere u = em.find(UserEnchere.class, encheres.get(i).getUserEnchere().getId());
            u.getNotifications().add(n);
            em.remove(em.merge(encheres.get(i)));
        }
        
        if (a.getPromotions().size() == 2){
            Promotion p1 = a.getPromotions().get(1);
            p1.getArticles().remove(a);
            a.getPromotions().remove(p1);
            Promotion p2 = a.getPromotions().get(0);
            p2.getArticles().remove(a);
            a.getPromotions().remove(p2);
            em.merge(p1);
            em.merge(p2);
            em.merge(a);
        } else {
            if (a.getPromotions().size() == 1){
                Promotion p3 = a.getPromotions().get(0);
                p3.getArticles().remove(a);
                a.getPromotions().remove(p3);
            }
        }
        em.merge(a);
        user.getSellArticles().remove(a);
        Notification n2 = new Notification("Vous avez supprimé l'article "+a.getName());
        user.getNotifications().add(n2);
        em.merge(user);
        em.remove(em.merge(a));
    }

    @Override
    public SubCategory getSubCategory(int i) {
        Query query;
        query = em.createNamedQuery("SubCategory.searchById");
        query.setParameter(1, i);
        SubCategory s = (SubCategory) query.getResultList().get(0);
        return s;
    }

    @Override
    public void addArticle(Article a) {
        this.em.persist(a);
        a.getOwner().getSellArticles().add(a);
        Notification n = new Notification("Vous avez ajouté l'article "+a.getName());
        a.getOwner().getNotifications().add(n);
        this.em.merge(a.getOwner());
    }

    @AroundTimeout
    public Object profile(InvocationContext ic) throws Exception {
        System.out.println("*** PROFILING: " + ic.getMethod().getName() + " in class "
                + ic.getTarget()
                +" called " + Calendar.getInstance().getTime() + " ***");
        System.out.println("Refresh...");
        
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        pushContext.push("/registrationEvent", "There was another registration");

        System.out.println("... done.");
        return ic.proceed();
    }
    @Override
    public double getMaxPrice() {
        Query query;
        query = em.createNamedQuery("Enchere.maxPrice");
        double result = (Double)query.getSingleResult();
        return result;
        
    }
}

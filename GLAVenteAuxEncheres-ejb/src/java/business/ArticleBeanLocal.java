package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import library.ArticleBeanInterface;
import persistence.Article;
import persistence.Category;
import persistence.Enchere;
import persistence.Notification;
import persistence.Promotion;
import persistence.SubCategory;
import persistence.UserEnchere;

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

    public Article getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(Article selectedArticle) {
        this.selectedArticle = selectedArticle;
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
    public List<Article> search(String keywords) {
        List<Article> result = new ArrayList<Article>();
        Query query = em.createNamedQuery("Article.searchArticles");
        query.setParameter(1, "%"+keywords+"%");
        query.setParameter(2, "%"+keywords+"%");
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
    public List<Category> getAllCategory() {
        List<Category> result = new ArrayList<Category>();
        Query query = em.createNamedQuery("Category.findAll");
        
        try {
             List<Category> category = (List<Category>) query.getResultList();
             for (int i = 0; i < category.size(); i++) {
                result.add(category.get(i));
            }
        } catch (NoResultException e){
            return null;
        }
        return result;
    }
    
   @Override
    public List<SubCategory> getAllSubCategory(int idCategory) {
        List<SubCategory> result = new ArrayList<SubCategory>();
        Query query;
        if (idCategory == 0) {
            query = em.createNamedQuery("SubCategory.findAll");
        } else {
            query = em.createNamedQuery("SubCategory.searchByCategory");
            query.setParameter(1, idCategory);
        }
        
        try {
             List<SubCategory> subCategory = (List<SubCategory>) query.getResultList();
             for (int i = 0; i < subCategory.size(); i++) {
                result.add(subCategory.get(i));
            }
        } catch (NoResultException e){
            return null;
        }
        return result;
    }

    @Override
    public List<Article> searchArticleByCategory(int category) {
        List<Article> result = new ArrayList<Article>();
        if (category == 0) {
            result = this.getCriticalsArticles();
        }
        else {
            Query query = em.createNamedQuery("Article.searchArticleByCategory");
            query.setParameter(1, category);
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
        }
        return result;
    }

    @Override
    public List<Article> searchArticleBySubCategory(int subCategory) {
        List<Article> result = new ArrayList<Article>();
        if (subCategory == 0) {
            result = this.getCriticalsArticles();
        }
        else {
            Query query = em.createNamedQuery("Article.searchArticleBySubCategory");
            query.setParameter(1, subCategory);
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
        }
        return result;
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
                Promotion p3 = a.getPromotions().get(1);
                p3.getArticles().remove(a);
                a.getPromotions().remove(p3);
            }
        }
        em.merge(a);
        user.getSellArticles().remove(a);
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
        this.em.merge(a.getOwner());
    }
    
}

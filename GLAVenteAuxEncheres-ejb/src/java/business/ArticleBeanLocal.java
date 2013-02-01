/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
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
import persistence.Promotion;
import persistence.SubCategory;

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
        Query query = em.createNamedQuery("Article.findCriticalsArticles");
        
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
    public int getActualPrice(Article a) {
        Query query = em.createNamedQuery("Article.findLastEnchereByArticles");
        query.setParameter(1, a.getId());
        List<Enchere> encheres = (List<Enchere>) query.getResultList();
        return encheres.get(0).getAmount();
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
                         result.add(promotions.get(i).getArticles().get(j));
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
                result.add(articles.get(i));
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
        Query query = em.createNamedQuery("SubCategory.findAll");
        
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
                    result.add(articles.get(i));
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
                    result.add(articles.get(i));
                }
            } catch (NoResultException e){
                return null;
            }
        }
        return result;
    }
    
    
    
}

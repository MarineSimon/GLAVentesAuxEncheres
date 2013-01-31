/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import library.ArticleBeanInterface;
import persistence.Article;
import persistence.Enchere;
import persistence.Promotion;

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
        int result = a.getInitialPrice();
        Query query = em.createNamedQuery("Article.findLastEnchereByArticles");
        query.setParameter(1, a.getId());
        List<Enchere> encheres = (List<Enchere>) query.getResultList();
        result += encheres.get(0).getAmount();
        return result;
    }

    @Override
    public List<Article> getArticlesInPromotion() {
        List<Article> result = new ArrayList<Article>();
        Query query = em.createNamedQuery("Promotion.findAllPromotions");
        
        try {
             List<Promotion> promotions = (List<Promotion>) query.getResultList();
             for (int i = 0; i < promotions.size(); i++) {
                 for (int j = 0; j < promotions.get(i).getArticles().size(); j++) {
                     result.add(promotions.get(i).getArticles().get(j));
                 }
                
            }
        } catch (NoResultException e){
            return null;
        }
        
        return result;
    }

    
}

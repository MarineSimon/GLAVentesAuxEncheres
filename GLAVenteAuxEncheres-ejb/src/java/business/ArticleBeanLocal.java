/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import library.ArticleBeanInterface;
import persistence.Article;
import persistence.Enchere;
import persistence.UserEnchere;

/**
 *
 * @author Marine
 */
@Stateless
@LocalBean
public class ArticleBeanLocal implements ArticleBeanInterface{
    @PersistenceContext(unitName="GLAVenteAuxEncheres-PU")
    private EntityManager em;
    
    @Override
    public List<Article> getCriticalsArticles() {
        List<Article> result = new ArrayList<Article>();
        Query query = em.createNamedQuery("Article.findCriticalsArticles");
        
        try {
             List<Article> articles = (List<Article>) query.getResultList();
             for (int i = 0; i < 4; i++) {
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
        System.out.println(""+encheres.get(0).getAmount());
        result += encheres.get(0).getAmount();
        return result;
    }

    
}

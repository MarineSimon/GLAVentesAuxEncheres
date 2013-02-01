/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import library.EnchereBeanInterface;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.Article;
import persistence.Enchere;
import persistence.UserEnchere;

/**
 *
 * @author Swann
 */
@Stateful(name="EnchereBeanLocal")
@LocalBean
public class EnchereBeanLocal implements EnchereBeanInterface {
    @PersistenceContext(unitName="GLAVenteAuxEncheres-PU")
    private EntityManager em;
    
    @Override
    public void addEnchere(Calendar date, double amount, Article article, UserEnchere user) {
        Enchere e = new Enchere(date,amount,article,user);
        em.persist(e);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Article> getRunningBill(UserEnchere user) {
        List<Article> result = new ArrayList<Article>();
        Query query = em.createNamedQuery("Enchere.getRunningBill");
        query.setParameter(1, user.getId());

        List<Enchere> encheres = (List<Enchere>) query.getResultList();
        for (Enchere e : encheres) {
            Article a = em.find(Article.class, e.getArticle().getId());
            if (!result.contains(a)) {
                result.add(a);
            }
        }     
        return result;
    }

}

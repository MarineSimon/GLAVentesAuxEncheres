/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Calendar;
import library.EnchereBeanInterface;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Article;
import persistence.Enchere;
import persistence.UserEnchere;

/**
 *
 * @author Swann
 */
@Stateless
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

}

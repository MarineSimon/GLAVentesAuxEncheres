/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import library.UserBeanInterface;
import persistence.UserEnchere;

/**
 *
 * @author Marine
 */
@Stateless
@LocalBean
public class UserBeanLocal implements UserBeanInterface{
    @PersistenceContext(unitName="GLAVenteAuxEncheres-PU")
    private EntityManager em;
    
    @Override
    public boolean loginAvailable(UserEnchere user) {
        boolean res = false;
        Query query = em.createNamedQuery("UserEnchere.findUserByLogin");
        query.setParameter(1, user.getLogin());

        if (query.getResultList().size() == 0) {
            res = true;
        }
        
        return res;
    }
    
    @Override
    public UserEnchere getUserByLogin(String login){
        Query query = em.createNamedQuery("UserEnchere.findUserByLogin");
        query.setParameter(1, login);
        
        return (UserEnchere) query.getSingleResult();
            
        }

    @Override
    public UserEnchere addUser(UserEnchere user) {
        em.persist(user);
        return user;
    }
    
}

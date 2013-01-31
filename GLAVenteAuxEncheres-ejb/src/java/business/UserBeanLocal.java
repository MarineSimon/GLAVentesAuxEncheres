/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import library.UserBeanInterface;
import persistence.UserEnchere;

/**
 *
 * @author Marine
 */
@Stateful(name="UserBeanLocal")
@LocalBean
public class UserBeanLocal implements UserBeanInterface{
    @PersistenceContext(unitName="GLAVenteAuxEncheres-PU")
    private EntityManager em;
    
    private UserEnchere user;
    
    //TESTE SI UN LOGIN A DEJA ETE UTILISE
    @Override
    public boolean loginAvailable(UserEnchere user) {
        boolean res = false;
        Query query = em.createNamedQuery("UserEnchere.findUserByLogin");
        query.setParameter(1, user.getLogin());

        if (query.getResultList().isEmpty()) {
            res = true;
        }
        
        return res;
    }
    
    //RETROUVE L'UTILISATEUR VIA SON LOGIN
    @Override
    public UserEnchere getUserByLogin(String login){
        Query query = em.createNamedQuery("UserEnchere.findUserByLogin");
        query.setParameter(1, login);
        UserEnchere result;
        
        try {
             result = (UserEnchere) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        
        return result;
            
        }
    
    //PERSISTE UN UTILISATEUR DANS LA BASE DE DONNEES
    @Override
    public UserEnchere addUser(UserEnchere user) {
        em.persist(user);
        return user;
    }

    public UserEnchere getUser() {
        return user;
    }

    public void setUser(UserEnchere user) {
        this.user = user;
    }
    
}

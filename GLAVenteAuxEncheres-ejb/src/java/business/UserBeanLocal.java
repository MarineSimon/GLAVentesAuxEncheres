/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.LocalBean;
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
@Stateless
@LocalBean
public class UserBeanLocal implements UserBeanInterface{
    @PersistenceContext(unitName="GLAVenteAuxEncheres-PU")
    private EntityManager em;
    
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
        System.out.println("dedans");
        Query query = em.createNamedQuery("UserEnchere.findUserByLogin");
        query.setParameter(1, login);
        UserEnchere result;
        
        try {
            System.out.println("avant "+login);
             result = (UserEnchere) query.getSingleResult();
             System.out.println(""+result);
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
    
}

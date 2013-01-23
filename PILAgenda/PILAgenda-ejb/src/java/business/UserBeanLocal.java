/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import library.UserBeanLocalInterface;
import persistence.UserAgenda;

/**
 *
 * @author Marine
 */
@Stateless
@LocalBean
public class UserBeanLocal implements UserBeanLocalInterface{
    @PersistenceContext(unitName="PILAgenda-PU")
    private EntityManager em;

    @Override
    public UserAgenda addAccount(UserAgenda ua) {
        Query query = em.createNamedQuery("UserAgenda.findPassWordByEmail");
        query.setParameter(1, ua.getEmail());

        if (query.getResultList().size() == 0) {
            em.persist(ua);
        }
        else {
            ua = null;
        }
        return ua;
    }
 
    @Override
    public UserAgenda userByMail(String email) {
        UserAgenda userConnected = new UserAgenda();
        Query query = em.createNamedQuery("UserAgenda.findPassWordByEmail");
        try {
            query.setParameter(1, email);
            userConnected = (UserAgenda) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
          return userConnected;
 }
}

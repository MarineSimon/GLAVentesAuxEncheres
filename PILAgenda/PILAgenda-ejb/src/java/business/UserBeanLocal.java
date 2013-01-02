/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        em.persist(ua);
        return ua;
    }

}

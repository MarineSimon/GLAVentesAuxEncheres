/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import persistence.UserAgenda;

/**
 *
 * @author Swann
 */
@Stateless
public class TestBean implements TestBeanLocal {
    @PersistenceContext(unitName = "PILAgenda-PU",type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @Override
    public UserAgenda addPerson(UserAgenda ua) {
        em.persist(ua);
        return ua;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}

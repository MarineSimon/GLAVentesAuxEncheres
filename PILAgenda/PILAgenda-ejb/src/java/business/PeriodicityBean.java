/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import library.PeriodicityBeanLocal;
import persistence.Periodicity;

/**
 *
 * @author Swann
 */
@Stateless
public class PeriodicityBean implements PeriodicityBeanLocal {
    @PersistenceContext(unitName="PILAgenda-PU")
    private EntityManager em;
    
    @Override
    public Periodicity addPeriodicity(Periodicity p) {
        em.persist(p);
        return p;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}

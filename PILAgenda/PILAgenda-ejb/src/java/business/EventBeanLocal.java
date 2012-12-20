/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import library.EventBeanLocalInterface;
import persistence.Event;

/**
 *
 * @author Swann
 */
@Stateless
public class EventBeanLocal implements EventBeanLocalInterface {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @PersistenceContext(unitName="PILAgenda-PU")
    private EntityManager em;

    @Override
    public Event addEvent(Event e) {
        em.persist(e);
        return e;
    }
}

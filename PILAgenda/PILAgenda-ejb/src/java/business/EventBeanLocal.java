/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import library.EventBeanLocalInterface;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Event;
import persistence.UserAgenda;

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

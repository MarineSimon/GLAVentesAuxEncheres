package business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import library.EventBeanLocalInterface;
import persistence.Agenda;
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

    @Override
    public List<Event> findAllEvent(UserAgenda userConnected) {
       TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.eventOwner = ?1", Event.class);
       query.setParameter(1, userConnected);
       return (List<Event>) query.getResultList();
    }

    @Override
    public Agenda getAgendaOfEventFromUser(Event event, UserAgenda user) {
        Agenda agenda = null;
        List<Agenda> belongedAgendas = event.getBelongToAgendas();
        int i = 0;
        while(i < belongedAgendas.size()){
            if(belongedAgendas.get(i).getAgendaOwner().equals(user)){
                agenda = belongedAgendas.get(i);
            }
            i++;
        }
        return agenda;
    }
    
}

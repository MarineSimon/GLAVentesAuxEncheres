/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.List;
import javax.ejb.Local;
import persistence.Agenda;
import persistence.Event;
import persistence.UserAgenda;

/**
 *
 * @author Swann
 */
@Local
public interface EventBeanLocalInterface {
    public persistence.Event addEvent(persistence.Event e);
    public List<Event> findAllEvent(UserAgenda userCo);
    public Agenda getAgendaOfEventFromUser(Event event, UserAgenda user);
}

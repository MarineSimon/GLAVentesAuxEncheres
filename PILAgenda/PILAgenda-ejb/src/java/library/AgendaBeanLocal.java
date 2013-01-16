/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import persistence.Agenda;
import persistence.Event;
import persistence.UserAgenda;

/**
 *
 * @author Mohamed
 */
@Local
public interface AgendaBeanLocal {
   public Agenda createAgenda(Agenda newAgenda); 
   public List<Agenda> findAllAgenda(UserAgenda userConencted);
   public List<Agenda> findDisplayedAgenda(UserAgenda userConnected);
   public Agenda findAgenda(Long id);
   public UserAgenda getUserConnected();
   public Agenda findDefaultAgenda(UserAgenda u);
   public List<Event> findAcceptedEvent(Agenda agenda);
   public void addDisplayedAgendaToUser(Agenda a, UserAgenda u);
}

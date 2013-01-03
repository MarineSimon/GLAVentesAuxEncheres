/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.List;
import javax.ejb.Local;
import persistence.Agenda;
import persistence.UserAgenda;

/**
 *
 * @author Mohamed
 */
@Local
public interface AgendaBeanLocal {
   public Agenda createAgenda(Agenda newAgenda); 
   public List<Agenda> findAllAgenda(UserAgenda userConencted);
   public Agenda findAgenda(Long id);
}

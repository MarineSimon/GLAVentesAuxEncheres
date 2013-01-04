/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.awt.Color;
import java.sql.Date;
import javax.ejb.LocalBean;
import library.BaseBeanLocal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Agenda;
import persistence.Event;
import persistence.Periodicity;
import persistence.UserAgenda;

/**
 *
 * @author Maxime
 */
@Stateless
@LocalBean
public class BaseBean implements BaseBeanLocal {
    @PersistenceContext(unitName="PILAgenda-PU")
    private EntityManager em;
    
    @Override
    public void fillingBase() {
        UserAgenda user1 = new UserAgenda("admin", "admin", "Name1", "user1", "FR", "adresse user1", "France", "Nancy", "0", "24", "1");
        em.persist(user1);
        
        Agenda agenda1 = new Agenda(1, Color.GREEN, "Personnel", "Agenda personnel de admin", user1);
        em.persist(agenda1);
        
        Periodicity periodicity1 = new Periodicity(2);
        em.persist(periodicity1);
        
        
        java.util.Date t = new java.util.Date();
        t.setHours(10);
        Date today = new Date(t.getTime());
        t.setHours(12);
        Date today2 = new Date(t.getTime());
        Event event1 = new Event("Evenement 1", "Nancy", today, today2, 1, "Evenement 1", periodicity1, user1, agenda1);
        em.persist(event1);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}

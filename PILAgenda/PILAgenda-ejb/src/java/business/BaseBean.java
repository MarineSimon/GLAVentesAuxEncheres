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
        UserAgenda user1 = new UserAgenda("user1@gmail.com", "user1", "Name1", "user1", "FR", "adresse user1", "France", "Nancy", "0", "24", "1");
        em.persist(user1);
        UserAgenda user2 = new UserAgenda("user2@gmail.com", "user2", "Name2", "user2", "FR", "adresse user2", "France", "Lyon", "0", "24", "1");
        em.persist(user2);
        UserAgenda user3 = new UserAgenda("user3@gmail.com", "user3", "Name3", "user3", "FR", "adresse user3", "France", "Paris", "0", "24", "1");
        em.persist(user3);
        
        Agenda agenda1 = new Agenda(1, Color.BLACK, "Personnel User 1", "Agenda personnel de user 1", user1);
        em.persist(agenda1);
        Agenda agenda2 = new Agenda(1, Color.GRAY, "Personnel User 2", "Agenda personnel de user 2", user2);
        em.persist(agenda2);
        Agenda agenda3 = new Agenda(1, Color.RED, "Personnel User 3", "Agenda personnel de user 3", user3);
        em.persist(agenda3);
        Agenda agenda4 = new Agenda(2, Color.GREEN, "Work User 1", "Agenda de travail de user 1", user1);
        em.persist(agenda4);
        Agenda agenda5 = new Agenda(1, Color.MAGENTA, "Birthday User 1", "Agenda des anniversaires de user 3", user3);
        em.persist(agenda5);
        
        Periodicity periodicity1 = new Periodicity(2);
        em.persist(periodicity1);
        Event event1 = new Event("Evenement 1", "Nancy", Date.valueOf("2013-01-01"), Date.valueOf("2013-01-02"), 1, "Evenement 1", periodicity1, user1, agenda1);
        em.persist(event1);

        Event event2 = new Event("Evenement 2", "Marseille", Date.valueOf("2013-01-03"), Date.valueOf("2013-01-03"), 1, "Evenement 2", periodicity1, user3, agenda5);
        em.persist(event2);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}

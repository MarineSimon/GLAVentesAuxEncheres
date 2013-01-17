/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import library.AgendaBeanLocal;
import persistence.Agenda;
import persistence.UserAgenda;

/**
 *
 * @author Mohamed
 */
@Stateless
@LocalBean
public class AgendaBean implements AgendaBeanLocal {

    @PersistenceContext(unitName = "PILAgenda-PU")
    private EntityManager em;
    private UserAgenda userConnected;

    public AgendaBean(){
        
    }
    
    public UserAgenda getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(UserAgenda userConnected) {
        this.userConnected = userConnected;
    }

    @Override
    public Agenda createAgenda(Agenda newAgenda) {

        newAgenda.setAgendaOwner(this.userConnected);
        em.persist(newAgenda);
        return newAgenda;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Agenda> findAllAgenda(UserAgenda userConnected) {

        TypedQuery<Agenda> query = em.createQuery("SELECT a FROM Agenda a WHERE a.agendaOwner = ?1", Agenda.class);
        query.setParameter(1, userConnected);
        return (List<Agenda>) query.getResultList();
    }

    public List<Agenda> findInAllAgendas(UserAgenda userConnected, String title, String email, String firstname, String lastname) {
        TypedQuery<Agenda> query = em.createQuery("SELECT a FROM Agenda a WHERE UPPER (a.name) LIKE UPPER (?1) AND UPPER (a.agendaOwner.email) LIKE UPPER (?2) AND UPPER (a.agendaOwner.firstname) LIKE UPPER (?3) AND UPPER (a.agendaOwner.lastname) LIKE UPPER (?4) AND a.access=1", Agenda.class);
        if (title != "") {
            query.setParameter(1, title);
        } else {
            query.setParameter(1, "%");
        }
        if (email != "") {
            query.setParameter(2, email);
        } else {
            query.setParameter(2, "%");
        }
        if (firstname != "") {
            query.setParameter(3, firstname);
        } else {
            query.setParameter(3, "%");
        }
        if (lastname != "") {
            query.setParameter(4, lastname);
        } else {
            query.setParameter(4, "%");
        }
        return (List<Agenda>) query.getResultList();
    }
    public List<Agenda> findAgenda(UserAgenda userConnected, String input) {
        TypedQuery<Agenda> query = em.createQuery("SELECT a FROM Agenda a WHERE a.agendaOwner = ?1 AND a.name LIKE ?2", Agenda.class);
        query.setParameter(1, userConnected);
        query.setParameter(2, "%"+input+"%");
        return (List<Agenda>) query.getResultList();
    }


    @Override
    public Agenda findAgenda(Long id) {
        return em.find(Agenda.class, id);
    }
}

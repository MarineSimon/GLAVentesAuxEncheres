/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import javax.ejb.LocalBean;
import library.AgendaBeanLocal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

    @Override
    public Agenda findAgenda(Long id) {
        return em.find(Agenda.class, id);
    }
}

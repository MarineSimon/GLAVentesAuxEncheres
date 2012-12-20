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

/**
 *
 * @author Mohamed
 */
@Stateless
@LocalBean
public class AgendaBean implements AgendaBeanLocal {
    @PersistenceContext(unitName="PILAgenda-PU")
    private EntityManager em;
    
    @Override
    public Agenda createAgenda(Agenda newAgenda) {
        em.persist(newAgenda);
        return newAgenda;
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Agenda> findAllAgenda() {
       
       TypedQuery<Agenda> query = em.createQuery("SELECT a FROM Agenda a", Agenda.class);
       return (List<Agenda>) query.getResultList();
    }
    
}

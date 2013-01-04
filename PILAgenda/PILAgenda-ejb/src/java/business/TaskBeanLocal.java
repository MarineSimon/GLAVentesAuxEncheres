/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import library.TaskBeanLocalInterface;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import persistence.Task;

/**
 *
 * @author flav
 */
@Stateless
public class TaskBeanLocal implements TaskBeanLocalInterface {
    @PersistenceContext(unitName="PILAgenda-PU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Task addTask(Task task) {
        em.persist(task);
        return task;
    }

    @Override
    public List<Task> findAllTask() {
       TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t", Task.class);
       return (List<Task>) query.getResultList();
    }
    

}

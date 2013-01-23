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
import persistence.UserAgenda;

/**
 *
 * @author flav
 */
@Stateless
public class TaskBeanLocal implements TaskBeanLocalInterface {
    @PersistenceContext(unitName="PILAgenda-PU")
    private EntityManager em;
    private UserAgenda userConnected;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public UserAgenda getUserConnected() {
        return userConnected;
    }
    @Override
    public void setUserConnected(UserAgenda userConnected) {
        this.userConnected = userConnected;
    }
    @Override
    public Task addTask(Task task) {
        task.setTaskOwner(userConnected);
        em.persist(task);
        this.addDisplayedTaskToUser(task, userConnected);
        em.merge(this.userConnected);
        return task;
    }

    @Override
    public List<Task> findAllTask(UserAgenda userCo) {
       TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t WHERE t.taskOwner = ?1", Task.class);
       query.setParameter(1, userCo);
       return (List<Task>) query.getResultList();
    }

    @Override
    public Task modifyTask(Task task) {
        em.merge(task);
        em.flush();
        return task;
    }

    @Override
    public void deleteTask(Task task) {
        em.remove(em.merge(task));
    }

    @Override
    public void addDisplayedTaskToUser(Task task, UserAgenda u) {
        UserAgenda us = em.find(UserAgenda.class, u.getId());
        Task ag = em.find(Task.class, task.getId());
        us.getTasks().add(ag);
        em.merge(us);
    }

    @Override
    public void deleteDisplayedTaskToUser(Task task, UserAgenda u) {
        UserAgenda us = em.find(UserAgenda.class, u.getId());
        Task ag = em.find(Task.class, task.getId());
        us.getTasks().remove(ag);
        em.merge(us);
    }
    

}

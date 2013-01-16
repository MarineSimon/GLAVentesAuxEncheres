/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.List;
import javax.ejb.Local;
import persistence.Task;
import persistence.UserAgenda;

/**
 *
 * @author flav
 */
@Local
public interface TaskBeanLocalInterface {
    Task addTask(Task task);
    Task modifyTask(Task task);
    public List<Task> findAllTask(UserAgenda userCo);
    void setUserConnected(UserAgenda userConnected);
    UserAgenda getUserConnected();
       
}

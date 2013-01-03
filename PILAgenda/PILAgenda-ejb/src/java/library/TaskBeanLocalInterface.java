/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.List;
import javax.ejb.Local;
import persistence.Task;

/**
 *
 * @author flav
 */
@Local
public interface TaskBeanLocalInterface {
    Task addTask(Task task);
    public List<Task> findAllTask();
    
}

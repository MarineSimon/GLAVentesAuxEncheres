/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import javax.ejb.Local;

/**
 *
 * @author Swann
 */
@Local
public interface EventBeanLocalInterface {
    public persistence.Event addEvent(persistence.Event e);
    
}

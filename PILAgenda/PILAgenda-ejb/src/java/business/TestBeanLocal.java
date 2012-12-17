/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.Local;
import persistence.UserAgenda;

/**
 *
 * @author Swann
 */
@Local
public interface TestBeanLocal {
    public UserAgenda addPerson(UserAgenda ua);
}

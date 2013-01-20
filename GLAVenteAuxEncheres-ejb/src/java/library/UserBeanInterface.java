/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import javax.ejb.Local;
import persistence.UserEnchere;

/**
 *
 * @author Marine
 */
@Local
public interface UserBeanInterface {
    public boolean loginAvailable(UserEnchere user);
    public UserEnchere addUser(UserEnchere user);
}

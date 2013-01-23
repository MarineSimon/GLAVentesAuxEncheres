/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import javax.ejb.Local;
import persistence.UserAgenda;

/**
 *
 * @author Marine
 */
@Local
public interface UserBeanLocalInterface {

    public UserAgenda addAccount(UserAgenda ua);
    public UserAgenda userByMail(String email);
}

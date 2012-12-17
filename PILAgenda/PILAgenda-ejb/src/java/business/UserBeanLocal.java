/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import library.UserBeanLocalInterface;
import persistence.UserAgenda;

/**
 *
 * @author Marine
 */
@Stateless
@LocalBean
public class UserBeanLocal implements UserBeanLocalInterface{

    @Override
    public UserAgenda addAccount(UserAgenda ua) {
        return ua;
    }

}

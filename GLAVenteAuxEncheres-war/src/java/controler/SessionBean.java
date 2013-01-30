/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import persistence.UserEnchere;

/**
 *
 * @author Marine
 */
@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable{
    private UserEnchere userConnected;
    
    public String getUserConnectedToString(){
        String result = "";
        if (userConnected != null){
            if (!userConnected.getFirstname().equals("") && !userConnected.getLastname().equals("")){
                result = userConnected.getFirstname()+" "+userConnected.getLastname();
            } else {
                result = userConnected.getLogin();
            }
        }
        
        return result;
    }

    public UserEnchere getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(UserEnchere userConnected) {
        this.userConnected = userConnected;
    }
    
}

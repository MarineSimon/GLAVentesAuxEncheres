/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import persistence.UserAgenda;



/**
 *
 * @author soleneantoine
 */
@Named(value = "autoCompleteBean")
public class AutoCompleteBean {
     private String userSelected;
     List<UserAgenda> users;
     
     public AutoCompleteBean() {  
         users = UserAgendaConverter.userDB;  
    } 
     
     public List<String> complete(String query) {
        List<String> suggestions = new ArrayList<String>();  
          
        for(UserAgenda u : users) {
            if(u.getEmail().startsWith(query)) {
                suggestions.add(u.getEmail());
            } 
        }
        return suggestions;     
    }

    public String getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(String userSelected) {
        this.userSelected = userSelected;
    }

    public List<UserAgenda> getUsers() {
        return users;
    }

    public void setUsers(List<UserAgenda> users) {
        this.users = users;
    }        
        
    
}

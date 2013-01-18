/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.AgendaBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import persistence.UserAgenda;



/**
 *
 * @author soleneantoine
 */
@Named(value = "autoCompleteBean")
public class AutoCompleteBean {
     private String userSelected;
     @EJB
    private AgendaBean agendaLocal;
     
     public List<String> complete(String query) {
        List<String> suggestions = new ArrayList<String>();  
        List<UserAgenda> l = agendaLocal.findAllUsers();
        
        for(UserAgenda u : l) {
            if((u.getEmail().startsWith(query))||(u.getFirstname().startsWith(query))||(u.getLastname().startsWith(query))) {
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
        
    
}

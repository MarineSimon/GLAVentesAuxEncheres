package controler;  
  
import business.AgendaBean;
import java.util.ArrayList;  
import java.util.List;  
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;  
  
import javax.faces.component.UIComponent;  
import javax.faces.context.FacesContext;  
import javax.faces.convert.Converter;  
import javax.faces.convert.ConverterException;  
import javax.persistence.TypedQuery;
import persistence.Agenda;
import persistence.UserAgenda;
  
  
public class UserAgendaConverter implements Converter {  
  
    public static List<UserAgenda> userDB; 
    @EJB
    private AgendaBean agendaLocal;
  
    static{  
        userDB = new ArrayList<UserAgenda>();
        //userDB.addAll(agendaLocal.findAllUsers());
        
        UserAgenda a = new UserAgenda("soso.antoine@gmail.com", "admin", "admin", "solene", "fr", "metz", "france", "metz", "", "", "");
        UserAgenda a2 = new UserAgenda("amelie.antoine@gmail.com", "admin", "admin", "ameli", "fr", "metz", "france", "metz", "", "", "");
        userDB.add(a);  
        userDB.add(a2);  

    }  
  
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {  
        if (submittedValue.trim().equals("")) {  
            return null;  
        } else {  
            try { 
  
                for (UserAgenda u : userDB) {  
                    if (u.getEmail() == submittedValue) {  
                        return u;  
                    }  
                }  
  
            } catch(NumberFormatException exception) {  
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));  
            }  
        }  
  
        return null;  
    }  
  
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((UserAgenda) value).getEmail());  
        }  
    }

    public List<UserAgenda> getUserDB() {
        return userDB;
    }

    public void setUserDB(List<UserAgenda> userDB) {
        this.userDB = userDB;
    }

    public AgendaBean getAgendaLocal() {
        return agendaLocal;
    }

    public void setAgendaLocal(AgendaBean agendaLocal) {
        this.agendaLocal = agendaLocal;
    }
    
    
}  
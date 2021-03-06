/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import library.UserBeanInterface;
import persistence.UserEnchere;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mohamed
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private UserEnchere userConnected;
    @EJB
    private UserBeanInterface userBean;
    
    private String login;
    private String password;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginBean", this);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String loginSuccessful() {
        UserEnchere user = userBean.getUserByLogin(login);
        if (user == null){
            FacesContext.getCurrentInstance().addMessage("formLogin:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login incorrect",""));
            return null;
        } else {
            if (!user.getPassword().equals(password)){
                FacesContext.getCurrentInstance().addMessage("formLogin:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Mot de passe incorrect",""));
                 return null;
            } else {
                userConnected = user;
            }
        }
        return "welcome";
    }
    
    public String logout(){
        userConnected = null;
        return "welcome";
    }
    
    public boolean isConnected(){
        return (userConnected != null);
    }
    
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
    
    public void verifyUseLogin(ComponentSystemEvent event){  
        if(!isConnected()){  
            doRedirect("login.xhtml");  
        }  
    }
    
    public void doRedirect(String url){  
    try {  
        FacesContext context=FacesContext.getCurrentInstance();  
        context.getExternalContext().redirect("login.xhtml");  
    } catch (IOException e) {  
          e.printStackTrace();  
    }  
   } 
    
}

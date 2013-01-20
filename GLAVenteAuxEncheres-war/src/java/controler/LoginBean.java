/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
import library.UserBeanInterface;

/**
 *
 * @author Mohamed
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    @EJB
    private UserBeanInterface userBean;
    private String login;
    private String password;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
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
        if (userBean.getUserByLogin(login) == null){
            FacesContext.getCurrentInstance().addMessage("formLogin:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login/Mot de passe incorrect",""));
            return null;
        }
        return "welcome";
    }
    
}

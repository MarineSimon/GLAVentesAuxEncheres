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
import persistence.UserEnchere;

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
        UserEnchere user = userBean.getUserByLogin(login);
        if (user == null){
            FacesContext.getCurrentInstance().addMessage("formLogin:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login incorrect",""));
            return null;
        } else {
            if (!user.getPassword().equals(password)){
                FacesContext.getCurrentInstance().addMessage("formLogin:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Mot de passe incorrect",""));
            return null;
            }
        }
        return "welcome";
    }
    
}

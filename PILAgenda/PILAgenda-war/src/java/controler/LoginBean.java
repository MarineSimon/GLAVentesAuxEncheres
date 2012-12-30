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
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.http.HttpSession;
import library.UserBeanLocalInterface;
import persistence.UserAgenda;

/**
 *
 * @author Mohamed
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private UserBeanLocalInterface userTryToConnect;
    private String userEmail;
    private String userPassWord;
    private String con;
    private boolean isLoggedIn;
    private UserAgenda userConnected;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public UserBeanLocalInterface getUserTryToConnect() {
        return userTryToConnect;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public UserAgenda getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(UserAgenda userConnected) {
        this.userConnected = userConnected;
    }

    public String loginSuccessful() {
        setUserConnected(userTryToConnect.userConnected(getUserEmail(), getUserPassWord()));
        if(getUserConnected() != null){
        if (getUserConnected().getPassword().equals(userPassWord)) {
            setCon("réussi");
            setIsLoggedIn(true);
            return "viewAgenda";

        } else {
            FacesContext.getCurrentInstance().addMessage("formLogin:btnLogin", new FacesMessage("email ou mot de passe érroné"));
            FacesContext.getCurrentInstance().addMessage("formLogin:btnLogin", new FacesMessage("vous ête nouveau, cliquez sur créer compte"));
            setIsLoggedIn(false);
            return "login";
        }
        }else{
            FacesContext.getCurrentInstance().addMessage("formLogin:btnLogin", new FacesMessage("email n'existe pas"));
            return "login";
        }
    }
     /** 
 * An event listener for redirecting the user to login page if he/she is not currently logged in 
 * @param event 
 */  
 public void verifyUseLogin(ComponentSystemEvent event){  
  if(!isLoggedIn){  
   doRedirect("login.xhtml");  
  }  
 }
 /** 
 * Method for redirecting a request 
 */  
 public void doRedirect(String url){  
  try {  
   FacesContext context=FacesContext.getCurrentInstance();  
   context.getExternalContext().redirect("login.xhtml");  
  } catch (IOException e) {  
     e.printStackTrace();  
  }  
 } 
 /** 
 * Method for loging out
 */ 
 public String logout(){
     FacesContext context=FacesContext.getCurrentInstance();
     HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
     session.invalidate();
     setIsLoggedIn(false);
     return "login";
 }
}
   
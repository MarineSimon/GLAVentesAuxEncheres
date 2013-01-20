/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import library.UserBeanInterface;
import persistence.InfosBuyer;
import persistence.UserEnchere;

/**
 *
 * @author Marine
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable{
    @EJB
    private UserBeanInterface userBean;
    
    private UserEnchere user;
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private String typeAccount;
    private String confirmPassword;
    private String deliveryAddress;
    private String deliveryZipCode;
    private String deliveryCity;
    private String numBankAccount;
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliberyZipCode() {
        return deliveryZipCode;
    }

    public void setDeliberyZipCode(String deliberyZipCode) {
        this.deliveryZipCode = deliberyZipCode;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }
    
    public String getNumBankAccount() {
        return numBankAccount;
    }

    public void setNumBankAccount(String numBankAccount) {
        this.numBankAccount = numBankAccount;
    }

    public String addUser(){
        String res = "welcome";
        user = new UserEnchere(lastname,firstname,login,password);
        if (!userBean.loginAvailable(user)){
            FacesContext.getCurrentInstance().addMessage("createAccount:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login déjà existant.",""));
            return null;
        }
        if (this.typeAccount.equals("buyer")){
            res = "additionalCreateAccount";
        } else {
            UserEnchere u = userBean.addUser(user);
        }
        return res;
    }
    
    public String additionnalAddUser(){
        user.setInfosBuyer(new InfosBuyer());
        user.getInfosBuyer().setDeliveryAddress(deliveryAddress);
        user.getInfosBuyer().setDeliveryCity(deliveryCity);
        user.getInfosBuyer().setDeliveryZipCode(Integer.parseInt(deliveryZipCode));
        user.getInfosBuyer().setNumBankAccount(Integer.parseInt(numBankAccount));
        
        userBean.addUser(user);
        return "welcome";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import library.UserBeanInterface;
import persistence.Address;
import persistence.BankInformations;
import persistence.InfosBuyer;
import persistence.UserEnchere;

/**
 *
 * @author Marine
 */
@Named(value = "accountBean")
@SessionScoped
public class AccountBean implements Serializable{
    @EJB
    private UserBeanInterface userBean;
    
    private UserEnchere user;
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private String confirmPassword;
    private String deliveryStreet;
    private String deliveryPostalCode;
    private String deliveryCity;
    private String deliveryNumber;
    private String deliveryCountry;
    private String numBankAccount;
    private String securityCode;
    private String expiryDate;
    private String email;
    private String birthday;
    
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

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    public void setDeliveryPostalCode(String deliveryPostalCode) {
        this.deliveryPostalCode = deliveryPostalCode;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }
    
    public String getNumBankAccount() {
        return numBankAccount;
    }

    public void setNumBankAccount(String numBankAccount) {
        this.numBankAccount = numBankAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    //REMPLI LES CHAMPS LOGIN ET MOT DE PASSE DE L'UTILISATEUR
    public String addUserAccountInfo(){
        user = new UserEnchere(login,password);
        if (!userBean.loginAvailable(user)){
            FacesContext.getCurrentInstance().addMessage("createAccount:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login déjà utilisé.",""));
            return null;
        }
        return "createAccountPersonnalInfo";
    }
    
    //REMPLI LES INFORMATIONS PERSONNELLES DE L'UTILISATEUR
    public String addUserPersonalInfo(){
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setBirthday(birthday);
        user.setEmail(email);
        return "createAccountDeliveryInfo";
    }
    
    //REMPLI LES INFORMATION DE LIVRAISON DE L'UTILISATEUR ET PERSISTE CELUI-CI
    public String addUserDeliveryInfo(){
        
        Address delivery = new Address();
        delivery.setCity(deliveryCity);
        delivery.setCountry(deliveryCountry);
        delivery.setStreet(deliveryStreet);
        if (!deliveryNumber.equals("")){
            delivery.setNumber(Integer.parseInt(deliveryNumber));
        }
        if (!deliveryPostalCode.equals("")){
            delivery.setPostalCode(Integer.parseInt(deliveryPostalCode));
        }
        List<Address> listAddress = new ArrayList<Address>();
        listAddress.add(delivery);
        user.setDelivery(listAddress);
        /*pour le moment, l'adresse de livraison est la mêmem que l'adresse de facturation*/
        user.setBiling(delivery); 
        
        BankInformations bankInfo = new BankInformations();
        bankInfo.setExpiryDate(expiryDate);
        if (!numBankAccount.equals("")){
            bankInfo.setBankAccountNumber(Integer.parseInt(numBankAccount));
        }
        if (!securityCode.equals("")){
            bankInfo.setSecurityCode(Integer.parseInt(securityCode));
        }
        List<BankInformations> listBank = new ArrayList<BankInformations>();
        listBank.add(bankInfo);
        //user.setBankInformations(listBank);
        
        userBean.addUser(user);
        return "welcome";
    }
    
}

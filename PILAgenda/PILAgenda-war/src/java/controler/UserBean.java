/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.UserBeanLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import persistence.UserAgenda;

/**
 *
 * @author Marine
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean{
    
    @EJB
    private UserBeanLocal local;
    
    private UserAgenda user;
    private String lastname;
    private String firstname;
    private String mail;
    private String phone;
    private String password;
    private String confirmPassword;
    private String language;
    private String country;
    private String locality;
    private String timeZone;
    private String hourFormat;
    private String defaultDuration;
    private boolean displayWeek;
    private boolean keyboardShortcut;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getHourFormat() {
        return hourFormat;
    }

    public void setHourFormat(String hourFormat) {
        this.hourFormat = hourFormat;
    }

    public String getDefaultDuration() {
        return defaultDuration;
    }

    public void setDefaultDuration(String DefaultDuration) {
        this.defaultDuration = DefaultDuration;
    }

    public boolean isDisplayWeek() {
        return displayWeek;
    }

    public void setDisplayWeek(boolean displayWeek) {
        this.displayWeek = displayWeek;
    }

    public boolean isKeyboardShortcut() {
        return keyboardShortcut;
    }

    public void setKeyboardShortcut(boolean keyboardShortcut) {
        this.keyboardShortcut = keyboardShortcut;
    }
    
    public String addAccount(){
        if (this.confirmPassword.equals(this.password)){
            user = new UserAgenda();
            
            user.setEmail(mail);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setPassword(password);
            user.setCity(locality);
            user.setCountry(country);
            user.setKeyboardShortcut(keyboardShortcut);
            user.setDefaultEventDuration(defaultDuration);
            user.setSeeWeekEnd(displayWeek);
            user.setLang(language);
            user.setPhone(phone);
            user.setHourFormat(hourFormat);
            user.setTimeZone(timeZone);
            
            local.addAccount(user);
        }
        return "viewAgenda";
    }
    
}

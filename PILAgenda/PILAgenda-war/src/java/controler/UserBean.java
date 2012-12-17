/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.UserBeanLocal;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Marine
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean{
    
    @EJB
    private UserBeanLocal local;
    
    private String lastname;
    private String firstname;
    private String mail;
    private int phone;
    private String password;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
                System.out.println(""+language);
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
}

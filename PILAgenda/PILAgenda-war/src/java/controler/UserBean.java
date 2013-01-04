/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.AgendaBean;
import business.UserBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import library.AgendaBeanLocal;
import persistence.Agenda;
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
    
    @EJB
    private AgendaBean agendaLocal;

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
    private Agenda agenda;
    
    private String[] timezones = new String[]{"GMT","GMT+1:00","GMT+2:00","GMT+3:00","GMT+3:30",
        "GMT+4:00","GMT+5:00","GMT+5:30","GMT+6:00","GMT+7:00","GMT+8:00","GMT+9:00","GMT+9:30",
        "GMT+10:00","GMT+11:00","GMT+12:00","GMT-11:00","GMT-10:00","GMT-9:00","GMT-8:00","GMT-7:00",
        "GMT-6:00","GMT-5:00","GMT-4:00","GMT-3:30","GMT-3:00","GMT-1:00"};
    
    private String[] languages = new String[]{"Français","Arabe","Chinois","Espagnol","Anglais",
        "Russe","Japonais","Allemand","Italien","Irlandais"," Luxembourgeois","Norvégien","Polonais"};
    
    private String[] cities = new String[]{"France","Russie","Canada","Chine","États-Unis","Brésil","Australie",
        "Inde","Argentine","Pologne","Algérie","République dominicaine","Suisse","Pays-Bas","Mexique","Italie",
        "Tunisie","Iran","Mongolie","Pérou","Royaume-Uni","Norvège","Irlande","Danemark","Afrique du Sud","Colombie",
        "Luxembourg","Autriche","Égypte","Roumanie","Grèce","Venezuela","Namibie","Mozambique","Pakistan","Turquie",
        "Chili","Zambie","Birmanie","Afghanistan","Belgique","Ukraine","Madagascar","Islande","Thaïlande","Espagne",
        "Suède","Maroc","Irak","Japon","Allemagne","Finlande"};

      public AgendaBean getAgendaLocal() {
        return agendaLocal;
    }
      
    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(String[] timezones) {
        this.timezones = timezones;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String[] getCities() {
        return cities;
    }

    public void setCities(String[] cities) {
        this.cities = cities;
    }
    
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

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    
    public void createDefaultAgenda() {
        agenda = new Agenda();
        agendaLocal.setUserConnected(user);
        String n;
        if (firstname.equals("")&&lastname.equals("")){
            n = mail;
        } else {
            n = firstname+" "+lastname;
        }
        agenda.setName(n);
        agenda.setAccess(0);
        agenda.setDescription("Agenda personnel");
        // agenda.setColor(c);
        agendaLocal.createAgenda(agenda);
    }
    
    public String addAccount(){
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
            createDefaultAgenda();
        return "viewAgenda";
    }
    @PostConstruct
    void initialiseSession() {
         FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
}

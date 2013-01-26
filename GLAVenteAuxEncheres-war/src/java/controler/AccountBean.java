/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import library.UserBeanInterface;
import persistence.Address;
import persistence.BankInformation;
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
    private Calendar birthday;
    private String[] days = new String[]{"--","1","2","3","4","5","6","7","8","9","10",
                                        "11","12","13","14","15","16","17","18","19",
                                        "20","21","22","23","24","25","26","27","28","29","30","31"};
    private String[] mounths = new String[]{"--","1","2","3","4","5","6","7","8","9","10",
                                        "11","12"};
    private String[] years = new String[]{"----","1930","1931","1932","1933","1934","1935","1936","1937","1938","1939",
                                        "1940","1941","1942","1943","1944","1945","1946","1947","1948","1949",
                                        "1950","1951","1952","1953","1954","1955","1956","1957","1958","1959",
                                        "1960","1961","1962","1963","1964","1965","1966","1967","1968","1969",
                                        "1970","1971","1972","1973","1974","1975","1976","1977","1978","1979",
                                        "1980","1981","1982","1983","1984","1985","1986","1987","1988","1989",
                                        "1990","1991","1992","1993","1994","1995","1996","1997","1998","1999"};
    private String dayBirthday;
    private String mounthBirthday;
    private String yearBirthday;
    
    
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

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
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

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    public String[] getMounths() {
        return mounths;
    }

    public void setMounths(String[] mounths) {
        this.mounths = mounths;
    }

    public String[] getYears() {
        return years;
    }

    public void setYears(String[] years) {
        this.years = years;
    }

    public String getDayBirthday() {
        return dayBirthday;
    }

    public void setDayBirthday(String dayBirthday) {
        this.dayBirthday = dayBirthday;
    }

    public String getMounthBirthday() {
        return mounthBirthday;
    }

    public void setMounthBirthday(String mounthBirthday) {
        this.mounthBirthday = mounthBirthday;
    }

    public String getYearBirthday() {
        return yearBirthday;
    }

    public void setYearBirthday(String yearBirthday) {
        if (!dayBirthday.equals("--")&&!mounthBirthday.equals("--")&&!yearBirthday.equals("----")){
            birthday = new GregorianCalendar();
            birthday.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayBirthday));
            birthday.set(Calendar.MONTH, Integer.parseInt(mounthBirthday)-1);
            birthday.set(Calendar.YEAR, Integer.parseInt(yearBirthday));
        }
        
        this.yearBirthday = yearBirthday;
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
    
    //REMPLI LES INFORMATIONS DE LIVRAISON DE L'UTILISATEUR ET PERSISTE CELUI-CI
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
        user.setDeliveryAdresses(listAddress);
        /*pour le moment, l'adresse de livraison est la même que l'adresse de facturation*/
        user.setBilingAdress(delivery); 
        
        BankInformation bankInfo = new BankInformation();
        bankInfo.setExpiryDate(expiryDate);
        if (!numBankAccount.equals("")){
            Long na = Long.parseLong(numBankAccount);
            bankInfo.setBankAccountNumber(na);
        }
        if (!securityCode.equals("")){
            bankInfo.setSecurityCode(Integer.parseInt(securityCode));
        }
        List<BankInformation> listBank = new ArrayList<BankInformation>();
        listBank.add(bankInfo);
        //user.setBankInformations(listBank);
        
        userBean.addUser(user);
        return "welcome";
    }
    
}

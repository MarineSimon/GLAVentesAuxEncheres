/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Marine
 */
@Entity
@NamedQueries({
        @NamedQuery(name="UserEnchere.findUserByLogin", query="SELECT u from UserEnchere u WHERE u.login = ?1")
    })
public class UserEnchere implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "BIRTHDAY")
    private String birthday;
    @Column(name = "ABANDONRECORDER")
    private int abandonsRecorder;
    @OneToOne(cascade = CascadeType.PERSIST,optional=false)
    private Address bilingAdress;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Address> deliveryAdresses;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<BankInformation> bankInformation;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Notification> notifications;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Article> sellArticles;
    
    public UserEnchere(){
        
    }
    
    public UserEnchere(String login,String password){
        this.abandonsRecorder = 0;
        this.login = login;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getAbandonsRecorder() {
        return abandonsRecorder;
    }

    public void setAbandonsRecorder(int abandonsRecorder) {
        this.abandonsRecorder = abandonsRecorder;
    }

    public Address getBilingAdress() {
        return bilingAdress;
    }

    public void setBilingAdress(Address biling) {
        this.bilingAdress = biling;
    }

    public List<Address> getDeliveryAdresses() {
        return deliveryAdresses;
    }

    public void setDeliveryAdresses(List<Address> delivery) {
        this.deliveryAdresses = delivery;
    }

    public List<BankInformation> getBankInformation() {
        return bankInformation;
    }

    public void setBankInformations(List<BankInformation> bankInformation) {
        this.bankInformation = bankInformation;
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

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Article> getSellArticles() {
        return sellArticles;
    }

    public void setSellArticles(List<Article> sellArticles) {
        this.sellArticles = sellArticles;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEnchere)) {
            return false;
        }
        UserEnchere other = (UserEnchere) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.User[ id=" + id + " ]";
    }
    
}

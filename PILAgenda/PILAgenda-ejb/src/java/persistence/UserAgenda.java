/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

/**
 *
 * @author gaelvarlet
 */
@Entity
public class UserAgenda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LANG")
    private String lang;
    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(mappedBy = "taskOwner")
    private Set<Task> tasks;
    
    @OneToMany(mappedBy = "customizedEventOwner")
    private Set<CustomizeEvent> customizedEvents;
      
    @OneToMany(mappedBy = "eventOwner")
    private Set<Event> events;
    
    @OneToMany(mappedBy = "groupAdministrator")
    private Set<GroupAgenda> administratedGroups;
    
    @OneToMany(mappedBy = "agendaOwner")
    private Set<Agenda> agendas;
    
    @ManyToMany
    private Set<GroupAgenda> belongToGroups;
    
    @ManyToMany
    private Set<GroupAgenda> guestToGroups;
    
    @ManyToMany
    private Set<Agenda> followedAgendas;
    
    @ManyToMany
    private Set<Agenda> displayedAgendas;
    
    
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof UserAgenda)) {
            return false;
        }
        UserAgenda other = (UserAgenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.UserAgenda[ id=" + id + " ]";
    }
    
}

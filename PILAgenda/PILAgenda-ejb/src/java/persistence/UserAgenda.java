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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "SEEWEEKEND")
    private boolean seeWeekEnd;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "CITY")
    private String city;
    @Column(name = "TIMEZONE")
    private String timeZone;
    @Column(name = "HOURFORMAT")
    private String hourFormat;
    @Column(name = "DEFAULTEVENTDURATION")
    private String defaultEventDuration;
    @Column(name = "KEYBOARDSHORTCUT")
    private boolean keyboardShortcut;
    

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

    public UserAgenda() {
    }

    public UserAgenda(String email, String password, String lastname, String firstname, String lang, String address, String pwd, String country, String city, String timeZone, String hourFormat, String defaultEventDuration) {
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.lang = lang;
        this.address = address;
        this.country = country;
        this.city = city;
        this.timeZone = timeZone;
        this.hourFormat = hourFormat;
        this.defaultEventDuration = defaultEventDuration;
        //Ajouter agenda par défaut
    }
    
    
    
    // Getters and Setters
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<CustomizeEvent> getCustomizedEvents() {
        return customizedEvents;
    }

    public void setCustomizedEvents(Set<CustomizeEvent> customizedEvents) {
        this.customizedEvents = customizedEvents;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<GroupAgenda> getAdministratedGroups() {
        return administratedGroups;
    }

    public void setAdministratedGroups(Set<GroupAgenda> administratedGroups) {
        this.administratedGroups = administratedGroups;
    }

    public Set<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(Set<Agenda> agendas) {
        this.agendas = agendas;
    }

    public Set<GroupAgenda> getBelongToGroups() {
        return belongToGroups;
    }

    public void setBelongToGroups(Set<GroupAgenda> belongToGroups) {
        this.belongToGroups = belongToGroups;
    }

    public Set<GroupAgenda> getGuestToGroups() {
        return guestToGroups;
    }

    public void setGuestToGroups(Set<GroupAgenda> guestToGroups) {
        this.guestToGroups = guestToGroups;
    }

    public Set<Agenda> getFollowedAgendas() {
        return followedAgendas;
    }

    public void setFollowedAgendas(Set<Agenda> followedAgendas) {
        this.followedAgendas = followedAgendas;
    }

    public Set<Agenda> getDisplayedAgendas() {
        return displayedAgendas;
    }

    public void setDisplayedAgendas(Set<Agenda> displayedAgendas) {
        this.displayedAgendas = displayedAgendas;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSeeWeekEnd() {
        return seeWeekEnd;
    }

    public void setSeeWeekEnd(boolean seeWeekEnd) {
        this.seeWeekEnd = seeWeekEnd;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getDefaultEventDuration() {
        return defaultEventDuration;
    }

    public void setDefaultEventDuration(String defaultEventDuration) {
        this.defaultEventDuration = defaultEventDuration;
    }

    public boolean isKeyboardShortcut() {
        return keyboardShortcut;
    }

    public void setKeyboardShortcut(boolean keyboardShortcut) {
        this.keyboardShortcut = keyboardShortcut;
    }
    
    
    
    
    // Default Functions
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

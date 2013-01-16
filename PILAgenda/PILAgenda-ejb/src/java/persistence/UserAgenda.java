/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author gaelvarlet
 */
@Entity
@NamedQueries({
        @NamedQuery(name="UserAgenda.findPassWordByEmail", query="SELECT u from UserAgenda u WHERE u.email = ?1")
    })
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
    

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "taskOwner")
    private List<Task> tasks;
    
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "customizedEventOwner")
    private List<CustomizeEvent> customizedEvents;
      
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "eventOwner")
    private List<Event> events;
    
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "groupAdministrator")
    private List<GroupAgenda> administratedGroups;
    
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "agendaOwner")
    private List<Agenda> agendas;
    
    @ManyToMany
    @JoinTable(name="USERS_BELONG_GROUP",joinColumns= @JoinColumn(name="USER_ID",referencedColumnName="ID"),inverseJoinColumns=@JoinColumn(name="GROUP_ID",referencedColumnName="ID"))
    private List<GroupAgenda> belongToGroups;
    
    @ManyToMany
    @JoinTable(name="USERS_GUESTS_GROUP",joinColumns= @JoinColumn(name="USER_ID",referencedColumnName="ID"),inverseJoinColumns=@JoinColumn(name="GROUP_ID",referencedColumnName="ID"))    
    private List<GroupAgenda> guestToGroups;
    
    @ManyToMany
    @JoinTable(name="USERS_FOLLOWED_AGENDAS",joinColumns= @JoinColumn(name="USER_ID",referencedColumnName="ID"),inverseJoinColumns=@JoinColumn(name="AGENDA_ID",referencedColumnName="ID"))
    private List<Agenda> followedAgendas;
    
    @ManyToMany
    @JoinTable(name="USERS_DISPLAYED_AGENDAS",joinColumns= @JoinColumn(name="USER_ID",referencedColumnName="ID"),inverseJoinColumns=@JoinColumn(name="AGENDA_ID",referencedColumnName="ID"))
    private List<Agenda> displayedAgendas;

    public UserAgenda() {
    }

    public UserAgenda(String email, String password, String lastname, String firstname, String lang, String address, String country, String city, String timeZone, String hourFormat, String defaultEventDuration) {
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
        this.tasks = new ArrayList<Task>();
        this.customizedEvents = new ArrayList<CustomizeEvent>();
        this.events = new ArrayList<Event>();
        this.administratedGroups = new ArrayList<GroupAgenda>();
        this.agendas = new ArrayList<Agenda>();
        this.belongToGroups = new ArrayList<GroupAgenda>();
        this.guestToGroups = new ArrayList<GroupAgenda>();
        this.followedAgendas = new ArrayList<Agenda>();
        this.displayedAgendas = new ArrayList<Agenda>();
        //Ajouter agenda par défaut
    }
    
    
    
    // Getters and Listters
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<CustomizeEvent> getCustomizedEvents() {
        return customizedEvents;
    }

    public void setCustomizedEvents(List<CustomizeEvent> customizedEvents) {
        this.customizedEvents = customizedEvents;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<GroupAgenda> getAdministratedGroups() {
        return administratedGroups;
    }

    public void setAdministratedGroups(List<GroupAgenda> administratedGroups) {
        this.administratedGroups = administratedGroups;
    }

    public List<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<Agenda> agendas) {
        this.agendas = agendas;
    }

    public List<GroupAgenda> getBelongToGroups() {
        return belongToGroups;
    }

    public void setBelongToGroups(List<GroupAgenda> belongToGroups) {
        this.belongToGroups = belongToGroups;
    }

    public List<GroupAgenda> getGuestToGroups() {
        return guestToGroups;
    }

    public void setGuestToGroups(List<GroupAgenda> guestToGroups) {
        this.guestToGroups = guestToGroups;
    }

    public List<Agenda> getFollowedAgendas() {
        return followedAgendas;
    }

    public void setFollowedAgendas(List<Agenda> followedAgendas) {
        this.followedAgendas = followedAgendas;
    }

    public List<Agenda> getDisplayedAgendas() {
        return displayedAgendas;
    }

    public void setDisplayedAgendas(List<Agenda> displayedAgendas) {
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

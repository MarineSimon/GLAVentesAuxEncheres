/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author gaelvarlet
 */
@Entity
public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int PRIVATE_AGENDA = 0;
    private final int PUBLIC_AGENDA = 1;
    private final int SHARED_AGENDA = 2;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "ACCESS")
    private int access;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    
    @ManyToOne(optional=false)
    private UserAgenda agendaOwner;
    
    @ManyToOne(optional=true)
    private GroupAgenda belongToGroup;
    
    @ManyToMany(mappedBy = "followedAgendas")
    private List<UserAgenda> followers;
    
    @ManyToMany(mappedBy = "displayedAgendas")
    private List<UserAgenda> displayers;
    
    @ManyToMany(mappedBy = "belongToAgendas")
    private List<Event> acceptedEvents;
    
    @ManyToMany(mappedBy = "guestToAgendas")
    private List<Event> guestEvents;

    public Agenda(int access, String color, String name, String description, UserAgenda agendaOwner) {
        this.access = access;
        this.color = color;
        this.name = name;
        this.description = description;
        this.agendaOwner = agendaOwner;
        this.followers = new ArrayList<UserAgenda>();
        this.displayers = new ArrayList<UserAgenda>();
        this.acceptedEvents = new ArrayList<Event>();
        this.guestEvents = new ArrayList<Event>();
    }
    
    public Agenda(){
    }
    
    public Long getId() {
        return id;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserAgenda getAgendaOwner() {
        return agendaOwner;
    }

    public void setAgendaOwner(UserAgenda agendaOwner) {
        this.agendaOwner = agendaOwner;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public GroupAgenda getBelongToGroup() {
        return belongToGroup;
    }

    public void setBelongToGroup(GroupAgenda belongToGroup) {
        this.belongToGroup = belongToGroup;
    }

    public List<UserAgenda> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserAgenda> followers) {
        this.followers = followers;
    }

    public List<UserAgenda> getDisplayers() {
        return displayers;
    }

    public void setDisplayers(List<UserAgenda> displayers) {
        this.displayers = displayers;
    }

    public List<Event> getAcceptedEvents() {
        return acceptedEvents;
    }

    public void setAcceptedEvents(List<Event> acceptedEvents) {
        this.acceptedEvents = acceptedEvents;
    }

    public List<Event> getGuestEvents() {
        return guestEvents;
    }

    public void setGuestEvents(List<Event> guestEvents) {
        this.guestEvents = guestEvents;
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
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Agenda[ id=" + id + " ]";
    }
    
}

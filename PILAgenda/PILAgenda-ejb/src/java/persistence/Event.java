/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author gaelvarlet
 */
@Entity
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "NAME")
    private String name;
    @Column(name = "PLACE")
    private String place;
    @Column(name = "BEGINDATE")
    private Date beginDate;
    @Column(name = "ENDDATE")
    private Date endDate;
    @Column(name = "VISIBILITY")
    private int visibility;
    @Column(name = "DESCRIPTION")
    private String description;
    
    @OneToMany(mappedBy="relatedEvent")
    private List<CustomizeEvent> customizedEvents;
    
    @OneToOne(cascade = CascadeType.PERSIST,optional=false)
    private Periodicity periodicity;
    
    @ManyToOne(optional=false)
    private UserAgenda eventOwner;
    
    @ManyToMany
    @JoinTable(name="EVENTS_BELONG_AGENDAS",joinColumns= @JoinColumn(name="EVENT_ID",referencedColumnName="ID"),inverseJoinColumns=@JoinColumn(name="AGENDA_ID",referencedColumnName="ID"))
    private List<Agenda> belongToAgendas;
    
    @ManyToMany
    @JoinTable(name="EVENTS_GUESTS_AGENDAS",joinColumns= @JoinColumn(name="EVENT_ID",referencedColumnName="ID"),inverseJoinColumns=@JoinColumn(name="AGENDA_ID",referencedColumnName="ID"))    
    private List<Agenda> guestToAgendas;

    public Event() {
    }

    public Event(String name, String place, Date beginDate, Date endDate, int visibility, String description, Periodicity periodicity, UserAgenda eventOwner, Agenda agenda) {
        this.name = name;
        this.place = place;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.visibility = visibility;
        this.description = description;
        this.periodicity = periodicity;
        this.eventOwner = eventOwner;
        this.belongToAgendas = new ArrayList<Agenda>();
        this.belongToAgendas.add(agenda);
        this.customizedEvents = new ArrayList<CustomizeEvent>();
        this.guestToAgendas = new ArrayList<Agenda>();
    }
    
    public Event(String name, String place, Date beginDate, Date endDate, int visibility, String description, Periodicity periodicity, UserAgenda eventOwner, Agenda agenda,List<Agenda> guests) {
        this.name = name;
        this.place = place;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.visibility = visibility;
        this.description = description;
        this.periodicity = periodicity;
        this.eventOwner = eventOwner;
        this.belongToAgendas = new ArrayList<Agenda>();
        this.belongToAgendas.add(agenda);
        this.customizedEvents = new ArrayList<CustomizeEvent>();
        this.guestToAgendas = guests;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CustomizeEvent> getCustomizedEvents() {
        return customizedEvents;
    }

    public void setCustomizedEvents(List<CustomizeEvent> customizedEvents) {
        this.customizedEvents = customizedEvents;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public UserAgenda getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(UserAgenda eventOwner) {
        this.eventOwner = eventOwner;
    }

    public List<Agenda> getBelongToAgendas() {
        return belongToAgendas;
    }

    public void setBelongToAgendas(List<Agenda> belongToAgendas) {
        this.belongToAgendas = belongToAgendas;
    }

    public List<Agenda> getGuestToAgendas() {
        return guestToAgendas;
    }

    public void setGuestToAgendas(List<Agenda> guestToAgendas) {
        this.guestToAgendas = guestToAgendas;
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Event[ id=" + id + " ]";
    }
    
}
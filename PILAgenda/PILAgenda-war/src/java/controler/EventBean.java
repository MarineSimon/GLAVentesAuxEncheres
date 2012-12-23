/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import library.AgendaBeanLocal;
import library.EventBeanLocalInterface;
import persistence.Agenda;
import persistence.Event;
import persistence.Periodicity;

/**
 *
 * @author Swann
 */
@Named(value = "eventBean")
@RequestScoped
public class EventBean implements Serializable{

    /**
     * Creates a new instance of EventBean
     */
    private String title, location, name, beginDate, endDate, description; 
    private int visibility;
    private Long idAgenda;
    //Periodicity periodicity, UserAgenda eventOwner, Agenda agenda;
    @EJB 
    private EventBeanLocalInterface eventInterface;
    @EJB 
    private AgendaBeanLocal agendaInterface;
    private Date dateLimite;
    private int typePeriod;
    private int numberOfRepetition;
    private Date eventBeginDate;
    private Date eventEndDate;
    
    public EventBean() {
        this.eventBeginDate = new Date(System.currentTimeMillis());
        this.eventEndDate = new Date(System.currentTimeMillis()+3600000);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public int getTypePeriod() {
        return typePeriod;
    }

    public void setTypePeriod(int typePeriod) {
        this.typePeriod = typePeriod;
    }

    public int getNumberOfRepetition() {
        return numberOfRepetition;
    }

    public void setNumberOfRepetition(int numberOfRepetition) {
        this.numberOfRepetition = numberOfRepetition;
    }

    public Date getEventBeginDate() {
        return eventBeginDate;
    }

    public void setEventBeginDate(Date eventBeginDate) {
        this.eventBeginDate = eventBeginDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }
    
    public String addEvent() throws ParseException {
        String res = "viewAgenda";
        //DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US); //Wed Dec 19 21:45:00 CET 2012
        //long debut = formatter.parse(this.beginDate).getTime();
        //long fin = formatter.parse(this.endDate).getTime();
        if (this.eventBeginDate.after(eventEndDate)) {
            res = "createEvent";
        }
        if(this.eventBeginDate.before(eventEndDate)){
            Periodicity period;
            if (this.dateLimite == null) {
                period = new Periodicity(this.typePeriod,this.numberOfRepetition);
            }
            else {
                java.sql.Date limit = new java.sql.Date(this.dateLimite.getTime());
                period = new Periodicity(this.typePeriod,this.numberOfRepetition,limit);
            }
            Agenda a = this.agendaInterface.findAgenda(this.idAgenda);
            Event e;
            java.sql.Date begin = new java.sql.Date(this.eventBeginDate.getTime());
            java.sql.Date end = new java.sql.Date(this.eventEndDate.getTime());
            e = new Event(this.name, this.location, begin, end, this.visibility, this.description, period, null, a);
            this.eventInterface.addEvent(e);
        }
        return res;
    }
    
    public List<SelectItem> listAllAgendaInEvent() {
        List<SelectItem> l = new ArrayList<SelectItem>();
        List<Agenda> listAgenda = this.agendaInterface.findAllAgenda();
        for (Agenda a : listAgenda) {
            l.add(new SelectItem(a.getId(), a.getName()));
        }
        return l;
    }
    
}

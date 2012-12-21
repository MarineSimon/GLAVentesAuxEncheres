/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import library.EventBeanLocalInterface;
import persistence.Agenda;
import persistence.Event;

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
    private boolean eventAdded;
    private Agenda agenda;
    //Periodicity periodicity, UserAgenda eventOwner, Agenda agenda;
    @EJB 
    private EventBeanLocalInterface eventInterface;
    
    public EventBean() {
        this.eventAdded = false;
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

    public boolean isEventAdded() {
        return eventAdded;
    }

    public void setEventAdded(boolean eventAdded) {
        this.eventAdded = eventAdded;
    }
    
    public boolean getEventAdded() {
        return this.eventAdded;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    
    public String addEvent() throws ParseException {
        System.out.println("test");
        String res = "viewAgenda";
        this.eventAdded = true;
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US); //Wed Dec 19 21:45:00 CET 2012
        long debut = formatter.parse(this.beginDate).getTime();
        long fin = formatter.parse(this.endDate).getTime();
        if (debut > fin) {
            res = "createEvent";
        }
        Date begin = new Date(debut);
        Date end = new Date(fin);
        if(debut <= fin){
            Event e;
            e = new Event(this.name, this.location, begin, end, this.visibility, this.description, null, null, agenda);
            this.eventInterface.addEvent(e);
        }
        return res;
    }
    
    public String goToAddEvent() {
        return "createEvent";
    }
    
}

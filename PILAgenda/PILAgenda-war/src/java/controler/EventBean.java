/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import library.EventBeanLocalInterface;
import persistence.Event;

/**
 *
 * @author Swann
 */
@Named(value = "eventBean")
@RequestScoped
public class EventBean {

    /**
     * Creates a new instance of EventBean
     */
    private String title, location, name, beginDate, endDate, description; 
    private int visibility;
    //Periodicity periodicity, UserAgenda eventOwner, Agenda agenda;
    @EJB 
    private EventBeanLocalInterface eventInterface;
    
    public EventBean() {
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
    
    
    
    public void addEvent() throws ParseException {
        
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US); //Wed Dec 19 21:45:00 CET 2012
        long debut = formatter.parse(this.beginDate).getTime();
        long fin = formatter.parse(this.endDate).getTime();
        if (debut > fin) {
            System.out.println("Début plus tard que fin");
            return;
        }
        Date begin = new Date(debut);
        Date end = new Date(fin);
        Event e;
        //Event(String name, String place, Date beginDate, Date endDate, int visibility, String description, Periodicity periodicity, UserAgenda eventOwner, Agenda agenda)
        e = new Event(this.name, this.location, begin, end, this.visibility, this.description, null, null, null);
        this.eventInterface.addEvent(e);
                
    }
}

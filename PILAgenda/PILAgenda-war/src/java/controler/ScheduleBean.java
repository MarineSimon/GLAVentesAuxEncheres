package controler;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import library.EventBeanLocalInterface;
import library.TaskBeanLocalInterface;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import persistence.Agenda;
import persistence.Event;
import persistence.UserAgenda;


/**
 *
 * @author soleneantoine
 */

@Named(value = "scheduleBean")
@SessionScoped
public class ScheduleBean implements Serializable{
    
    @EJB
    private TaskBeanLocalInterface bean;
    @EJB
    private EventBeanLocalInterface eventBeanLocal;

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private EventBean eventBean;
    private Date dateSelectedToDisplay;
    private String displayMode;

    public ScheduleBean() {
        eventBean = new EventBean();
        this.dateSelectedToDisplay = new Date(System.currentTimeMillis());
        eventModel = new DefaultScheduleModel();
        this.displayMode = "agendaDay";
    }

    public String getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(String displayMode) {
        this.displayMode = displayMode;
    }
    
    public void switchToDay() {
        this.displayMode = "agendaDay";
        RequestContext.getCurrentInstance().update("j_idt8:vueAgenda:agenda");
    }
    
    public void switchToWeek() {
        this.displayMode = "agendaWeek";
        RequestContext.getCurrentInstance().update("j_idt8:vueAgenda:agenda");
    }

    public void switchToMonth() {
        this.displayMode = "month";
        RequestContext.getCurrentInstance().update("j_idt8:vueAgenda:agenda");
    }
        
    public Date getDateSelectedToDisplay() {
        return dateSelectedToDisplay;
    }

    public void setDateSelectedToDisplay(Date dateSelectedToDisplay) {
        this.dateSelectedToDisplay = dateSelectedToDisplay;
    }
        
        
        public void findEvents(){
            eventModel.clear();
            
            //Récupération de l'utilisateur courant
                UserAgenda user = bean.getUserConnected();
                
                //Liste des évènements de l'utilisateur courant
                List<Event> events = (List<Event>) eventBeanLocal.findAllEvent(user);
                for(Event event : events){
                    
                    Date begin = event.getBeginDate();
                    Date end = event.getEndDate();
                    String name = event.getName();
                    
                    Agenda agendaOfEventAndUser =  eventBeanLocal.getAgendaOfEventFromUser(event, user);
                    String colorOfEvent = agendaOfEventAndUser.getColor();
                    
                    DefaultScheduleEvent ev = new DefaultScheduleEvent(name, begin, end, ("evt-" + colorOfEvent));
                    ev.setData(event);
                    
                    eventModel.addEvent(ev);
                }
        }
        
    public void handleDateSelect(DateSelectEvent event) { 
        RequestContext.getCurrentInstance().update("j_idt8:vueAgenda:agenda");
    } 
    
    public void goToPrevious() {
        if (this.displayMode.equals("agendaWeek")) {
            this.dateSelectedToDisplay.setHours(this.dateSelectedToDisplay.getDay()-6*24);
        }
        if (this.displayMode.equals("month")) {
            this.dateSelectedToDisplay.setMonth(this.dateSelectedToDisplay.getMonth()-1);
        }
        else {
            this.dateSelectedToDisplay.setHours(this.dateSelectedToDisplay.getDay()-24);
        }
        RequestContext.getCurrentInstance().update("j_idt8:vueAgenda:agenda");
        RequestContext.getCurrentInstance().update("j_idt8:miniCal:inlineCal");
    }
    
    public void goToNext() {
        if (this.displayMode.equals("agendaWeek")) {
            this.dateSelectedToDisplay.setHours(this.dateSelectedToDisplay.getDay()+6*24); //6 au lieu de 7...
        }
        if (this.displayMode.equals("month")) {
            this.dateSelectedToDisplay.setMonth(this.dateSelectedToDisplay.getMonth()+1);
        }
        else {
            this.dateSelectedToDisplay.setHours(this.dateSelectedToDisplay.getDay()+24);
        }
        RequestContext.getCurrentInstance().update("j_idt8:vueAgenda:agenda");
        RequestContext.getCurrentInstance().update("j_idt8:miniCal:inlineCal");
    }
    
    public void goToToday() {
        this.dateSelectedToDisplay.setTime(System.currentTimeMillis());
        RequestContext.getCurrentInstance().update("j_idt8:vueAgenda:agenda");
        RequestContext.getCurrentInstance().update("j_idt8:miniCal:inlineCal");
    }
        
        public Date getInitialDate() {
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
                
                return calendar.getTime();
        }
        
        public ScheduleModel getEventModel() {
                return eventModel;
        }
        
        public ScheduleModel getModel() {
            return eventModel;
        }
        
        public void addEvent(ActionEvent actionEvent) {
        try {
            eventBean.addEvent();
        } catch (ParseException ex) {
            Logger.getLogger(ScheduleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(event.getId() == null) {
                eventModel.addEvent(event);
            }  
            else {
                eventModel.updateEvent(event);
            }  
            event = new DefaultScheduleEvent();  
        }
        
        private Calendar today() {
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE), 0, 0, 0);

                return calendar;
        }
        
        public void onEventSelect(ScheduleEntrySelectEvent e) {
            System.out.println("Title : " + e.getScheduleEvent().getTitle()); 
            //System.out.println("" + ((Event)(e.getScheduleEvent().getData())).getName()); 
               event = e.getScheduleEvent();
        }
        
        public void onDateSelect(DateSelectEvent e) {
               event = new DefaultScheduleEvent("", e.getDate(), e.getDate());
        }
        
        public ScheduleEvent getEvent() {
                return event;
        }

        public void setEvent(ScheduleEvent event) {
                this.event = event;
        }
        
        public void onEventMove(ScheduleEntryMoveEvent event) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
                addMessage(message);
        }
        
         public void onEventResize(ScheduleEntryResizeEvent event) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
                addMessage(message);
        }
         
         private void addMessage(FacesMessage message) {
                FacesContext.getCurrentInstance().addMessage(null, message);
        }
}

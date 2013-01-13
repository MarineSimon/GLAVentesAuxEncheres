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
        
        public ScheduleBean() {
            eventBean = new EventBean();
            
            eventModel = new DefaultScheduleModel();
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
                    
                    DefaultScheduleEvent ev = new DefaultScheduleEvent(name, begin, end);
                    ev.setData(event);
                    
                    eventModel.addEvent(ev);
                }
        }
        
    public void handleDateSelect(DateSelectEvent event) { 
        RequestContext.getCurrentInstance().update("j_idt8:vueAgenda:agenda");
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
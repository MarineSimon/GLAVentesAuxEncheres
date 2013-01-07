package controler;

import business.AgendaBean;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
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
    
        @Inject
        private AgendaBean agendaInterface;
    
        private ScheduleModel eventModel;
        private ScheduleEvent event = new DefaultScheduleEvent();
        private EventBean eventBean;
        
        public ScheduleBean() {
            eventBean = new EventBean();
            
            eventModel = new DefaultScheduleModel();
            
        }
        
        public Date getInitialDate() {
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
                
                //Récupération de l'utilisateur courant
                UserAgenda user = agendaInterface.getUserConnected();
                
                //Liste des évènements de l'utilisateur courant
                ArrayList<Event> events = new ArrayList<Event>();
                
                
                
                return calendar.getTime();
        }
        
        public ScheduleModel getEventModel() {
                return eventModel;
        }
        
        public ScheduleModel getModel() {
            return eventModel;
        }
        
        public void addEvent(ActionEvent actionEvent) {  
            System.out.println("AddEvent appelé");
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
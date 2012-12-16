/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.Date;
import javax.inject.Named;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author soleneantoine
 */

@Named(value = "scheduleBean")
public class ScheduleBean {
    
        private ScheduleModel eventModel;
        
        public ScheduleBean() {
            eventModel = new DefaultScheduleModel();  
        }
        
        public ScheduleModel getModel() {
            return eventModel;
        }
}
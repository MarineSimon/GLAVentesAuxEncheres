/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Swann
 */
@Named(value = "eventBean")
@ViewScoped
public class EventBean {

    /**
     * Creates a new instance of EventBean
     */
    private String title, location;
    
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
    
    public void addEvent() {
        System.out.println("Evenement appelé");
    }
}

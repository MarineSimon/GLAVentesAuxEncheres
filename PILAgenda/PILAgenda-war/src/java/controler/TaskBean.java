/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import library.TaskBeanLocalInterface;
import persistence.Task;
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;
import org.primefaces.event.DateSelectEvent;

/**
 *
 * @author flav
 */
@Named(value = "taskBean")
@RequestScoped
public class TaskBean {
    @EJB
    private TaskBeanLocalInterface beanLocal;
    
    private String name;
    private Date dateLimite;
    private String description;
    private List<Task> listOfTask;

    /**
     * Creates a new instance of TaskBean
     */
    public TaskBean() {
        dateLimite=new Date(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public List<Task> getListOfTask() {
        return listOfTask;
    }

    public void setListOfTask(List<Task> listOfTask) {
        this.listOfTask = listOfTask;
    }
   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String addTask(){
        String retour="viewAgenda.xhtml";
        Task task = new Task(name,new java.sql.Date(this.dateLimite.getTime()),description,null);
        beanLocal.addTask(task);
        return retour;
    }
    public void handleDateSelect(DateSelectEvent event) {  
        Date curent = new Date(System.currentTimeMillis());
        if(curent.after(dateLimite)){
            FacesContext facesContext = FacesContext.getCurrentInstance();  
            SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La date sélectionnée est passée :", format.format(event.getDate()))); 
        }
    }  
        
    public List<String> listAllTask() {
        setListOfTask(beanLocal.findAllTask(beanLocal.getUserConnected()));
        List<String> l = new ArrayList<String>();
        l.removeAll(l);
        for (int i = 0; i < listOfTask.size(); i++) {
            Task a = listOfTask.get(i);
            l.add(a.getName());
        }
        return l;
    }

}

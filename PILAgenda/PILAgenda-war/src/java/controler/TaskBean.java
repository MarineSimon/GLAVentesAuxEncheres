/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.swing.text.View;
import library.TaskBeanLocalInterface;
import org.primefaces.event.DateSelectEvent;
import persistence.Task;

/**
 *
 * @author flav
 */
@Named(value = "taskBean")
@SessionScoped
public class TaskBean implements Serializable {
    @EJB
    private TaskBeanLocalInterface beanLocal;
    
    private String name;
    private Date dateLimite;
    private String description;
    private List<Task> listOfTask;
    private Task selectedTask;
    private String nameSelectedTask;
    private Date dateSelectedTask;
    private String descriptionSelectedTask;

    /**
     * Creates a new instance of TaskBean
     */
    public TaskBean() {
        dateLimite=new Date(System.currentTimeMillis());
    }


    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
        this.setNameSelectedTask(selectedTask.getName());
        this.setDescriptionSelectedTask(selectedTask.getDescription());
        this.setDateSelectedTask(selectedTask.getLimitDate());
    }

    public String getNameSelectedTask() {
        return nameSelectedTask;
    }

    public void setNameSelectedTask(String nameSelectedTask) {
        this.nameSelectedTask = nameSelectedTask;
    }

    public Date getDateSelectedTask() {
        return dateSelectedTask;
    }

    public void setDateSelectedTask(Date dateSelectedTask) {
        this.dateSelectedTask = dateSelectedTask;
    }

    public String getDescriptionSelectedTask() {
        return descriptionSelectedTask;
    }

    public void setDescriptionSelectedTask(String descriptionSelectedTask) {
        this.descriptionSelectedTask = descriptionSelectedTask;
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
        String nameBefore=name;      
        Task task = new Task(name,new java.sql.Date(this.dateLimite.getTime()),description,null);
        beanLocal.addTask(task);
        this.name="";
        this.dateLimite=new Date(System.currentTimeMillis());
        this.description="";
        return retour;
    }
    public String modifyTask(){
        String retour="viewAgenda.xhtml";
        this.selectedTask.setDescription(this.descriptionSelectedTask);
        this.selectedTask.setLimitDate(new java.sql.Date(this.dateSelectedTask.getTime()));
        this.selectedTask.setName(this.nameSelectedTask);
        System.out.println("modify"+selectedTask.getName());
        selectedTask=beanLocal.modifyTask(selectedTask);
        this.getListOfTask();
        return retour;
    }
    public String removeSelectionedTask(){
        this.beanLocal.deleteTask(selectedTask);
        return "viewAgenda.xhtml";
    }
    public void handleDateSelect(DateSelectEvent event) {  
        //-86400000 mettre un jour en moins
        Date curent = new Date(System.currentTimeMillis()-86400000);
        if(curent.after(dateLimite)){
            FacesContext facesContext = FacesContext.getCurrentInstance();  
            SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La date sélectionnée est passée :", format.format(event.getDate()))); 
        }
    }  
    public void handleDateSelect2(DateSelectEvent event) {  
        Date curent = new Date(System.currentTimeMillis()-86400000);
        if(this.dateSelectedTask.compareTo(curent)<=0){
            FacesContext facesContext = FacesContext.getCurrentInstance();  
            SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La date sélectionnée est passée :", format.format(event.getDate()))); 
        }
    }  
        
    public List<Task> listAllTask() {
        setListOfTask(beanLocal.findAllTask(beanLocal.getUserConnected()));
        return listOfTask;
    }
    
}
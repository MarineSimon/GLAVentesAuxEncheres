/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.AgendaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import persistence.Agenda;

/**
 *
 * @author Romain
 */
@Named(value = "searchAgendaBean")
@RequestScoped
public class SearchAgendaBean {
    private String title, email, firstname, lastname, selectedAgenda;
    private List<Agenda> listAgendas;
    
    @EJB
    private AgendaBean agendaLocal;
    
        
    public SearchAgendaBean() {
    }
    public String getTitle() {
        return this.title;
    }
    public String getEmail() {
        return this.email;
    }
    public String getFirstName() {
        return this.firstname;
    }
    public String getLastName() {
        return this.lastname;
    }
    public void setTitle(String input) {
        this.title = input;
    }
    public void setEmail(String input) {
        this.email = input;
    }
    public void setFirstName(String input) {
        this.firstname = input;
    }
    public void setLastName(String input) {
        this.lastname = input;
    }
    public String getSelectedAgenda() {
        return selectedAgenda;
    }
    public List<Agenda> getListAgendas() {
        return listAgendas;
    }
    public void setSelectedAgenda(String input) {
        this.selectedAgenda = input;
    }
    public void setListAgendas(List<Agenda> input) {
        this.listAgendas = input;
    }
    
    public String findInAllAgendas() {
        if(title == "" && email == "" && firstname == "" && lastname == "") {
            FacesContext.getCurrentInstance().addMessage("formLogin:btnLogin", new FacesMessage("Veuillez remplir au moins un champ."));
        } else {
            List<Agenda> list = agendaLocal.findInAllAgendas(agendaLocal.getUserConnected(), title, email, firstname, lastname);
            listAgendas = new ArrayList<Agenda>();
            for(int i=0; i<list.size(); i++) {
                listAgendas.add(list.get(i));
            }
        }
        RequestContext.getCurrentInstance().update("j_idt9:j_idt17:agendas");
        return "searchAgenda.xhtml";
    }
    public String viewSelectedAgenda() {
        System.out.println("Selection faite! "+selectedAgenda);
        return selectedAgenda;
        //TODO : je connais pas le lien vers un agenda
    }
    public List<String> complete(String input) {
        List<String> results = new ArrayList<String>();
        List<Agenda> list = agendaLocal.findAgenda(agendaLocal.getUserConnected(), input);
        for(int i=0; i<list.size(); i++) {
            results.add(list.get(i).getName());
        }
        return results;
    }
    public String viewSelectedOwnAgenda() {
        System.out.println("Selection faite! "+selectedAgenda);
        return selectedAgenda;
        //TODO : je connais pas le lien vers un agenda
    }
}

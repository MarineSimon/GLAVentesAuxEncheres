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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import library.AgendaBeanLocal;
import persistence.Agenda;

/**
 *
 * @author Mohamed
 */
@Named(value = "agendaManagedBean")
@RequestScoped
public class AgendaManagedBean implements Serializable, Converter {

    @EJB
    private AgendaBean agendaLocal;
    private String nameAgenda;
    private String description;
    private Agenda agenda;
    private String accessibility;
    private String color;
    private String groups;
    
    private List<Long> selectedItems;
   
    public String getGroups() {
        return groups;
    }
  
    public void setGroups(String groups) {
        this.groups = groups;
    }
    private List<Agenda> listOfAgenda;

    public List<Agenda> getListOfAgenda() {
        return listOfAgenda;
    }

    public void setListOfAgenda(List<Agenda> listOfAgenda) {
        this.listOfAgenda = listOfAgenda;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public AgendaBeanLocal getAgendaLocal() {
        return agendaLocal;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public String getNameAgenda() {
        return nameAgenda;
    }

    public void setNameAgenda(String nameAgenda) {
        this.nameAgenda = nameAgenda;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Creates a new instance of AgendaManagedBean
     */
    public AgendaManagedBean() {
    }

    public String createNewAgenda() {
        agenda = new Agenda();
        agenda.setName(nameAgenda);
        agenda.setColor(this.extractColor(color));
        if (accessibility.equalsIgnoreCase("PRIVE")) {
            agenda.setAccess(0);
        }
        if (accessibility.equalsIgnoreCase("PUBLIC")) {
            agenda.setAccess(1);
        }
        if (accessibility.equalsIgnoreCase("PARTAGE")) {
            agenda.setAccess(2);
        }
        agenda.setDescription(description);
        agendaLocal.createAgenda(agenda);
        return "viewAgenda";
    }

    public List<Agenda> listAllAgenda() {
        setListOfAgenda(agendaLocal.findAllAgenda(agendaLocal.getUserConnected()));
        /*List<String> l = new ArrayList<String>();
        l.removeAll(l);
        for (int i = 0; i < listOfAgenda.size(); i++) {
            Agenda a = listOfAgenda.get(i);
            l.add(a.getName());
        }*/
        return this.getListOfAgenda();
    }
    
    // Liste des couleurs proposées pour un nouvel agenda.
    public List<ColorAgenda> listAllColors() {
        List<ColorAgenda> colors = new ArrayList<ColorAgenda>();
        
        colors.add(new ColorAgenda("Bleu","blue"));
        colors.add(new ColorAgenda("Vert","green"));
        colors.add(new ColorAgenda("Rouge","red"));
        
        return colors;
    }
    
    
    // Converter pour liste des couleurs
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return new ColorAgenda();
    }

    private String extractColor(String color) {
        String result = null;
        int indexLastEqual = color.lastIndexOf("=");
        int indexLastAcollade = color.indexOf("}");
        
        result = color.substring(indexLastEqual+1, indexLastAcollade);
        
        return result;
    }
    
    public void printSmthg(){
        System.out.println("[AgendaManagedBean] printSmthg");
    }

    public List<Long> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<Long> selectedItems) {
        this.selectedItems = selectedItems;
    }
}

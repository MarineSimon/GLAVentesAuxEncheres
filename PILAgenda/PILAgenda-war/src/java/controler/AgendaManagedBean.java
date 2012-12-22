/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import library.AgendaBeanLocal;
import persistence.Agenda;

/**
 *
 * @author Mohamed
 */
@Named(value = "agendaManagedBean")
@RequestScoped
public class AgendaManagedBean implements Serializable {

    @EJB
    private AgendaBeanLocal agendaLocal;
    private String nameAgenda;
    private String description;
    private Agenda agenda;
    private String accessibility;
    private String color;
    private String groups;

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
        //   Color c = Color.decode(color);
        // agenda.setColor(c);
        agendaLocal.createAgenda(agenda);
        return "viewAgenda";
    }

    public List<String> listAllAgenda() {
        setListOfAgenda(agendaLocal.findAllAgenda());
        List<String> l = new ArrayList<String>();
        l.removeAll(l);
        for (int i = 0; i < listOfAgenda.size(); i++) {
            Agenda a = listOfAgenda.get(i);
            l.add(a.getName());
        }
        return l;
    }
}

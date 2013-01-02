/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import library.BaseBeanLocal;

/**
 *
 * @author Maxime
 */
@Named(value = "baseManagedBean")
@RequestScoped
public class BaseManagedBean implements Serializable{
    @EJB
    private BaseBeanLocal baseLocal;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String fillingBase() {
        this.baseLocal.fillingBase();
        return "index";
    }
}

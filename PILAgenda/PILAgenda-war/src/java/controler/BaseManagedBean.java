/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.SessionBean;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import library.BaseBeanLocal;

/**
 *
 * @author Maxime
 */
@Named(value = "baseManagedBean")
@SessionScoped
public class BaseManagedBean implements Serializable{
    @EJB
    private BaseBeanLocal baseLocal;
    private boolean checkBase = false;

    public boolean isCheckBase() {
        return checkBase;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String fillingBase() {
        if (!this.checkBase) {
            this.baseLocal.fillingBase();
            this.checkBase = true;
        } 
        return "filling";
    }
}

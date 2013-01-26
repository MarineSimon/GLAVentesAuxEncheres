/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.BaseBeanLocal;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Marine
 */
@Named(value = "baseBean")
@ApplicationScoped
public class BaseBean {
    @EJB
    private BaseBeanLocal baseLocal;
    private boolean checkBase = false;

    public boolean isCheckBase() {
        return checkBase;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void initBase() {
        if (!this.checkBase) {
            this.baseLocal.initBase();
            this.checkBase = true;
        } 
    }
}

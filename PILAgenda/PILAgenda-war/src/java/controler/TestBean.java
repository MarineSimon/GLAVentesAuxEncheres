/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import business.TestBeanLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Swann
 */
@Named(value = "testBean")
@Dependent
public class TestBean {
    @EJB
    private TestBeanLocal tbl;
    /**
     * Creates a new instance of TestBean
     */
    public TestBean() {
    }
    
    
    
    
}

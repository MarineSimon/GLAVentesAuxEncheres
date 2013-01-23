/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import javax.ejb.Local;
import persistence.Periodicity;

/**
 *
 * @author Swann
 */
@Local
public interface PeriodicityBeanLocal {
    public Periodicity addPeriodicity(Periodicity p);

}

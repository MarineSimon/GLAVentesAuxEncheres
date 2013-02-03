/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.Calendar;
import javax.ejb.Local;
import persistence.Article;
import persistence.UserEnchere;

/**
 *
 * @author Swann
 */
@Local
public interface EnchereBeanInterface {
    public void addEnchere(Calendar date, double amount, Article article, UserEnchere user);
}

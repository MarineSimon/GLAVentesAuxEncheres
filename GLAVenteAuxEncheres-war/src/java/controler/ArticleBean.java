/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import library.ArticleBeanInterface;
import persistence.Article;

/**
 *
 * @author Marine
 */

@Named(value = "articleBean")
@RequestScoped
public class ArticleBean {
    @EJB
    private ArticleBeanInterface articleLocal; 

    public List<Article> getCriticalsArticles() {
        return articleLocal.getCriticalsArticles();
    }

    public String getActualPrice(Article a){
        return articleLocal.getActualPrice(a)+"€";
    }
    
    //ATTENTION, A VERIFIE SI CORRECT
    public String getReminingTime(Article a){
        Calendar endTime = a.getEndDate();
        Calendar actualTime = new GregorianCalendar();
        
        long diff = Math.abs(endTime.getTimeInMillis() - actualTime.getTimeInMillis());
        long numberOfDay = (long)diff/(86400000);
        
        return numberOfDay+" jours restants";
    }
    
}

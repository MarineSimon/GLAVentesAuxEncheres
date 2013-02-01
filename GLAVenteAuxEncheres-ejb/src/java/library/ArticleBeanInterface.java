/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.List;
import javax.ejb.Local;
import persistence.Article;
import persistence.Category;
import persistence.SubCategory;

/**
 *
 * @author Marine
 */
@Local
public interface ArticleBeanInterface {
    public List<Article> getCriticalsArticles();
    public List<Article> getArticlesInPromotion();
    public int getActualPrice(Article a);
    public List<Category> getCategories();
    public List<SubCategory> getSousCategories();
}

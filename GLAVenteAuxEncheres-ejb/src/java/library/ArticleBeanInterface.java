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
    public double getActualPrice(Article a);
    public List<Article> search(String keywords);
    public List<Category> getAllCategory();
    public List<SubCategory> getAllSubCategory(int idCategory);
    public SubCategory getSubCategory(int i);

    public void addArticle(Article a);
}

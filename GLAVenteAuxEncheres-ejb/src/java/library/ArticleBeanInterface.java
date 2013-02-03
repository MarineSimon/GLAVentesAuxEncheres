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
import persistence.UserEnchere;

/**
 *
 * @author Marine
 */
@Local
public interface ArticleBeanInterface {
    public List<Article> getCriticalsArticles();
    public List<Article> getCriticalsArticles(UserEnchere user);
    public List<Article> getArticlesInPromotion();
    public double getActualPrice(Article a);
    public List<Article> search(String keywords);
    public List<Category> getAllCategory();
    public List<Article> searchArticleByCategory(int category);
    public List<SubCategory> getAllSubCategory(int idCategory);
    public List<Article> searchArticleBySubCategory(int subCategory);
    public void removeArticle(Article a,UserEnchere u);
    public SubCategory getSubCategory(int i);
    public void addArticle(Article a);
}

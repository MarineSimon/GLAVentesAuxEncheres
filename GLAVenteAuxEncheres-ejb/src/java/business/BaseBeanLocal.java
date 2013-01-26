/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import library.BaseBeanInterface;
import persistence.Address;
import persistence.Article;
import persistence.BankInformation;
import persistence.Category;
import persistence.Enchere;
import persistence.Promotion;
import persistence.SubCategory;
import persistence.UserEnchere;

/**
 *
 * @author Marine
 */
@Stateless
@LocalBean
public class BaseBeanLocal implements BaseBeanInterface{
    @PersistenceContext(unitName="GLAVenteAuxEncheres-PU")
    private EntityManager em;
    
    @Override
    public void initBase() {
        /*Category*/
        Category category1 = new Category("Telephonie");
        Category category2 = new Category("Informatique");
        Category category3 = new Category("Musique");
        
        /*SubCategory*/
        SubCategory subCategory1 = new SubCategory("Telephone");
        subCategory1.setCategory(category1);
        SubCategory subCategory2 = new SubCategory("Ordinateur");
        subCategory2.setCategory(category2);
        SubCategory subCategory3 = new SubCategory("Balladeur");
        subCategory3.setCategory(category3);
        
        /*Utilisateurs*/
        Calendar date1 = createDate(27,8,1990);
        UserEnchere user1 = new UserEnchere("user1", "u1", "Simon", "Marine", "marine.simon@gmail.com",date1, 2);
        /*Adresses*/
        Address biling1 = new Address(12,"rue des Baxarts",54630,"Flavigny","FRANCE");
        Address delivery1 = new Address(26,"rue Hubert Sensiquet",54850,"Messein","FRANCE");
        List<Address> adresses1 = new ArrayList<Address>();
        adresses1.add(biling1);
        adresses1.add(delivery1);
        user1.setBilingAdress(biling1);
        user1.setDeliveryAdresses(adresses1);
        /*Articles*/
        Calendar date12 = new GregorianCalendar();
        date12.add(Calendar.DAY_OF_MONTH, 2);
        Article article1 = new Article("iMac","Bon état",800,date12,"GLAVenteAuxEncheres/GLAVenteAuxEncheres-war/web/resources/pictures/articles/imac.jpg");
        article1.setSubCategory(subCategory2);
        List<Article> articles1 = new ArrayList<Article>();
        articles1.add(article1);
        user1.setSellArticles(articles1);
        
        Calendar date2 = createDate(5,6,1990);
        UserEnchere user2 = new UserEnchere("user2", "u2", "Antoine", "Solene", "solene.antoine@gmail.com",date2, 1);
        /*Adresses*/
        Address biling2 = new Address(0,"Résidence Velodrome, rue Jacques Callot",54500,"Vandoeuvre-lès-Nancy","FRANCE");
        Address delivery2 = new Address(26,"rue de Solène",54850,"Metz","FRANCE");
        List<Address> adresses2 = new ArrayList<Address>();
        adresses2.add(biling2);
        adresses2.add(delivery2);
        user2.setBilingAdress(biling2);
        user2.setDeliveryAdresses(adresses2);
        /*Articles*/
        Calendar date22 = new GregorianCalendar();
        date22.add(Calendar.DAY_OF_MONTH, 2);
        Article article2 = new Article("iPhone","Etat neuf",800,date22,"GLAVenteAuxEncheres/GLAVenteAuxEncheres-war/web/resources/pictures/articles/iphone5.jpg");
        article2.setSubCategory(subCategory1);
        List<Article> articles2 = new ArrayList<Article>();
        articles2.add(article2);
        user2.setSellArticles(articles2);
        
        Calendar date3 = createDate(4,10,1989);
        UserEnchere user3 = new UserEnchere("user3", "u3", "Guillaume", "Maxime", "maxime.guillaume@gmail.com",date3, 3);
        /*Adresses*/
        Address biling3 = new Address(8,"rue Gambetta",54500,"Vandoeuvre","FRANCE");
        Address delivery3 = new Address(26,"rue de la Justice",5950,"Marseille","FRANCE");
        List<Address> adresses3 = new ArrayList<Address>();
        adresses3.add(biling3);
        adresses3.add(delivery3);
        user3.setBilingAdress(biling3);
        user3.setDeliveryAdresses(adresses3);
        /*Articles*/
        Calendar date32 = new GregorianCalendar();
        date32.add(Calendar.DAY_OF_MONTH, 2);
        Article article3 = new Article("iPod Nano","Comme neuf",800,date32,"GLAVenteAuxEncheres/GLAVenteAuxEncheres-war/web/resources/pictures/articles/ipodnano.jpg");
        article3.setSubCategory(subCategory3);
        List<Article> articles3 = new ArrayList<Article>();
        articles3.add(article3);
        user3.setSellArticles(articles3);
        
        
        Calendar date4 = createDate(28,7,1989);
        UserEnchere user4 = new UserEnchere("user4", "u4", "Cochard", "Swann", "swann.cochard@gmail.com",date4, 0);
        /*Adresses*/
        Address biling4 = new Address(9,"rue Saint Jean",54630,"Nancy","FRANCE");
        Address delivery4 = new Address(24,"rue du pont",54850,"Toulouse","FRANCE");
        List<Address> adresses4 = new ArrayList<Address>();
        adresses4.add(biling4);
        adresses4.add(delivery4);
        user4.setBilingAdress(biling4);
        user4.setDeliveryAdresses(adresses4);
        /*Articles*/
        Calendar date42 = new GregorianCalendar();
        date42.add(Calendar.DAY_OF_MONTH, 2);
        Article article4 = new Article("MacBook Pro Retina","Impeccable",800,date42,"GLAVenteAuxEncheres/GLAVenteAuxEncheres-war/web/resources/pictures/articles/mcbookproretina.jpg");
        article4.setSubCategory(subCategory2);
        List<Article> articles4 = new ArrayList<Article>();
        articles4.add(article4);
        user4.setSellArticles(articles4);
       
        
        /*Promotions*/
        List<Article> articles5 = new ArrayList<Article>();
        articles5.add(article3);
        articles5.add(article2);
        Promotion promo1 = new Promotion("Livraison gratuite",0,articles5);
        List<Promotion> promos11 = new ArrayList<Promotion>();
        promos11.add(promo1);
        List<Promotion> promos12 = new ArrayList<Promotion>();
        promos12.add(promo1);
        article2.setPromotions(promos12);
        
        List<Article> articles6 = new ArrayList<Article>();
        articles6.add(article4);
        Promotion promo2 = new Promotion("Bon d'achat",50,articles6);
        List<Promotion> promos21 = new ArrayList<Promotion>();
        promos21.add(promo2);
        article4.setPromotions(promos21);
        
        /*Encheres*/
        Calendar date51 = createDate(7,9,2012);
        Enchere enchere1 = new Enchere(date51,10,article2,user1);
        Calendar date52 = createDate(7,9,2012);
        Enchere enchere2 = new Enchere(date52,15,article1,user1);
        Calendar date53 = createDate(7,9,2012);
        Enchere enchere3 = new Enchere(date53,20,article4,user1);
        Calendar date54 = createDate(7,9,2012);
        Enchere enchere4 = new Enchere(date54,14,article3,user1);
        
        
        em.persist(category1);
        em.persist(category2);
        em.persist(category3);
        em.persist(subCategory1);
        em.persist(subCategory2);
        em.persist(subCategory3);
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);
        em.persist(promo1);
        em.persist(promo2);
        em.persist(enchere1);
        em.persist(enchere2);
        em.persist(enchere3);
        em.persist(enchere4);
        
    }
    
    public Calendar createDate(int day,int mounth,int year){
        Calendar date = new GregorianCalendar();
        date.set(Calendar.DAY_OF_MONTH, day);
        date.set(Calendar.MONTH, mounth-1);
        date.set(Calendar.YEAR, year);
        
        return date;
    }
    
}

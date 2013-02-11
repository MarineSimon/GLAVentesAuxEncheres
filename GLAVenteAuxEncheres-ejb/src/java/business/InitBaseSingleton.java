/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.Address;
import persistence.Article;
import persistence.Category;
import persistence.Enchere;
import persistence.Promotion;
import persistence.SubCategory;
import persistence.UserEnchere;
import org.primefaces.context.RequestContext;
import persistence.Notification;

/**
 *
 * @author Marine
 */
@Singleton
@Startup
public class InitBaseSingleton {
    @PersistenceContext(unitName="GLAVenteAuxEncheres-PU")
    private EntityManager em;
    
    @Resource
    TimerService timerService;
    
    private List<Promotion> promotions;
    
    @PostConstruct
    public void init(){
        /*Category*/
        Category category1 = new Category("Telephonie");
        Category category2 = new Category("Informatique");
        Category category3 = new Category("Multimedia");
        Category category4 = new Category("Jeux");
        Category category5 = new Category("Véhicule");
        Category category6 = new Category("Bijou");
        Category category7 = new Category("Littérature");
        Category category8 = new Category("Vêtement");
        Category category9 = new Category("Bricolage");
        Category category10 = new Category("Jardin");
        Category category11 = new Category("Maison");
        
        
        /*SubCategory*/
        SubCategory subCategory1 = new SubCategory("Telephone");
        SubCategory subCategory4 = new SubCategory("Accessoire");
        subCategory1.setCategory(category1);
        subCategory4.setCategory(category1);
        SubCategory subCategory2 = new SubCategory("Ordinateur");
        SubCategory subCategory5 = new SubCategory("Accessoire");
        subCategory2.setCategory(category2);
        subCategory5.setCategory(category2);
        SubCategory subCategory3 = new SubCategory("Lecteur mp3");
        SubCategory subCategory6 = new SubCategory("Appareil photo");
        SubCategory subCategory7 = new SubCategory("Camescope");
        subCategory3.setCategory(category3);
        subCategory6.setCategory(category3);
        subCategory7.setCategory(category3);
        SubCategory subCategory8 = new SubCategory("Jeux de société");
        SubCategory subCategory9 = new SubCategory("Jouets");
        SubCategory subCategory10 = new SubCategory("Jeux vidéos");
        SubCategory subCategory11 = new SubCategory("Console");
        subCategory8.setCategory(category4);
        subCategory9.setCategory(category4);
        subCategory10.setCategory(category4);
        subCategory11.setCategory(category4);
        SubCategory subCategory12 = new SubCategory("Voiture");
        SubCategory subCategory13 = new SubCategory("Cyclomoteur");
        subCategory12.setCategory(category5);
        subCategory13.setCategory(category5);
        SubCategory subCategory14 = new SubCategory("Montre");
        SubCategory subCategory15 = new SubCategory("Collier");
        SubCategory subCategory16 = new SubCategory("Bague");
        SubCategory subCategory17 = new SubCategory("Bracelet");
        SubCategory subCategory18 = new SubCategory("Boucle d'oreille");
        subCategory14.setCategory(category6);
        subCategory15.setCategory(category6);
        subCategory16.setCategory(category6);
        subCategory17.setCategory(category6);
        subCategory18.setCategory(category6);
        SubCategory subCategory19 = new SubCategory("Livre");
        SubCategory subCategory20 = new SubCategory("Magazine");
        subCategory19.setCategory(category7);
        subCategory20.setCategory(category7);
        SubCategory subCategory21 = new SubCategory("Femme");
        SubCategory subCategory22 = new SubCategory("Homme");
        SubCategory subCategory23 = new SubCategory("Enfant");
        SubCategory subCategory24 = new SubCategory("Bebe");
        subCategory21.setCategory(category8);
        subCategory22.setCategory(category8);
        subCategory23.setCategory(category8);
        subCategory24.setCategory(category8);
        SubCategory subCategory25 = new SubCategory("Outils");
        SubCategory subCategory26 = new SubCategory("Materiel");
        subCategory25.setCategory(category9);
        subCategory26.setCategory(category9);
        SubCategory subCategory27 = new SubCategory("Materiel");
        SubCategory subCategory28 = new SubCategory("Plantes");
        subCategory27.setCategory(category10);
        subCategory28.setCategory(category10);
        SubCategory subCategory29 = new SubCategory("Decoration");
        SubCategory subCategory30 = new SubCategory("Electromenager");
        SubCategory subCategory31 = new SubCategory("Mobilier");
        subCategory29.setCategory(category11);
        subCategory30.setCategory(category11);
        subCategory31.setCategory(category11);
        
        /*Utilisateurs*/
        Calendar date1 = createDate(27,8,1990);
        UserEnchere user1 = new UserEnchere("user1", "u1", "Simon", "Marine", "marine.simon@gmail.com",date1, 5);
        /*Adresses*/
        Address biling1 = new Address(12,"rue des Baxarts","54630","Flavigny","FRANCE");
        Address delivery1 = new Address(26,"rue Hubert Sensiquet","54850","Messein","FRANCE");
        List<Address> adresses1 = new ArrayList<Address>();
        adresses1.add(biling1);
        adresses1.add(delivery1);
        user1.setBilingAdress(biling1);
        user1.setDeliveryAdresses(adresses1);
        /*Articles*/
        Calendar date12 = Calendar.getInstance();
        date12.add(Calendar.SECOND, 30);
        Article article1 = new Article("iMac","Bon état",800,date12,"resources/pictures/articles/imac.jpg");
        
        article1.setSubCategory(subCategory2);
        article1.setOwner(user1);
        List<Article> articles1 = new ArrayList<Article>();
        articles1.add(article1);
        user1.setSellArticles(articles1);
        
        Calendar date2 = createDate(5,6,1990);
        UserEnchere user2 = new UserEnchere("user2", "u2", "Antoine", "Solene", "solene.antoine@gmail.com",date2, 1);
        /*Adresses*/
        Address biling2 = new Address(0,"Résidence Velodrome, rue Jacques Callot","54500","Vandoeuvre-lès-Nancy","FRANCE");
        Address delivery2 = new Address(26,"rue de Solène","54850","Metz","FRANCE");
        List<Address> adresses2 = new ArrayList<Address>();
        adresses2.add(biling2);
        adresses2.add(delivery2);
        user2.setBilingAdress(biling2);
        user2.setDeliveryAdresses(adresses2);
        /*Articles*/
        Calendar date22 = new GregorianCalendar();
        date22.add(Calendar.DAY_OF_MONTH, 2);
        Article article2 = new Article("iPhone","Etat neuf",800,date22,"resources/pictures/articles/iphone5.jpg");
        article2.setSubCategory(subCategory1);
        article2.setOwner(user2);
        List<Article> articles2 = new ArrayList<Article>();
        articles2.add(article2);
        user2.setSellArticles(articles2);
        
        Calendar date3 = createDate(4,10,1989);
        UserEnchere user3 = new UserEnchere("user3", "u3", "Guillaume", "Maxime", "maxime.guillaume@gmail.com",date3, 3);
        /*Adresses*/
        Address biling3 = new Address(8,"rue Gambetta","54500","Vandoeuvre","FRANCE");
        Address delivery3 = new Address(26,"rue de la Justice","5950","Marseille","FRANCE");
        List<Address> adresses3 = new ArrayList<Address>();
        adresses3.add(biling3);
        adresses3.add(delivery3);
        user3.setBilingAdress(biling3);
        user3.setDeliveryAdresses(adresses3);
        /*Articles*/
        Calendar date32 = new GregorianCalendar();
        date32.add(Calendar.DAY_OF_MONTH, 3);
        Article article3 = new Article("iPod Nano","Comme neuf",800,date32,"resources/pictures/articles/ipodnano.jpg");
        article3.setSubCategory(subCategory3);
        article3.setOwner(user3);
        List<Article> articles3 = new ArrayList<Article>();
        articles3.add(article3);
        user3.setSellArticles(articles3);
        
        
        Calendar date4 = createDate(28,7,1989);
        UserEnchere user4 = new UserEnchere("user4", "u4", "Cochard", "Swann", "swann.cochard@gmail.com",date4, 0);
        /*Adresses*/
        Address biling4 = new Address(9,"rue Saint Jean","54630","Nancy","FRANCE");
        Address delivery4 = new Address(24,"rue du pont","54850","Toulouse","FRANCE");
        List<Address> adresses4 = new ArrayList<Address>();
        adresses4.add(biling4);
        adresses4.add(delivery4);
        user4.setBilingAdress(biling4);
        user4.setDeliveryAdresses(adresses4);
        /*Articles*/
        Calendar date42 = new GregorianCalendar();
        date42.add(Calendar.DAY_OF_MONTH, 4);
        Article article4 = new Article("MacBook Pro Retina","Impeccable",800,date42,"resources/pictures/articles/mcbookproretina.jpg");
        article4.setSubCategory(subCategory2);
        article4.setOwner(user4);
        List<Article> articles4 = new ArrayList<Article>();
        articles4.add(article4);
        
        Calendar date43 = new GregorianCalendar();
        date43.add(Calendar.DAY_OF_MONTH, 4);
        Article article5 = new Article("MacBook Pro Retina","Impeccable",800,date43,"resources/pictures/articles/mcbookproretina.jpg");
        article5.setSubCategory(subCategory2);
        article5.setOwner(user4);
        articles4.add(article5);
        
        Calendar date44 = new GregorianCalendar();
        date44.add(Calendar.DAY_OF_MONTH, -4);
        Article article6 = new Article("Iphone5","Impeccable",800,date44,"resources/pictures/articles/iphone5.jpg");
        article6.setSubCategory(subCategory2);
        article6.setOwner(user4);
        articles4.add(article6);
        
        user4.setSellArticles(articles4);
       
        
        
        /*Promotions*/
        ArrayList<Article> articles = new ArrayList<Article>();
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);
        articles.add(article5);
        articles.add(article6);
        
        this.promotions = new ArrayList<Promotion>();
        List<Article> articles5 = new ArrayList<Article>();
        articles5.add(article3);
        articles5.add(article2);
        Promotion promo1 = new Promotion(0,0,articles5);
        this.promotions.add(promo1);
        article2.getPromotions().add(promotions.get(0));
        article3.getPromotions().add(promotions.get(0));
        
        List<Article> articles6 = new ArrayList<Article>();
        articles6.add(article4);
        articles6.add(article3);
        Promotion promo2 = new Promotion(1,50,articles6);
        this.promotions.add(promo2);
        article4.getPromotions().add(promotions.get(1));
        article3.getPromotions().add(promotions.get(1));
        
        /*Encheres*/
        Calendar date51 = createDate(7,9,2012);
        Enchere enchere1 = new Enchere(date51,810,article2,user1);
        Calendar date52 = createDate(7,9,2012);
        Enchere enchere2 = new Enchere(date52,815,article1,user2);
        Calendar date53 = createDate(7,9,2012);
        Enchere enchere3 = new Enchere(date53,820,article4,user1);
        Calendar date54 = createDate(7,9,2012);
        Enchere enchere4 = new Enchere(date54,814,article3,user1);
        Calendar date55 = createDate(7,9,2012);
        Enchere enchere5 = new Enchere(date55,814,article6,user3);
        
        
        em.persist(category1);
        em.persist(category2);
        em.persist(category3);
        em.persist(category4);
        em.persist(category5);
        em.persist(category6);
        em.persist(category7);
        em.persist(category8);
        em.persist(category9);
        em.persist(category10);
        em.persist(category11);
        em.persist(subCategory1);
        em.persist(subCategory2);
        em.persist(subCategory3);
        em.persist(subCategory4);
        em.persist(subCategory5);
        em.persist(subCategory6);
        em.persist(subCategory7);
        em.persist(subCategory8);
        em.persist(subCategory9);
        em.persist(subCategory10);
        em.persist(subCategory11);
        em.persist(subCategory12);
        em.persist(subCategory13);
        em.persist(subCategory14);
        em.persist(subCategory15);
        em.persist(subCategory16);
        em.persist(subCategory17);
        em.persist(subCategory18);
        em.persist(subCategory19);
        em.persist(subCategory20);
        em.persist(subCategory21);
        em.persist(subCategory22);
        em.persist(subCategory23);
        em.persist(subCategory24);
        em.persist(subCategory25);
        em.persist(subCategory26);
        em.persist(subCategory27);
        em.persist(subCategory28);
        em.persist(subCategory29);
        em.persist(subCategory30);
        em.persist(subCategory31);
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
        em.persist(enchere5);
        
        timerService.createTimer(article1.getEndDate().getTime(), article1);
        timerService.createTimer(article2.getEndDate().getTime(), article2);
        timerService.createTimer(article3.getEndDate().getTime(), article3);
        timerService.createTimer(article4.getEndDate().getTime(), article4);
        timerService.createTimer(article5.getEndDate().getTime(), article5);
        timerService.createTimer(article6.getEndDate().getTime(), article6);
    }

    @Schedule(second="0", minute="0",hour="0",dayOfMonth="*", month="*", year="*", persistent=false)
    private void loadPromotions() {
        Query query = em.createNamedQuery("Article.findArticles");
        List<Article> everyArticles = (List<Article>) query.getResultList();
        List<Article> articles = new ArrayList<Article>();
        for (int i = 0; i < everyArticles.size(); i++) {
            if (everyArticles.get(i).getEndDate().after(new GregorianCalendar())){
                articles.add(everyArticles.get(i));
            }
        }
        int random;
        Article article;
        if (articles.size()>4){
            for (int i = 0; i < articles.size(); i++) {
                articles.get(i).getPromotions().remove(this.promotions.get(0));
                articles.get(i).getPromotions().remove(this.promotions.get(1));
                em.merge(articles.get(i));
            }
            for (int i = 0; i < 2; i++) {
                random = (int)(Math.random()*articles.size());
                article = articles.get(random);
                while (article.getPromotions().contains(this.promotions.get(0))){
                    random = (int)(Math.random()*articles.size());
                    article = articles.get(random);
                }
                article.getPromotions().add(this.promotions.get(0));
                em.merge(article);
                em.merge(this.promotions.get(0));
            }
            for (int i = 0; i < 2; i++) {
                random = (int)(Math.random()*articles.size());
                article = articles.get(random);
                while (article.getPromotions().contains(this.promotions.get(1))){
                    random = (int)(Math.random()*articles.size());
                    article = articles.get(random);
                }
                article.getPromotions().add(this.promotions.get(1));
                em.merge(article);
                em.merge(this.promotions.get(1));
            }
        }
        
    }
    
    public List<Promotion> getPromotions(){
        return this.promotions;
    }
    
    public Calendar createDate(int day,int mounth,int year){
        Calendar date = new GregorianCalendar();
        date.set(Calendar.DAY_OF_MONTH, day);
        date.set(Calendar.MONTH, mounth-1);
        date.set(Calendar.YEAR, year);

        return date;
    }
    
    public void addTimerToArticle(Article a){
        timerService.createTimer(a.getEndDate().getTime(), a);
    }
    
    @Timeout
    public void endArticle(Timer t){
        Query query = em.createNamedQuery("Enchere.getRunningBillByArticle");
        Article a = (Article)t.getInfo();
        query.setParameter(1, a.getId());

        List<Enchere> encheres = (List<Enchere>) query.getResultList();
        Notification n = new Notification("La vente de l'article "+a.getName()+" est terminée");
        for (int i = 0; i < encheres.size(); i++) {
            encheres.get(i).getUserEnchere().getNotifications().add(n);
            em.persist(n);
            em.merge(encheres.get(i).getUserEnchere());
        }
        Notification n2 = new Notification("La vente de votre article "+a.getName()+" est terminée");
        a.getOwner().getNotifications().add(n2);
        em.persist(n2);
        em.merge(a.getOwner());
        
    }
    
}

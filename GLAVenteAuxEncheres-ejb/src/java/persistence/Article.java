/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marine
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Article.findCriticalsArticles", query="SELECT a from Article a ORDER BY a.endDate ASC"),
        @NamedQuery(name="Article.findLastEnchereByArticles", query="SELECT e from Enchere e WHERE e.article.id = ?1 ORDER BY e.creationDate ASC")
    })
public class Article implements Serializable {
    public static final int ARTICLE_BUY = 0;
    public static final int ARTICLE_SALE = 1;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "INITIALPRICE")
    private int initialPrice;
    @Column(name = "BUYSTATE")
    private int buyState;
    @Column(name = "PICTURE")
    private String picture;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endDate;
    
    @ManyToMany
    private List<Promotion> promotions;
    @ManyToOne(optional=false)
    private SubCategory subCategory;
    @ManyToOne(optional=false)
    private UserEnchere owner;

    public Article(){
    }
    
    public Article(String name, String description, int price, Calendar date, String picture){
        this.name = name;
        this.buyState = Article.ARTICLE_BUY;
        this.description = description;
        this.initialPrice = price;
        this.endDate = date;
        this.picture = picture;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNom(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice = initialPrice;
    }

    public int getBuyState() {
        return buyState;
    }

    public void setBuyState(int buyState) {
        this.buyState = buyState;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
    
    public boolean haveDeliveryFreePromotion(){
        boolean result = false;
        for (int i = 0; i < promotions.size(); i++) {
            result = result || (promotions.get(i).getType() == Promotion.TYPE_DELIVERED_FREE);
        }
        return result;
    }
    
    public boolean haveGiftCertificatePromotion(){
        boolean result = false;
        for (int i = 0; i < promotions.size(); i++) {
            result = result || (promotions.get(i).getType() == Promotion.TYPE_GIFT_CERTIFICATE);
        }
        return result;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public UserEnchere getOwner() {
        return owner;
    }

    public void setOwner(UserEnchere owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Article[ id=" + id + " ]";
    }
    
}
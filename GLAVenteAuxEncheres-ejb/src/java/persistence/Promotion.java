/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Marine
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Promotion.findAllPromotions", query="SELECT p from Promotion p")
})
public class Promotion implements Serializable {
    public static final int TYPE_DELIVERED_FREE = 0;
    public static final int TYPE_GIFT_CERTIFICATE = 1;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "TYPE")
    private int type;
    @Column(name = "AMOUNT")
    private int amount;
    
    @ManyToMany
    @JoinTable(name="PROMOTION_ARTICLE",joinColumns= @JoinColumn(name="PROMOTION_ID",referencedColumnName="ID"),inverseJoinColumns=@JoinColumn(name="ARTICLE_ID",referencedColumnName="ID"))
    private List<Article> articles;

    public Promotion(){
    }
    
    public Promotion(int type, int amount, List<Article> articles){
        this.type = type;
        
        this.amount = amount;
        this.articles = articles;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
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
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Promotion[ id=" + id + " ]";
    }
    
}

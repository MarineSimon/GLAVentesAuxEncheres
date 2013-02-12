/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name="Enchere.getRunningBill", query="SELECT e from Enchere e WHERE e.userEnchere.id = ?1 ORDER BY e.amount ASC"),
    @NamedQuery(name="Enchere.getUserRunningBillArticle", query="SELECT e from Enchere e WHERE e.userEnchere.id = ?1 AND e.article.id = ?2 ORDER BY e.amount ASC"),
    @NamedQuery(name="Enchere.getRunningBillByArticle", query="SELECT e from Enchere e WHERE e.article.id = ?1 ORDER BY e.amount ASC"),
    @NamedQuery(name="Enchere.getRunningBillArticle", query="SELECT e from Enchere e WHERE e.article.id = ?1 ORDER BY e.amount ASC"),
    @NamedQuery(name="Enchere.maxPrice", query="Select MAX(e.amount) from Enchere e")
})
public class Enchere implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CREATIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar creationDate;
    @Column(name = "AMOUNT")
    private double amount;
    
    @ManyToOne
    private Article article;
    @ManyToOne
    private UserEnchere userEnchere;
    
    public Enchere(){
    }
    
    public Enchere(Calendar date, double amount, Article article, UserEnchere user){
        this.creationDate = date;
        this.amount = amount;
        this.userEnchere = user;
        this.article = article;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public UserEnchere getUserEnchere() {
        return userEnchere;
    }

    public void setUserEnchere(UserEnchere userEnchere) {
        this.userEnchere = userEnchere;
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
        if (!(object instanceof Enchere)) {
            return false;
        }
        Enchere other = (Enchere) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Bid[ id=" + id + " ]";
    }
    
}

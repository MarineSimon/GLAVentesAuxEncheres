/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Marine
 */
@Entity
public class InfosBuyer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "NUMBANKACCOUNT")
    private int numBankAccount;
    
    @OneToOne(cascade = CascadeType.PERSIST,optional=false)
    private Address biling;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Address> delivery;
    
    public InfosBuyer(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumBankAccount() {
        return numBankAccount;
    }

    public void setNumBankAccount(int numBankAccount) {
        this.numBankAccount = numBankAccount;
    }

    public Address getBiling() {
        return biling;
    }

    public void setBiling(Address biling) {
        this.biling = biling;
    }

    public List<Address> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<Address> delivery) {
        this.delivery = delivery;
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
        if (!(object instanceof InfosBuyer)) {
            return false;
        }
        InfosBuyer other = (InfosBuyer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.InfosAcheteur[ id=" + id + " ]";
    }
    
}

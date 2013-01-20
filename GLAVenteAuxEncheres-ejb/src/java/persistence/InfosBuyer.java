/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    
    @Column(name = "DELIVERYADDRESS")
    private String deliveryAddress;
    @Column(name = "DELIVERYZIPCODE")
    private int deliveryZipCode;
    @Column(name = "DELIVERYCITY")
    private String deliveryCity;
    @Column(name = "NUMBANKACCOUNT")
    private int numBankAccount;
    
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getDeliveryZipCode() {
        return deliveryZipCode;
    }

    public void setDeliveryZipCode(int deliveryZipCode) {
        this.deliveryZipCode = deliveryZipCode;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
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

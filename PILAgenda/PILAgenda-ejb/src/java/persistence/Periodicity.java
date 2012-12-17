/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author gaelvarlet
 */
@Entity
public class Periodicity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "TYPEPERIOD")
    private int typePeriod;
    @Column(name = "NUMBEROFREPETITION")
    private int numberOfRepetition;
    @Column(name = "LIMITDATE")
    private Date limitDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTypePeriod() {
        return typePeriod;
    }

    public void setTypePeriod(int typePeriod) {
        this.typePeriod = typePeriod;
    }

    public int getNumberOfRepetition() {
        return numberOfRepetition;
    }

    public void setNumberOfRepetition(int numberOfRepetition) {
        this.numberOfRepetition = numberOfRepetition;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
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
        if (!(object instanceof Periodicity)) {
            return false;
        }
        Periodicity other = (Periodicity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Periodicity[ id=" + id + " ]";
    }
    
}

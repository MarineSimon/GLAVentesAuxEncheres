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
import javax.persistence.ManyToOne;

/**
 *
 * @author gaelvarlet
 */
@Entity
public class CustomizeEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int TYPE_ALERT_AUCUN = 0;
    private final int TYPE_ALERT_EMAIL = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "TYPEALARM")
    private int typeAlarm;
    @Column(name = "ALARM")
    private Date alarm;
    @Column(name = "COMMENT")
    private String comment;
    
    @ManyToOne
    private UserAgenda customizedEventOwner;
    
    @ManyToOne
    private Event relatedEvent;

    public CustomizeEvent() {
    }

    public CustomizeEvent(int typeAlarm, Date alarm, String comment, UserAgenda customizedEventOwner, Event relatedEvent) {
        this.typeAlarm = typeAlarm;
        this.alarm = alarm;
        this.comment = comment;
        this.customizedEventOwner = customizedEventOwner;
        this.relatedEvent = relatedEvent;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAlarm() {
        return alarm;
    }

    public void setAlarm(Date alarm) {
        this.alarm = alarm;
    }

    public String getCommentaire() {
        return comment;
    }

    public void setCommentaire(String comment) {
        this.comment = comment;
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
        if (!(object instanceof CustomizeEvent)) {
            return false;
        }
        CustomizeEvent other = (CustomizeEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.CustomizeEvent[ id=" + id + " ]";
    }
    
}

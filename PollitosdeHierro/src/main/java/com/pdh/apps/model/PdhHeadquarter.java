/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leaca
 */
@Entity
@Table(name = "pdh_headquarter")
@XmlRootElement
@Stateless
@NamedQueries({
    @NamedQuery(name = "PdhHeadquarter.findAll", query = "SELECT p FROM PdhHeadquarter p")
    , @NamedQuery(name = "PdhHeadquarter.findByPdhHeadquarterId", query = "SELECT p FROM PdhHeadquarter p WHERE p.pdhHeadquarterId = :pdhHeadquarterId")
    , @NamedQuery(name = "PdhHeadquarter.findByPdhHeadquarterCreatedDate", query = "SELECT p FROM PdhHeadquarter p WHERE p.pdhHeadquarterCreatedDate = :pdhHeadquarterCreatedDate")
    , @NamedQuery(name = "PdhHeadquarter.findByPdhHeadquarterUpdatedDate", query = "SELECT p FROM PdhHeadquarter p WHERE p.pdhHeadquarterUpdatedDate = :pdhHeadquarterUpdatedDate")
    , @NamedQuery(name = "PdhHeadquarter.findByPdhHeadquarterLocation", query = "SELECT p FROM PdhHeadquarter p WHERE p.pdhHeadquarterLocation = :pdhHeadquarterLocation")})
public class PdhHeadquarter implements Serializable {

    @JoinColumn(name = "pdh_headquarter_location", referencedColumnName = "pdh_geolocations_id")
    @ManyToOne
    private PdhGeolocations pdhHeadquarterLocation;

    @Column(name = "pdh_headquarter_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhHeadquarterUpdatedDate;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pdh_headquarter_id")
    private Integer pdhHeadquarterId;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_headquarter_code")
    private String pdhHeadquarterCode;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_headquarter_name")
    private String pdhHeadquarterName;
    @Column(name = "pdh_headquarter_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhHeadquarterCreatedDate;
    @JoinColumn(name = "pdh_headquarter_created_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhHeadquarterCreatedBy;
    @JoinColumn(name = "pdh_headquarter_updated_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhHeadquarterUpdatedBy;
    @OneToMany(mappedBy = "pdhKidHeadquarters")
    private Collection<PdhKid> pdhKidCollection;

    public PdhHeadquarter() {
    }

    public PdhHeadquarter(Integer pdhHeadquarterId) {
        this.pdhHeadquarterId = pdhHeadquarterId;
    }

    public Integer getPdhHeadquarterId() {
        return pdhHeadquarterId;
    }

    public void setPdhHeadquarterId(Integer pdhHeadquarterId) {
        this.pdhHeadquarterId = pdhHeadquarterId;
    }

    public String getPdhHeadquarterCode() {
        return pdhHeadquarterCode;
    }

    public void setPdhHeadquarterCode(String pdhHeadquarterCode) {
        this.pdhHeadquarterCode = pdhHeadquarterCode;
    }

    public String getPdhHeadquarterName() {
        return pdhHeadquarterName;
    }

    public void setPdhHeadquarterName(String pdhHeadquarterName) {
        this.pdhHeadquarterName = pdhHeadquarterName;
    }

    public Date getPdhHeadquarterCreatedDate() {
        return pdhHeadquarterCreatedDate;
    }

    public void setPdhHeadquarterCreatedDate(Date pdhHeadquarterCreatedDate) {
        this.pdhHeadquarterCreatedDate = pdhHeadquarterCreatedDate;
    }

    public PdhUser getPdhHeadquarterCreatedBy() {
        return pdhHeadquarterCreatedBy;
    }

    public void setPdhHeadquarterCreatedBy(PdhUser pdhHeadquarterCreatedBy) {
        this.pdhHeadquarterCreatedBy = pdhHeadquarterCreatedBy;
    }

    public PdhUser getPdhHeadquarterUpdatedBy() {
        return pdhHeadquarterUpdatedBy;
    }

    public void setPdhHeadquarterUpdatedBy(PdhUser pdhHeadquarterUpdatedBy) {
        this.pdhHeadquarterUpdatedBy = pdhHeadquarterUpdatedBy;
    }

    @XmlTransient
    public Collection<PdhKid> getPdhKidCollection() {
        return pdhKidCollection;
    }

    public void setPdhKidCollection(Collection<PdhKid> pdhKidCollection) {
        this.pdhKidCollection = pdhKidCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdhHeadquarterId != null ? pdhHeadquarterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PdhHeadquarter)) {
            return false;
        }
        PdhHeadquarter other = (PdhHeadquarter) object;
        if ((this.pdhHeadquarterId == null && other.pdhHeadquarterId != null) || (this.pdhHeadquarterId != null && !this.pdhHeadquarterId.equals(other.pdhHeadquarterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdh.apps.model.PdhHeadquarter[ pdhHeadquarterId=" + pdhHeadquarterId + " ]";
    }

    public Date getPdhHeadquarterUpdatedDate() {
        return pdhHeadquarterUpdatedDate;
    }

    public void setPdhHeadquarterUpdatedDate(Date pdhHeadquarterUpdatedDate) {
        this.pdhHeadquarterUpdatedDate = pdhHeadquarterUpdatedDate;
    }

    public PdhGeolocations getPdhHeadquarterLocation() {
        return pdhHeadquarterLocation;
    }

    public void setPdhHeadquarterLocation(PdhGeolocations pdhHeadquarterLocation) {
        this.pdhHeadquarterLocation = pdhHeadquarterLocation;
    }
    
}

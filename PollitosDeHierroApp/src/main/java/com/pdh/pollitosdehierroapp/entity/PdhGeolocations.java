/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.pollitosdehierroapp.entity;

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
 * @author lea
 */
@Entity
@Table(name = "pdh_geolocations")
@XmlRootElement
@Stateless
@NamedQueries({
    @NamedQuery(name = "PdhGeolocations.findAll", query = "SELECT p FROM PdhGeolocations p")
    , @NamedQuery(name = "PdhGeolocations.findByPdhGeolocationsId", query = "SELECT p FROM PdhGeolocations p WHERE p.pdhGeolocationsId = :pdhGeolocationsId")
    , @NamedQuery(name = "PdhGeolocations.findByPdhGeolocationsCreatedDate", query = "SELECT p FROM PdhGeolocations p WHERE p.pdhGeolocationsCreatedDate = :pdhGeolocationsCreatedDate")
    , @NamedQuery(name = "PdhGeolocations.findByPdhGeolocationsUpdatedDate", query = "SELECT p FROM PdhGeolocations p WHERE p.pdhGeolocationsUpdatedDate = :pdhGeolocationsUpdatedDate")
    , @NamedQuery(name = "PdhGeolocations.findByPdhGeolocationsParent", query = "SELECT p FROM PdhGeolocations p WHERE p.pdhGeolocationsParent = :pdhGeolocationsParent")})
public class PdhGeolocations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pdh_geolocations_id")
    private Integer pdhGeolocationsId;
    @Column(name = "pdh_geolocations_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhGeolocationsCreatedDate;
    @Column(name = "pdh_geolocations_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhGeolocationsUpdatedDate;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_geolocations_name")
    private String pdhGeolocationsName;
    @Column(name = "pdh_geolocations_parent")
    private Integer pdhGeolocationsParent;
    @JoinColumn(name = "pdh_geolocations_created_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhGeolocationsCreatedBy;
    @JoinColumn(name = "pdh_geolocations_updated_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhGeolocationsUpdatedBy;
    @OneToMany(mappedBy = "pdhKidGeolocation")
    private Collection<PdhKid> pdhKidCollection;

    public PdhGeolocations() {
    }

    public PdhGeolocations(Integer pdhGeolocationsId) {
        this.pdhGeolocationsId = pdhGeolocationsId;
    }

    public Integer getPdhGeolocationsId() {
        return pdhGeolocationsId;
    }

    public void setPdhGeolocationsId(Integer pdhGeolocationsId) {
        this.pdhGeolocationsId = pdhGeolocationsId;
    }

    public Date getPdhGeolocationsCreatedDate() {
        return pdhGeolocationsCreatedDate;
    }

    public void setPdhGeolocationsCreatedDate(Date pdhGeolocationsCreatedDate) {
        this.pdhGeolocationsCreatedDate = pdhGeolocationsCreatedDate;
    }

    public Date getPdhGeolocationsUpdatedDate() {
        return pdhGeolocationsUpdatedDate;
    }

    public void setPdhGeolocationsUpdatedDate(Date pdhGeolocationsUpdatedDate) {
        this.pdhGeolocationsUpdatedDate = pdhGeolocationsUpdatedDate;
    }

    public String getPdhGeolocationsName() {
        return pdhGeolocationsName;
    }

    public void setPdhGeolocationsName(String pdhGeolocationsName) {
        this.pdhGeolocationsName = pdhGeolocationsName;
    }

    public Integer getPdhGeolocationsParent() {
        return pdhGeolocationsParent;
    }

    public void setPdhGeolocationsParent(Integer pdhGeolocationsParent) {
        this.pdhGeolocationsParent = pdhGeolocationsParent;
    }

    public PdhUser getPdhGeolocationsCreatedBy() {
        return pdhGeolocationsCreatedBy;
    }

    public void setPdhGeolocationsCreatedBy(PdhUser pdhGeolocationsCreatedBy) {
        this.pdhGeolocationsCreatedBy = pdhGeolocationsCreatedBy;
    }

    public PdhUser getPdhGeolocationsUpdatedBy() {
        return pdhGeolocationsUpdatedBy;
    }

    public void setPdhGeolocationsUpdatedBy(PdhUser pdhGeolocationsUpdatedBy) {
        this.pdhGeolocationsUpdatedBy = pdhGeolocationsUpdatedBy;
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
        hash += (pdhGeolocationsId != null ? pdhGeolocationsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PdhGeolocations)) {
            return false;
        }
        PdhGeolocations other = (PdhGeolocations) object;
        if ((this.pdhGeolocationsId == null && other.pdhGeolocationsId != null) || (this.pdhGeolocationsId != null && !this.pdhGeolocationsId.equals(other.pdhGeolocationsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdh.pollitosdehierroapp.entity.PdhGeolocations[ pdhGeolocationsId=" + pdhGeolocationsId + " ]";
    }
    
}

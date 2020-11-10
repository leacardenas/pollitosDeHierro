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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leaca
 */
@Entity
@Table(name = "pdh_role")
@XmlRootElement
@Stateless
@NamedQueries({
    @NamedQuery(name = "PdhRole.findAll", query = "SELECT p FROM PdhRole p")
    , @NamedQuery(name = "PdhRole.findByPdhRoleId", query = "SELECT p FROM PdhRole p WHERE p.pdhRoleId = :pdhRoleId")
    , @NamedQuery(name = "PdhRole.findByPdhRoleCreatedDate", query = "SELECT p FROM PdhRole p WHERE p.pdhRoleCreatedDate = :pdhRoleCreatedDate")
    , @NamedQuery(name = "PdhRole.findByPdhRoleUpdatedDate", query = "SELECT p FROM PdhRole p WHERE p.pdhRoleUpdatedDate = :pdhRoleUpdatedDate")})
public class PdhRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pdh_role_id")
    private Integer pdhRoleId;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_role_name")
    private String pdhRoleName;
    @Column(name = "pdh_role_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhRoleCreatedDate;
    @Column(name = "pdh_role_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhRoleUpdatedDate;
    @JoinColumn(name = "pdh_role_created_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhRoleCreatedBy;
    @JoinColumn(name = "pdh_role_updated_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhRoleUpdatedBy;
    @OneToMany(mappedBy = "pdhUserRole")
    private Collection<PdhUser> pdhUserCollection;

    public PdhRole() {
    }

    public PdhRole(Integer pdhRoleId) {
        this.pdhRoleId = pdhRoleId;
    }

    public Integer getPdhRoleId() {
        return pdhRoleId;
    }

    public void setPdhRoleId(Integer pdhRoleId) {
        this.pdhRoleId = pdhRoleId;
    }

    public String getPdhRoleName() {
        return pdhRoleName;
    }

    public void setPdhRoleName(String pdhRoleName) {
        this.pdhRoleName = pdhRoleName;
    }

    public Date getPdhRoleCreatedDate() {
        return pdhRoleCreatedDate;
    }

    public void setPdhRoleCreatedDate(Date pdhRoleCreatedDate) {
        this.pdhRoleCreatedDate = pdhRoleCreatedDate;
    }

    public Date getPdhRoleUpdatedDate() {
        return pdhRoleUpdatedDate;
    }

    public void setPdhRoleUpdatedDate(Date pdhRoleUpdatedDate) {
        this.pdhRoleUpdatedDate = pdhRoleUpdatedDate;
    }

    public PdhUser getPdhRoleCreatedBy() {
        return pdhRoleCreatedBy;
    }

    public void setPdhRoleCreatedBy(PdhUser pdhRoleCreatedBy) {
        this.pdhRoleCreatedBy = pdhRoleCreatedBy;
    }

    public PdhUser getPdhRoleUpdatedBy() {
        return pdhRoleUpdatedBy;
    }

    public void setPdhRoleUpdatedBy(PdhUser pdhRoleUpdatedBy) {
        this.pdhRoleUpdatedBy = pdhRoleUpdatedBy;
    }

    @XmlTransient
    public Collection<PdhUser> getPdhUserCollection() {
        return pdhUserCollection;
    }

    public void setPdhUserCollection(Collection<PdhUser> pdhUserCollection) {
        this.pdhUserCollection = pdhUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdhRoleId != null ? pdhRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PdhRole)) {
            return false;
        }
        PdhRole other = (PdhRole) object;
        if ((this.pdhRoleId == null && other.pdhRoleId != null) || (this.pdhRoleId != null && !this.pdhRoleId.equals(other.pdhRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdh.apps.model.PdhRole[ pdhRoleId=" + pdhRoleId + " ]";
    }
    
}

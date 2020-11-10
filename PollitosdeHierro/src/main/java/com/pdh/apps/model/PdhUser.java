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
@Table(name = "pdh_user")
@XmlRootElement
@Stateless
@NamedQueries({
    @NamedQuery(name = "PdhUser.findAll", query = "SELECT p FROM PdhUser p")
    , @NamedQuery(name = "PdhUser.findByPdhUserId", query = "SELECT p FROM PdhUser p WHERE p.pdhUserId = :pdhUserId")
    , @NamedQuery(name = "PdhUser.findByPdhUserCreatedDate", query = "SELECT p FROM PdhUser p WHERE p.pdhUserCreatedDate = :pdhUserCreatedDate")
    , @NamedQuery(name = "PdhUser.findByPdhUserUpdatedDate", query = "SELECT p FROM PdhUser p WHERE p.pdhUserUpdatedDate = :pdhUserUpdatedDate")
    , @NamedQuery(name = "PdhUser.findByPdhUserIsSponsor", query = "SELECT p FROM PdhUser p WHERE p.pdhUserIsSponsor = :pdhUserIsSponsor")
    , @NamedQuery(name = "PdhUser.findByPdhUserIsHeadquarterManager", query = "SELECT p FROM PdhUser p WHERE p.pdhUserIsHeadquarterManager = :pdhUserIsHeadquarterManager")
    , @NamedQuery(name = "PdhUser.findByPdhUserIsVoluntary", query = "SELECT p FROM PdhUser p WHERE p.pdhUserIsVoluntary = :pdhUserIsVoluntary")
    , @NamedQuery(name = "PdhUser.findByPdhUserProvince", query = "SELECT p FROM PdhUser p WHERE p.pdhUserProvince = :pdhUserProvince")
    , @NamedQuery(name = "PdhUser.findByPdhUserCity", query = "SELECT p FROM PdhUser p WHERE p.pdhUserCity = :pdhUserCity")
    , @NamedQuery(name = "PdhUser.findByPdhUserDistrict", query = "SELECT p FROM PdhUser p WHERE p.pdhUserDistrict = :pdhUserDistrict")})
public class PdhUser implements Serializable {

    @Size(max = 45)
    @Column(name = "pdh_user_timezone")
    private String pdhUserTimezone;

    @Column(name = "pdh_user_active")
    private Boolean pdhUserActive;
    @JoinColumn(name = "pdh_user_kid", referencedColumnName = "pdh_kid_id")
    @ManyToOne
    private PdhKid pdhUserKid;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pdh_user_id")
    private Integer pdhUserId;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_user_name")
    private String pdhUserName;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_user_surname")
    private String pdhUserSurname;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_user_username")
    private String pdhUserUsername;
    @Column(name = "pdh_user_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhUserCreatedDate;
    @Column(name = "pdh_user_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhUserUpdatedDate;
    @Column(name = "pdh_user_is_sponsor")
    private Boolean pdhUserIsSponsor;
    @Column(name = "pdh_user_is_headquarter_manager")
    private Boolean pdhUserIsHeadquarterManager;
    @Column(name = "pdh_user_is_voluntary")
    private Boolean pdhUserIsVoluntary;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_user_identification_number")
    private String pdhUserIdentificationNumber;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_user_phone_1")
    private String pdhUserPhone1;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_user_phone_2")
    private String pdhUserPhone2;
    @Column(name = "pdh_user_province")
    private Integer pdhUserProvince;
    @Column(name = "pdh_user_city")
    private Integer pdhUserCity;
    @Column(name = "pdh_user_district")
    private Integer pdhUserDistrict;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_user_address")
    private String pdhUserAddress;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_user_pacient_relationship")
    private String pdhUserPacientRelationship;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_user_help_given")
    private String pdhUserHelpGiven;
    @OneToMany(mappedBy = "pdhHeadquarterCreatedBy")
    private Collection<PdhHeadquarter> pdhHeadquarterCollection;
    @OneToMany(mappedBy = "pdhHeadquarterUpdatedBy")
    private Collection<PdhHeadquarter> pdhHeadquarterCollection1;
    @OneToMany(mappedBy = "pdhGeolocationsCreatedBy")
    private Collection<PdhGeolocations> pdhGeolocationsCollection;
    @OneToMany(mappedBy = "pdhGeolocationsUpdatedBy")
    private Collection<PdhGeolocations> pdhGeolocationsCollection1;
    @OneToMany(mappedBy = "pdhPersonCreatedBy")
    private Collection<PdhPerson> pdhPersonCollection;
    @OneToMany(mappedBy = "pdhPersonUpdatedBy")
    private Collection<PdhPerson> pdhPersonCollection1;
    @OneToMany(mappedBy = "pdhRoleCreatedBy")
    private Collection<PdhRole> pdhRoleCollection;
    @OneToMany(mappedBy = "pdhRoleUpdatedBy")
    private Collection<PdhRole> pdhRoleCollection1;
    @OneToMany(mappedBy = "pdhUserCreatedBy")
    private Collection<PdhUser> pdhUserCollection;
    @JoinColumn(name = "pdh_user_created_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhUserCreatedBy;
    @JoinColumn(name = "pdh_user_role", referencedColumnName = "pdh_role_id")
    @ManyToOne
    private PdhRole pdhUserRole;
    @OneToMany(mappedBy = "pdhUserUpdatedBy")
    private Collection<PdhUser> pdhUserCollection1;
    @JoinColumn(name = "pdh_user_updated_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhUserUpdatedBy;
    @OneToMany(mappedBy = "pdhKidCreatedBy")
    private Collection<PdhKid> pdhKidCollection;
    @OneToMany(mappedBy = "pdhKidUpdatedBy")
    private Collection<PdhKid> pdhKidCollection1;

    public PdhUser() {
    }

    public PdhUser(Integer pdhUserId) {
        this.pdhUserId = pdhUserId;
    }

    public Integer getPdhUserId() {
        return pdhUserId;
    }

    public void setPdhUserId(Integer pdhUserId) {
        this.pdhUserId = pdhUserId;
    }

    public String getPdhUserName() {
        return pdhUserName;
    }

    public void setPdhUserName(String pdhUserName) {
        this.pdhUserName = pdhUserName;
    }

    public String getPdhUserSurname() {
        return pdhUserSurname;
    }

    public void setPdhUserSurname(String pdhUserSurname) {
        this.pdhUserSurname = pdhUserSurname;
    }

    public String getPdhUserUsername() {
        return pdhUserUsername;
    }

    public void setPdhUserUsername(String pdhUserUsername) {
        this.pdhUserUsername = pdhUserUsername;
    }

    public Date getPdhUserCreatedDate() {
        return pdhUserCreatedDate;
    }

    public void setPdhUserCreatedDate(Date pdhUserCreatedDate) {
        this.pdhUserCreatedDate = pdhUserCreatedDate;
    }

    public Date getPdhUserUpdatedDate() {
        return pdhUserUpdatedDate;
    }

    public void setPdhUserUpdatedDate(Date pdhUserUpdatedDate) {
        this.pdhUserUpdatedDate = pdhUserUpdatedDate;
    }

    public Boolean getPdhUserIsSponsor() {
        return pdhUserIsSponsor;
    }

    public void setPdhUserIsSponsor(Boolean pdhUserIsSponsor) {
        this.pdhUserIsSponsor = pdhUserIsSponsor;
    }

    public Boolean getPdhUserIsHeadquarterManager() {
        return pdhUserIsHeadquarterManager;
    }

    public void setPdhUserIsHeadquarterManager(Boolean pdhUserIsHeadquarterManager) {
        this.pdhUserIsHeadquarterManager = pdhUserIsHeadquarterManager;
    }

    public Boolean getPdhUserIsVoluntary() {
        return pdhUserIsVoluntary;
    }

    public void setPdhUserIsVoluntary(Boolean pdhUserIsVoluntary) {
        this.pdhUserIsVoluntary = pdhUserIsVoluntary;
    }

    public String getPdhUserIdentificationNumber() {
        return pdhUserIdentificationNumber;
    }

    public void setPdhUserIdentificationNumber(String pdhUserIdentificationNumber) {
        this.pdhUserIdentificationNumber = pdhUserIdentificationNumber;
    }

    public String getPdhUserPhone1() {
        return pdhUserPhone1;
    }

    public void setPdhUserPhone1(String pdhUserPhone1) {
        this.pdhUserPhone1 = pdhUserPhone1;
    }

    public String getPdhUserPhone2() {
        return pdhUserPhone2;
    }

    public void setPdhUserPhone2(String pdhUserPhone2) {
        this.pdhUserPhone2 = pdhUserPhone2;
    }

    public Integer getPdhUserProvince() {
        return pdhUserProvince;
    }

    public void setPdhUserProvince(Integer pdhUserProvince) {
        this.pdhUserProvince = pdhUserProvince;
    }

    public Integer getPdhUserCity() {
        return pdhUserCity;
    }

    public void setPdhUserCity(Integer pdhUserCity) {
        this.pdhUserCity = pdhUserCity;
    }

    public Integer getPdhUserDistrict() {
        return pdhUserDistrict;
    }

    public void setPdhUserDistrict(Integer pdhUserDistrict) {
        this.pdhUserDistrict = pdhUserDistrict;
    }

    public String getPdhUserAddress() {
        return pdhUserAddress;
    }

    public void setPdhUserAddress(String pdhUserAddress) {
        this.pdhUserAddress = pdhUserAddress;
    }

    public String getPdhUserPacientRelationship() {
        return pdhUserPacientRelationship;
    }

    public void setPdhUserPacientRelationship(String pdhUserPacientRelationship) {
        this.pdhUserPacientRelationship = pdhUserPacientRelationship;
    }

    public String getPdhUserHelpGiven() {
        return pdhUserHelpGiven;
    }

    public void setPdhUserHelpGiven(String pdhUserHelpGiven) {
        this.pdhUserHelpGiven = pdhUserHelpGiven;
    }

    @XmlTransient
    public Collection<PdhHeadquarter> getPdhHeadquarterCollection() {
        return pdhHeadquarterCollection;
    }

    public void setPdhHeadquarterCollection(Collection<PdhHeadquarter> pdhHeadquarterCollection) {
        this.pdhHeadquarterCollection = pdhHeadquarterCollection;
    }

    @XmlTransient
    public Collection<PdhHeadquarter> getPdhHeadquarterCollection1() {
        return pdhHeadquarterCollection1;
    }

    public void setPdhHeadquarterCollection1(Collection<PdhHeadquarter> pdhHeadquarterCollection1) {
        this.pdhHeadquarterCollection1 = pdhHeadquarterCollection1;
    }

    @XmlTransient
    public Collection<PdhGeolocations> getPdhGeolocationsCollection() {
        return pdhGeolocationsCollection;
    }

    public void setPdhGeolocationsCollection(Collection<PdhGeolocations> pdhGeolocationsCollection) {
        this.pdhGeolocationsCollection = pdhGeolocationsCollection;
    }

    @XmlTransient
    public Collection<PdhGeolocations> getPdhGeolocationsCollection1() {
        return pdhGeolocationsCollection1;
    }

    public void setPdhGeolocationsCollection1(Collection<PdhGeolocations> pdhGeolocationsCollection1) {
        this.pdhGeolocationsCollection1 = pdhGeolocationsCollection1;
    }

    @XmlTransient
    public Collection<PdhPerson> getPdhPersonCollection() {
        return pdhPersonCollection;
    }

    public void setPdhPersonCollection(Collection<PdhPerson> pdhPersonCollection) {
        this.pdhPersonCollection = pdhPersonCollection;
    }

    @XmlTransient
    public Collection<PdhPerson> getPdhPersonCollection1() {
        return pdhPersonCollection1;
    }

    public void setPdhPersonCollection1(Collection<PdhPerson> pdhPersonCollection1) {
        this.pdhPersonCollection1 = pdhPersonCollection1;
    }

    @XmlTransient
    public Collection<PdhRole> getPdhRoleCollection() {
        return pdhRoleCollection;
    }

    public void setPdhRoleCollection(Collection<PdhRole> pdhRoleCollection) {
        this.pdhRoleCollection = pdhRoleCollection;
    }

    @XmlTransient
    public Collection<PdhRole> getPdhRoleCollection1() {
        return pdhRoleCollection1;
    }

    public void setPdhRoleCollection1(Collection<PdhRole> pdhRoleCollection1) {
        this.pdhRoleCollection1 = pdhRoleCollection1;
    }

    @XmlTransient
    public Collection<PdhUser> getPdhUserCollection() {
        return pdhUserCollection;
    }

    public void setPdhUserCollection(Collection<PdhUser> pdhUserCollection) {
        this.pdhUserCollection = pdhUserCollection;
    }

    public PdhUser getPdhUserCreatedBy() {
        return pdhUserCreatedBy;
    }

    public void setPdhUserCreatedBy(PdhUser pdhUserCreatedBy) {
        this.pdhUserCreatedBy = pdhUserCreatedBy;
    }

    public PdhRole getPdhUserRole() {
        return pdhUserRole;
    }

    public void setPdhUserRole(PdhRole pdhUserRole) {
        this.pdhUserRole = pdhUserRole;
    }

    @XmlTransient
    public Collection<PdhUser> getPdhUserCollection1() {
        return pdhUserCollection1;
    }

    public void setPdhUserCollection1(Collection<PdhUser> pdhUserCollection1) {
        this.pdhUserCollection1 = pdhUserCollection1;
    }

    public PdhUser getPdhUserUpdatedBy() {
        return pdhUserUpdatedBy;
    }

    public void setPdhUserUpdatedBy(PdhUser pdhUserUpdatedBy) {
        this.pdhUserUpdatedBy = pdhUserUpdatedBy;
    }

    @XmlTransient
    public Collection<PdhKid> getPdhKidCollection() {
        return pdhKidCollection;
    }

    public void setPdhKidCollection(Collection<PdhKid> pdhKidCollection) {
        this.pdhKidCollection = pdhKidCollection;
    }

    @XmlTransient
    public Collection<PdhKid> getPdhKidCollection1() {
        return pdhKidCollection1;
    }

    public void setPdhKidCollection1(Collection<PdhKid> pdhKidCollection1) {
        this.pdhKidCollection1 = pdhKidCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdhUserId != null ? pdhUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PdhUser)) {
            return false;
        }
        PdhUser other = (PdhUser) object;
        if ((this.pdhUserId == null && other.pdhUserId != null) || (this.pdhUserId != null && !this.pdhUserId.equals(other.pdhUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdh.apps.model.PdhUser[ pdhUserId=" + pdhUserId + " ]";
    }

    public Boolean getPdhUserActive() {
        return pdhUserActive;
    }

    public void setPdhUserActive(Boolean pdhUserActive) {
        this.pdhUserActive = pdhUserActive;
    }

    public PdhKid getPdhUserKid() {
        return pdhUserKid;
    }

    public void setPdhUserKid(PdhKid pdhUserKid) {
        this.pdhUserKid = pdhUserKid;
    }

    public String getPdhUserTimezone() {
        return pdhUserTimezone;
    }

    public void setPdhUserTimezone(String pdhUserTimezone) {
        this.pdhUserTimezone = pdhUserTimezone;
    }
    
}

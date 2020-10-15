/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.pollitosdehierroapp.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lea
 */
@Entity
@Table(name = "pdh_kid")
@XmlRootElement
@Stateless
@NamedQueries({
    @NamedQuery(name = "PdhKid.findAll", query = "SELECT p FROM PdhKid p")
    , @NamedQuery(name = "PdhKid.findByPdhKidId", query = "SELECT p FROM PdhKid p WHERE p.pdhKidId = :pdhKidId")
    , @NamedQuery(name = "PdhKid.findByPdhKidAge", query = "SELECT p FROM PdhKid p WHERE p.pdhKidAge = :pdhKidAge")
    , @NamedQuery(name = "PdhKid.findByPdhKidBirthDay", query = "SELECT p FROM PdhKid p WHERE p.pdhKidBirthDay = :pdhKidBirthDay")
    , @NamedQuery(name = "PdhKid.findByPdhKidTshirtSize", query = "SELECT p FROM PdhKid p WHERE p.pdhKidTshirtSize = :pdhKidTshirtSize")
    , @NamedQuery(name = "PdhKid.findByPdhKidPantsSize", query = "SELECT p FROM PdhKid p WHERE p.pdhKidPantsSize = :pdhKidPantsSize")
    , @NamedQuery(name = "PdhKid.findByPdhKidFootwareSize", query = "SELECT p FROM PdhKid p WHERE p.pdhKidFootwareSize = :pdhKidFootwareSize")
    , @NamedQuery(name = "PdhKid.findByPdhKidDiaperSize", query = "SELECT p FROM PdhKid p WHERE p.pdhKidDiaperSize = :pdhKidDiaperSize")
    , @NamedQuery(name = "PdhKid.findByPdhKidDiagnose", query = "SELECT p FROM PdhKid p WHERE p.pdhKidDiagnose = :pdhKidDiagnose")
    , @NamedQuery(name = "PdhKid.findByPdhKidSetback", query = "SELECT p FROM PdhKid p WHERE p.pdhKidSetback = :pdhKidSetback")
    , @NamedQuery(name = "PdhKid.findByPdhKidCurrentDiagnose", query = "SELECT p FROM PdhKid p WHERE p.pdhKidCurrentDiagnose = :pdhKidCurrentDiagnose")
    , @NamedQuery(name = "PdhKid.findByPdhKidAttachedEpicrisis", query = "SELECT p FROM PdhKid p WHERE p.pdhKidAttachedEpicrisis = :pdhKidAttachedEpicrisis")
    , @NamedQuery(name = "PdhKid.findByPdhKidNumberOfSiblings", query = "SELECT p FROM PdhKid p WHERE p.pdhKidNumberOfSiblings = :pdhKidNumberOfSiblings")
    , @NamedQuery(name = "PdhKid.findByPdhKidLastBasketReceived", query = "SELECT p FROM PdhKid p WHERE p.pdhKidLastBasketReceived = :pdhKidLastBasketReceived")
    , @NamedQuery(name = "PdhKid.findByPdhKidNumberOfPeopleLivingInHouse", query = "SELECT p FROM PdhKid p WHERE p.pdhKidNumberOfPeopleLivingInHouse = :pdhKidNumberOfPeopleLivingInHouse")
    , @NamedQuery(name = "PdhKid.findByPdhKidCreatedDate", query = "SELECT p FROM PdhKid p WHERE p.pdhKidCreatedDate = :pdhKidCreatedDate")
    , @NamedQuery(name = "PdhKid.findByPdhKidUpdatedDate", query = "SELECT p FROM PdhKid p WHERE p.pdhKidUpdatedDate = :pdhKidUpdatedDate")
    , @NamedQuery(name = "PdhKid.findByPdhKidHelpingPeople", query = "SELECT p FROM PdhKid p WHERE p.pdhKidHelpingPeople = :pdhKidHelpingPeople")})
public class PdhKid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pdh_kid_id")
    private Integer pdhKidId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "pdh_kid_code")
    private String pdhKidCode;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "pdh_kid_name")
    private String pdhKidName;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_kid_surname")
    private String pdhKidSurname;
    @Column(name = "pdh_kid_age")
    private Integer pdhKidAge;
    @Column(name = "pdh_kid_birth_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhKidBirthDay;
    @Column(name = "pdh_kid_tshirt_size")
    private Integer pdhKidTshirtSize;
    @Column(name = "pdh_kid_pants_size")
    private Integer pdhKidPantsSize;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pdh_kid_footware_size")
    private Double pdhKidFootwareSize;
    @Column(name = "pdh_kid_diaper_size")
    private Integer pdhKidDiaperSize;
    @Size(max = 45)
    @Column(name = "pdh_kid_diagnose")
    private String pdhKidDiagnose;
    @Column(name = "pdh_kid_setback")
    private Boolean pdhKidSetback;
    @Size(max = 45)
    @Column(name = "pdh_kid_current_diagnose")
    private String pdhKidCurrentDiagnose;
    @Column(name = "pdh_kid_attached_epicrisis")
    private Boolean pdhKidAttachedEpicrisis;
    @Column(name = "pdh_kid_number_of_siblings")
    private Integer pdhKidNumberOfSiblings;
    @Size(max = 45)
    @Column(name = "pdh_kid_last_basket_received")
    private String pdhKidLastBasketReceived;
    @Column(name = "pdh_kid_number_of_people_living_in_house")
    private Integer pdhKidNumberOfPeopleLivingInHouse;
    @Column(name = "pdh_kid_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhKidCreatedDate;
    @Column(name = "pdh_kid_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhKidUpdatedDate;
    @Column(name = "pdh_kid_helping_people")
    private Integer pdhKidHelpingPeople;
    @JoinColumn(name = "pdh_kid_created_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhKidCreatedBy;
    @JoinColumn(name = "pdh_kid_geolocation", referencedColumnName = "pdh_geolocations_id")
    @ManyToOne
    private PdhGeolocations pdhKidGeolocation;
    @JoinColumn(name = "pdh_kid_headquarters", referencedColumnName = "pdh_headquarter_id")
    @ManyToOne
    private PdhHeadquarter pdhKidHeadquarters;
    @JoinColumn(name = "pdh_kid_updated_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhKidUpdatedBy;

    public PdhKid() {
    }

    public PdhKid(Integer pdhKidId) {
        this.pdhKidId = pdhKidId;
    }

    public PdhKid(Integer pdhKidId, String pdhKidCode, String pdhKidName) {
        this.pdhKidId = pdhKidId;
        this.pdhKidCode = pdhKidCode;
        this.pdhKidName = pdhKidName;
    }

    public Integer getPdhKidId() {
        return pdhKidId;
    }

    public void setPdhKidId(Integer pdhKidId) {
        this.pdhKidId = pdhKidId;
    }

    public String getPdhKidCode() {
        return pdhKidCode;
    }

    public void setPdhKidCode(String pdhKidCode) {
        this.pdhKidCode = pdhKidCode;
    }

    public String getPdhKidName() {
        return pdhKidName;
    }

    public void setPdhKidName(String pdhKidName) {
        this.pdhKidName = pdhKidName;
    }

    public String getPdhKidSurname() {
        return pdhKidSurname;
    }

    public void setPdhKidSurname(String pdhKidSurname) {
        this.pdhKidSurname = pdhKidSurname;
    }

    public Integer getPdhKidAge() {
        return pdhKidAge;
    }

    public void setPdhKidAge(Integer pdhKidAge) {
        this.pdhKidAge = pdhKidAge;
    }

    public Date getPdhKidBirthDay() {
        return pdhKidBirthDay;
    }

    public void setPdhKidBirthDay(Date pdhKidBirthDay) {
        this.pdhKidBirthDay = pdhKidBirthDay;
    }

    public Integer getPdhKidTshirtSize() {
        return pdhKidTshirtSize;
    }

    public void setPdhKidTshirtSize(Integer pdhKidTshirtSize) {
        this.pdhKidTshirtSize = pdhKidTshirtSize;
    }

    public Integer getPdhKidPantsSize() {
        return pdhKidPantsSize;
    }

    public void setPdhKidPantsSize(Integer pdhKidPantsSize) {
        this.pdhKidPantsSize = pdhKidPantsSize;
    }

    public Double getPdhKidFootwareSize() {
        return pdhKidFootwareSize;
    }

    public void setPdhKidFootwareSize(Double pdhKidFootwareSize) {
        this.pdhKidFootwareSize = pdhKidFootwareSize;
    }

    public Integer getPdhKidDiaperSize() {
        return pdhKidDiaperSize;
    }

    public void setPdhKidDiaperSize(Integer pdhKidDiaperSize) {
        this.pdhKidDiaperSize = pdhKidDiaperSize;
    }

    public String getPdhKidDiagnose() {
        return pdhKidDiagnose;
    }

    public void setPdhKidDiagnose(String pdhKidDiagnose) {
        this.pdhKidDiagnose = pdhKidDiagnose;
    }

    public Boolean getPdhKidSetback() {
        return pdhKidSetback;
    }

    public void setPdhKidSetback(Boolean pdhKidSetback) {
        this.pdhKidSetback = pdhKidSetback;
    }

    public String getPdhKidCurrentDiagnose() {
        return pdhKidCurrentDiagnose;
    }

    public void setPdhKidCurrentDiagnose(String pdhKidCurrentDiagnose) {
        this.pdhKidCurrentDiagnose = pdhKidCurrentDiagnose;
    }

    public Boolean getPdhKidAttachedEpicrisis() {
        return pdhKidAttachedEpicrisis;
    }

    public void setPdhKidAttachedEpicrisis(Boolean pdhKidAttachedEpicrisis) {
        this.pdhKidAttachedEpicrisis = pdhKidAttachedEpicrisis;
    }

    public Integer getPdhKidNumberOfSiblings() {
        return pdhKidNumberOfSiblings;
    }

    public void setPdhKidNumberOfSiblings(Integer pdhKidNumberOfSiblings) {
        this.pdhKidNumberOfSiblings = pdhKidNumberOfSiblings;
    }

    public String getPdhKidLastBasketReceived() {
        return pdhKidLastBasketReceived;
    }

    public void setPdhKidLastBasketReceived(String pdhKidLastBasketReceived) {
        this.pdhKidLastBasketReceived = pdhKidLastBasketReceived;
    }

    public Integer getPdhKidNumberOfPeopleLivingInHouse() {
        return pdhKidNumberOfPeopleLivingInHouse;
    }

    public void setPdhKidNumberOfPeopleLivingInHouse(Integer pdhKidNumberOfPeopleLivingInHouse) {
        this.pdhKidNumberOfPeopleLivingInHouse = pdhKidNumberOfPeopleLivingInHouse;
    }

    public Date getPdhKidCreatedDate() {
        return pdhKidCreatedDate;
    }

    public void setPdhKidCreatedDate(Date pdhKidCreatedDate) {
        this.pdhKidCreatedDate = pdhKidCreatedDate;
    }

    public Date getPdhKidUpdatedDate() {
        return pdhKidUpdatedDate;
    }

    public void setPdhKidUpdatedDate(Date pdhKidUpdatedDate) {
        this.pdhKidUpdatedDate = pdhKidUpdatedDate;
    }

    public Integer getPdhKidHelpingPeople() {
        return pdhKidHelpingPeople;
    }

    public void setPdhKidHelpingPeople(Integer pdhKidHelpingPeople) {
        this.pdhKidHelpingPeople = pdhKidHelpingPeople;
    }

    public PdhUser getPdhKidCreatedBy() {
        return pdhKidCreatedBy;
    }

    public void setPdhKidCreatedBy(PdhUser pdhKidCreatedBy) {
        this.pdhKidCreatedBy = pdhKidCreatedBy;
    }

    public PdhGeolocations getPdhKidGeolocation() {
        return pdhKidGeolocation;
    }

    public void setPdhKidGeolocation(PdhGeolocations pdhKidGeolocation) {
        this.pdhKidGeolocation = pdhKidGeolocation;
    }

    public PdhHeadquarter getPdhKidHeadquarters() {
        return pdhKidHeadquarters;
    }

    public void setPdhKidHeadquarters(PdhHeadquarter pdhKidHeadquarters) {
        this.pdhKidHeadquarters = pdhKidHeadquarters;
    }

    public PdhUser getPdhKidUpdatedBy() {
        return pdhKidUpdatedBy;
    }

    public void setPdhKidUpdatedBy(PdhUser pdhKidUpdatedBy) {
        this.pdhKidUpdatedBy = pdhKidUpdatedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdhKidId != null ? pdhKidId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PdhKid)) {
            return false;
        }
        PdhKid other = (PdhKid) object;
        if ((this.pdhKidId == null && other.pdhKidId != null) || (this.pdhKidId != null && !this.pdhKidId.equals(other.pdhKidId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdh.pollitosdehierroapp.entity.PdhKid[ pdhKidId=" + pdhKidId + " ]";
    }
    
}

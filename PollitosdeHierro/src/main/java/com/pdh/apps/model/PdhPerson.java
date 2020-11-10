/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.model;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leaca
 */
@Entity
@Table(name = "pdh_person")
@XmlRootElement
@Stateless
@NamedQueries({
    @NamedQuery(name = "PdhPerson.findAll", query = "SELECT p FROM PdhPerson p")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonId", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonId = :pdhPersonId")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonIncome", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonIncome = :pdhPersonIncome")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonCreatedDate", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonCreatedDate = :pdhPersonCreatedDate")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonUpdatedDate", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonUpdatedDate = :pdhPersonUpdatedDate")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonAge", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonAge = :pdhPersonAge")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonFootwearSize", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonFootwearSize = :pdhPersonFootwearSize")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonBirthday", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonBirthday = :pdhPersonBirthday")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonIsKid", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonIsKid = :pdhPersonIsKid")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonIsMother", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonIsMother = :pdhPersonIsMother")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonIsFather", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonIsFather = :pdhPersonIsFather")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonIsOther", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonIsOther = :pdhPersonIsOther")
    , @NamedQuery(name = "PdhPerson.findByPdhPersonNeedsDiaper", query = "SELECT p FROM PdhPerson p WHERE p.pdhPersonNeedsDiaper = :pdhPersonNeedsDiaper")})
public class PdhPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pdh_person_id")
    private Integer pdhPersonId;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_person_name")
    private String pdhPersonName;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_person_surname")
    private String pdhPersonSurname;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_person_identification_number")
    private String pdhPersonIdentificationNumber;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_person_phone_1")
    private String pdhPersonPhone1;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_person_phone_2")
    private String pdhPersonPhone2;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_person_address")
    private String pdhPersonAddress;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_person_income_origin")
    private String pdhPersonIncomeOrigin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pdh_person_income")
    private Float pdhPersonIncome;
    @Column(name = "pdh_person_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhPersonCreatedDate;
    @Column(name = "pdh_person_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdhPersonUpdatedDate;
    @Column(name = "pdh_person_age")
    private Integer pdhPersonAge;
    @Column(name = "pdh_person_footwear_size")
    private Double pdhPersonFootwearSize;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_person_tshirt_size")
    private String pdhPersonTshirtSize;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_person_pants_size")
    private String pdhPersonPantsSize;
    @Column(name = "pdh_person_birthday")
    @Temporal(TemporalType.DATE)
    private Date pdhPersonBirthday;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdh_person_diaper_size")
    private String pdhPersonDiaperSize;
    @Column(name = "pdh_person_is_kid")
    private Boolean pdhPersonIsKid;
    @Column(name = "pdh_person_is_mother")
    private Boolean pdhPersonIsMother;
    @Column(name = "pdh_person_is_father")
    private Boolean pdhPersonIsFather;
    @Column(name = "pdh_person_is_other")
    private Boolean pdhPersonIsOther;
    @Column(name = "pdh_person_needs_diaper")
    private Boolean pdhPersonNeedsDiaper;
    @JoinColumn(name = "pdh_person_district", referencedColumnName = "pdh_geolocations_id")
    @ManyToOne
    private PdhGeolocations pdhPersonDistrict;
    @JoinColumn(name = "pdh_person_city", referencedColumnName = "pdh_geolocations_id")
    @ManyToOne
    private PdhGeolocations pdhPersonCity;
    @JoinColumn(name = "pdh_person_created_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhPersonCreatedBy;
    @JoinColumn(name = "pdh_person_province", referencedColumnName = "pdh_geolocations_id")
    @ManyToOne
    private PdhGeolocations pdhPersonProvince;
    @JoinColumn(name = "pdh_person_updated_by", referencedColumnName = "pdh_user_id")
    @ManyToOne
    private PdhUser pdhPersonUpdatedBy;

    public PdhPerson() {
    }

    public PdhPerson(Integer pdhPersonId) {
        this.pdhPersonId = pdhPersonId;
    }

    public Integer getPdhPersonId() {
        return pdhPersonId;
    }

    public void setPdhPersonId(Integer pdhPersonId) {
        this.pdhPersonId = pdhPersonId;
    }

    public String getPdhPersonName() {
        return pdhPersonName;
    }

    public void setPdhPersonName(String pdhPersonName) {
        this.pdhPersonName = pdhPersonName;
    }

    public String getPdhPersonSurname() {
        return pdhPersonSurname;
    }

    public void setPdhPersonSurname(String pdhPersonSurname) {
        this.pdhPersonSurname = pdhPersonSurname;
    }

    public String getPdhPersonIdentificationNumber() {
        return pdhPersonIdentificationNumber;
    }

    public void setPdhPersonIdentificationNumber(String pdhPersonIdentificationNumber) {
        this.pdhPersonIdentificationNumber = pdhPersonIdentificationNumber;
    }

    public String getPdhPersonPhone1() {
        return pdhPersonPhone1;
    }

    public void setPdhPersonPhone1(String pdhPersonPhone1) {
        this.pdhPersonPhone1 = pdhPersonPhone1;
    }

    public String getPdhPersonPhone2() {
        return pdhPersonPhone2;
    }

    public void setPdhPersonPhone2(String pdhPersonPhone2) {
        this.pdhPersonPhone2 = pdhPersonPhone2;
    }

    public String getPdhPersonAddress() {
        return pdhPersonAddress;
    }

    public void setPdhPersonAddress(String pdhPersonAddress) {
        this.pdhPersonAddress = pdhPersonAddress;
    }

    public String getPdhPersonIncomeOrigin() {
        return pdhPersonIncomeOrigin;
    }

    public void setPdhPersonIncomeOrigin(String pdhPersonIncomeOrigin) {
        this.pdhPersonIncomeOrigin = pdhPersonIncomeOrigin;
    }

    public Float getPdhPersonIncome() {
        return pdhPersonIncome;
    }

    public void setPdhPersonIncome(Float pdhPersonIncome) {
        this.pdhPersonIncome = pdhPersonIncome;
    }

    public Date getPdhPersonCreatedDate() {
        return pdhPersonCreatedDate;
    }

    public void setPdhPersonCreatedDate(Date pdhPersonCreatedDate) {
        this.pdhPersonCreatedDate = pdhPersonCreatedDate;
    }

    public Date getPdhPersonUpdatedDate() {
        return pdhPersonUpdatedDate;
    }

    public void setPdhPersonUpdatedDate(Date pdhPersonUpdatedDate) {
        this.pdhPersonUpdatedDate = pdhPersonUpdatedDate;
    }

    public Integer getPdhPersonAge() {
        return pdhPersonAge;
    }

    public void setPdhPersonAge(Integer pdhPersonAge) {
        this.pdhPersonAge = pdhPersonAge;
    }

    public Double getPdhPersonFootwearSize() {
        return pdhPersonFootwearSize;
    }

    public void setPdhPersonFootwearSize(Double pdhPersonFootwearSize) {
        this.pdhPersonFootwearSize = pdhPersonFootwearSize;
    }

    public String getPdhPersonTshirtSize() {
        return pdhPersonTshirtSize;
    }

    public void setPdhPersonTshirtSize(String pdhPersonTshirtSize) {
        this.pdhPersonTshirtSize = pdhPersonTshirtSize;
    }

    public String getPdhPersonPantsSize() {
        return pdhPersonPantsSize;
    }

    public void setPdhPersonPantsSize(String pdhPersonPantsSize) {
        this.pdhPersonPantsSize = pdhPersonPantsSize;
    }

    public Date getPdhPersonBirthday() {
        return pdhPersonBirthday;
    }

    public void setPdhPersonBirthday(Date pdhPersonBirthday) {
        this.pdhPersonBirthday = pdhPersonBirthday;
    }

    public String getPdhPersonDiaperSize() {
        return pdhPersonDiaperSize;
    }

    public void setPdhPersonDiaperSize(String pdhPersonDiaperSize) {
        this.pdhPersonDiaperSize = pdhPersonDiaperSize;
    }

    public Boolean getPdhPersonIsKid() {
        return pdhPersonIsKid;
    }

    public void setPdhPersonIsKid(Boolean pdhPersonIsKid) {
        this.pdhPersonIsKid = pdhPersonIsKid;
    }

    public Boolean getPdhPersonIsMother() {
        return pdhPersonIsMother;
    }

    public void setPdhPersonIsMother(Boolean pdhPersonIsMother) {
        this.pdhPersonIsMother = pdhPersonIsMother;
    }

    public Boolean getPdhPersonIsFather() {
        return pdhPersonIsFather;
    }

    public void setPdhPersonIsFather(Boolean pdhPersonIsFather) {
        this.pdhPersonIsFather = pdhPersonIsFather;
    }

    public Boolean getPdhPersonIsOther() {
        return pdhPersonIsOther;
    }

    public void setPdhPersonIsOther(Boolean pdhPersonIsOther) {
        this.pdhPersonIsOther = pdhPersonIsOther;
    }

    public Boolean getPdhPersonNeedsDiaper() {
        return pdhPersonNeedsDiaper;
    }

    public void setPdhPersonNeedsDiaper(Boolean pdhPersonNeedsDiaper) {
        this.pdhPersonNeedsDiaper = pdhPersonNeedsDiaper;
    }

    public PdhGeolocations getPdhPersonDistrict() {
        return pdhPersonDistrict;
    }

    public void setPdhPersonDistrict(PdhGeolocations pdhPersonDistrict) {
        this.pdhPersonDistrict = pdhPersonDistrict;
    }

    public PdhGeolocations getPdhPersonCity() {
        return pdhPersonCity;
    }

    public void setPdhPersonCity(PdhGeolocations pdhPersonCity) {
        this.pdhPersonCity = pdhPersonCity;
    }

    public PdhUser getPdhPersonCreatedBy() {
        return pdhPersonCreatedBy;
    }

    public void setPdhPersonCreatedBy(PdhUser pdhPersonCreatedBy) {
        this.pdhPersonCreatedBy = pdhPersonCreatedBy;
    }

    public PdhGeolocations getPdhPersonProvince() {
        return pdhPersonProvince;
    }

    public void setPdhPersonProvince(PdhGeolocations pdhPersonProvince) {
        this.pdhPersonProvince = pdhPersonProvince;
    }

    public PdhUser getPdhPersonUpdatedBy() {
        return pdhPersonUpdatedBy;
    }

    public void setPdhPersonUpdatedBy(PdhUser pdhPersonUpdatedBy) {
        this.pdhPersonUpdatedBy = pdhPersonUpdatedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdhPersonId != null ? pdhPersonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PdhPerson)) {
            return false;
        }
        PdhPerson other = (PdhPerson) object;
        if ((this.pdhPersonId == null && other.pdhPersonId != null) || (this.pdhPersonId != null && !this.pdhPersonId.equals(other.pdhPersonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdh.apps.model.PdhPerson[ pdhPersonId=" + pdhPersonId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.pollitosdehierroapp.managed;

import com.pdh.pollitosdehierroapp.dao.KidDAO;
import com.pdh.pollitosdehierroapp.entity.PdhKid;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author lea
 */
@Named
@ViewScoped
public class KidManaged implements Serializable {
    @EJB 
    KidDAO kidDAO;

    List<PdhKid> kidList;
    
    PdhKid kid;
    
    PdhKid requestedKid;
    
    public KidManaged() {
    }
    
    public void create(){
        kidDAO.create(kid);
    }

    public List<PdhKid> getKidList() {
        return kidDAO.findAll();
    }

    public void setKidList(List<PdhKid> kidList) {
        this.kidList = kidList;
    }
    
    public String printKids(){
        String data = "";
        for(PdhKid kid : getKidList()){    
            data += kid.getPdhKidCode() + "\n\n";
        }
        
        return data;
    }

    public PdhKid getKid() {
        return kid;
    }

    public void setKid(PdhKid kid) {
        this.kid = kid;
    }

    public PdhKid getRequestedKid() {
        return requestedKid;
    }

    public void setRequestedKid(PdhKid requestedKid) {
        this.requestedKid = requestedKid;
    }
}

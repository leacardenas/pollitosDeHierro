/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.converters;

import com.pdh.apps.dao.PdhKidDAO;
import com.pdh.apps.model.PdhKid;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.Model;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author lea
 */
@Model
@RequestScoped
@FacesConverter(value = "kidConverter", forClass = PdhKid.class)
public class PdhKidConverter implements Converter {

    @EJB
    private PdhKidDAO kidDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || "".equals(value)) {
            return null;
        }

        return kidDAO.find(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object objValue) {

        if (objValue == null || "".equals(objValue)) {
            return "";
        }

        if (!(objValue instanceof PdhKid)) {
            // handle error
        }

        PdhKid kid = (PdhKid) objValue;
        return String.valueOf(kid.getPdhKidId());
    }
}

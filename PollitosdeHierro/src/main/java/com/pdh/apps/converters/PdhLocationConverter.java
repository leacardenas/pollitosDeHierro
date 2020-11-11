/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.converters;

import com.pdh.apps.dao.PdhGeolocationsDAO;
import com.pdh.apps.model.PdhGeolocations;
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
@FacesConverter(value = "pdhLocationConverter", forClass = PdhGeolocations.class)
public class PdhLocationConverter implements Converter {

    @EJB
    private PdhGeolocationsDAO locationDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || "".equals(value)) {
            return null;
        }

        return locationDAO.find(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object objValue) {

        if (objValue == null || "".equals(objValue)) {
            return "";
        }

        if (!(objValue instanceof PdhGeolocations)) {
            // handle error
        }

        PdhGeolocations location = (PdhGeolocations) objValue;
        return String.valueOf(location.getPdhGeolocationsId());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.converters;

import com.pdh.apps.dao.PdhUsersDAO;
import com.pdh.apps.model.PdhUser;
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
@FacesConverter(value = "pdhUserConverter", forClass = PdhUser.class)
public class PdhUserConverter implements Converter {

    @EJB
    private PdhUsersDAO userDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || "".equals(value)) {
            return null;
        }

        return userDAO.find(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object objValue) {

        if (objValue == null || "".equals(objValue)) {
            return "";
        }

        if (!(objValue instanceof PdhUser)) {
            // handle error
        }

        PdhUser user = (PdhUser) objValue;
        return String.valueOf(user.getPdhUserId());
    }
}

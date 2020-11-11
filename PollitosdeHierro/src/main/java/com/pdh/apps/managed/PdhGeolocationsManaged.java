/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.managed;

import com.pdh.apps.dao.PdhGeolocationsDAO;
import com.pdh.apps.exception.ExceptionPollitosDeHierro;
import com.pdh.apps.model.PdhGeolocations;
import com.pdh.apps.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author leaca
 */
@Named
@ViewScoped
public class PdhGeolocationsManaged implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PdhGeolocationsManaged.class);
    private static final String CREATED = "Creado exitosamente";
    private static final String EDITED = "Editado exitosamente";
    private static final String DELETED = "Borrado exitosamente";
    private static final String ERROR = "Ha pasado un error";
    private static final String DUPLICATED = "Error, ya existe está localización";
    
    private List<PdhGeolocations> filteredPdhGeolocations;

    private PdhGeolocations selectedPdhGeolocations;

    @EJB
    private PdhGeolocationsDAO pdhGeolocationDAO;

    @EJB
    private PdhGeolocations pdhGeolocation;

    @EJB
    private PdhGeolocations requestedPdhGeolocationById;

    @Inject
    private UserLoginManaged userLoginManaged;

    private List<PdhGeolocations> pdhGeolocationsList;

    public PdhGeolocationsManaged() {
        /**
         * Creates a new instance of PdhGeolocationsManaged
         */
    }

    @PostConstruct
    public void init() {
        pdhGeolocation = new PdhGeolocations();
    }

    public void create() {
        pdhGeolocationsList = null;
        try {
            if (pdhGeolocationDAO.validateCode(pdhGeolocation.getPdhGeolocationsName(), pdhGeolocation.getPdhGeolocationsId(), "create")) {
                pdhGeolocation.setPdhGeolocationsCreatedBy(userLoginManaged.getUser());
                pdhGeolocation.setPdhGeolocationsCreatedDate(getCurrentDate());
                pdhGeolocationDAO.create(pdhGeolocation);
                JsfUtil.addSuccessMessage(CREATED);
                JsfUtil.executeRequest("create-dlg");
                pdhGeolocation = new PdhGeolocations();
            } else {
                JsfUtil.addErrorMessage(DUPLICATED);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);

        }
    }

    public void edit() {
        pdhGeolocationsList = null;
        try {
            if (pdhGeolocationDAO.validateCode(requestedPdhGeolocationById.getPdhGeolocationsName(), requestedPdhGeolocationById.getPdhGeolocationsId(), "edit")) {
                requestedPdhGeolocationById.setPdhGeolocationsUpdatedBy(userLoginManaged.getUser());
                requestedPdhGeolocationById.setPdhGeolocationsUpdatedDate(getCurrentDate());
                pdhGeolocationDAO.edit(requestedPdhGeolocationById);
                JsfUtil.addSuccessMessage(EDITED);
                JsfUtil.executeRequest("edit-dlg");
            } else {
                JsfUtil.addErrorMessage(DUPLICATED);
            }

        } catch (Exception e) {
            LOGGER.error(e);
            if (e.getCause() instanceof ConstraintViolationException) {
                JsfUtil.addErrorMessage(DUPLICATED);
            } else {
                JsfUtil.addErrorMessage(ERROR + e);
            }
        }
    }

    public void delete() {
        pdhGeolocationsList = null;
        try {
            pdhGeolocationDAO.delete(requestedPdhGeolocationById);
            pdhGeolocationDAO.refresh();
            JsfUtil.addSuccessMessage(DELETED);
            JsfUtil.executeRequest("delete-dlg");
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
    }

    /**
     * @return the pdhGeolocations
     */
    public List<PdhGeolocations> getPdhGeolocations() {
        try {
            if (pdhGeolocationsList == null) {
                pdhGeolocationsList = pdhGeolocationDAO.findAll();
            }
            return pdhGeolocationsList;
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
        return null;
    }

    public List<PdhGeolocations> getFilteredPdhGeolocations() {
        return filteredPdhGeolocations;
    }

    public void setFilteredPdhGeolocations(List<PdhGeolocations> filteredPdhGeolocations) {
        this.filteredPdhGeolocations = filteredPdhGeolocations;
    }

    public PdhGeolocations getSelectedPdhGeolocations() {
        return selectedPdhGeolocations;
    }

    public void setSelectedPdhGeolocations(PdhGeolocations selectedPdhGeolocations) {
        this.selectedPdhGeolocations = selectedPdhGeolocations;
    }

    public PdhGeolocations getPdhGeolocation() {
        return pdhGeolocation;
    }

    public void setPdhGeolocation(PdhGeolocations pdhGeolocation) {
        this.pdhGeolocation = pdhGeolocation;
    }

    /**
     * @return the requestedPdhGeolocationById
     */
    public PdhGeolocations getRequestedPdhGeolocationsById() {
        String id = JsfUtil.getRequestParameter("id");
        if (!StringUtils.isEmpty(id)) {
            requestedPdhGeolocationById = pdhGeolocationDAO.find(Integer.parseInt(id));
        }
        return requestedPdhGeolocationById;
    }

    /**
     * @param requestedPdhGeolocationById the requestedPdhGeolocationById to set
     */
    public void setRequestedPdhGeolocationsById(PdhGeolocations requestedPdhGeolocationById) {
        this.requestedPdhGeolocationById = requestedPdhGeolocationById;
    }

    public List<PdhGeolocations> getPdhGeolocationsList() {
        return pdhGeolocationsList;
    }

    public void setPdhGeolocationsList(List<PdhGeolocations> pdhGeolocationsList) {
        this.pdhGeolocationsList = pdhGeolocationsList;
    }

    public Date getCurrentDate() {
        return userLoginManaged.getCurrentDate();
    }
}

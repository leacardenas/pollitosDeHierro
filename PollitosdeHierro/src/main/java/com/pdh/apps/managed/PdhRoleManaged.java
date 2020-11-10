/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.managed;

import com.pdh.apps.dao.PdhRoleDAO;
import com.pdh.apps.exception.ExceptionPollitosDeHierro;
import com.pdh.apps.model.PdhRole;
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
public class PdhRoleManaged implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PdhRoleManaged.class);
    private static final String CREATED = "Creado exitosamente";
    private static final String EDITED = "Editado exitosamente";
    private static final String DELETED = "Borrado exitosamente";
    private static final String ERROR = "Ha pasado un error";
    private static final String DUPLICATED = "Error, ya existe está localización";
    
    private List<PdhRole> filteredPdhRole;

    private PdhRole selectedPdhRole;

    @EJB
    private PdhRoleDAO pdhRoleDAO;

    @EJB
    private PdhRole pdhRole;

    @EJB
    private PdhRole requestedPdhRoleById;

    @Inject
    private UserLoginManaged userLoginManaged;

    private List<PdhRole> pdhRolesList;

    public PdhRoleManaged() {
        /**
         * Creates a new instance of PdhRoleManaged
         */
    }

    @PostConstruct
    public void init() {
        pdhRole = new PdhRole();
    }

    public void create() {
        pdhRolesList = null;
        try {
            if (pdhRoleDAO.validateCode(pdhRole.getPdhRoleName(), pdhRole.getPdhRoleId(), "create")) {
                pdhRole.setPdhRoleCreatedBy(userLoginManaged.getUser());
                pdhRole.setPdhRoleCreatedDate(getCurrentDate());
                pdhRoleDAO.create(pdhRole);
                JsfUtil.addSuccessMessage(CREATED);
                JsfUtil.executeRequest("create-dlg");
                pdhRole = new PdhRole();
            } else {
                JsfUtil.addErrorMessage(DUPLICATED);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);

        }
    }

    public void edit() {
        pdhRolesList = null;
        try {
            if (pdhRoleDAO.validateCode(requestedPdhRoleById.getPdhRoleName(), requestedPdhRoleById.getPdhRoleId(), "edit")) {
                requestedPdhRoleById.setPdhRoleUpdatedBy(userLoginManaged.getUser());
                requestedPdhRoleById.setPdhRoleUpdatedDate(getCurrentDate());
                pdhRoleDAO.edit(requestedPdhRoleById);
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
        pdhRolesList = null;
        try {
            pdhRoleDAO.delete(requestedPdhRoleById);
            pdhRoleDAO.refresh();
            JsfUtil.addSuccessMessage(DELETED);
            JsfUtil.executeRequest("delete-dlg");
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
    }

    /**
     * @return the pdhRoles
     */
    public List<PdhRole> getPdhRoles() {
        try {
            if (pdhRolesList == null) {
                pdhRolesList = pdhRoleDAO.findAll();
            }
            return pdhRolesList;
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
        return null;
    }

    public List<PdhRole> getFilteredPdhRole() {
        return filteredPdhRole;
    }

    public void setFilteredPdhRole(List<PdhRole> filteredPdhRole) {
        this.filteredPdhRole = filteredPdhRole;
    }

    public PdhRole getSelectedPdhRole() {
        return selectedPdhRole;
    }

    public void setSelectedPdhRole(PdhRole selectedPdhRole) {
        this.selectedPdhRole = selectedPdhRole;
    }

    public PdhRole getPdhRole() {
        return pdhRole;
    }

    public void setPdhRole(PdhRole pdhRole) {
        this.pdhRole = pdhRole;
    }

    /**
     * @return the requestedPdhRoleById
     */
    public PdhRole getRequestedPdhRoleById() {
        String id = JsfUtil.getRequestParameter("id");
        if (!StringUtils.isEmpty(id)) {
            requestedPdhRoleById = pdhRoleDAO.find(Integer.parseInt(id));
        }
        return requestedPdhRoleById;
    }

    /**
     * @param requestedPdhRoleById the requestedPdhRoleById to set
     */
    public void setRequestedPdhRoleById(PdhRole requestedPdhRoleById) {
        this.requestedPdhRoleById = requestedPdhRoleById;
    }

    public List<PdhRole> getPdhRoleList() {
        return pdhRolesList;
    }

    public void setPdhRoleList(List<PdhRole> pdhRolesList) {
        this.pdhRolesList = pdhRolesList;
    }

    public Date getCurrentDate() {
        return userLoginManaged.getCurrentDate();
    }
}

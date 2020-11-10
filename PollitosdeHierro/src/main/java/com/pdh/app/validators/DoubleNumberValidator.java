package com.pdh.app.validators;

import com.pdh.apps.util.JsfUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.log4j.Logger;

@FacesValidator("DoubleValidator")
public class DoubleNumberValidator implements Validator {
    /*
     Double number validator class
     */

    private static final String NUMBER_PATTERN = "^[0-9][0-9.]+[0-9]+|[0-9]+";
    private static final Logger LOGGER = Logger.getLogger(DoubleNumberValidator.class);

    private Pattern pattern;
    private Matcher matcher;

    public DoubleNumberValidator() {
        /*
         Constructor
         */
        pattern = Pattern.compile(NUMBER_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        try {
            matcher = pattern.matcher(value.toString());
            if (!matcher.matches()) {
                FacesMessage msg = new FacesMessage(JsfUtil.getMessage("INTEGER.NUMBER.VALIDATOR"), JsfUtil.getMessage("INTEGER.NUMBER.VALIDATOR"));
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }

        } catch (Exception e) {
            LOGGER.error(e);
            FacesMessage msg = new FacesMessage(JsfUtil.getMessage("ERROR.VALIDATING.NUMBER"), JsfUtil.getMessage("ERROR.VALIDATING.NUMBER") + ". " + e);
            throw new ValidatorException(msg);
        }
    }

}

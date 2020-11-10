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

@FacesValidator("NumberValidator")
public class IntegerNumberValidator implements Validator {

    /*
     Double number validator class
     */

    private static final String NUMBER_PATTERN = "^[0-9]+";

    private Pattern pattern;
    private Matcher matcher;

    public IntegerNumberValidator() {
        /*
         Constructor
         */
        pattern = Pattern.compile(NUMBER_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object object) throws ValidatorException {

        Integer value = (Integer) object;
        
        // Si no hay dato, no lo valida
        if(value == null){
            return;
        }

        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage(JsfUtil.getMessage("INTEGER.NUMBER.VALIDATOR"), JsfUtil.getMessage("INTEGER.NUMBER.VALIDATOR"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}

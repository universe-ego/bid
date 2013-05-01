package edu.app.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator{

	public void validate(FacesContext context, UIComponent component, Object toValidate)
			throws ValidatorException {
		String password = null;
		if (toValidate instanceof String) {
			password = (String) toValidate;
		}
		UIInput confirmComponent = (UIInput) component.getAttributes().get("confirm");
		String confirmPassword = (String) confirmComponent.getSubmittedValue();
		
		if(password.isEmpty() || password == null || confirmPassword.isEmpty() || confirmPassword == null) {
			return;
		}
		
		boolean passwordsMatch = password.equals(confirmPassword);
		if (!passwordsMatch) {
			throw new ValidatorException(new FacesMessage("passwords don't match"));
		}
	}

}

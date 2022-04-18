package br.com.techdive.listadecompras.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Map;

@FacesValidator("StringValidator")
public class StringValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if(o == null ) {
            Map<String, Object> attributes = uiComponent.getAttributes();
            String stringAttr = (String) attributes.getOrDefault("stringAttr","attr");
            FacesMessage msg = new FacesMessage(stringAttr + "valor informado é inválido","Erro de validação");
            throw new ValidatorException(msg);
        }
    }
}

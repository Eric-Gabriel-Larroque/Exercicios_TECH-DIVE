package br.com.techdive.listadecompras.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Map;

@FacesValidator("QuantidadeValidator")
public class QuantidadeValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        if ( o == null ) {
            Map<String, Object> atributos = uiComponent.getAttributes();
            String quantidade = (String) atributos.getOrDefault("quantidade", 0);
            FacesMessage msg = new FacesMessage(quantidade + ": Valor informado é inválido", "Erro de validação");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

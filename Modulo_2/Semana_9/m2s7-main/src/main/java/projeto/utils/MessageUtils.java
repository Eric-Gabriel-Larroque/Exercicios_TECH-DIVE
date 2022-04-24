package projeto.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

public class MessageUtils {

    private MessageUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void returnMessageOnFail(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "",
                message)
        );
    }

    public static void returnMessageOnFail(List<String> erros) {
        erros.forEach(MessageUtils::returnMessageOnFail);
    }

    public static void returnMessageOnSuccess(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "",
                message)
        );
    }

    public static void returnGlobalMessageOnFail(List<String> erros) {
        erros.forEach(MessageUtils::returnGlobalMessageOnFail);
    }

    public static void returnGlobalMessageOnFail(String message) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "",
                message)
        );
    }

    public static void returnGlobalMessageOnSuccess(String message) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "",
                message)
        );
    }

    public static void limparMensagens() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().clear();
    }
}
package org.techdive.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RegistroExistenteException extends WebApplicationException {

    public RegistroExistenteException(String tipoRegistro, String identificador) {
        super(String.format("%s: Registro existente com identificador: %s", tipoRegistro, identificador), Response.Status.CONFLICT);
    }

}

package org.techdive.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RegistroNaoEncontradoException extends WebApplicationException {

    public RegistroNaoEncontradoException(String tipoRegistro, String identificador) {
        super(String.format("%s: Registro não encontrado com identificador: %s", tipoRegistro, identificador), Response.Status.NOT_FOUND);
    }

}

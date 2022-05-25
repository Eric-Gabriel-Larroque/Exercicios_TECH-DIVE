package org.techdive.exception;

public class RegistroExistenteException extends Exception{

    private final String tipoRegistro;
    private final String identificador;

    public RegistroExistenteException(String tipoRegistro, String identificador) {
        this.tipoRegistro = tipoRegistro;
        this.identificador = identificador;
    }

    @Override
    public String getMessage() {
        return String.format("%s: Registro existente com identificador: %s", tipoRegistro, identificador);
    }

}

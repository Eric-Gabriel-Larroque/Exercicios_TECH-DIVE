package org.techdive.exception;

public class RegistroNaoEncontradoException extends Exception {

    private final String tipoRegistro;
    private final String identificador;

    public RegistroNaoEncontradoException(String tipoRegistro, String identificador) {
        this.tipoRegistro = tipoRegistro;
        this.identificador = identificador;
    }

    @Override
    public String getMessage() {
        return String.format("%s: Registro não encontrado com identificador: %s", tipoRegistro, identificador);
    }

}

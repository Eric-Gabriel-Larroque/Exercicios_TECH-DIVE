package batalhafinal.enums;

public enum Motivacao {

    VINGANCA("vingança"),
    GLORIA("glória");

    private String motivacao;

    Motivacao(String motivacao) {
        this.motivacao = motivacao;
    }

    public String getValue() {
        return this.motivacao;
    }
}

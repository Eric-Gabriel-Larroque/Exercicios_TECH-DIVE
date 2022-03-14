package mercado.models.entitys;

import java.util.LinkedHashSet;

import static mercado.util.Validacao.*;

public class Marca {

    private int id;
    private String nomeMarca;
    private LinkedHashSet<Marca> listaMarcas = new LinkedHashSet<>();
    public static LinkedHashSet<String> listaNomes = new LinkedHashSet<>();
    public Marca(String nomeMarca, int id) {
        this.nomeMarca = nomeMarca;
        this.id = id;
        listaNomes.add(nomeMarca);
        listaMarcas.add(this);
    }

    public Marca() {
        setNomeMarca();
        listaMarcas.add(this);
    }

    public int getId() {
        return id;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca() {
        this.nomeMarca = validaString("Insira o nome da marca\n--> ");
    }

    public LinkedHashSet<Marca> getListaMarcas() {
        return listaMarcas;
    }
}

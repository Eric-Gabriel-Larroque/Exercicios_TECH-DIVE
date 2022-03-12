package mercado.models.entitys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Marca {

    private int id;
    private String nomeMarca;
    private LinkedHashSet<Marca> listaMarcas = new LinkedHashSet<>();

    public Marca(String nomeMarca, int id) {
        this.nomeMarca = nomeMarca;
        this.id = id;
        listaMarcas.add(this);
    }

    public Marca() {}

    public int getId() {
        return id;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }
}

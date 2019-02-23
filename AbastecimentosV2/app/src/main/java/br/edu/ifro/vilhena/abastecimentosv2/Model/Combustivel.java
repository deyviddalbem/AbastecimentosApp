package br.edu.ifro.vilhena.abastecimentosv2.Model;

import java.io.Serializable;

public class Combustivel implements Serializable {

    private int id;
    private String tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}

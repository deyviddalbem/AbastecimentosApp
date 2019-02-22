package br.edu.ifro.vilhena.abastecimentosv2.Model;

import java.sql.Date;

public class Abastecimento {

    private int id;
    private String nomePosto;
    private Combustivel combustivel;
    private float quilometragem;
    private double valorLitro;
    private double quantLitros;
    private double total;
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public float getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(float quilometragem) {
        this.quilometragem = quilometragem;
    }

    public double getValorLitro() {
        return valorLitro;
    }

    public void setValorLitro(double valorLitro) {
        this.valorLitro = valorLitro;
    }

    public double getQuantLitros() {
        return quantLitros;
    }

    public void setQuantLitros(double quantLitros) {
        this.quantLitros = quantLitros;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}

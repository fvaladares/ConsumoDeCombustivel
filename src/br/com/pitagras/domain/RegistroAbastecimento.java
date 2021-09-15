package br.com.pitagras.domain;

import java.util.Date;

public class RegistroAbastecimento {
    private Date dataAbastecimento;
    private String nomePosto;
    private Double valorTotal;
    private Double quantidade;

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public RegistroAbastecimento() {
    }

    public Date getDataAbastecimento() {
        return dataAbastecimento;
    }

    public void setDataAbastecimento(final Date dataAbastecimento) {
        this.dataAbastecimento = dataAbastecimento;
    }

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(final String nomePosto) {
        this.nomePosto = nomePosto;
    }
}
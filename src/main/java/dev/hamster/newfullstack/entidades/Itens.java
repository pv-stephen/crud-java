package dev.hamster.newfullstack.entidades;

import dev.hamster.newfullstack.entidades.enums.Categoria;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name="itens")
@Table(name="itens")
public class Itens implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String descricao;
    private Integer quantidade;
    private Double preco;
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name="orcamento_id")
    private Orcamento orcamento;

    public Itens() {}

    public Itens(Long ID, String descricao, Integer quantidade, Double preco, Categoria categoria, Orcamento orcamento) {
        this.ID = ID;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.categoria = categoria;
        this.orcamento = orcamento;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public double getSubtotal() {
        return getPreco()*getQuantidade();
    }
}
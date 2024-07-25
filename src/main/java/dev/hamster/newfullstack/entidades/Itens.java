package dev.hamster.newfullstack.entidades;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name="itens")
@Table(name="itens")
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Integer quantidade;
    private Double preco;

    @ManyToOne
    @JoinTable(name = "categoria_item", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Categoria categoria;

    @ManyToOne()
    @JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;


    public Itens(){}

    public Itens(Long id, String descricao, Integer quantidade, Double preco, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    // Métodos
    public Double getSubtotal(){
        return getPreco() * getQuantidade();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itens itens = (Itens) o;
        return Objects.equals(id, itens.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

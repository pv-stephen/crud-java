package dev.hamster.newfullstack.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "orcamentos")
@Table(name = "orcamentos")
public class Orcamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "orcamento")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Itens> itens = new HashSet<>();

    public Orcamento(){}

    public Orcamento(Long ID, LocalDate data, Cliente cliente) {
        this.ID = ID;
        this.data = data;
        this.cliente = cliente;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Itens> getItens() {
        return itens;
    }

    // Métodos
    public Double getTotal(){
        double soma = 0.0;

        for(Itens x : itens){
            soma += (x.getSubtotal());
        }
        return soma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orcamento orcamento = (Orcamento) o;
        return Objects.equals(ID, orcamento.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
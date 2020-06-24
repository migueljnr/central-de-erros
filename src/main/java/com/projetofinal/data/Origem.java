package com.projetofinal.data;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "origem")
public class Origem {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "origem")
    @Size(max = 100)
    @NotNull
    private String origem;

    public Origem() {

    }

    public Origem(String origem, int id) {
        this.origem = origem;
        this.id = id;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Origem origem1 = (Origem) o;
        return origem.equals(origem1.origem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origem);
    }
}

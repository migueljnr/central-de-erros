package com.projetofinal.dto;

import com.projetofinal.data.Level;
import com.projetofinal.data.Origem;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LogDTO {

    private int id;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalTime horaCriacao;
    private LocalDateTime dataModificacao;
    private int quantidade;
    private Level level;
    private Origem origem;

    public LogDTO(int id, String descricao, LocalDate dataCriacao, LocalTime horaCriacao, LocalDateTime dataModificacao, int quantidade, Level level, Origem origem) {
        this.id = id;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.horaCriacao = horaCriacao;
        this.dataModificacao = dataModificacao;
        this.quantidade = quantidade;
        this.level = level;
        this.origem = origem;
    }

    public LogDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalTime getHoraCriacao() {
        return horaCriacao;
    }

    public void setHoraCriacao(LocalTime horaCriacao) {
        this.horaCriacao = horaCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }
}

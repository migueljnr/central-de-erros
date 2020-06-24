package com.projetofinal.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.hibernate.annotations.CreationTimestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "evento")
@ApiModel("Evento")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(hidden = true)
    private int id;

    @Column(name = "descricao")
    @NotNull
    @Size(max = 255)
    @ApiModelProperty(notes = "Descrição do evento. Maximo 255 caracteres.", required = true)
    private String descricao;

    @Column(name = "log")
    @NotNull
    @Size(max = 255)
    @ApiModelProperty(notes = "Log do evento. Maximo 255 caracteres.", required = true)
    private String log;

    @Column(name = "data_criacao", updatable = false)
    @CreatedDate
    @ApiModelProperty(hidden = true)
    @CreationTimestamp
    private LocalDate dataCriacao;

    @Column(name = "hora_criacao", updatable = false)
    @CreatedDate
    @ApiModelProperty(hidden = true)
    @CreationTimestamp
    private LocalTime horaCriacao;

    @Column(name = "data_modificacao")
    @LastModifiedDate
    @ApiModelProperty(hidden = true)
    private LocalDateTime dataModificacao;

    @NotNull
    @Column(name = "quantidade")
    @ApiModelProperty(hidden = true)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "level",  referencedColumnName = "id")
    @ApiModelProperty(required = true, allowableValues = "WARNING, ERROR, INFO")
    private Level level;

    @ManyToOne
    @JoinColumn(name = "origem", referencedColumnName = "id")
    @ApiModelProperty(required = true)
    private Origem origem;

    public Log(int id, @NotNull @Size(max = 255) String descricao, @NotNull @Size(max = 255) String log, LocalDate dataCriacao, LocalTime horaCriacao, @NotNull int quantidade, Level level, Origem origem) {
        this.id = id;
        this.descricao = descricao;
        this.log = log;
        this.dataCriacao = dataCriacao;
        this.horaCriacao = horaCriacao;
        this.quantidade = quantidade;
        this.level = level;
        this.origem = origem;
    }

    public Log() {

    }

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

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return this.log.equals(log.log) &&
                level == log.level &&
                origem.equals(log.origem) &&
                descricao.equals(log.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(log, level, origem, descricao);
    }
}

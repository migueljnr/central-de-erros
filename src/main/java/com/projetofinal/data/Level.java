package com.projetofinal.data;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "level")
public class Level {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "level", unique = true)
    @ApiModelProperty(allowableValues = "WARNING, INFO, ERROR")
    @Size(max = 10)
    LevelName levelName;

    public Level() {}

    public Level(int id, @NotNull LevelName levelName) {
        this.id = id;
        this.levelName = levelName;
    }

    public Level(String level) {
        this.levelName = LevelName.valueOf(level.toUpperCase());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LevelName getLevelName() {
        return levelName;
    }

    public void setLevelName(LevelName levelName) {
        this.levelName = levelName;
    }
}

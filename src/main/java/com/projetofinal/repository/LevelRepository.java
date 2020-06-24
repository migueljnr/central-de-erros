package com.projetofinal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.projetofinal.data.LevelName;
import com.projetofinal.data.Level;

import java.util.Optional;

public interface LevelRepository extends CrudRepository<Level, Integer> {

    Optional<Level> findByLevelName(@Param("level") LevelName level);
}

package com.projetofinal.service.interfaces;

import com.projetofinal.data.Log;
import com.projetofinal.data.Level;
import com.projetofinal.data.Origem;
import com.projetofinal.dto.LogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;


public interface LogServiceInterface {

    public Page<LogDTO> findAll(Pageable pageable);

    public Log save(Log log);

    public Optional<Log> findById(int id);

    public Page<LogDTO> findAll(Specification<Log> eventoSpecification, Pageable pageable);

    public Page<LogDTO> findByLevel(Level level, Pageable pageable);

    public Page<LogDTO> findByOrigem(Origem origem, Pageable pageable);

    public Optional<Log> findByLogAndOrigemAndDescricaoAndLevel(String log, Origem origem, String descricao, Level level);
}

package com.projetofinal.service.impl;

import com.projetofinal.data.Log;
import com.projetofinal.data.Level;
import com.projetofinal.data.Origem;
import com.projetofinal.dto.LogDTO;
import com.projetofinal.mappers.LogMapper;
import com.projetofinal.repository.LogRepository;
import com.projetofinal.service.interfaces.LogServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogService implements LogServiceInterface {

    @Autowired
    LogRepository logRepository;

    public Optional<Log> findById(int id) {
        return logRepository.findById(id);
    }

    public Page<LogDTO> findByLevel(Level level, Pageable pageable) {
        Page<Log> page = logRepository.findByLevel(level, pageable);
        Page<LogDTO> eventoDTOPage = page.map(LogMapper::map);
        return eventoDTOPage;
    }

    public Page<LogDTO> findByOrigem(Origem origem, Pageable pageable) {
        Page<Log> page = logRepository.findByOrigem(origem, pageable);
        Page<LogDTO> eventoDTOPage = page.map(LogMapper::map);
        return eventoDTOPage;
    }

    @Override
    public Optional<Log> findByLogAndOrigemAndDescricaoAndLevel(String log, Origem origem, String descricao, Level level) {
        return logRepository.findByLogAndOrigemAndDescricaoAndLevel(log, origem, descricao, level);
    }

    @Override
    public Log save(Log log) {
        return logRepository.save(log);
    }

    @Override
    public Page<LogDTO> findAll(Pageable pageable) {
        Page<Log> page = logRepository.findAll(pageable);
        Page<LogDTO> eventoDTOPage = page.map(LogMapper::map);
        return eventoDTOPage;
    }

    @Override
    public Page<LogDTO> findAll(Specification<Log> eventoSpecification, Pageable pageable) {
        Page<Log> page = logRepository.findAll(eventoSpecification, pageable);
        Page<LogDTO> eventoDTOPage = page.map(LogMapper::map);
        return eventoDTOPage;
    }
}

package com.projetofinal.service.impl;

import com.projetofinal.data.Level;
import com.projetofinal.data.LevelName;
import com.projetofinal.repository.LevelRepository;
import com.projetofinal.service.interfaces.LevelServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LevelService implements LevelServiceInterface {

    @Autowired
    LevelRepository levelRepository;

    @Override
    public Optional<Level> findByLevel(LevelName level) {
        return levelRepository.findByLevelName(level);
    }
}

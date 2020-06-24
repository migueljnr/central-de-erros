package com.projetofinal.service.interfaces;

import com.projetofinal.data.LevelName;
import com.projetofinal.data.Level;

import java.util.Optional;

public interface LevelServiceInterface {
    Optional<Level> findByLevel(LevelName level);

}

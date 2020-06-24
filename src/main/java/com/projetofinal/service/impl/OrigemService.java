package com.projetofinal.service.impl;

import com.projetofinal.data.Origem;
import com.projetofinal.repository.OrigemRepository;
import com.projetofinal.service.interfaces.OrigemServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrigemService implements OrigemServiceInterface {

    @Autowired
    OrigemRepository origemRepository;

    @Override
    public Origem save(Origem origem) {
        return origemRepository.save(origem);
    }

    @Override
    public Optional<Origem> findByOrigem(String origem) {
        return origemRepository.findByOrigem(origem);
    }
}

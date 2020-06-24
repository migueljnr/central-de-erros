package com.projetofinal.service.interfaces;

import com.projetofinal.data.Origem;

import java.util.Optional;

public interface OrigemServiceInterface {

    public Origem save(Origem origem);

    public Optional<Origem> findByOrigem(String origem);
}

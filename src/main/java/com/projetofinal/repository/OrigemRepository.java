package com.projetofinal.repository;

import java.util.Optional;
import com.projetofinal.data.Origem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrigemRepository extends CrudRepository<Origem, Integer> {

    public Origem save(@Param("origem") Origem origem);

    public Optional<Origem> findByOrigem(@Param("origem") String origem);

}

package com.projetofinal.repository;

import com.projetofinal.data.Log;
import com.projetofinal.data.Level;
import com.projetofinal.data.Origem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface LogRepository extends PagingAndSortingRepository<Log, Integer>, JpaSpecificationExecutor<Log> {

    public Log save(@Param("evento") Log log);

    public Optional<Log> findById(@Param("id") int id );

    public Page<Log> findAll(Specification<Log> eventoSpecification, Pageable pageable);

    public Page<Log> findAll(@Param("pageable") Pageable pageable);

    public Page<Log> findByDataCriacao(@Param("data") LocalDateTime data, Pageable pageable);

    public Page<Log> findByLevel(@Param("level") Level level, Pageable pageable);

    public Page<Log> findByOrigem(@Param("origem") Origem origem, Pageable pageable);

    public Optional<Log> findByLogAndOrigemAndDescricaoAndLevel(@Param("log") String log, @Param("origem") Origem origem, @Param("descricao") String descricao, @Param("level") Level level);
}

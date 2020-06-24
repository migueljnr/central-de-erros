package com.projetofinal.endpoints;

import com.projetofinal.data.Log;
import com.projetofinal.data.Level;
import com.projetofinal.data.LevelName;
import com.projetofinal.data.Origem;
import com.projetofinal.dto.LogDTO;
import com.projetofinal.repository.specs.LogSpecification;
import com.projetofinal.repository.specs.SearchOperation;
import com.projetofinal.repository.specs.SearcheCriteria;
import com.projetofinal.service.interfaces.LogServiceInterface;
import com.projetofinal.service.interfaces.LevelServiceInterface;
import com.projetofinal.service.interfaces.OrigemServiceInterface;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import java.util.*;

@RestController
public class LogController {
    @Autowired
    LogServiceInterface logServiceInterface;

    @Autowired
    OrigemServiceInterface origemServiceInterface;

    @Autowired
    LevelServiceInterface levelServiceInterface;

    //######################################################################DOCUMENTAÇÃO######################################################################
    @ApiOperation(value = "Salva novos logs.", notes = "Salva um log de erro. Se o log já existe o atributo quantidade é incrementado.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Evento salvo ou atualizado com sucesso.", response = Log.class),
    @ApiResponse(code = 400, message = "Erro na requisiçao, causado provavelmente por atribuir valores inválidos para um campo.")
    })
    //########################################################################################################################################################
    @PostMapping(value = "/logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Log> salvar(@RequestBody Log log) {

        if (log == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Level level = log.getLevel();
        Optional<Level> optionalLevel = levelServiceInterface.findByLevel(level.getLevelName());
        if (!optionalLevel.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.setLevel(optionalLevel.get());

        Optional<Origem> optionalOrigem = origemServiceInterface.findByOrigem(log.getOrigem().getOrigem());
        if (optionalOrigem.isPresent()) {
            Optional<Log> eventoRepetido = logServiceInterface.findByLogAndOrigemAndDescricaoAndLevel(log.getLog(), optionalOrigem.get(), log.getDescricao(), log.getLevel());
            log.setOrigem(optionalOrigem.get());
            if (eventoRepetido.isPresent()) {
                Log e = eventoRepetido.get();
                e.setQuantidade(e.getQuantidade() + 1);
                e.setLevel(optionalLevel.get());
                e.setDataModificacao(LocalDateTime.now());
                return new ResponseEntity<>(logServiceInterface.save(e), HttpStatus.CREATED) ;
            } else {
                log.setQuantidade(1);
                return new ResponseEntity<>(logServiceInterface.save(log), HttpStatus.CREATED);
            }
        }
        Origem o = origemServiceInterface.save(log.getOrigem());
        log.setOrigem(o);
        log.setQuantidade(1);
        return new ResponseEntity<>(logServiceInterface.save(log), HttpStatus.CREATED);
    }

    //######################################################################DOCUMENTAÇÃO######################################################################
    @ApiOperation(value = "Pesquisa por logs.", notes = "Se nenhum parametro for informado todos os logs serão retornados. Esse metodo suporta paginação e a " +
    "quantidade padrão de items por pagina é 10. O usuario pode filtrar logs por qualquer atributo (exceto id - existe uma url especificar para esse proposito) nos parametros de requisição, " +
    "bem como pesquisar por mais de um parametro. Ex.: /eventos?level=WARNING retornara todos os logs cujo atributo level é igual a WARNING. Já /eventos?level=WARNING&descricao=descricao123, " +
    "retornará todos os logs cujo level é WARNING  e descrição é igual a descricao123. Essas consultas não retornarão o conteudo do atributo log.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log encontrado e retornado com sucesso.", response = Log.class),
                           @ApiResponse(code = 204, message = "Nenhum log com os critérios especificados foi encontrado."),
                           @ApiResponse(code = 400, message = "Erro na requisiçao. Provelmente por algum tipo de dado informado errado. Ex.: Data não formatada em YYYY-MM-DD.")
    })
    //########################################################################################################################################################    
    @GetMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Page<LogDTO>> buscarEventos(@RequestParam(defaultValue = "") String descricao, @ApiParam(value = "YYYY-MM-DD") @RequestParam(defaultValue = "") String data,
                                                  @RequestParam(defaultValue = "") String origem, @ApiParam(value = "HH:MM:SS") @RequestParam(defaultValue = "") String time,
                                                  @RequestParam(defaultValue = "") String quantidade, @RequestParam(defaultValue = "") String level, @RequestParam(defaultValue = "") String log,
                                                  @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                                  @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "ASC") String direction) {

        Sort.Direction sortDirection = ( direction.equalsIgnoreCase("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, sortDirection, sort);
        Page<LogDTO> logDTOPage = null;
        HashMap<String, Object> parametrosDaPesquisa = new HashMap<>();

        if (!time.equals("")) {
            try {
                parametrosDaPesquisa.put("horaCriacao", LocalTime.parse(time));
            } catch (DateTimeException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!descricao.equals("")) {
            parametrosDaPesquisa.put("descricao", descricao);
        }
        if (!data.equals("")) {
            try {
                parametrosDaPesquisa.put("dataCriacao", LocalDate.parse(data));
            } catch (DateTimeException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!quantidade.equals("")) {
            parametrosDaPesquisa.put("quantidade", quantidade);
        }
        if (!level.equals("")) {
            try {
                Optional<Level> optionalLevel = levelServiceInterface.findByLevel(LevelName.valueOf(level.toUpperCase()));
                if (!optionalLevel.isPresent()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
                parametrosDaPesquisa.put("level", optionalLevel.get());
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        if (!origem.equals("")) {
            Optional<Origem> optionalOrigem = origemServiceInterface.findByOrigem(origem);
            if (!optionalOrigem.isPresent()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
            parametrosDaPesquisa.put("origem", optionalOrigem.get());
        }
        if (!log.equals("")) { parametrosDaPesquisa.put("log", log); }

        if (parametrosDaPesquisa.isEmpty()) {
            logDTOPage = logServiceInterface.findAll(pageable);
        } else {
            LogSpecification logSpecification = new LogSpecification();
            for (String key : parametrosDaPesquisa.keySet()) {
                logSpecification.add(new SearcheCriteria(key, parametrosDaPesquisa.get(key), SearchOperation.EQUAL));
            }
            logDTOPage = logServiceInterface.findAll(logSpecification, pageable);
        }

        if (logDTOPage == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (logDTOPage.getTotalElements() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(logDTOPage, HttpStatus.OK);
    }

    //######################################################################DOCUMENTAÇÃO######################################################################
    @ApiOperation(value = "Pesquisa por um log.", notes = "Pesquisa um log por id. Essa pesquisa retorna o valor do atributo log.")
    @ApiResponses( value = {@ApiResponse (code = 200, message = "Log encontrado", response = Log.class),
                            @ApiResponse(code = 404, message = "Log não encontrado"),
    })
    //########################################################################################################################################################
    @GetMapping(value = "/logs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Log> buscarEventoPeloId(@PathVariable("id") Integer id) {

        Optional<Log> evento = logServiceInterface.findById(id);
        if (evento.isPresent()) {
            return new ResponseEntity<Log>(evento.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

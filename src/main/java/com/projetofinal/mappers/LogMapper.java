package com.projetofinal.mappers;

import com.projetofinal.data.Log;
import com.projetofinal.dto.LogDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LogMapper {

    public static LogDTO map(Log log) {

        Objects.requireNonNull(log);

        LogDTO logDTO = new LogDTO();
        logDTO.setDataCriacao(log.getDataCriacao());
        logDTO.setHoraCriacao(log.getHoraCriacao());
        logDTO.setDataModificacao(log.getDataModificacao());
        logDTO.setDataModificacao(log.getDataModificacao());
        logDTO.setDescricao(log.getDescricao());
        logDTO.setId(log.getId());
        logDTO.setLevel(log.getLevel());
        logDTO.setOrigem(log.getOrigem());
        logDTO.setQuantidade(log.getQuantidade());

        return logDTO;
    }

    public static List<LogDTO> map(List<Log> logs) {
        Objects.requireNonNull(logs);
        List<LogDTO> returnedList = new ArrayList<>();

        for (Log log : logs) {
            returnedList.add(map(log));
        }

        return returnedList;
    }
}

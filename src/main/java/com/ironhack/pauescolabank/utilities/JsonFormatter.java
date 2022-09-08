package com.ironhack.pauescolabank.utilities;

public interface JsonFormatter {
    String toDTO(String entity);

    String toEntity(String dto);

}

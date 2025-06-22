package com.teach3035.modulo6_desafio.DTO.res;

public record LoginResDTO(
        String type,
        String token,
        Long expiresAt
) {
}
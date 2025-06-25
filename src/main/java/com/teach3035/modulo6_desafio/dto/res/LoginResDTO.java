package com.teach3035.modulo6_desafio.dto.res;

public record LoginResDTO(
        String type,
        String token,
        Long expiresAt
) {
}
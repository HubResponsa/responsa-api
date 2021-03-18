package br.com.responsa.api.model.command

import lombok.Data

@Data
data class NoticiaCommand(
    val titulo : String,
    val descricao : String,
    val autor : String
)

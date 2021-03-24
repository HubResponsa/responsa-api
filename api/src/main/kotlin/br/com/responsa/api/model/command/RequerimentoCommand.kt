package br.com.responsa.api.model.command

import lombok.Data

@Data
data class RequerimentoCommand(
    val titulo : String,
    val descricao : String,
    val autor : String
)

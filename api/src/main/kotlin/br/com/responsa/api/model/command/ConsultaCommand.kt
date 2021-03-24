package br.com.responsa.api.model.command

import lombok.Data

@Data
data class ConsultaCommand(
    val titulo : String,
    val descricao : String,
    val autor : String
)

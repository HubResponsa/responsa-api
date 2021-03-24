package br.com.responsa.api.model

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Data
import lombok.RequiredArgsConstructor
import org.jetbrains.annotations.NotNull
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@Data
data class Consulta(
    @Id
    @GeneratedValue
    @JsonIgnore
    val id : Long?,

    val titulo : String,
    val descricao : String,
    val data : Date,
    val autor : String?
){

    companion object {
        fun create(titulo: String, descricao: String, autor: String? = null): Consulta =
            Consulta(null, titulo, descricao, Date(), autor)
    }
}

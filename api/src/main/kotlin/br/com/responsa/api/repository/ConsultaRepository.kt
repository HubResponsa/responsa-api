package br.com.responsa.api.repository

import br.com.responsa.api.model.Consulta
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ConsultaRepository : CrudRepository<Consulta, Long> {

    fun findAll(pageable: Pageable): Page<Consulta>

    fun findTop5ByOrderByDataDesc(): List<Consulta>

    fun findOneById(id: Long): Optional<Consulta>
}

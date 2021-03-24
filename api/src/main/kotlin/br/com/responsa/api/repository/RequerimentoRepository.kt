package br.com.responsa.api.repository

import br.com.responsa.api.model.Requerimento
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RequerimentoRepository : CrudRepository<Requerimento, Long> {

    fun findAll(pageable: Pageable): Page<Requerimento>

    fun findTop5ByOrderByDataDesc(): List<Requerimento>

    fun findOneById(id: Long): Optional<Requerimento>
}

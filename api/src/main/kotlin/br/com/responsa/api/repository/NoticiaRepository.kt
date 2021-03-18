package br.com.responsa.api.repository

import br.com.responsa.api.model.Noticia
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NoticiaRepository : CrudRepository<Noticia, Long> {

    fun findAll(pageable: Pageable): Page<Noticia>

    fun findTop5ByOrderByDataDesc(): List<Noticia>

    fun findOneById(id: Long): Optional<Noticia>
}

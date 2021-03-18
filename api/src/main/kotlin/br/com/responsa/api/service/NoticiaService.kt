package br.com.responsa.api.service

import br.com.responsa.api.model.Noticia
import br.com.responsa.api.model.command.NoticiaCommand
import br.com.responsa.api.repository.NoticiaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import javax.ws.rs.NotFoundException

@Service
class NoticiaService (private val noticiaRepository: NoticiaRepository) {

    fun getAll(pageable: Pageable): Page<Noticia> {
        return noticiaRepository.findAll(pageable)
    }

    fun getPrincipaisNoticias(): List<Noticia> {
        return noticiaRepository.findTop5ByOrderByDataDesc()
    }

    fun add(noticiaCommand: NoticiaCommand) {
        noticiaRepository.save(
            Noticia.create(
                noticiaCommand.titulo,
                noticiaCommand.descricao,
                noticiaCommand.autor
            )
        )
    }

    fun delete(id: Long) : Noticia{
        val noticiaOpcional = noticiaRepository.findOneById(id)
        if(noticiaOpcional.isPresent){
            noticiaRepository.deleteById(id)
            return noticiaOpcional.get()
        }

        throw Exception("O ${id} não existe")
    }
}

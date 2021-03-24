package br.com.responsa.api.service

import br.com.responsa.api.model.Requerimento
import br.com.responsa.api.model.command.RequerimentoCommand
import br.com.responsa.api.repository.RequerimentoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import javax.ws.rs.NotFoundException

@Service
class RequerimentoService (private val requerimentoRepository: RequerimentoRepository) {

    fun getAll(pageable: Pageable): Page<Requerimento> {
        return requerimentoRepository.findAll(pageable)
    }

    fun getPrincipaisRequerimentos(): List<Requerimento> {
        return requerimentoRepository.findTop5ByOrderByDataDesc()
    }

    fun add(requerimentoCommand: RequerimentoCommand) {
        requerimentoRepository.save(
            Requerimento.create(
                requerimentoCommand.titulo,
                requerimentoCommand.descricao,
                requerimentoCommand.autor
            )
        )
    }

    fun delete(id: Long) : Requerimento{
        val requerimentoOpcional = requerimentoRepository.findOneById(id)
        if(requerimentoOpcional.isPresent){
            requerimentoRepository.deleteById(id)
            return requerimentoOpcional.get()
        }

        throw Exception("O ${id} não existe")
    }
}

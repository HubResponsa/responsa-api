package br.com.responsa.api.service

import br.com.responsa.api.model.Consulta
import br.com.responsa.api.model.command.ConsultaCommand
import br.com.responsa.api.repository.ConsultaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import javax.ws.rs.NotFoundException

@Service
class ConsultaService (private val consultaRepository: ConsultaRepository) {

    fun getAll(pageable: Pageable): Page<Consulta> {
        return consultaRepository.findAll(pageable)
    }

    fun getPrincipaisConsultas(): List<Consulta> {
        return consultaRepository.findTop5ByOrderByDataDesc()
    }

    fun add(consultaCommand: ConsultaCommand) {
        consultaRepository.save(
            Consulta.create(
                consultaCommand.titulo,
                consultaCommand.descricao,
                consultaCommand.autor
            )
        )
    }

    fun delete(id: Long) : Consulta{
        val consultaOpcional = consultaRepository.findOneById(id)
        if(consultaOpcional.isPresent){
            consultaRepository.deleteById(id)
            return consultaOpcional.get()
        }

        throw Exception("O ${id} não existe")
    }
}

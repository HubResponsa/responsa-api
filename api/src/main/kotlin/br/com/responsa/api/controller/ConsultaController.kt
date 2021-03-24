package br.com.responsa.api.controller

import br.com.responsa.api.model.Consulta
import br.com.responsa.api.model.command.ConsultaCommand
import br.com.responsa.api.service.ConsultaService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.function.Function
import javax.validation.constraints.NotNull


@RestController
@RequestMapping("/consultas")
class ConsultaController(private val consultaService: ConsultaService){

    @GetMapping("/main")
    @ApiOperation("List get most 5 recents appointments")
    fun getPrincipaisConsultas() : ResponseEntity<List<Consulta>> = ResponseEntity.ok(consultaService.getPrincipaisConsultas())

    @GetMapping
    @ApiOperation("List get all appointments")
    fun getPageable(
        @PageableDefault(
            sort = ["data"],
            direction = Sort.Direction.ASC
        ) pageable : Pageable
    ) : ResponseEntity<Page<Consulta>> {
        return ResponseEntity.ok(consultaService.getAll(pageable))
    }

    @PostMapping
    fun add(@NotNull @RequestBody consultaCommand: ConsultaCommand){
        consultaService.add(consultaCommand);
    }

    @PutMapping
    fun update(@NotNull @RequestBody consultaCommand: ConsultaCommand){
        consultaService.add(consultaCommand);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
        value = "Remove appointments",
        response = Consulta::class,
        httpMethod = "DELETE",
        produces = "application/json",
        code = 201
    )
    fun delete(@NotNull @ApiParam(name = "id", value = "The id of the appointments being removed") @PathVariable("id") id: Long) : ResponseEntity<Consulta>{
        try{
            val consulta = consultaService.delete(id);
            return ResponseEntity.ok(consulta)
        }catch (e : Exception){
            return ResponseEntity.notFound().build()
        }
    }


}

package br.com.responsa.api.controller

import br.com.responsa.api.model.Requerimento
import br.com.responsa.api.model.command.RequerimentoCommand
import br.com.responsa.api.service.RequerimentoService
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
@RequestMapping("/requerimentos")
class RequerimentoController(private val requerimentoService: RequerimentoService){

    @GetMapping("/main")
    @ApiOperation("List get most 5 recents requests")
    fun getPrincipaisRequerimentos() : ResponseEntity<List<Requerimento>> = ResponseEntity.ok(requerimentoService.getPrincipaisRequerimentos())

    @GetMapping
    @ApiOperation("List get all requests")
    fun getPageable(
        @PageableDefault(
            sort = ["data"],
            direction = Sort.Direction.ASC
        ) pageable : Pageable
    ) : ResponseEntity<Page<Requerimento>> {
        return ResponseEntity.ok(requerimentoService.getAll(pageable))
    }

    @PostMapping
    fun add(@NotNull @RequestBody requerimentoCommand: RequerimentoCommand){
        requerimentoService.add(requerimentoCommand);
    }

    @PutMapping
    fun update(@NotNull @RequestBody requerimentoCommand: RequerimentoCommand){
        requerimentoService.add(requerimentoCommand);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
        value = "Remove requests",
        response = Requerimento::class,
        httpMethod = "DELETE",
        produces = "application/json",
        code = 201
    )
    fun delete(@NotNull @ApiParam(name = "id", value = "The id of the requests being removed") @PathVariable("id") id: Long) : ResponseEntity<Requerimento>{
        try{
            val requerimento = requerimentoService.delete(id);
            return ResponseEntity.ok(requerimento)
        }catch (e : Exception){
            return ResponseEntity.notFound().build()
        }
    }


}

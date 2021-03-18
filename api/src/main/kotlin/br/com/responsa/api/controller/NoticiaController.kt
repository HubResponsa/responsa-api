package br.com.responsa.api.controller

import br.com.responsa.api.model.Noticia
import br.com.responsa.api.model.command.NoticiaCommand
import br.com.responsa.api.service.NoticiaService
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
@RequestMapping("/noticias")
class NoticiaController(private val noticiaService: NoticiaService){

    @GetMapping("/main")
    @ApiOperation("List get most 5 recents news")
    fun getPrincipaisNoticias() : ResponseEntity<List<Noticia>> = ResponseEntity.ok(noticiaService.getPrincipaisNoticias())

    @GetMapping
    @ApiOperation("List get all news")
    fun getPageable(
        @PageableDefault(
            sort = ["data"],
            direction = Sort.Direction.ASC
        ) pageable : Pageable
    ) : ResponseEntity<Page<Noticia>> {
        return ResponseEntity.ok(noticiaService.getAll(pageable))
    }

    @PostMapping
    fun add(@NotNull @RequestBody noticiaCommand: NoticiaCommand){
        noticiaService.add(noticiaCommand);
    }

    @PutMapping
    fun update(@NotNull @RequestBody noticiaCommand: NoticiaCommand){
        noticiaService.add(noticiaCommand);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
        value = "Remove news",
        response = Noticia::class,
        httpMethod = "DELETE",
        produces = "application/json",
        code = 201
    )
    fun delete(@NotNull @ApiParam(name = "id", value = "The id of the news being removed") @PathVariable("id") id: Long) : ResponseEntity<Noticia>{
        try{
            val noticia = noticiaService.delete(id);
            return ResponseEntity.ok(noticia)
        }catch (e : Exception){
            return ResponseEntity.notFound().build()
        }
    }


}

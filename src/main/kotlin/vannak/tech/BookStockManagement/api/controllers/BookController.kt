package vannak.tech.BookStockManagement.api.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import vannak.tech.BookStockManagement.api.DTOs.CreateBookDTO
import vannak.tech.BookStockManagement.services.BookService
import javax.validation.Valid

@RestController
@RequestMapping("/books")
class BookController (
        @Autowired var service: BookService
){

    @GetMapping
    fun index(@RequestParam(defaultValue = "0",name = "page")page:Int, @RequestParam(defaultValue = "5",name = "size") size:Int, @RequestParam(defaultValue = "",name = "q")q:String, @RequestParam(defaultValue = "",name = "category")categoryId:Long):ResponseEntity<Any>{
        return ResponseEntity.ok(service.index(q,categoryId,page,size))
    }

    @PostMapping
    fun create(@Valid @RequestBody createBookDTO: CreateBookDTO):ResponseEntity<Any>{
        return ResponseEntity.ok(service.create(createBookDTO))
    }
}
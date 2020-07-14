package vannak.tech.BookStockManagement.api.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import vannak.tech.BookStockManagement.api.DTOs.CreateBookDTO
import vannak.tech.BookStockManagement.api.DTOs.UpdateBookDTO
import vannak.tech.BookStockManagement.services.BookService
import javax.validation.Valid

@RestController
@RequestMapping("/books")
class BookController (
        @Autowired var service: BookService
){

    @GetMapping
    fun index(@RequestParam(name = "q",required = false) q:String?,@RequestParam(name = "page",defaultValue = "0") page:Int,@RequestParam(name = "size",defaultValue = "5")size:Int,@RequestParam(name = "category_id",defaultValue = "0")category_id:Long):ResponseEntity<Any>{
        return ResponseEntity.ok(service.index(category_id,page,size,q))
    }

    @PostMapping
    fun create(@Valid @RequestBody createBookDTO: CreateBookDTO):ResponseEntity<Any>{
        return ResponseEntity.ok(service.create(createBookDTO))
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id:Long):ResponseEntity<Any>{
        return ResponseEntity.ok(service.show(id))
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody updateBookDTO: UpdateBookDTO,@PathVariable id: Long):ResponseEntity<Any>{
        return ResponseEntity.ok(service.update(updateBookDTO,id))
    }
//    @PutMapping("/{id}")
//    fun update():ResponseEntity<Any>{
////        return ResponseEntity.ok()
//    }
}
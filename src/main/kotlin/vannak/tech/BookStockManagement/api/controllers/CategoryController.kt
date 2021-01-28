package vannak.tech.BookStockManagement.api.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import vannak.tech.BookStockManagement.domain.models.Category
import vannak.tech.BookStockManagement.services.CategoryService

@RestController
@RequestMapping("/categories")
class CategoryController (
        @Autowired var categoryService: CategoryService
){
    @GetMapping
    fun index():ResponseEntity<Any>{
        return ResponseEntity.ok(categoryService.index())
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id:Long):ResponseEntity<Any>{
        return  ResponseEntity.ok(categoryService.show(id))
    }
}
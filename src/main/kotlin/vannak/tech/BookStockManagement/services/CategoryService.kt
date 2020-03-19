package vannak.tech.BookStockManagement.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import vannak.tech.BookStockManagement.domain.models.Category
import vannak.tech.BookStockManagement.repositories.CategoryRepository

@Component
class CategoryService (
        @Autowired var categoryRepository: CategoryRepository
){

    fun index():ResponseEntity<Any>{
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    fun show(id:Long):ResponseEntity<Any>{
        return ResponseEntity.ok(categoryRepository.findById(id)!!)
    }

    fun create(category: Category):ResponseEntity<Any>{
        return ResponseEntity.ok(categoryRepository.save(category))
    }
}
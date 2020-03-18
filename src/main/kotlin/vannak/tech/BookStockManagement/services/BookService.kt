package vannak.tech.BookStockManagement.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import vannak.tech.BookStockManagement.api.DTOs.CreateBookDTO
import vannak.tech.BookStockManagement.domain.models.Book
import vannak.tech.BookStockManagement.repositories.BookRepository
import vannak.tech.BookStockManagement.repositories.CategoryRepository

@Component
class BookService(
        @Autowired
        var repository: BookRepository,
        @Autowired
        var categoryRepository: CategoryRepository
) {
        lateinit var pageable: Pageable

        fun index(q:String,categoryId:Long,page:Int,size:Int):ResponseEntity<Any>{
                val category = categoryRepository.findById(categoryId)
                pageable = PageRequest.of(page,size)
                return ResponseEntity.ok(
                        repository.findAll(q,category!!,pageable)
                )
        }

        fun create(createBookDTO: CreateBookDTO):ResponseEntity<Any>{
                var category = categoryRepository.findById(createBookDTO.category!!)
                var book = repository.save(Book.fromDTO(createBookDTO,category!!))
                return ResponseEntity.ok(book)
        }
}
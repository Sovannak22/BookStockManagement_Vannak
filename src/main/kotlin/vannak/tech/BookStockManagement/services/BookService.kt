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

        fun index(categoryId:Long, page:Int, size:Int, q: String?):ResponseEntity<Any>{
                pageable = PageRequest.of(page,size)
                val zero:Long = 0
                var books = when {
                    categoryId==(zero) -> {
                            repository.findAllWithParam(q!!,pageable)
                    }
                    q==null -> {
                            val category = categoryRepository.findById(categoryId)
                            repository.findAllWithParam(category,pageable)
                    }
                    else -> {

                            val category = categoryRepository.findById(categoryId)
                            repository.findAllWithParam(q,category,pageable)
                    }
                }
                return ResponseEntity.ok(books)
        }

        fun create(createBookDTO: CreateBookDTO):ResponseEntity<Any>{
                var category = categoryRepository.findById(createBookDTO.category!!)
                var book = repository.save(Book.fromDTO(createBookDTO,category!!))
                return ResponseEntity.ok(book.toDTO())
        }
}
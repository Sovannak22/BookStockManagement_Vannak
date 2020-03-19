package vannak.tech.BookStockManagement.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import vannak.tech.BookStockManagement.api.DTOs.CreateBookDTO
import vannak.tech.BookStockManagement.api.DTOs.UpdateBookDTO
import vannak.tech.BookStockManagement.api.exceptions.IDNotFoundException
import vannak.tech.BookStockManagement.domain.models.Book
import vannak.tech.BookStockManagement.repositories.BookRepository
import vannak.tech.BookStockManagement.repositories.CategoryRepository
import java.lang.NullPointerException

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
                    (q==null && categoryId==(zero)) -> {
                        repository.findAll(pageable)
                    }
                    categoryId==(zero) -> {
                            repository.findAllWithParam(q!!,pageable)
                    }
                    q==null -> {
                            val category = categoryRepository.findById(categoryId)
                            repository.findAllWithParam(category!!,pageable)
                    }
                    else -> {

                            val category = categoryRepository.findById(categoryId)
                            repository.findAllWithParam(q,category!!,pageable)
                    }
                }
                return ResponseEntity.ok(books)
        }

        fun create(createBookDTO: CreateBookDTO):ResponseEntity<Any>{
                try {
                    val category = categoryRepository.findById(createBookDTO.category!!)
                    val book = repository.save(Book.fromDTO(createBookDTO,category!!))
                    return ResponseEntity.ok(book.toDTO())
                }catch (e:NullPointerException){
                    throw IDNotFoundException("${createBookDTO.category}")
                }
        }

        fun show(id:Long):ResponseEntity<Any>{
            try {
                val book = repository.findById(id)
                return ResponseEntity.ok(book!!)
            }catch (e:NullPointerException){
                throw IDNotFoundException("$id")
            }
        }

        fun update(updateBookDTO: UpdateBookDTO,id: Long):ResponseEntity<Any>{
            val category = if (updateBookDTO.category != null)categoryRepository.findById(updateBookDTO.category!!)else null
            val originalBook = repository.findById(id)
            return ResponseEntity.ok(repository.save(Book.fromDTO(updateBookDTO,category,originalBook!!)))
        }
}
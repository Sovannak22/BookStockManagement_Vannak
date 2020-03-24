package vannak.tech.BookStockManagement.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import vannak.tech.BookStockManagement.api.DTOs.CreateBookDTO
import vannak.tech.BookStockManagement.api.DTOs.UpdateBookDTO
import vannak.tech.BookStockManagement.api.exceptions.IDNotFoundException
import vannak.tech.BookStockManagement.domain.models.Book
import vannak.tech.BookStockManagement.repositories.BookRepository
import vannak.tech.BookStockManagement.repositories.CategoryRepository
import vannak.tech.BookStockManagement.specifications.BookSpecs
import java.lang.NullPointerException

@Component
class BookService(
        @Autowired
        var repository: BookRepository,
        @Autowired
        var categoryRepository: CategoryRepository
) {

        lateinit var pageable: Pageable

        fun index(categoryId:Long?, page:Int, limit:Int, q: String?):ResponseEntity<Any>{
            pageable = PageRequest.of(page,limit)
            var specification: Specification<Book> = Specification.where(BookSpecs.filterLikeParamSpec(q!!))!!
            if (q.toIntOrNull()!=null)
                specification = specification.or(BookSpecs.filterEqualParamSpec(q))!!
            if (categoryId != null)
                specification = specification.and(BookSpecs.filterCategory(categoryId))!!
            val books = repository.findAll(specification,pageable).map {
                it.toDTO()
            }

            return ResponseEntity.ok(books)
        }

        fun create(createBookDTO: CreateBookDTO):ResponseEntity<Any>{
                val category = categoryRepository.findById(createBookDTO.category!!).orElseThrow {
                    IDNotFoundException("${createBookDTO.category}")
                }
                val book = repository.save(Book.fromDTO(createBookDTO,category))
                return ResponseEntity.ok(book.toDTO())
        }

        fun show(id:Long):ResponseEntity<Any>{
            val book = repository.findById(id).orElseThrow{
                IDNotFoundException("$id")
            }
            return ResponseEntity.ok(book.toDTO())

        }

        fun update(updateBookDTO: UpdateBookDTO,id: Long):ResponseEntity<Any>{
            val category = if (updateBookDTO.category != null)categoryRepository.findById(updateBookDTO.category!!)else null
            val originalBook = repository.findById(id)
            return ResponseEntity.ok(
                    repository.save(
                            Book.fromDTO(updateBookDTO,category!!.get(),originalBook.get())
                    ).toDTO()
            )
        }
}
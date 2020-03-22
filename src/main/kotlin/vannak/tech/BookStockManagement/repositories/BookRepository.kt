package vannak.tech.BookStockManagement.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import vannak.tech.BookStockManagement.domain.models.Book
import vannak.tech.BookStockManagement.domain.models.Category

interface BookRepository: CrudRepository<Book,Long> {

    @Query("SELECT b FROM Book b WHERE (b.title LIKE %:q% OR "+
            "b.isbn LIKE %:q% OR " +
            "b.author LIKE %:q% OR "+
            "b.publisher LIKE %:q% OR "+
            "b.edition LIKE %:q% OR "+
            "b.quantity LIKE %:q% OR "+
            "b.status LIKE %:q% OR "+
            "b.publishYear LIKE %:q%) AND "+
            "b.category=:category"
    )
    fun findAllWithParam(@Param("q") q:String,@Param("category") category: Category,page: Pageable): Page<Book>

    @Query("SELECT b FROM Book b WHERE (b.title LIKE %:q% OR "+
            "b.isbn LIKE %:q% OR " +
            "b.author LIKE %:q% OR "+
            "b.publisher LIKE %:q% OR "+
            "b.edition LIKE %:q% OR "+
            "b.quantity LIKE %:q% OR "+
            "b.status LIKE %:q% OR "+
            "b.publishYear LIKE %:q%)"
    )
    fun findAllWithParam(@Param("q")q:String,page: Pageable):Page<Book>

    @Query("SELECT b FROM Book b WHERE b.category=:category")
    fun findAllWithParam(@Param(value = "category")category: Category,page: Pageable):Page<Book>

    fun findAll(page: Pageable):Page<Book>

}
package vannak.tech.BookStockManagement.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import vannak.tech.BookStockManagement.domain.models.Book
import vannak.tech.BookStockManagement.domain.models.Category

interface BookRepository: JpaRepository<Book,Int> {

    @Query("SELECT b FROM Book b where b.title LIKE %:q% OR "+
            "b.isbn LIKE %:q% OR " +
            "b.author LIKE %:q% OR "+
            "b.publisher LIKE %:q% OR "+
            "b.edition=:q OR "+
            "b.publisher LIKE %:q% OR "+
            "b.quantity=:q OR "+
            "b.status=:q OR "+
            "b.publishYear=:q AND "+
            "b.category = :category"
    )
    fun findAll(@Param("q") q:String,@Param("category") category: Category,page: Pageable): Page<Book>

}
package vannak.tech.BookStockManagement.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import vannak.tech.BookStockManagement.domain.models.Book
import vannak.tech.BookStockManagement.domain.models.Category

interface BookRepository: CrudRepository<Book,Long> ,JpaSpecificationExecutor<Book>{

}
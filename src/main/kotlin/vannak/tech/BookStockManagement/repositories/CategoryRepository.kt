package vannak.tech.BookStockManagement.repositories

import org.springframework.data.jpa.repository.JpaRepository
import vannak.tech.BookStockManagement.domain.models.Category
import java.util.*

interface CategoryRepository: JpaRepository<Category,Int> {
    fun findById(id: Long): Category
}
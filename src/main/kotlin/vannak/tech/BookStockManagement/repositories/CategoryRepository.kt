package vannak.tech.BookStockManagement.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import vannak.tech.BookStockManagement.domain.models.Category
import java.util.*

interface CategoryRepository: CrudRepository<Category,Long> {
}
package vannak.tech.BookStockManagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookStockManagementApplication

fun main(args: Array<String>) {
	runApplication<BookStockManagementApplication>(*args)
}

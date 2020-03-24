package vannak.tech.BookStockManagement.domain.models

import vannak.tech.BookStockManagement.api.DTOs.BookDTO
import vannak.tech.BookStockManagement.api.DTOs.CreateBookDTO
import vannak.tech.BookStockManagement.api.DTOs.UpdateBookDTO
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Book (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        var id:Long=0,
        @Column(name = "title")
        var title:String?,
        @Column(name = "isbn")
        var isbn:String?,
        @Column(name = "author")
        var author:String?,
        @Column(name = "publisher")
        var publisher:String?,
        @Column(name = "edition")
        var edition:Int?,
        @Column(name = "price")
        var price:Float?,
        @Column(name = "quantity")
        var quantity:Int?,
        @Column(name = "status")
        var status:Int?,
        @Column(name = "publish_year")
        var publishYear:Int?,
        @Column(name = "created_at")
        var createdAt:LocalDateTime = LocalDateTime.now(),
        @Column(name = "updated_at")
        var updatedAt:LocalDateTime = LocalDateTime.now()
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    lateinit var category: Category

    fun toDTO(): BookDTO = BookDTO(
            id = id,
            title = title,
            isbn = isbn,
            author = author,
            publisher = publisher,
            edition = edition,
            price = price,
            quantity = quantity,
            status = status,
            publisherYear = publishYear,
            category = category.id,
            createdAt = createdAt,
            updatedAt = updatedAt
    )

    companion object{

        fun fromDTO(createBookDTO: CreateBookDTO, category: Category ):Book{
            var book = Book(
                    title = createBookDTO.title,
                    isbn = createBookDTO.isbn,
                    author = createBookDTO.author,
                    publisher = createBookDTO.publisher,
                    edition = createBookDTO.edition,
                    price = createBookDTO.price,
                    quantity = createBookDTO.quantity,
                    status = createBookDTO.status,
                    publishYear = createBookDTO.publisherYear
            )
            book.category = category
            return book
        }

        fun fromDTO(updateBookDTO: UpdateBookDTO, category: Category?, originalBook: Book ):Book{
            var book = Book(
                    id = originalBook.id,
                    title = updateBookDTO.title?:originalBook.title,
                    isbn = updateBookDTO.isbn?:originalBook.isbn,
                    author = updateBookDTO.author?:originalBook.author,
                    publisher = updateBookDTO.publisher?:originalBook.publisher,
                    edition = updateBookDTO.edition?:originalBook.edition,
                    price = updateBookDTO.price?:originalBook.price,
                    quantity = updateBookDTO.quantity?:originalBook.quantity,
                    status = updateBookDTO.status?:originalBook.status,
                    publishYear = updateBookDTO.publisherYear?:originalBook.publishYear
            )
            book.category = category?:originalBook.category
            return book
        }
    }
}
package vannak.tech.BookStockManagement.api.DTOs

import vannak.tech.BookStockManagement.domain.models.Category
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

data class BookDTO (
        var id:Long?,
        var title:String?,
        var isbn:String?,
        var author:String?,
        var publisher:String?,
        var edition:Int?,
        var price:Float?,
        var quantity:Int?,
        var status:String?,
        var publisherYear:Int?,
        var category:Long?
)

data class CreateBookDTO(
        @field:NotEmpty
        var title: String?,
        @field:NotEmpty
        @field:Pattern(regexp = "[0-9]{3}-[0-9]-[0-9]{2}-[0-9]{6}-[0-9]")
        var isbn: String?,
        @field:NotEmpty
        var author: String?,
        @field:NotEmpty
        var publisher: String?,
        var edition: Int?,
        var price: Float,
        var quantity: Int?,
        @field:NotEmpty
        var status: String?,
        var publisherYear: Int?,
        var category: Long?
)

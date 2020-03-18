package vannak.tech.BookStockManagement.api.DTOs

import vannak.tech.BookStockManagement.domain.models.Category

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
        var id: Long?,
        var title: String?,
        var isbn: String?,
        var author: String?,
        var publisher: String?,
        var edition: Int?,
        var price: Float?,
        var quantity: Int?,
        var status: String?,
        var publisherYear: Int?,
        var category: Long?
)

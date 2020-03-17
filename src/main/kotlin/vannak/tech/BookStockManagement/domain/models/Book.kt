package vannak.tech.BookStockManagement.domain.models

import javax.persistence.*

@Entity
data class Book (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        var id:Long?,
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
        var status:String?,
        @Column(name = "publish_year")
        var publishYear:Int?
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    lateinit var category: Category
}
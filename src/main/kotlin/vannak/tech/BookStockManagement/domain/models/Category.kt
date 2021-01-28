package vannak.tech.BookStockManagement.domain.models

import javax.persistence.*

@Entity
@Table(name = "categories")
data class Category (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        var id:Long?,
        @Column(name = "name")
        var name:String?,
        @Column(name = "description")
        var description:String?
){
}
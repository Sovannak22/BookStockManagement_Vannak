package vannak.tech.BookStockManagement.specifications

import org.springframework.data.jpa.domain.Specification
import vannak.tech.BookStockManagement.domain.models.Book
import vannak.tech.BookStockManagement.domain.models.Category
import java.text.MessageFormat
import java.util.*
import javax.persistence.criteria.*

class BookSpecs {
    companion object{
        fun filterLikeParamSpec(value:String):Specification<Book>{
            return Specification { root, _, builder -> builder.or(
                    builder.like(builder.lower(root.get("title")), contains(value)),
                    builder.like(builder.lower(root.get("author")), contains(value)),
                    builder.like(builder.lower(root.get("isbn")), contains(value)),
                    builder.like(builder.lower(root.get("publisher")), contains(value))
            ) }
        }

        fun filterEqualParamSpec(value: String):Specification<Book>{
            return Specification { root, _, criteriaBuilder -> criteriaBuilder.or(
                    criteriaBuilder.equal(root.get<Int>("edition"),value.toInt()),
                    criteriaBuilder.equal(root.get<Float>("price"),value.toFloat()),
                    criteriaBuilder.equal(root.get<Int>("status"),value.toInt()),
                    criteriaBuilder.equal(root.get<Int>("publishYear"),value.toInt())
            )  }
        }

        fun filterCategory(id:Long):Specification<Book>{
            return Specification { root, _, criteriaBuilder ->
                val bookCategory = root.join<Book,Category>("category",JoinType.INNER)
                criteriaBuilder.equal(bookCategory.get<Long>("id"),id)
            }
        }

        fun contains(expression:String):String{
            return MessageFormat.format("%{0}%",expression)
        }
    }
}
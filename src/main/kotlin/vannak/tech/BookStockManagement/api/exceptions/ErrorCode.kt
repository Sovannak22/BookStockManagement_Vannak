package vannak.tech.BookStockManagement.api.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

enum class ErrorCode(val code: Int, val message:String){
    INTERNAL_SERVER_ERROR(1,"Internal Server Error"),
    ID_NOT_FOUND(2,"ID Not found: %s"),
    INVALID_INPUT_FORMAT(3,"It is invalid format: %s"),
    MISSING_PARAMETER_FOR_REQUEST(4,"Missing Parameter %s for request")

}
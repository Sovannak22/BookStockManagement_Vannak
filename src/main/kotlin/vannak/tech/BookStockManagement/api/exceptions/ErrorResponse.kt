package vannak.tech.BookStockManagement.api.exceptions

import java.time.LocalDateTime

class ErrorResponse(val time: LocalDateTime,val  code:Int,val errorMessage:String) {
    constructor(errorCode: ErrorCode,errorField:String?): this(LocalDateTime.now(),errorCode.code,String.format(errorCode.message,errorField))
}
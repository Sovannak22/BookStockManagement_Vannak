package vannak.tech.BookStockManagement.api.exceptions

import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class EntityExceptionHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(Exception::class)
    final fun handleAllExceptions(e : Exception, request:WebRequest): ResponseEntity<ErrorResponse>{
        logger.error("Error Log: ", e)
        val errorResponse: ErrorResponse = ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, null)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)

    }

    @ExceptionHandler(IDNotFoundException::class)
    fun handleNullPointerException(e: IDNotFoundException): ResponseEntity<ErrorResponse>{
        val errorResponse:ErrorResponse = ErrorResponse(ErrorCode.ID_NOT_FOUND,e.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e : MethodArgumentNotValidException,request:WebRequest):ResponseEntity<ErrorResponse>{
        val errorResponse = ErrorResponse(ErrorCode.INVALID_INPUT_FORMAT, e.bindingResult.fieldError!!.field)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissServletRequestParameterException(e : MethodArgumentNotValidException,request:WebRequest):ResponseEntity<ErrorResponse>{
        val errorResponse = ErrorResponse(ErrorCode.MISSING_PARAMETER_FOR_REQUEST,e.bindingResult.fieldError!!.field)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}
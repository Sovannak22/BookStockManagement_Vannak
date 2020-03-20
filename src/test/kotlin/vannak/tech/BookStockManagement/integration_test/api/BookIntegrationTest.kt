package vannak.tech.BookStockManagement.integration_test.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import com.github.javafaker.Faker
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import vannak.tech.BookStockManagement.api.DTOs.BookDTO
import vannak.tech.BookStockManagement.api.DTOs.CreateBookDTO
import vannak.tech.BookStockManagement.domain.models.Category
import vannak.tech.BookStockManagement.services.BookService
import vannak.tech.BookStockManagement.services.CategoryService
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@AutoConfigureMockMvc
class BookIntegrationTest{
    val faker = Faker()
    val mapper = ObjectMapper()
    var writer:ObjectWriter?=null
    @Autowired val bookService: BookService?=null
    @Autowired val categoryService: CategoryService?=null
    @Autowired var mockMvc: MockMvc?=null
    @BeforeEach
    fun initObjectWriter(){
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false)
        writer = mapper.writer().withDefaultPrettyPrinter()
    }
    @BeforeEach
    fun setUp(){
        setUpCategory()
        setUpBook()
    }


    @Test
    fun indexTestWithNoParam(){
        mockMvc!!.perform(
                get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.body.content").isNotEmpty)
                .andExpect(jsonPath("$.body.totalElements").value(5))

    }

    @Test
    fun indexTestWithSizeAndPage(){
        mockMvc!!.perform(
                get("/books?size=4&page=2")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.body.content").isNotEmpty)
                .andExpect(jsonPath("$.body.totalElements").value(10))
                .andExpect(jsonPath("$.body.number").value(2))
                .andExpect(jsonPath("$.body.numberOfElements").value(2))

    }

    @Test
    fun indexTestWithCategory(){

    }


    fun setUpBook(){
        for (i in 1..5){
            val createBook = bookService!!.create(
                    CreateBookDTO(
                            title = faker.book().title(),
                            isbn = faker.bothify("###-#-##-######-#"),
                            author = faker.book().author(),
                            publisher = faker.book().publisher(),
                            edition = faker.number().randomDigitNotZero(),
                            price = faker.number().randomDouble(2,10,100).toFloat(),
                            quantity = faker.number().numberBetween(1,100),
                            status = 1,
                            publisherYear = faker.number().numberBetween(1900,2020),
                            category = faker.number().numberBetween(1,5).toLong()

                    )
            ).body as BookDTO
        }
    }
    fun setUpCategory(){

        for(i in 1..5){
            val createCategory = categoryService!!.create(
                    Category(
                            name = faker.book().genre(),
                            description = faker.lorem().characters(15,true)
                    )
            ).body as Category

            print("INIT_CREATE: ${createCategory.toString()}")
        }
    }
}
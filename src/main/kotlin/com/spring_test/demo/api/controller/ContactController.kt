package com.spring_test.demo.api.controller

import com.spring_test.demo.api.dto.ContactBody
import com.spring_test.demo.model.Contact
import com.spring_test.demo.repository.ContactRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.coroutines.Continuation

@RestController
@RequestMapping("/contacts")
class ContactController(
    private val contactRepository: ContactRepository
) {

    @PostMapping
    fun saveContact(
        @RequestBody body: ContactBody
    ): ContactResponse{
        try {
            contactRepository.save<Contact>(
                Contact(
                    contactName = body.contactName,
                    phoneNumber = body.contactNumber
                )
            )
            return ContactResponse(
                message = "contact added successfully"
            )
        }catch (e:Exception){
            return ContactResponse(
                message = "error adding contact ${e.message}"
            )
        }
    }

    @GetMapping
    fun hello(): ContactResponse{
        return ContactResponse(
            message = "hello from the other side"
        )
    }


    data class ContactResponse(
        val message: String,
    )

//    @GetMapping
//    fun hello(
//        @RequestParam(required = true) by: String
//    ): HelloResponse {
//        return HelloResponse(
//            greetingName = "hello this is omer", by = by
//        )
//    }
//
////    @PostMapping
////    fun saveHello(
////        @RequestBody body: HelloBody
////    ) {
////        println(body)
////    }
////
////
////    data class HelloResponse(
////        val greetingName: String, val by: String
////    )
////
////    data class HelloBody(
////        val greetingName: String, val by: String
////    )
}
//docker run --name demo_test -p 5432:5432 -e POSTGRES_USER=omer -e POSTGRES_PASSWORD=omer -e POSTGRES_DB=demo_db postgres:15-alpine

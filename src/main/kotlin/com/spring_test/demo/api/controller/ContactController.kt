package com.spring_test.demo.api.controller

import com.spring_test.demo.api.dto.ContactBody
import com.spring_test.demo.model.Contact
import com.spring_test.demo.repository.ContactRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contacts")
class ContactController(
    private val contactRepository: ContactRepository
) {

    @PostMapping
    fun saveContact(
        @RequestBody body: ContactBody
    ): ContactResponse {
        try {
            contactRepository.save<Contact>(
                Contact(
                    contactName = body.contactName, phoneNumber = body.contactNumber
                )
            )
            return ContactResponse(
                message = "contact added successfully"
            )
        } catch (e: Exception) {
            return ContactResponse(
                message = "error adding contact ${e.message}"
            )
        }
    }

    @DeleteMapping("/{phone}")
    fun deleteByNumber(@PathVariable phone: String): ContactResponse {
        try {
            contactRepository.deleteByPhoneNumber(phone)
            return ContactResponse(
                message = "contact deleted successfully"
            )
        } catch (e: Exception) {
            return ContactResponse(
                message = "Error deleting contact ${e.message}"
            )
        }
    }

    @GetMapping
    fun getAllContacts(): ContactListResponse {
        return ContactListResponse(
            contacts = contactRepository.findAll().mapNotNull {
                ContactDetail(
                    name = it.contactName, phone = it.phoneNumber, imgUrl = it.imageUrl
                )
            })
    }


    data class ContactResponse(
        val message: String,
    )

    data class ContactListResponse(
        val contacts: List<ContactDetail>
    )

    data class ContactDetail(
        val name: String, val phone: String, val imgUrl: String
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

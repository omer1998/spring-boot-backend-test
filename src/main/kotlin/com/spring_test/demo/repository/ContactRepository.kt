package com.spring_test.demo.repository

import com.spring_test.demo.model.Contact
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ContactRepository: JpaRepository<Contact, Long>{
    @Transactional
    fun deleteByPhoneNumber(phoneNumber: String)
}
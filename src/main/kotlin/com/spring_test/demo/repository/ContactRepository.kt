package com.spring_test.demo.repository

import com.spring_test.demo.model.Contact
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository: JpaRepository<Contact, Long>
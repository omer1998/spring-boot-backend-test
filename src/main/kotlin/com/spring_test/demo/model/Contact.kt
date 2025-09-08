package com.spring_test.demo.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "contacts")
class Contact(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
    @Column(name = "contact_name") val contactName: String,
    @Column(name = "phone_number") val phoneNumber: String
)
package com.phonebookbackend.controllers

import com.phonebookbackend.entities.Contact
import com.phonebookbackend.services.ContactService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contacts")
class ContactController(private val contactService: ContactService) {

    @GetMapping
    fun getAllContacts(): List<Contact> {
        return contactService.getAllContacts()
    }

    @GetMapping("/{id}")
    fun getContactById(@PathVariable id: Long): ResponseEntity<Contact?> {
        val contact = contactService.getContactById(id)
        return if (contact != null) {
            ResponseEntity(contact, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createContact(@RequestBody contact: Contact): ResponseEntity<Contact> {
        val createdContact = contactService.createContact(contact)
        return ResponseEntity(createdContact, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateContact(
            @PathVariable id: Long,
            @RequestBody updatedContact: Contact
    ): ResponseEntity<Contact?> {
        val updated = contactService.updateContact(id, updatedContact)
        return if (updated != null) {
            ResponseEntity(updated, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteContact(@PathVariable id: Long): ResponseEntity<Unit> {
        return if (contactService.deleteContact(id)) {
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}

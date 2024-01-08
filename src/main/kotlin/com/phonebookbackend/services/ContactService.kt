package com.phonebookbackend.services
import com.phonebookbackend.entities.Contact
import com.phonebookbackend.repositories.ContactRepository
import org.springframework.stereotype.Service

@Service
class ContactService(private val contactRepository: ContactRepository) {

    fun getAllContacts(): List<Contact> {
        return contactRepository.findAll()
    }

    fun getContactById(id: Long): Contact? {
        return contactRepository.findById(id).orElse(null)
    }

    fun createContact(contact: Contact): Contact {
        return contactRepository.save(contact)
    }

    fun updateContact(id: Long, updatedContact: Contact): Contact? {
        return contactRepository.findById(id).map {
            it.name = updatedContact.name
            it.phoneNumber = updatedContact.phoneNumber
            it.address = updatedContact.address
            contactRepository.save(it)
        }.orElse(null)
    }

    fun deleteContact(id: Long): Boolean {
        return if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}

package com.example.lab_3.data.repository

import com.example.lab_3.data.local.ContactDao
import com.example.lab_3.data.local.ContactEntity
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {
    val contacts: Flow<List<ContactEntity>> = contactDao.getAllContacts()

    fun getContactById(id: String): Flow<ContactEntity?> {
        return contactDao.getContactById(id)
    }

    suspend fun addContact(contact: ContactEntity) {
        contactDao.insertContact(contact)
    }

    suspend fun updateContact(contact: ContactEntity) {
        contactDao.updateContact(contact)
    }

    suspend fun deleteContact(contact: ContactEntity) {
        contactDao.deleteContact(contact)
    }
}

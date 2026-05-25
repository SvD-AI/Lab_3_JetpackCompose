package com.example.lab_3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContactsViewModel : ViewModel() {
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow()

    init {
        // Mock data
        _contacts.value = listOf(
            Contact(name = "John Doe", phoneNumber = "+380501234567", email = "john@example.com"),
            Contact(name = "Jane Smith", phoneNumber = "+380671234567", email = "jane@example.com"),
            Contact(name = "Taras Shevchenko", phoneNumber = "+380991234567")
        )
    }

    fun addContact(name: String, phoneNumber: String, email: String) {
        val newContact = Contact(name = name, phoneNumber = phoneNumber, email = email)
        _contacts.update { it + newContact }
    }

    fun updateContact(id: String, name: String, phoneNumber: String, email: String) {
        _contacts.update { currentContacts ->
            currentContacts.map { if (it.id == id) it.copy(name = name, phoneNumber = phoneNumber, email = email) else it }
        }
    }

    fun deleteContact(id: String) {
        _contacts.update { it.filter { contact -> contact.id != id } }
    }

    fun getContactById(id: String): Contact? {
        return _contacts.value.find { it.id == id }
    }
}

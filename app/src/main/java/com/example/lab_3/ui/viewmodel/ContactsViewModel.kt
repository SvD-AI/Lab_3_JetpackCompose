package com.example.lab_3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lab_3.data.local.ContactEntity
import com.example.lab_3.data.repository.ContactRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContactsViewModel(private val repository: ContactRepository) : ViewModel() {
    val contacts = repository.contacts.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addContact(name: String, phoneNumber: String, email: String) {
        viewModelScope.launch {
            repository.addContact(ContactEntity(name = name, phoneNumber = phoneNumber, email = email))
        }
    }

    fun updateContact(id: String, name: String, phoneNumber: String, email: String) {
        viewModelScope.launch {
            repository.updateContact(ContactEntity(id = id, name = name, phoneNumber = phoneNumber, email = email))
        }
    }

    fun deleteContact(contact: ContactEntity) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }

    fun getContactById(id: String) = repository.getContactById(id)
}

class ContactsViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

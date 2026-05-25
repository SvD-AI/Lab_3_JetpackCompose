package com.example.lab_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab_3.data.local.DatabaseProvider
import com.example.lab_3.data.repository.ContactRepository
import com.example.lab_3.ui.screens.AppNavigation
import com.example.lab_3.ui.viewmodel.ContactsViewModel
import com.example.lab_3.ui.viewmodel.ContactsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val database = DatabaseProvider.getDatabase(context)
            val repository = ContactRepository(database.contactDao())
            
            val viewModel: ContactsViewModel = viewModel(
                factory = ContactsViewModelFactory(repository)
            )

            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}
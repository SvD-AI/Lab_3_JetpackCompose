package com.example.lab_3.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab_3.ui.viewmodel.ContactsViewModel

@Composable
fun AppNavigation(viewModel: ContactsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ContactsListScreen(
                viewModel = viewModel,
                onNavigateToAdd = { navController.navigate("add") },
                onNavigateToDetails = { id -> navController.navigate("details/$id") }
            )
        }
        composable("add") {
            AddContactScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                contactIdToEdit = null
            )
        }
        composable(
            "add?contactId={contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            AddContactScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                contactIdToEdit = backStackEntry.arguments?.getString("contactId")
            )
        }
        composable(
            "details/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.StringType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId") ?: return@composable
            DetailsContactScreen(
                viewModel = viewModel,
                contactId = contactId,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToEdit = { id -> navController.navigate("add?contactId=$id") },
                onNavigateHome = {
                    navController.popBackStack("list", inclusive = false)
                }
            )
        }
    }
}

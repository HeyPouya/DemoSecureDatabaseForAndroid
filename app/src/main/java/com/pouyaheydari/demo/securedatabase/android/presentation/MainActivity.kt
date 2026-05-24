package com.pouyaheydari.demo.securedatabase.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.pouyaheydari.demo.securedatabase.android.data.local.AppDatabase
import com.pouyaheydari.demo.securedatabase.android.data.repository.UserRepositoryImpl
import com.pouyaheydari.demo.securedatabase.android.presentation.theme.DemoSecureDatabaseAndroidTheme
import com.pouyaheydari.demo.securedatabase.android.presentation.users.UserScreen
import com.pouyaheydari.demo.securedatabase.android.presentation.users.UserViewModel
import com.pouyaheydari.demo.securedatabase.android.presentation.users.UserViewModelFactory

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels {
        val sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val database = AppDatabase.getDatabase(applicationContext, sharedPreferences)
        val repository = UserRepositoryImpl(database.userDao())
        UserViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoSecureDatabaseAndroidTheme {
                UserScreen(userViewModel)
            }
        }
    }
}

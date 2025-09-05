package com.pouyaheydari.demo.securedatabase.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pouyaheydari.demo.securedatabase.android.ui.theme.DemoSecureDatabaseAndroidTheme

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels {
        val applicationContext = applicationContext
        val sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        System.loadLibrary("sqlcipher") // Ensure SQLCipher is loaded
        val database = AppDatabase.getDatabase(applicationContext, sharedPreferences)
        UserViewModelFactory(database.userDao())
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

@Composable
fun UserScreen(viewModel: UserViewModel) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var showUsers by remember { mutableStateOf(false) }
    val usersList by viewModel.allUsers.collectAsState(initial = emptyList())

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {
                    viewModel.addUser(name, email)
                    name = "" // Clear input after saving
                    email = ""
                }) {
                    Text("Save User")
                }
                Button(onClick = { showUsers = !showUsers }) {
                    Text(if (showUsers) "Hide Users" else "Show Users")
                }
            }

            if (showUsers) {
                if (usersList.isEmpty()) {
                    Text("No users in the database.")
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(usersList) { user ->
                            UserItem(user)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Name: ${user.name}")
            Text(text = "Email: ${user.email}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoSecureDatabaseAndroidTheme {
        // This is a simplified preview and won't have actual ViewModel logic
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = "Test Name",
                onValueChange = { },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = "test@example.com",
                onValueChange = { },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { }) {
                    Text("Save User")
                }
                Button(onClick = { }) {
                    Text("Show Users")
                }
            }
            UserItem(User(id = 1, name = "Preview User", email = "preview@example.com"))
        }
    }
}

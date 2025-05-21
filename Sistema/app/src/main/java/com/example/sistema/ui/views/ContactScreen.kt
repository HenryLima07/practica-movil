import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sistema.ui.utils.Contact
import com.example.sistema.ui.utils.fetchContacts
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactsScreen() {
    val context = LocalContext.current
    val contactsPermissionState = rememberPermissionState(Manifest
        .permission.READ_CONTACTS)
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    var contacts by remember { val mutableStateOf = mutableStateOf<List<Contact>>(emptyList())
        mutableStateOf
    }
    var isLoading by remember { mutableStateOf(false) }

    // Efecto para cargar contactos cuando el permiso es concedido
    LaunchedEffect(contactsPermissionState.status) {
        if (contactsPermissionState.status.isGranted) {
            isLoading = true
            coroutineScope.launch {
                contacts =
                    fetchContacts(context.contentResolver)
                isLoading = false
            }
        } else {
            // Opcional: Limpiar la lista si el permiso es revocado
            contacts = emptyList()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (contactsPermissionState.status.isGranted) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else {
                if (contacts.isEmpty()) {
                    Text("No se encontraron contactos o la lista está vacía.")
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items (contacts) { contact ->
                            Text(
                                text = contact.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        } else {
            Text("Se necesita permiso para leer los contactos.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { contactsPermissionState.launchPermissionRequest() }) {
                Text("Solicitar Permiso de Contactos")
            }
        }
    }
}
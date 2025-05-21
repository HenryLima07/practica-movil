package com.example.sistema.ui.utils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.provider.ContactsContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class Contact(val id: String, val name: String)

// Es importante ejecutar esta función en un hilo de fondo (Dispatchers.IO)
@SuppressLint("Range") // Se usa para suprimir el lint cuando sabemos que la columna existe
suspend fun fetchContacts(contentResolver: ContentResolver): List<Contact> {
    return withContext(Dispatchers.IO) {
        val contactsList = mutableListOf<Contact>()
        val projection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        )
        // Puedes añadir un filtro (selection) y argumentos (selectionArgs) si es necesario
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null, // No selection filter
            null, // No selection arguments
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " ASC" // Sort order
        )

        cursor?.use { // .use asegura que el cursor se cierre automáticamente
            while (it.moveToNext()) {
                val id = it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                if (name != null) { // Asegurarse de que el nombre no es nulo
                    contactsList.add(Contact(id, name))
                }
            }
        }
        contactsList
    }
}
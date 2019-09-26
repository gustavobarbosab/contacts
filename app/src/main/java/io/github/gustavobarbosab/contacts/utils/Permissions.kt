package io.github.gustavobarbosab.contacts.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Context.isPermissionGranted(permission: String) =
    ContextCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED

fun Activity.requestPermission(vararg permissions: String) {
    ActivityCompat.requestPermissions(
        this,
        permissions,
        REQUEST_CODE_PERMISSION_READ_CONTACT
    )
}

fun Fragment.requestPermission(vararg permissions: String) {
    requestPermissions(
        permissions,
        REQUEST_CODE_PERMISSION_READ_CONTACT
    )
}

const val REQUEST_CODE_PERMISSION_READ_CONTACT = 10
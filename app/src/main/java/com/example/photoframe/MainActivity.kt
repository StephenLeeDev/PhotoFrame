package com.example.photoframe

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val buttonAddPhoto: Button by lazy {
        findViewById(R.id.buttonAddPhoto)
    }

    private val buttonStart: Button by lazy {
        findViewById(R.id.buttonStart)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun initButtonAddPhoto() {
        buttonAddPhoto.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {

                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    showPermissionContextPopup()
                }
                else -> {
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
                }
            }
        }
    }

    private fun showPermissionContextPopup() {
        AlertDialog.Builder(this)
                .setTitle("Permission is required")
                .setMessage("Permission is required to use this app")
                .setPositiveButton("Agree") { _, _ ->
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
                .show()
    }

    private fun initButtonStart() {

    }
}
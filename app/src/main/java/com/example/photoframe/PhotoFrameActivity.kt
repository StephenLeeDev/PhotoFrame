package com.example.photoframe

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlin.concurrent.timer

class PhotoFrameActivity : AppCompatActivity() {

    private val photoList = mutableListOf<Uri>()

    private var currentPosition = 0

    private val imageViewPhoto: ImageView by lazy {
        findViewById<ImageView>(R.id.imageViewPhoto)
    }

    private val imageViewBackground: ImageView by lazy {
        findViewById<ImageView>(R.id.imageViewBackground)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_frame)

        getPhotoUriFromIntent()

        startTimer()
    }

    private fun getPhotoUriFromIntent() {
        val size = intent.getIntExtra("photoListSize", 0)
        for (i in 0..size) {
            intent.getStringExtra("photo$i")?.let {
                photoList.add(Uri.parse(it))
            }
        }
    }

    private fun startTimer() {
        timer(period = 5 * 1000) {
            runOnUiThread {
                val current = currentPosition
                val next = if (photoList.size <= currentPosition + 1) 0 else currentPosition + 1

                imageViewBackground.setImageURI(photoList[current])

                imageViewPhoto.alpha = 0f
                imageViewPhoto.setImageURI(photoList[next])
                imageViewPhoto.animate()
                        .alpha(1.0f)
                        .setDuration(1000)
                        .start()

                currentPosition = next
            }
        }
    }
}
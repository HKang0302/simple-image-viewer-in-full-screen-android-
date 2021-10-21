package com.example.fullscreenpicture

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.os.Build




class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var fullIntent : Intent
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.image)
        val btn: Button = findViewById(R.id.btn_image)
        val btn_fullscreen: Button = findViewById(R.id.fullscreen)

        btn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        btn_fullscreen.setOnClickListener {
            fullIntent = Intent(this, FullScreenImage::class.java)
            fullIntent.putExtra("image", imageUri.toString())
            startActivity(fullIntent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }

}
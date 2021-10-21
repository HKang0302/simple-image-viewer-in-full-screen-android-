package com.example.fullscreenpicture

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import java.io.InputStream
import android.graphics.BitmapFactory

import android.graphics.Bitmap




class FullScreenImage : AppCompatActivity() {
    private lateinit var imageInBitMapAfterResize: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)
        fullscreen()

        val extras = intent.extras
        var fullImage : ImageView = findViewById(R.id.fullImage)

        if(extras != null){
            var image_path = extras.getString("image")
            var imageUri = Uri.parse(image_path)
            val imageStream: InputStream? = contentResolver.openInputStream(imageUri)
            var selectedImage = BitmapFactory.decodeStream(imageStream)

            imageInBitMapAfterResize = resizePhoto(selectedImage)
            fullImage.setImageBitmap(imageInBitMapAfterResize)
        }
    }

    fun resizePhoto(bitmap: Bitmap): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, 1920, 1080, false)
    }

    fun fullscreen(){
        val uiOptions = window.decorView.systemUiVisibility
        var newUiOptions = uiOptions

        val isImmersiveModeEnabled = uiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY === uiOptions

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }

        window.decorView.systemUiVisibility = newUiOptions
        //END_INCLUDE (set_ui_flags)

    }
}
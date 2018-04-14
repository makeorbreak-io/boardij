package com.casarder.todopick

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_fullscreen.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {

    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        image = logo_image

        val myanim = AnimationUtils.loadAnimation(this, R.anim.mytransaction)
        image.startAnimation(myanim)

        val i = Intent(this, MainActivity::class.java)
        val timer = object : Thread() {
            override fun run() {
                super.run()
                try {
                    sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    finish()
                    startActivity(i)

                }
            }

        }

        timer.start()
    }

}

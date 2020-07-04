/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.propertyanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.example.android.propertyanimation.R.*


class MainActivity : AppCompatActivity() {

    lateinit var star: ImageView
    lateinit var rotateButton: Button
    lateinit var translateButton: Button
    lateinit var scaleButton: Button
    lateinit var fadeButton: Button
    lateinit var colorizeButton: Button
    lateinit var showerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        star = findViewById(id.star)
        rotateButton = findViewById(id.rotateButton)
        translateButton = findViewById(id.translateButton)
        scaleButton = findViewById(id.scaleButton)
        fadeButton = findViewById(id.fadeButton)
        colorizeButton = findViewById(id.colorizeButton)
        showerButton = findViewById(id.showerButton)

        rotateButton.setOnClickListener {
            rotater()
        }

        translateButton.setOnClickListener {
            translater()
        }

        scaleButton.setOnClickListener {
            scaler()
        }

        fadeButton.setOnClickListener {
            fader()
        }

        colorizeButton.setOnClickListener {
            colorizer()
        }

        showerButton.setOnClickListener {
            shower()
        }
    }

    private fun rotater() {
        ObjectAnimator.ofFloat(
            star,
            View.ROTATION,
            -360.0f,
            0.0f

        ).apply {
            duration = 1000

            disableViewUnderAnimation(rotateButton)

            start()
        }
    }

    private fun translater() {
        ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 200f).apply {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE

            disableViewUnderAnimation(translateButton)

            start()
        }

    }

    private fun scaler() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)

        ObjectAnimator.ofPropertyValuesHolder(star, scaleX, scaleY).apply {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewUnderAnimation(scaleButton)

            start()
        }
    }

    private fun fader() {
        ObjectAnimator.ofFloat(star, View.ALPHA, 0f).apply {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewUnderAnimation(fadeButton)

            start()
        }

    }

    private fun colorizer() {
        ObjectAnimator.ofArgb(
            star.parent,
        "backgroundColor",
        Color.BLACK,
        Color.RED

        ).apply {
            duration = 500
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewUnderAnimation(colorizeButton)

            start()
        }
    }

    private fun shower() {
        val container = star.parent as ViewGroup
        val containerW = container.width
        val containerH = container.height

        var starW = star.width.toFloat()
        var starH = star.height.toFloat()

        val newStar = AppCompatImageView(this).apply {
            setImageResource(R.drawable.ic_star)
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
        }

        container.addView(newStar)
    }

    // Private:

    private fun ObjectAnimator.disableViewUnderAnimation(view: View) {
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }

}

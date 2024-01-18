package com.magdy.mazaadytask.choosePage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.magdy.mazaadytask.R
import com.magdy.mazaadytask.firstPage.FirstPageActivity
import com.magdy.mazaadytask.secondPage.SecondPageActivity

class ChoosePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_page)
        initViews()
    }

    private fun initViews() {
        findViewById<Button>(R.id.firstPage).setOnClickListener {
            startActivity(Intent(this,FirstPageActivity::class.java))
        }
        findViewById<Button>(R.id.secondPage).setOnClickListener {
            startActivity(Intent(this, SecondPageActivity::class.java))
        }
    }

}
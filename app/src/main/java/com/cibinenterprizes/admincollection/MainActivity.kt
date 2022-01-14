package com.cibinenterprizes.admincollection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home_store_list.setOnClickListener {
            startActivity(Intent(this, StoreUpdate::class.java))
        }
        home_report.setOnClickListener {
            startActivity(Intent(this, Report::class.java))
        }
        home_store_map.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }
}
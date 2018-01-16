package home.times.designpatterns.adapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import home.times.designpatterns.R

class AdapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adapter)
    }
}

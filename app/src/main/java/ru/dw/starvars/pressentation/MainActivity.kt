package ru.dw.starvars.pressentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.dw.starvars.R
import ru.dw.starvars.pressentation.view.list.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance()).commit()
        }
    }


}
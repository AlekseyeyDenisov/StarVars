package ru.dw.starvars.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.dw.starvars.R
import ru.dw.starvars.presentation.view.list.ListPeoplesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListPeoplesFragment.newInstance()).commit()
        }
    }


}
package ru.dw.starvars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.dw.starvars.presenter.list.ListPeoplesFragment

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
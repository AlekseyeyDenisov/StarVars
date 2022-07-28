package ru.dw.starvars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.dw.starvars.view.list.ListCharactersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListCharactersFragment.newInstance()).commit()
        }
    }


}
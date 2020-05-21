package com.example.newsletter.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsletter.MainActivity
import com.example.newsletter.R
import com.example.newsletter.adapter.NewsAdapter
import com.example.newsletter.helper.NewsletterDBHelper

class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_create -> {
                val intent = Intent(this@HomeActivity, CreateActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_profile -> {
                val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.menu_kosong -> {
                val intent = Intent(this@HomeActivity, KosongActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_logout -> {
                val intent = Intent(this@HomeActivity, MainActivity::class.java)
                startActivity(intent)
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecycler(){
        val db = NewsletterDBHelper(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_viw)
        val adapter = NewsAdapter(db.getAllNews(), this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
    }
}
package com.example.newsletter.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsletter.R
import com.example.newsletter.helper.News
import com.example.newsletter.helper.NewsletterDBHelper
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.layout_news.*

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        if(intent.getStringExtra("title-extra") == ""){
            btn_insert.setOnClickListener{
                insertNews()
                Toast.makeText(this, "Success Insert Data", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@CreateActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }else{
            title_create.setText(R.string.update_title)
            input_title.setText(intent.getStringExtra("title-extra"))
            input_body.setText(intent.getStringExtra("body-extra"))
            input_date.setText(intent.getStringExtra("date-extra"))
            btn_insert.setOnClickListener {
                update()
                Toast.makeText(this, "Success Insert Data", Toast.LENGTH_SHORT).show()
                val  intent = Intent(this@CreateActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }

    }
    
    fun insertNews()
    {
        val db = NewsletterDBHelper(this)
        val news = News(input_title.text.toString(),
                        input_body.text.toString(),
                        input_date.text.toString())
        db.insertNews(news)
    }

    private fun update(){
        val db = NewsletterDBHelper(this)
        val news = News(input_title.text.toString(),
            input_body.text.toString(),
            input_date.text.toString())
        db.updateNews(news, intent.getIntExtra("id-extra", 0))

    }

    private fun news(toString: String, toString1: String, toString2: String): Any {
        return true
    }
}
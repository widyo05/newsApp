package com.example.newsletter.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NewsletterDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "newsletter.db"
        const val TABLE_NAME = "news"
        const val COLUMN_ID = "_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_BODY = "body"
        const val COLUMN_IMAGE = "image"
        const val COLUMN_DATE = "date"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATES_NEWS_TABLE = ("CREATE TABLE $TABLE_NAME("
                + "$COLUMN_ID INTEGER PRIMARY KEY,"
                + "$COLUMN_TITLE TEXT NOT NULL,"
                + "$COLUMN_BODY TEXT NOT NULL,"
                + "$COLUMN_DATE TEXT NOT NULL)")
        p0?.execSQL(CREATES_NEWS_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    fun insertNews(news: News){
        val values = ContentValues()
        values.put(COLUMN_TITLE, news.title)
        values.put(COLUMN_BODY, news.body)
        values.put(COLUMN_DATE, news.date)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null,values)
        db.close()
    }

    fun getAllNews() : ArrayList<News>{
        val listNews = ArrayList<News>()
        val query = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()) {
            do {
                val news = News(cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3))
                news.id = cursor.getInt(0)
                listNews.add(news)
            }while (cursor.moveToNext())
        }
        db.close()
        return listNews
    }

    fun updateNews(news: News, id:Int){
        val values = ContentValues()
        values.put(COLUMN_TITLE, news.title)
        values.put(COLUMN_BODY, news.body)
        values.put(COLUMN_DATE, news.date)
        val db = this.writableDatabase
        db.update(TABLE_NAME, values, "_id = $id", null)

    }

    fun deleteNews(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "_id = $id", null)
    }
}
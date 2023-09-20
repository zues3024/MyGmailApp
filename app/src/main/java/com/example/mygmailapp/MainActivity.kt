package com.example.mygmailapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var emails: List<Email>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerV = findViewById<RecyclerView>(R.id.emailRecyclerV)

        emails = EmailFetcher.getEmails()
        val emailAdapter = EmailAdapter(emails)
        recyclerV.adapter = emailAdapter
        recyclerV.layoutManager = LinearLayoutManager(this)
        findViewById<Button>(R.id.loadMoreButton).setOnClickListener{
            val newEmails = EmailFetcher.getNext5Emails()
            (emails as MutableList<Email>).addAll(newEmails)
            emailAdapter.notifyDataSetChanged()
        }
    }
}
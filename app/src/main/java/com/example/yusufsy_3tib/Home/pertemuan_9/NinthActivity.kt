package com.example.yusufsy_3tib.Home.pertemuan_9

import android.os.Bundle
import android.view.MenuItem // BARU: untuk onOptionsItemSelected
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.yusufsy_3tib.R
import com.example.yusufsy_3tib.databinding.ActivityNinthBinding

class NinthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        // DIUBAH: pakai apply { } dengan subtitle dan home-as-up, bukan setNavigationIcon
        supportActionBar?.apply {
            title = "Activity Ninth"
            subtitle = "Penerapan Material Komponen"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    // BARU: override yang kurang, menangani klik tombol back di Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
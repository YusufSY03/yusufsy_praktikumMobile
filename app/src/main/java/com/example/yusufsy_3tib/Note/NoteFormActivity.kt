package com.example.yusufsy_3tib.Note

import android.os.Bundle
import android.view.MenuItem // BARU
import android.widget.Toast
import androidx.activity.enableEdgeToEdge // BARU
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat // BARU
import androidx.core.view.WindowInsetsCompat // BARU
import androidx.lifecycle.lifecycleScope
import com.example.yusufsy_3tib.R // BARU
import com.example.yusufsy_3tib.data.AppDatabase
import com.example.yusufsy_3tib.data.entity.NoteEntity
import com.example.yusufsy_3tib.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // BARU
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BARU: insets listener gaya dosen
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // BARU: Toolbar sebagai ActionBar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Note Form"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        db = AppDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            if (title.isBlank() || content.isBlank()) {
                Toast.makeText(
                    this, "Isi semua kolom!", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            binding.btnSaveNote.isEnabled = false
            lifecycleScope.launch {
                try {
                    db.noteDao().insert(
                        NoteEntity(title = title, content = content)
                    )
                    finish()
                } catch (e: Exception) {
                    binding.btnSaveNote.isEnabled = true
                    Toast.makeText(
                        this@NoteFormActivity,
                        "Gagal menyimpan catatan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    // BARU: tombol back di Toolbar
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
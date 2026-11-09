package com.example.yusufsy_3tib.Message.tutorial

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.yusufsy_3tib.R
import com.example.yusufsy_3tib.databinding.ActivityTutorialMessageBinding
import com.example.yusufsy_3tib.tutorial.Tutorial1Fragment
import com.example.yusufsy_3tib.tutorial.Tutorial2Fragment
import com.example.yusufsy_3tib.tutorial.Tutorial3Fragment

class TutorialMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // BARU: setup ViewPager dengan adapter
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialMessageViewPager.adapter = adapter

        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
    }
}
//package com.example.kopiko
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.Toast
//import com.example.kopiko.databinding.ItemMenuBinding
//
//class MenuAdapter(
//    context: Context,
//    private val list: List<MenuKopi>
//) : ArrayAdapter<MenuKopi>(context, 0, list) {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val binding = ItemMenuBinding.inflate(LayoutInflater.from(context), parent, false)
//        val data = list[position]
//
//        val hargaFormat = "Rp" + String.format("%,d", data.tarif).replace(',', '.') + " / cup"
//
//        binding.tvNamaKopi.text = data.nama
//        binding.tvJenisSuhu.text = "${data.jenis} • ${data.suhu}"
//        binding.tvTarifKopi.text = hargaFormat
//
//        // Klik salah satu data -> Tampilkan Toast berisi nama dan harga
//        binding.root.setOnClickListener {
//            Toast.makeText(context, "${data.nama} • $hargaFormat", Toast.LENGTH_SHORT).show()
//        }
//
//        return binding.root
//    }
//}
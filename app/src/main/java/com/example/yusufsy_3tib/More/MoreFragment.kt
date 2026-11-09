package com.example.yusufsy_3tib.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.yusufsy_3tib.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private val dataList = listOf(
        "Kotlin", "Java", "Python", "C++", "JavaScript",
        "Dart", "Swift", "Go", "Ruby", "R",
        "PHP", "C#", "TypeScript", "Shell", "SQL",
        "Perl", "Rust", "Scala", "Haskell", "Lua",
        "Erlang", "Prolog", "Assembly", "Objective-C", "VBA"
    )

    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa untuk Android modern"),
        mapOf("title" to "Java", "desc" to "Bahasa OOP yang populer"),
        mapOf("title" to "Python", "desc" to "Bahasa yang mudah dipahami")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "More"

        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),                        // key yang diambil dari data
            intArrayOf(android.R.id.text1, android.R.id.text2) // tempat tampilnya
        )

        binding.listViewItems.adapter = adapter

        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(requireContext(), "Kamu memilih: $title ($desc)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//package com.example.ruangkarya
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.example.ruangkarya.databinding.FragmentInformasiBinding
//import com.google.android.material.snackbar.Snackbar
//
//class InformasiFragment : Fragment() {
//
//    private var _binding: FragmentInformasiBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentInformasiBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Klik tombol Hubungi Admin -> Munculkan Snackbar dengan aksi "OK"
//        binding.btnHubungiAdmin.setOnClickListener {
//            Snackbar.make(
//                binding.root,
//                getString(R.string.pesan_snackbar_admin),
//                Snackbar.LENGTH_LONG
//            ).setAction(getString(R.string.aksi_ok)) {
//                // Saat tombol OK diklik, Snackbar langsung otomatis hilang
//            }.show()
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
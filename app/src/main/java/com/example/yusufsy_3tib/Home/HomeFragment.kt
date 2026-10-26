package com.example.yusufsy_3tib.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yusufsy_3tib.Home.pertemuan_5.LimaActivity
import com.example.yusufsy_3tib.Home.pertemuan_9.NinthActivity // BARU
import com.example.yusufsy_3tib.LoginActivity
import com.example.yusufsy_3tib.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // Username & password: dari intent kalau ada, kalau tidak dari SharedPreferences
        val user = requireActivity().intent.getStringExtra("username")
            ?: sharedPref.getString("username", null)
        val pass = requireActivity().intent.getStringExtra("password")
            ?: sharedPref.getString("password", null)
        if (user != null) binding.txtUsername.text = user
        if (pass != null) binding.txtPassword.text = pass

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Item dihapus", Snackbar.LENGTH_LONG)
                .setAction("BATAL") {
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                }
                .show()
        }

        binding.btnAlert.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak bisa dikembalikan.")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Hapus") { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)
                .show()
        }

        // DIUBAH: Kembali sekarang logout (finish() di Fragment akan menutup seluruh aplikasi)
        binding.btnKembali.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit().clear().apply()
                    dialog.dismiss()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        binding.btnToLima.setOnClickListener {
            startActivity(Intent(requireContext(), LimaActivity::class.java))
        }

        // BARU: buka NinthActivity
        binding.btnToNinth.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
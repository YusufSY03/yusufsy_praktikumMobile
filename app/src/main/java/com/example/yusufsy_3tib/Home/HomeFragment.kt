package com.example.yusufsy_3tib.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yusufsy_3tib.Home.pertemuan_5.LimaActivity
import com.example.yusufsy_3tib.LoginActivity
import com.example.yusufsy_3tib.R
import com.example.yusufsy_3tib.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = requireActivity().intent.getStringExtra("username")
        val pass = requireActivity().intent.getStringExtra("password")
        val umur = requireActivity().intent.getIntExtra("umur", 0)

        binding.txtUsername.text = user
        binding.txtPassword.setText(pass)

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Item dihapus",
                Snackbar.LENGTH_LONG)
                .setAction("BATAL") {
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                }
                .show()
        }
        binding.btnAlert.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak " +
                        "bisa dikembalikan.")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Hapus") { dialog, _ ->
                    // proses hapus
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
        binding.btnKembali.setOnClickListener {
            //  val intent = Intent(this, LoginActivity::class.java)
            // startActivity(intent)

            requireActivity().finish()
        }
        binding.btnToLima.setOnClickListener {
            val intent = Intent(requireContext(), LimaActivity::class.java)
            startActivity(intent)
        }
    }
}

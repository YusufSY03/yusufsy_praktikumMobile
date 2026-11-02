package com.example.yusufsy_3tib.Message

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.yusufsy_3tib.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = messages[position]

        // Muat gambar dari URL ke ImageView
        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText



        view.setOnClickListener {

            val i = Intent(context, MessageActivity::class.java)
            i.putExtra("sender", data.senderName)
            i.putExtra("pesan",data.messageText)
            context.startActivity(i)
//             fun tampilkanSnackbarPromo(namaPromo: String) {
//            Snackbar.make(
//                binding.root,
//                "Promo $namaPromo digunakan",
//                Snackbar.LENGTH_LONG
//            ).setAction(getString(R.string.aksi_batal)) {
//                Toast.makeText(
//                    requireContext(),
//                    getString(R.string.toast_promo_dibatalkan),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }.show()
//        }
        }

        return view
    }
}
//package com.example.ruangkarya
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.Toast
//import com.example.ruangkarya.databinding.ItemStudioBinding
//import com.google.android.material.snackbar.Snackbar
//
//class StudioAdapter(
//    context: Context,
//    private val list: List<Studio>
//) : ArrayAdapter<Studio>(context, 0, list) {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val binding = ItemStudioBinding.inflate(LayoutInflater.from(context), parent, false)
//        val data = list[position]
//
//        // 1. Tampilkan data ke TextView
//        binding.tvNama.text = data.nama
//        binding.tvFasilitasKapasitas.text = "${data.fasilitas} • ${data.kapasitas}"
//
//        val hargaFormat = "Rp" + String.format("%,d", data.tarif).replace(',', '.') + " / jam"
//        binding.tvTarif.text = hargaFormat
//
//        // 2. Klik biasa -> Toast nama dan tarif
//        binding.root.setOnClickListener {
//            Toast.makeText(context, "${data.nama} • $hargaFormat", Toast.LENGTH_SHORT).show()
//        }
//
//        // 3. Tekan lama (Long Click) -> Snackbar dengan aksi "BATAL" -> Toast
//        binding.root.setOnLongClickListener { view ->
//            Snackbar.make(view, "${data.nama} ditandai", Snackbar.LENGTH_LONG)
//                .setAction("BATAL") {
//                    Toast.makeText(context, "Tanda dibatalkan", Toast.LENGTH_SHORT).show()
//                }
//                .show()
//            true // Wajib return true agar tidak memicu click biasa!
//        }
//
//        return binding.root
//    }
//}
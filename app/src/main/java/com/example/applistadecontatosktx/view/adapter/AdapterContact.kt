package com.example.applistadecontatosktx.view.adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applistadecontatosktx.R
import com.example.applistadecontatosktx.model.ContactWithPhoto
import com.example.applistadecontatosktx.view.cadastro.ActivityRegisterContact
import com.example.applistadecontatosktx.view.main.MainViewModel

class AdapterContact(
    private val context: Context,
    private val viewModel: MainViewModel,
    private var listContact: ArrayList<ContactWithPhoto>
) :
    RecyclerView.Adapter<AdapterContact.AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AdapterViewHolder {

        val view =
            LayoutInflater.from(context).inflate(R.layout.adapter_item_contact, parent, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {

        val item = listContact[position]
        val length = item.photo.data?.size ?: 0

        holder.icon.setImageBitmap(BitmapFactory.decodeByteArray(item.photo.data, 0, length))
        holder.name.text = "${item.contact}"
        holder.phone.text = item.contact.phone

        holder.btnEdit.setOnClickListener {

            val intent = Intent(it.context, ActivityRegisterContact::class.java)
            intent.putExtra("contact", item.contact)

            it.context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener {
            viewModel.deleteContact(item)
        }

    }

    override fun getItemCount(): Int {
        return listContact.size
    }

    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var icon: ImageView = itemView.findViewById(R.id.adapter_icon_profile)
        var name: TextView = itemView.findViewById(R.id.adapter_name)
        var phone: TextView = itemView.findViewById(R.id.adapter_phone)

        var btnEdit: ImageView = itemView.findViewById(R.id.adapter_ic_edit_btn)
        var btnDelete: ImageView = itemView.findViewById(R.id.adapter_ic_delete_btn)

    }

    fun getListContact(): List<ContactWithPhoto> {
        return listContact
    }

}
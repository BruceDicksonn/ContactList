package com.example.applistadecontatosktx.view.cadastro

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.applistadecontatosktx.R
import com.example.applistadecontatosktx.databinding.ActivityRegisterContactBinding
import com.example.applistadecontatosktx.model.Contact
import com.example.applistadecontatosktx.model.ContactWithPhoto
import com.example.applistadecontatosktx.view.main.MainActivity

class ActivityRegisterContact : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterContactBinding
    private lateinit var viewModel: RegisterContactViewModel

    private var contactID: Long = 0
    private lateinit var objInternal: ContactWithPhoto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            contactID = (intent.getSerializableExtra("contact") as Contact).id ?: 0
        }

        viewModel = RegisterContactViewModel(application, contactID)
        initEventListeners()

    }

    override fun onResume() {
        super.onResume()
        observeComponents()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == R.id.icon_profile) {

            if (resultCode == RESULT_OK) {

                val bitmap: Bitmap?
                val uri = data?.data

                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)

                if (bitmap != null) {
                    viewModel.icon.value = bitmap
                }

            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_register, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.saveContact -> {
                if(viewModel.checkContactAndSave()) finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeComponents() {

        viewModel.getObjInternal().observe(this) {
            objInternal = it
        }

        viewModel.getIcon().observe(this) {
            binding.iconProfile.setImageBitmap(it)
        }

        viewModel.getFirstName().observe(this) {
            binding.name.setText(it)
        }

        viewModel.getPhoneticName().observe(this) {
            binding.phonetic.setText(it)
        }

        viewModel.getLastName().observe(this) {
            binding.lastName.setText(it)
        }

        viewModel.getCompany().observe(this) {
            binding.company.setText(it)
        }

        viewModel.getPhone().observe(this) {
            binding.phoneNumber.setText(it)
        }

    }

    private fun initEventListeners() {

        binding.iconProfile.setOnClickListener {
            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                ),
                R.id.icon_profile
            )
        }

        binding.name.addTextChangedListener(createTextWatcher(R.id.name))
        binding.phonetic.addTextChangedListener(createTextWatcher(R.id.phonetic))
        binding.lastName.addTextChangedListener(createTextWatcher(R.id.lastName))
        binding.company.addTextChangedListener(createTextWatcher(R.id.company))
        binding.phoneNumber.addTextChangedListener(createTextWatcher(R.id.phone_number))

    }

    private fun createTextWatcher(editTextId: Int): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onTextChange(editTextId, text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
    }


}
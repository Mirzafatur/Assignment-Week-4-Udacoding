package com.example.mahasiswaappmirzafaturrachman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mahasiswaappmirzafaturrachman.model.action.ResponseAction
import com.example.mahasiswaappmirzafaturrachman.model.data.DataItem
import com.example.mahasiswaappmirzafaturrachman.network.NetworkModule
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        supportActionBar?.title = "Input Form"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = intent.getParcelableExtra<DataItem>("data")
        if (data != null){
            edtNama.setText(data.mahasiswaNama)
            edtNohp.setText(data.mahasiswaNohp)
            edtAlamat.setText(data.mahasiswaAlamat)

            btnSimpan.text = "Update"
        }

        when(btnSimpan.text){
            "Update" ->{
                btnSimpan.setOnClickListener {
                    updateData(data?.idMahasiswa, edtNama.text.toString(), edtNohp.text.toString(), edtAlamat.text.toString())
                }
            }
            else -> {
                btnSimpan.setOnClickListener {
                    inputData(edtNama.text.toString(), edtNohp.text.toString(), edtAlamat.text.toString())
                }
            }
        }

        btnBatal.setOnClickListener {
            finish()
        }
    }

    private fun inputData(nama : String?, nohp : String?, alamat : String?) {
        val input = NetworkModule.service().insertData(nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction>{
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()

            }
        })
    }

    private fun updateData(id : String?, nama : String?, nohp : String?, alamat : String?) {
        val update = NetworkModule.service().updateData(id ?: "" ,nama ?: "", nohp ?: "", alamat ?: "")
        update.enqueue(object : Callback<ResponseAction>{
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Data berhasil di update", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
package com.example.mahasiswaappmirzafaturrachman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mahasiswaappmirzafaturrachman.adapter.MahasiswaAdapter
import com.example.mahasiswaappmirzafaturrachman.model.action.ResponseAction
import com.example.mahasiswaappmirzafaturrachman.model.data.DataItem
import com.example.mahasiswaappmirzafaturrachman.model.data.ResponseMahasiswa
import com.example.mahasiswaappmirzafaturrachman.network.NetworkModule
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Mahasiswa App"

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            startActivity(intent)
        }
        showData()
    }



    private fun showData() {
        val mahasiswa = NetworkModule.service().getData()
        mahasiswa.enqueue(object : Callback<ResponseMahasiswa>{

            override fun onFailure(call: Call<ResponseMahasiswa>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseMahasiswa>,
                response: Response<ResponseMahasiswa>
            ) {
                if (response.isSuccessful){
                    val item = response.body()?.data
                    val adapter = MahasiswaAdapter(item, object : MahasiswaAdapter.OnItemClickCallBack{

                        override fun onItemClicked(item: DataItem?) {
                            val intent = Intent(applicationContext, InputActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)
                        }

                        override fun delete(item: DataItem?) {
                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Hapus Data")
                                setMessage("Anda yakin ingin menghapus data ini?")
                                setPositiveButton("Hapus"){dialogInterface, i ->
                                    deleteData(item?.idMahasiswa)
                                    dialogInterface.dismiss()
                                }
                                setNegativeButton("Batal") { dialogInterface, i ->
                                    dialogInterface.dismiss()
                                }
                            }.show()
                        }
                    })
                    recycler_view.adapter = adapter
                }
            }
        })
        //bantu buka databasenya mas
    }

    private fun deleteData(idMahasiswa: String?) {

        val delete = NetworkModule.service().deleteData(idMahasiswa ?: "")
        delete.enqueue(object : Callback<ResponseAction>{

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                showData()
            }
        })

    }

    override fun onResume() {
        super.onResume()
        showData()
    }


}
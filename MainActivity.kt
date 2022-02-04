package com.example.mobilprovize

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {
    var data: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mobilprovizeAsyncTask(this).execute()
    }

    fun calistir(r: String) {
        var sonuc = Gson().fromJson(r, datam::class.java)
        Toast.makeText(this, r, Toast.LENGTH_LONG).show()

        var newlist: MutableList<String> = mutableListOf()
        var kodlist: MutableList<Int> = mutableListOf()
        var uygula = ArrayAdapter(this, android.R.layout.simple_list_item_1, newlist)
        list_view.adapter = uygula
        var i=0;
        for (bilgi:kiyafetler in sonuc.dolabim){
            newlist.add("ADET :"+bilgi.adet+"\n"+"\n"+"TÜR :"+bilgi.tur+"\n"+"\n"+"FİYAT ORTALAMASI :"+bilgi.fiyat_ort+"\n"+"\n"+"EN ÇOK BULUNAN MARKA :"+ bilgi.en_cok_olan_marka)

        }

        list_view.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val id = kodlist[position]
            val intent = Intent(this, datam::class.java).apply {
                putExtra("id", id)
            }

            var kod:Int=0
            startActivityForResult(intent, kod)
        }

    }
}
class mobilprovizeAsyncTask(etkinlik: MainActivity) : AsyncTask<Unit, Unit, String>() {

    val etkinlik: MainActivity? = etkinlik

    override fun doInBackground(vararg params: Unit?): String? {

        try {
            val apiResponse =
                URL("https://run.mocky.io/v3/e90253a0-f364-4613-ab0f-89f114967679").readText()
            return apiResponse
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)


        etkinlik?.calistir(result!!);

    }

}
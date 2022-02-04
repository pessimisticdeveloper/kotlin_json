package com.example.mobilprovize

data class datam(val adi:String,val soyadi:String, val dolabim: List<kiyafetler> )
data class kiyafetler(val adet:String, val tur:String, val fiyat_ort:String, val en_cok_olan_marka:String)

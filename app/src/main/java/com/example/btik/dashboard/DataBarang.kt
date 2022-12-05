package com.example.btik.dashboard

class DataBarang {
    //set data
    var namaBarang : String? = null
    var hargaBarang : String? = null
    var imgBarang : String? = null
    var info : String? = null

    constructor(namaBarang: String?, hargaBarang: String?, imgBarang : String?, info : String?){
        this.namaBarang = namaBarang
        this.hargaBarang = hargaBarang
        this.imgBarang = imgBarang
        this.info = info
    }
}
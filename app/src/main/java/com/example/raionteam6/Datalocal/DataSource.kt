package com.example.raionteam6.Datalocal

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import com.example.raionteam6.R

class DataSource() {

    public fun loadToko(): List<TokoMakanan> {
        return listOf<TokoMakanan>(
            TokoMakanan("Moon Bakery ", "Jln. SIGMA", R.drawable.rm_padang),
            TokoMakanan("Bread Dog ", "Jln. Mundur", R.drawable.moon_bakery),
            TokoMakanan("Nyam_Nyam ", "Jln. Soekarno hatta", R.drawable.rm_padang),
            TokoMakanan("Tahu Pakde", "Jln. Candi mendoet", R.drawable.moon_bakery)
        )
    }

}

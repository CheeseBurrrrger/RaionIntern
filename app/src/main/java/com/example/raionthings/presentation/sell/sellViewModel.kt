package com.example.raionthings.presentation.sell

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raionthings.data.remote.dto.supabase
import com.example.raionthings.presentation.login.UserData
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class sellViewModel:ViewModel() {

    private val db = Firebase.firestore
    private val profileUser = db.collection("Profile")
    private var currentJob: Job? = null
    public override fun onCleared() {
        super.onCleared()
        currentJob?.cancel()
    }
    fun addDagang(
        context: Context,
        namaProduk: String,
        hargaProduk: Int,
        dateCreated: Timestamp,
        dateExpired: Timestamp,
        stokProduk: Int,
        userData: UserData,
        productPictureUrl:String
    ) {
        val dagangCollection = profileUser.document(userData.userId).collection("Dagang")
        val newProd = dagangCollection.document("${userData.userId}$namaProduk")
        val prodId = newProd.id
        dagangCollection.document("${userData.userId}$namaProduk").set(
            UserProduk(
                namaProduk = namaProduk,
                hargaProduk = hargaProduk,
                dateCreated = dateCreated,
                dateExpired = dateExpired,
                sellerId = userData.userId,
                stokProduk = stokProduk,
                produkId = prodId,
                productPictureUrl = productPictureUrl
            )
        ).addOnSuccessListener { documentRef ->
            Log.d("cekLogSell", "Produk berhasil ditambahkan dengan ID: $prodId")
            Toast.makeText(context, "Produk berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener { e ->
                Log.e("cekLogSell", "Gagal memperbarui produkId", e)
                Toast.makeText(context, "Gagal memperbarui produk ID", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener { e ->
                Log.e("cekLogSell", "Gagal menambahkan produk", e)
                Toast.makeText(context, "Gagal menambahkan produk. Coba lagi!", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    fun getAllDagangByUser(
        userData: UserData,
        onSuccess: (List<UserProduk>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        Log.d("cek","masuk funct get all")
        profileUser
            .document(userData.userId)
            .collection("Dagang")
            .get()
            .addOnSuccessListener { documents ->
                val dagangList = documents.mapNotNull { it.toObject(UserProduk::class.java) }
                Log.d("blok sukses","entah cok")
                onSuccess(dagangList)
            }
            .addOnFailureListener { e ->
                onFailure(e)
                Log.d("blok gagal","entah cok")

            }
    }

    fun getSellerFromUserData(
        userData: UserData,
        onSuccess: (List<UserProduk>) -> Unit,
        onFailure: (Exception) -> Unit){
        db.collectionGroup("Dagang")
            .whereEqualTo("sellerId", userData.userId)
            .get()
            .addOnSuccessListener { documents ->
                val dagangList = documents.mapNotNull { it.toObject(UserProduk::class.java) }
                onSuccess(dagangList)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }
    fun getDagangItem(
        userData: UserData,
        productName: String,
        onSuccess: (UserProduk?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val productId = "${userData.userId}$productName"

        profileUser
            .document(userData.userId)
            .collection("Dagang")
            .document(productId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val dagangItem = document.toObject(UserProduk::class.java)
                    onSuccess(dagangItem)
                } else {
                    onSuccess(null) // Product not found
                }
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun getAllDagangExceptSeller(
        userData: UserData,
        onSuccess: (List<UserProduk>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db
            .collectionGroup("Dagang")
            .whereNotEqualTo("sellerId", userData.userId)
            .get()
            .addOnSuccessListener { documents ->
                val dagangList = documents.mapNotNull { it.toObject(UserProduk::class.java) }
                onSuccess(dagangList)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }
    fun getSpecificDagangItemsByNameExceptMe(
        userData: UserData,
        productName: String,
        onSuccess: (List<UserProduk>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db
            .collectionGroup("Dagang") // Search across all users' "Dagang" collections
            .whereEqualTo("namaProduk", productName) // Filter by product name
            .whereNotEqualTo("sellerId", userData.userId) // Exclude current user
            .get()
            .addOnSuccessListener { documents ->
                val products = documents.mapNotNull { it.toObject(UserProduk::class.java) }
                onSuccess(products)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun getAllSeller(
        userData: UserData,
        onSuccess: (List<UserData>) -> Unit,
        onFailure: (Exception) -> Unit
    ){
        profileUser.whereNotEqualTo("userId",userData.userId)
            .get()
            .addOnSuccessListener { documents ->
                val pedagangs = documents.mapNotNull { it.toObject(UserData::class.java)}
                onSuccess(pedagangs)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }

    }
    fun uploadProductPicture(userData: UserData,userProduk: UserProduk, byteArray: ByteArray, context: Context){
        viewModelScope.launch {
            try {
                val bucket = supabase.client.storage["dagang"]
                bucket.upload("${userProduk.produkId}.jpg",byteArray,true)
                UserProduk(
                    productPictureUrl = bucket.publicUrl("${userData.userId}${userProduk.namaProduk}.jpg"),
                    produkId = userProduk.produkId,
                    hargaProduk = userProduk.hargaProduk,
                    namaProduk = userProduk.namaProduk,
                    dateExpired = userProduk.dateExpired,
                    dateCreated = userProduk.dateCreated,
                    stokProduk = userProduk.stokProduk,
                    sellerId = userProduk.sellerId
                )
                Log.d("UploadPic", "success i guess")
                profileUser.document(userProduk.produkId).update("productPictureUrl",
                    bucket.publicUrl("${userProduk.produkId}.jpg")
                ).addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "product picture uploaded!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }catch (e:Exception){
                Log.d("UploadPic", "fail: ${e.message}"
                )
            }
        }
    }
    fun singleProductPicture(userData: UserData, namaProduk: String,byteArray: ByteArray, context: Context ){
        viewModelScope.launch {
            val bucket = supabase.client.storage["dagang"]
            bucket.upload("${userData.userId}$namaProduk.jpg",byteArray,true)
        }
    }
    fun readProductPicture(
        userData: UserData,
        namaProduk: String,
        onImageUrlRetrieved: (url: String) -> Unit,){
        viewModelScope.launch {
            try {
                val bucket = supabase.client.storage["dagang"]
                val url = bucket.publicUrl("${userData.userId}$namaProduk.jpg")
                onImageUrlRetrieved(url)
            }catch (e:Exception){

            }
        }
    }

}
//-----------------------------------------------
//TRASH
//
//    db.collection('users').doc(this.username).collection('booksList').add({
//        password: this.password,
//        name: this.name,
//        rollno: this.rollno
//    })
//    fun addDagang (
////    userProduk: UserProduk,
//    context: Context,
//    namaProduk:String,
//    hargaProduk: Int,
//    dateCreated: Timestamp,
//    dateExpired:Timestamp,
////    productPictureUrl: String,
////    isSold: Boolean,
//    stokProduk: Int,
//    userData:UserData){
//        val dagang = run {
//            UserProduk(
//
//                namaProduk = namaProduk,
//                hargaProduk = hargaProduk,
//                dateCreated = dateCreated,
//                dateExpired = dateExpired,
////                productPictureUrl = productPictureUrl,
//                sellerId = userData.userId,
////                isSold = isSold,
//                stokProduk = stokProduk
//            )
//        }
//
//    Log.d("cekLogSell", "Dagang: $dagang\n ")
//    profileUser.document(userData.userId).collection("Dagang").add(dagang)
//        .addOnSuccessListener {  Toast.makeText(
//            context,
//            "lancar dek",
//            Toast.LENGTH_SHORT
//        ).show()}
//    }

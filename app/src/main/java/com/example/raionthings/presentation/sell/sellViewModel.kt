package com.example.raionthings.presentation.sell

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.raionthings.presentation.login.UserData
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class sellViewModel:ViewModel() {

    private val db = Firebase.firestore
    private val profileUser = db.collection("Profile")
    fun addDagang(
        context: Context,
        namaProduk: String,
        hargaProduk: Int,
        dateCreated: Timestamp,
        dateExpired: Timestamp?,
        stokProduk: Int,
        userData: UserData
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
                produkId = prodId
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
        profileUser
            .document(userData.userId)
            .collection("Dagang")
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
        val productId = "${userData.userId}$productName" // The custom document ID

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

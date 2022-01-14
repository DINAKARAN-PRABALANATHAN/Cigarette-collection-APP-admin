package com.cibinenterprizes.admincollection

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.cibinenterprizes.admincollection.Model.CollectionId
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_update_report.*
import java.time.LocalDate
import java.util.*

class UpdateReport : AppCompatActivity() {

    lateinit var storeId: String
    var database = FirebaseDatabase.getInstance().reference
    lateinit var date: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_report)

        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val myLd = LocalDate.of(year, month, day)
        date = myLd.toString()

        storeId = getIntent().extras?.get("Store ID").toString()

        update_report_back_botton.setOnClickListener {
            finish()
        }

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var userName =
                    snapshot.child("User Details").child(storeId)
                        .child("username").getValue().toString()
                var storeName =
                    snapshot.child("User Details").child(storeId)
                        .child("storeName").getValue().toString()
                var mobile =
                    snapshot.child("User Details").child(storeId)
                        .child("mobile").getValue().toString()
                var collection =
                    snapshot.child("User Details").child(storeId)
                        .child("totalCollected").getValue().toString()
                var intCollected = collection.toInt()
                var amount =
                    snapshot.child("User Details").child(storeId)
                        .child("totalAmount").getValue().toString()
                var intAmount = amount.toInt()

                update_report_owner.setText(userName)
                update_report_store.setText(storeName)
                update_report_mobile.setText(mobile)
                update_report_update.setOnClickListener {
                    var newCollected = update_report_collected.text.toString().trim()
                    var newAmount = update_report_amount.text.trim().toString()
                    var intNewCollected = newCollected.toInt()
                    var intNewAmount = newAmount.toInt()
                    var totalCollected = intCollected+intNewCollected
                    var totalAmount = intAmount+intNewAmount
                    database.child("User Details").child(storeId).child("totalCollected").setValue(totalCollected).addOnCompleteListener {
                        database.child("User Details").child(storeId).child("totalAmount").setValue(totalAmount).addOnCompleteListener {
                            database.child("User Details").child(storeId).child("Report").child(date).setValue(CollectionId(newCollected,newAmount,date)).addOnCompleteListener {
                                    Toast.makeText(this@UpdateReport,"Updated",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
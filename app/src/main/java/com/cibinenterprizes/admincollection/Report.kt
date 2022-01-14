package com.cibinenterprizes.admincollection

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibinenterprizes.admincollection.Model.ReportDetails
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_report.*
import java.time.LocalDate
import java.util.*

class Report : AppCompatActivity() {

    private lateinit var recview: RecyclerView
    lateinit var storeId: String
    lateinit var date: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        recview = findViewById(R.id.work_report_recyclerview)
        recview.setLayoutManager(LinearLayoutManager(this))

        work_report_back_botton.setOnClickListener {
            finish()
        }
    }
    override fun onStart() {
        super.onStart()

        val options: FirebaseRecyclerOptions<ReportDetails> = FirebaseRecyclerOptions.Builder<ReportDetails>()
            .setQuery(FirebaseDatabase.getInstance().getReference().child("User Details"), ReportDetails::class.java).build()

        var adapter: FirebaseRecyclerAdapter<ReportDetails, myviewholder> =object: FirebaseRecyclerAdapter<ReportDetails, myviewholder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
                var view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_report, parent, false)
                return myviewholder(view)
            }

            override fun onBindViewHolder(holder: myviewholder, position: Int, model: ReportDetails) {

                holder.storeId?.setText(model.StoreId)
                holder.owner?.setText(model.Username)
                holder.store?.setText(model.StoreName)
                holder.mobile?.setText(model.Mobile)

                holder.itemView.setOnClickListener {
                    val storeId = getRef(position).getKey()
                    val intent = Intent(this@Report,FullReport::class.java)
                    intent.putExtra("Store ID",storeId)
                    startActivity(intent)
                }
            }

        }
        recview.setAdapter(adapter)
        adapter.startListening()
    }
    class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var storeId: TextView? = null
        var owner: TextView? = null
        var store: TextView? = null
        var mobile: TextView? = null

        init {

            storeId= itemView.findViewById(R.id.work_report_id)
            owner= itemView.findViewById(R.id.work_report_owner)
            store= itemView.findViewById(R.id.work_report_store)
            mobile= itemView.findViewById(R.id.work_report_mobile)
            super.itemView
        }
    }
}
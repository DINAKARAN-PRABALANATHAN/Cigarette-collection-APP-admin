package com.cibinenterprizes.admincollection

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
import com.cibinenterprizes.admincollection.Model.CollectionId
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_full_report.*
import java.time.LocalDate
import java.util.*

class FullReport : AppCompatActivity() {

    private lateinit var recview: RecyclerView
    lateinit var storeId: String
    lateinit var date: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_report)

        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val myLd = LocalDate.of(year, month, day)
        date = myLd.toString()
        storeId = getIntent().extras?.get("Store ID").toString()

        recview = findViewById(R.id.report_recyclerview)
        recview.setLayoutManager(LinearLayoutManager(this))

        report_back_botton.setOnClickListener {
            finish()
        }
    }
    override fun onStart() {
        super.onStart()

        val options: FirebaseRecyclerOptions<CollectionId> = FirebaseRecyclerOptions.Builder<CollectionId>()
            .setQuery(FirebaseDatabase.getInstance().getReference().child("User Details").child(storeId).child("Report"), CollectionId::class.java).build()

        var adapter: FirebaseRecyclerAdapter<CollectionId, myviewholder> =object: FirebaseRecyclerAdapter<CollectionId, myviewholder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
                var view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_full_report, parent, false)
                return myviewholder(view)
            }

            override fun onBindViewHolder(holder: myviewholder, position: Int, model: CollectionId) {

                holder.date?.setText(model.Date)
                holder.collected?.setText(model.Collected)
                holder.amount?.setText(model.Amount)


            }

        }
        recview.setAdapter(adapter)
        adapter.startListening()
    }
    class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var date: TextView? = null
        var collected: TextView? = null
        var amount: TextView? = null

        init {

            date= itemView.findViewById(R.id.report_date)
            collected= itemView.findViewById(R.id.report_collected)
            amount= itemView.findViewById(R.id.report_amount)
            super.itemView
        }
    }
}
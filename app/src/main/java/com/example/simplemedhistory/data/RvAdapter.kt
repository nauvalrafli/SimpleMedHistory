package com.example.simplemedhistory.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemedhistory.R
import com.example.simplemedhistory.data.model.MedHistory

class RvAdapter(val list: List<MedHistory>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.title)
        val loc : TextView = itemView.findViewById(R.id.location)
        val delete : ImageButton = itemView.findViewById(R.id.btDelete)
        val parent : ConstraintLayout = itemView.findViewById(R.id.rvHistory)

        fun bind(medhis: MedHistory) {
            parent.setOnClickListener { onClicked?.onClicked(medhis) }
            delete.setOnClickListener { onClicked?.onDelete(medhis) }
        }
    }

    //set onClick variable
    private var onClicked : IOonClicked? = null

    //set interface
    interface  IOonClicked {
        fun onClicked(medhis: MedHistory)
        fun onDelete(medhis: MedHistory)
    }

    fun callableOnClick(onClicked : IOonClicked) {
        this.onClicked = onClicked
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_history, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].disease
        holder.loc.text = list[position].location

        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}


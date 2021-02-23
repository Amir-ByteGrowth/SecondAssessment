package com.example.secondassessment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmpAdaptor(val list: ArrayList<EmpModel>, itemClickListner: ItemClickListner) :
    RecyclerView.Adapter<EmpAdaptor.MovieViewHolder>() {


    var itemClickListner: ItemClickListner = itemClickListner

    interface ItemClickListner {
        fun onItemClick(empModel: EmpModel)
        fun delEmp(empModel: EmpModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: EmpModel = list[position]
        holder.bind(movie, itemClickListner)
    }

    fun clearData() {
        list.clear()
        notifyDataSetChanged()
    }

    fun addData(listItems: ArrayList<EmpModel>) {

        var size = this.list!!.size
        list!!.addAll(listItems)
        var sizeNew = this.list!!.size
        notifyItemRangeChanged(size, sizeNew)
    }

    override fun getItemCount(): Int = list.size
    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.rec_item, parent, false)) {
        private var tvName: TextView? = null
        private var tvEmail: TextView? = null
        private var tvEdit: TextView? = null
        private var tvDel: TextView? = null


        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvEmail = itemView.findViewById(R.id.tvEmail)
            tvEdit = itemView.findViewById(R.id.tvEdit)
            tvDel = itemView.findViewById(R.id.tvDel)
        }

        fun bind(movie: EmpModel, itemClickListner: ItemClickListner) {
            tvName?.text = movie.name
            tvEmail?.text = movie.email
            tvEdit!!.setOnClickListener(View.OnClickListener { itemClickListner.onItemClick(empModel = movie) })
            tvDel!!.setOnClickListener(View.OnClickListener { itemClickListner.delEmp(empModel = movie) })
        }

    }
}
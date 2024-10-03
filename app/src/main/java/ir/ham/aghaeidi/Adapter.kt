package ir.ham.aghaeidi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.ham.aghaeidi.databinding.ItemBinding

val listOfData = listOf("FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD","FARZAD")

class Adapter:RecyclerView.Adapter<Adapter.ViewHolder>() {

    var data = mutableListOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){
            binding.tv.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
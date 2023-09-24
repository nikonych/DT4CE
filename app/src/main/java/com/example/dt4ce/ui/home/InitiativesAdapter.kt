package com.example.dt4ce.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dt4ce.databinding.ItemInitiativeBinding
import com.example.dt4ce.network.model.Content
import com.example.dt4ce.util.startNewActivity

class InitiativesAdapter(
    private var list: List<Content>,
    private val onDetailClick: OnDetailClick
) : RecyclerView.Adapter<InitiativesAdapter.InitiativesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InitiativesViewHolder {
        return InitiativesViewHolder(
            ItemInitiativeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: InitiativesViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    inner class InitiativesViewHolder(private val binding: ItemInitiativeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(content: Content, position: Int) {
            with(binding) {
                tvTitle.text = content.title
                tvDescription.text = content.description

                binding.btnDetails.setOnClickListener {
                    onDetailClick.onClick(content.id)
                }
            }


        }

    }
}
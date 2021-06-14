package com.abdhilabs.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.entity.ItemClickListener
import com.abdhilabs.coreandroid.utils.extension.setImageFromUrl
import com.abdhilabs.coreandroid.utils.formatter.DateTimeFormatter
import com.abdhilabs.home.databinding.ItemGameBinding

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    private var listener: ItemClickListener? = null

    private val games = mutableListOf<Game>()

    fun fillGames(games: List<Game>) {
        this.games.clear()
        this.games.addAll(games)
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(private val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Game, listener: ItemClickListener?) {
            with(binding) {
                imgBanner.setImageFromUrl(item.backgroundImage)
                tvTitle.text = item.name
                tvReleaseDate.text = DateTimeFormatter.getDateFromString(item.released)
                root.setOnClickListener { listener?.onItemClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGameBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(games[position], listener)
    }

    override fun getItemCount(): Int = games.size
}

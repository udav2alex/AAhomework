package ru.gressor.movies_browser.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gressor.movies_browser.R
import ru.gressor.movies_browser.entity.Actor

class ActorsRVAdapter(
    var actors: List<Actor>
): RecyclerView.Adapter<ActorsRVAdapter.ActorsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ActorsViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_actor, parent, false)
    )

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val castPhoto: ImageView = itemView.findViewById(R.id.iv_cast_photo)
        private val castName: TextView = itemView.findViewById(R.id.tv_cast_name)

        fun bind(actor: Actor) {
            actor.let {
                castName.text = it.name

                Glide.with(context)
                    .load(
                        context.resources
                            .getIdentifier(it.avatar, "drawable", context.packageName)
                    )
                    .into(castPhoto)
            }
        }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context
package nur.toza.geminiaichat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nur.toza.geminiaichat.databinding.ItemChatBinding


class MessageAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val messages = mutableListOf<Pair<String,Int>>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMessages(message : List<Pair<String,Int>>){
        this.messages.apply {
            clear()
            addAll(message)
        }
        notifyDataSetChanged()
    }

    companion object {
        const val VIEW_TYPE_USER = 1
        const val VIEW_TYPE_GEMINI = 2
    }
    inner class UserViewHolder(private val binding: ItemChatBinding):ViewHolder(binding.root){
        fun bind(message: Pair<String, Int>){
            binding.rightChatView.visibility = View.VISIBLE
            binding.rightChatTextView.text = message.first
            binding.leftChatView.visibility = View.GONE
            binding.profileImage.visibility = View.GONE
        }
    }

    inner class GeminiViewHolder(private val binding: ItemChatBinding):ViewHolder(binding.root){
        fun bind(message: Pair<String, Int>){
            binding.rightChatView.visibility = View.GONE
            binding.leftChatTextView.text = message.first
            binding.leftChatView.visibility = View.VISIBLE
            binding.profileImage.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            VIEW_TYPE_USER->{
                val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                UserViewHolder(binding)
            }
            VIEW_TYPE_GEMINI->{
                val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                GeminiViewHolder(binding)
            }
            else -> throw IllegalAccessException("invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        when(holder.itemViewType){
            VIEW_TYPE_USER->{
                val userViewHolder = holder as UserViewHolder
                userViewHolder.bind(message)
            }

            VIEW_TYPE_GEMINI->{
                val geminiViewHolder = holder as GeminiViewHolder
                geminiViewHolder.bind(message)
            }

        }
    }

    override fun getItemCount() = messages.size

    override fun getItemViewType(position: Int): Int {
        return  messages[position].second
    }
}
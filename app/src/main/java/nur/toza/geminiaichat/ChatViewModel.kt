package nur.toza.geminiaichat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import kotlinx.coroutines.launch

class ChatViewModel(geminiPro : GenerativeModel) : ViewModel() {
    private var _messageResponse = MutableLiveData<GenerateContentResponse>()
    val messageResponse : LiveData<GenerateContentResponse> get() = _messageResponse

    private val geminiChat : Chat = geminiPro.startChat()

    fun geminiChat(message:String){
        viewModelScope.launch {
            _messageResponse.value = geminiChat.sendMessage(message)
        }
    }

    class ChatViewModelFactory(private val geminiPro : GenerativeModel) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
                return ChatViewModel(geminiPro) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
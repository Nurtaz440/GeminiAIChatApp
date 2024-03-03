package nur.toza.geminiaichat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import nur.toza.geminiaichat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var  _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = ChatFragment()

        val ft : FragmentTransaction = supportFragmentManager.beginTransaction()

        ft.replace(R.id.ft,fragment)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
    }
}
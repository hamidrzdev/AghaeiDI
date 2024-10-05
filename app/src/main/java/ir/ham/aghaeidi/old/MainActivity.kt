package ir.ham.aghaeidi.old

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import ir.ham.aghaeidi.databinding.ActivityMainBinding
import kotlinx.coroutines.Job

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var job: Job? = null
    private val TAG = MainActivity::class.simpleName
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.i(TAG, "onCreate: $it")
        }.addOnFailureListener {
            Log.e(TAG, "onCreate: $it")
        }


        FragmentFactory()

//        val human = Human(id = 0, name = "HamidReza", age = 22)
//        Log.i(TAG, "onCreate: original human: $human")
//
//        val copyHuman = human.copy(name = "Farzad")
//        Log.i(TAG, "onCreate: copyHuman: $copyHuman")

//        val email1 = NotificationFactory.createNotification(NotificationType.EMAIL)
//        val email2 = NotificationFactory.createNotification(NotificationType.EMAIL)
//
//        Log.i(TAG, "onCreate: email1: $email1")
//        Log.i(TAG, "onCreate: email2: $email2")
    }


}
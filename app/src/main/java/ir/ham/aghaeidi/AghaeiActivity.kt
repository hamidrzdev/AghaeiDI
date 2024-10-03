package ir.ham.aghaeidi

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ir.ham.aghaeidi.databinding.ActivityAghaeiBinding

class AghaeiActivity:AppCompatActivity() {
    private val TAG = AghaeiActivity::class.java.simpleName
    private lateinit var binding:ActivityAghaeiBinding

    private var isContainerAStackCleared = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAghaeiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragmentsAtTop(FirstFragment.newInstance("1"))
        loadFragmentsAtBottom(FirstFragment.newInstance("1"))
        loadFragmentsAtTop(FirstFragment.newInstance("2"))
        loadFragmentsAtBottom(FirstFragment.newInstance("2"))
        loadFragmentsAtTop(FirstFragment.newInstance("3"))
        loadFragmentsAtBottom(FirstFragment.newInstance("3"))

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.i(TAG, "handleOnBackPressed: isContainerAStackCleared: $isContainerAStackCleared")
                if (!isContainerAStackCleared) {
                    // Clear fragments from ContainerA's stack first
                    if (supportFragmentManager.backStackEntryCount > 0) {
                        clearSpecificBackStack("ContainerAStack");
                    } else {
                        isContainerAStackCleared = true;
                    }
                } else {
                    // Clear fragments from ContainerB's stack next
                    if (supportFragmentManager.backStackEntryCount > 0) {
                        clearSpecificBackStack("ContainerBStack");
                    } else {
                        finish(); // Exit the activity
                    }
                }
            }
        })
    }

    private fun clearSpecificBackStack(stackName: String) {
        var backStackEntry: FragmentManager.BackStackEntry
        var found = false

        // Loop through the back stack entries in reverse order to find the target stack
        for (i in supportFragmentManager.backStackEntryCount - 1 downTo 0) {
            backStackEntry = supportFragmentManager.getBackStackEntryAt(i)
            if (backStackEntry.name != null && backStackEntry.name == stackName) {
                found = true
                fragmentManager.popBackStackImmediate(
                    stackName,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                break
            }
        }

        // If no matching entry found, consider the stack as cleared
        if (!found) {
            if (stackName == "ContainerAStack") {
                isContainerAStackCleared = true
            }
        }
    }

    private fun clearBackStack(name: String) {
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun loadFragmentsAtTop(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(binding.topContainer.id,fragment,"${(fragment as FirstFragment).getNumber()}")
            .addToBackStack("ContainerAStack")
            .commit()
    }
    private fun loadFragmentsAtBottom(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(binding.bottomContainer.id,fragment,"${(fragment as FirstFragment).getNumber()}")
            .addToBackStack("ContainerBStack")
            .commit()
    }
}
package ir.ham.aghaeidi.old

import android.content.Context
import java.lang.ref.WeakReference


class OuterClass{
    val name = "Hamid"


      class InnerClass{
        val innerName = "Farazd"
        init {
        }
    }
}

fun main(){
//    val outerClass = OuterClass()
    OuterClass.InnerClass()
}



class OuterClass2{
    companion object {
        private var leakyContext: WeakReference<Context> ? = null

        fun setContext(context: Context){
            leakyContext = WeakReference(context)
        }
    }
}
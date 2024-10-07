package ir.ham.aghaeidi.designPattern

object Singleton {
    var mString = ""
    fun process(){
        val stringBuilder = StringBuilder()
        repeat(10){
            stringBuilder.append("$it , ")
        }
        mString = stringBuilder.toString()
    }
}
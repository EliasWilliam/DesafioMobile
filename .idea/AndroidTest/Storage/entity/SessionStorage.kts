import android.content.Context
import android.content.SharedPreferences

abstract class SessionStorage(private val context: Context) {

    protected fun getShared() : SharedPreferences {
        return this.context.getSharedPreferences(null, Context.MODE_PRIVATE)
    }

}
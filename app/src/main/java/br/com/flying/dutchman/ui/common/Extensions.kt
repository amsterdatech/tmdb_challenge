import android.content.Context
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatImageView
import br.com.flying.dutchman.App
import br.com.flying.dutchman.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlin.math.roundToInt

fun AppCompatImageView.load(url: String?, circle: Boolean = false) {
    val options = RequestOptions()
        .priority(Priority.NORMAL)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
//        .placeholder(R.color.colorAccent)

    if (circle) {
        options.circleCrop()
    }

    Glide
        .with(App.instance)
        .load(url)
        .apply(options)
        .into(this@load)
}

fun Context.dpToPx(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    ).roundToInt()
}
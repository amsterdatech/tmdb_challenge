import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.flying.dutchman.App
import br.com.flying.dutchman.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlin.math.roundToInt
import java.text.SimpleDateFormat
import java.util.*

fun AppCompatImageView.load(url: String?, circle: Boolean = false) {
    val options = RequestOptions()
        .priority(Priority.NORMAL)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .placeholder(R.drawable.vector_placeholder_title_small)

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

inline fun <reified T : ViewModel> AppCompatActivity.withViewModel(
    viewModelFactory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val viewModel = viewModelProviders<T>(viewModelFactory)
    viewModel.body()
    return viewModel
}

inline fun <reified T : ViewModel> AppCompatActivity.viewModelProviders(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.viewModelProviders(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.withViewModel(
    viewModelFactory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val viewModel = viewModelProviders<T>(viewModelFactory)
    viewModel.body()
    return viewModel
}


fun String.toDate(format: String): Date = SimpleDateFormat(format).parse(this)

fun Date.toString(format: String): String = SimpleDateFormat(format).format(this)


fun SpannableString.color(color: String, start: Int, end: Int): SpannableString {
    this.setSpan(ForegroundColorSpan(Color.parseColor(color)), start, end, 0)
    return this
}

fun SpannableString.image(
    context: Context,
    drawableRes: Int,
    start: Int,
    end: Int
): SpannableString {
    this.setSpan(ImageSpan(context, drawableRes, ImageSpan.ALIGN_BOTTOM), start, end, 0)
    return this
}
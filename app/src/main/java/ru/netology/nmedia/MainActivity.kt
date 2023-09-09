package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // простой способ от google
//    private var _binding: ActivityMainBinding? = null // так советует google инициализировать Binding-класс для каждого layout, в целях использования с фрагментами вместо строки 10 и 19
//    val binding = ActivityMainBinding // см. строку 11
//        get() = binding!! // см. строку 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//      _binding = ActivityMainBinding.inflate(layoutInflater) // см. строку 11
//      val binding = ActivityMainBinding.inflate(layoutInflater) // инициализация Binding-класса из лекции
        binding = ActivityMainBinding.inflate(layoutInflater) // простой способ от google
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            publishedDate = "21 мая в 18:36",
            likedByMe = false,
            likesCount = 1_299,
            shareCount = 997,
            viewCount = 236_521
        )

        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.publishedDate
            if (post.likedByMe) {
                likes.setImageResource(R.drawable.ic_liked_24)
            } else {
                likes.setImageResource(R.drawable.ic_like_24)
            }
//            likesCount.text = post.likesCount.toString()
            likesCount.text = compactDecimalFormat(post.likesCount)
//            shareCount.text = post.shareCount.toString()
            shareCount.text = compactDecimalFormat(post.shareCount)
//            viewCount.text = post.viewCount.toString()
            viewCount.text = compactDecimalFormat(post.viewCount)

            binding.likes.setOnClickListener {
                post.likedByMe = !post.likedByMe
                Log.d("ActivityMainLikes", "onCreate: ClickedLikes")
                if (post.likedByMe) {
                    likes.setImageResource(R.drawable.ic_liked_24)
                    post.likesCount++
                } else {
                    likes.setImageResource(R.drawable.ic_like_24)
                    post.likesCount--
                }
//                likesCount.text = post.likesCount.toString()
                likesCount.text = compactDecimalFormat(post.likesCount)
            }

            binding.share.setOnClickListener {
                Log.d("ActivityMainShare", "onCreate: ClickedShare")
                post.shareCount++
//                shareCount.text = post.shareCount.toString()
                shareCount.text = compactDecimalFormat(post.shareCount)
            }

            binding.root.setOnClickListener {
                Log.d("ActivityMain", "onCreate: ClickedRoot")
            }

            binding.avatar.setOnClickListener {
                Log.d("ActivityMainAvatar", "onCreate: ClickedAvatar")
            }
        }
    }

    private fun compactDecimalFormat(number: Long): String {
        val suffix = charArrayOf(' ', 'K', 'M', 'B', 'T', 'P', 'E')
        val value = log10(number.toDouble()).toInt()
        val base = value / 3
        return if (value == 4 || value == 5) {
            (number / 10.0.pow(base * 3).toLong()).toString() + suffix[base]
        } else if (value >= 3) {
            DecimalFormat("#.#").format(
                floor(number / (10.0.pow(base * 3)) * 10) / 10) + suffix[base]
        } else {
            number.toString()
        }
    }
}
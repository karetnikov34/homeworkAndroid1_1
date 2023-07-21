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
      private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            publishedDate = "21 мая в 18:36",
            likedByMe = false,
            likesCount = 999,
            shareCount = 2300,
            viewCount = 9100
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
            likesCount.text = compactDecimalFormat(post.likesCount)
            shareCount.text = compactDecimalFormat(post.shareCount)
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
                likesCount.text = compactDecimalFormat(post.likesCount)
            }

            binding.share.setOnClickListener {
                Log.d("ActivityMainShare", "onCreate: ClickedShare")
                post.shareCount++
                shareCount.text = compactDecimalFormat(post.shareCount)
            }
        }
    }

    private fun compactDecimalFormat(number: Int): String {
        val suffix = charArrayOf(' ', 'K', 'M', 'B', 'T', 'P', 'E')
        val value = floor(log10(number.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                number / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(number)
        }
    }
}
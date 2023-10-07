package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel
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

        val viewModel by viewModels<PostViewModel>()

        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                content.text = post.content
                published.text = post.publishedDate
                likes.setImageResource(if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24)
                likesCount.text = compactDecimalFormat(post.likesCount)
                shareCount.text = compactDecimalFormat(post.shareCount)
                viewCount.text = compactDecimalFormat(post.viewCount)
            }
        }

        binding.likes.setOnClickListener { viewModel.like() }

        binding.share.setOnClickListener { viewModel.share() }
    }
    private fun compactDecimalFormat(number: Long): String {
        val suffix = charArrayOf(' ', 'K', 'M', 'B', 'T', 'P', 'E')
        val value = log10(number.toDouble()).toInt()
        val base = value / 3
        return if (value == 4 || value == 5) {
            (number / 10.0.pow(base * 3).toLong()).toString() + suffix[base]
        } else if (value >= 3) {
            DecimalFormat("#.#").format(
                floor(number / (10.0.pow(base * 3)) * 10) / 10
            ) + suffix[base]
        } else {
            number.toString()
        }
    }
}
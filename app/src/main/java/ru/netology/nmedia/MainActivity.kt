package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        with(binding) {
            viewModel.data.observe(this@MainActivity) { post ->
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likedCount.text = convertCountToString(post.likedCount)
                sharedCount.text = convertCountToString(post.sharedCount)
                viewedCount.text = convertCountToString(post.viewedCount)
                if (post.likedByMe) {
                    liked.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    liked.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }

            liked.setOnClickListener {
                viewModel.like()
            }

            shared.setOnClickListener {
                viewModel.share()
            }
        }
    }

    private fun convertCountToString(count: Long): String {
        val result = when {
            count < 1_000 -> count.toString()
            count in 1_000..1_099 -> "1K"
            count in 1_100..9_999 -> String.format("%.1fK", (count / 100).toDouble() / 10)
            count in 10_000..999_999 -> (count / 1000).toString() + "K"
            count in 1_000_000..1_999_999 -> "1M"
            else -> String.format("%.1fМ", (count / 100_000).toDouble() / 10)
        }
        return result
    }
}
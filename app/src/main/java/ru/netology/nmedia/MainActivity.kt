package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.data.Post
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedCount = 999,
            sharedCount = 9998,
            viewedCount = 999
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likedCount.text = convertCountToString(post.likedCount)
            sharedCount.text = convertCountToString(post.sharedCount)
            viewedCount.text = convertCountToString(post.viewedCount)
            liked.setOnClickListener {
                if (!post.likedByMe) {
                    liked.setImageResource(R.drawable.ic_baseline_favorite_24)
                    post.likedCount++
                } else {
                    liked.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    post.likedCount--
                }
                post.likedByMe = !post.likedByMe
                likedCount.text = convertCountToString(post.likedCount)
            }
            shared.setOnClickListener {
                post.sharedCount++
                sharedCount.text = convertCountToString(post.sharedCount)
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
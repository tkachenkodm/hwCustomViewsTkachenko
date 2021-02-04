package com.example.hwcustomviews

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout

class MovieTileView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttrs, defStyleRes) {

    private val tvTitle: TextView
    private val tvGenre: TextView
    private val tvPrice: TextView
    private val ivPoster: ImageView

    private val attributes: TypedArray

    init {
        inflate(context, R.layout.movie_tile_view, this)

        tvTitle = findViewById(R.id.tvTitle)
        tvGenre = findViewById(R.id.tvGenre)
        tvPrice = findViewById(R.id.tvPrice)
        ivPoster = findViewById(R.id.ivPoster)

        attributes = context.obtainStyledAttributes(attributeSet, R.styleable.MovieTileView)
        tvTitle.text = attributes.getString(R.styleable.MovieTileView_titleText)
        tvGenre.text = attributes.getString(R.styleable.MovieTileView_genreText)
        tvPrice.text = attributes.getString(R.styleable.MovieTileView_priceText)
        ivPoster.setImageDrawable(attributes.getDrawable(R.styleable.MovieTileView_posterDrawable))
    }

    fun setTitleText(@StringRes stringId: Int) {
        tvTitle.text = resources.getString(stringId)
    }

    fun setTitleText(string: String) {
        tvTitle.text = string
    }

    fun setGenreText(@StringRes stringId: Int) {
        tvGenre.text = resources.getString(stringId)
    }

    fun setGenreText(string: String) {
        tvGenre.text = string
    }

    fun setPriceText(@StringRes stringId: Int) {
        tvPrice.text = resources.getString(stringId)
    }

    fun setPriceText(string: String) {
        tvPrice.text = string
    }

    fun setPosterDrawable(@DrawableRes drawableId: Int) {
        ivPoster.setImageResource(drawableId)
    }
}
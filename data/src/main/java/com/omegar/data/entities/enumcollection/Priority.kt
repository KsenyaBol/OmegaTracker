package com.omegar.data.entities.enumcollection

import com.omegar.data.R

enum class Priority(val backgroundColor: Int, val textColor: Int) {
    BLOCKER(
        R.color.red_tag,
        R.color.white_tag
    ),
    CRITICAL(
        R.color.pink_tag,
        R.color.gray_tag
    ),
    MAJOR(
        R.color.light_green_tag,
        R.color.green_tag
    ),
    MINOR(
        R.color.light_gray_tag,
        R.color.gray_tag
    ),
    TRIVIAL(
        R.color.gray_tag,
        R.color.white_tag
    )
}
package com.omegar.data.entities.enumcollection

import com.omegar.data.R

enum class StateEnum(val searchName: String, val backgroundColor: Int, val textColor: Int) {
    IN_PROGRESS(
        "In Progress",
        R.color.light_blue_tag,
        R.color.gray_tag
    ),
    REOPENED(
        "Reopened",
        R.color.red_tag,
        R.color.white_tag
    ),
    IN_REVIEW(
        "In Review",
        R.color.orange_tag,
        R.color.white_tag
    ),
    IN_TESTING(
        "In Testing",
        R.color.turquoise_tag,
        R.color.gray_tag
    ),
    DONE(
        "Done",
        R.color.yellow_tag,
        R.color.gray_tag
    ),
    FINISHED(
        "Finished",
        R.color.green_tag,
        R.color.white_tag
    ),
    NOT_ClEARED(
        "Not Cleared",
        R.color.black_tag,
        R.color.white_tag
    ),
    BACKLOG(
        "Backlog",
        R.color.white_tag,
        R.color.black_tag
    )
}
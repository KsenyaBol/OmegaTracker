package com.omegar.omegatracker.ui.main

import com.omegar.omegatracker.ui.base.BaseView

interface MainView : BaseView {

    var mainPages: List<MainPage>

    var currentMainPage: MainPage?

    var notificationPages: List<MainPage>

}
package com.example.demo.app

import com.example.demo.controllers.LoginController
import com.example.demo.app.Styles
import com.example.demo.views.LoginScreen
import javafx.application.Application
import javafx.stage.Stage
import tornadofx.*

class DemoCreatorApp : App(LoginScreen::class, Styles::class) {
    private val loginController: LoginController by inject()

    init {
        reloadStylesheetsOnFocus()
        reloadViewsOnFocus()
    }

    override fun start(stage: Stage) {
        importStylesheet(Styles::class)
        super.start(stage)
        loginController.init()
    }
}

fun main(args: Array<String>) {
    Application.launch(DemoCreatorApp::class.java, *args)
}
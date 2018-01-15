package com.example.demo.app

import com.example.demo.controllers.LoginController
import com.example.demo.views.LoginScreen
import javafx.application.Application
import javafx.stage.Stage
import tornadofx.*

class GridCreatorApp : App(LoginScreen::class, Styles::class) {
    private val loginController: LoginController by inject()

    override fun start(stage: Stage) {
        importStylesheet(Styles::class)
        super.start(stage)
        loginController.init()
    }
}

fun main(args: Array<String>) {
    Application.launch(GridCreatorApp::class.java, *args)
}
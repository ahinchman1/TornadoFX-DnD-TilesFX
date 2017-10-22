package com.example.demo.controllers

import com.example.demo.views.Workbench
import com.example.demo.views.LoginScreen
import javafx.application.Platform
import tornadofx.Controller
import tornadofx.FX

class LoginController : Controller() {
    private val loginScreen: LoginScreen by inject()
    private val workbench: Workbench by inject()

    fun init() {
        with (config) {
            if (containsKey(USERNAME) && containsKey(PASSWORD))
                tryLogin(string(USERNAME), string(PASSWORD), true)
            else
                showLoginScreen("Please log in")
        }
    }

    private fun showLoginScreen(message: String, shake: Boolean = false) {
        if (FX.primaryStage.scene.root != loginScreen.root) {
            FX.primaryStage.scene.root = loginScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = message

        Platform.runLater {
            loginScreen.username.requestFocus()
            if (shake) loginScreen.shakeStage()
        }
    }

    private fun showWorkbench() {
        if (FX.primaryStage.scene.root != workbench.root) {
            FX.primaryStage.scene.root = workbench.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }
    }

    fun tryLogin(username: String, password: String, remember: Boolean) {
        runAsync {
            username == "admin" && password == "secret"
        } ui { successfulLogin ->

            if (successfulLogin) {
                loginScreen.clear()

                if (remember) {
                    with (config) {
                        set(USERNAME to username)
                        set(PASSWORD to password)
                        save()
                    }
                }

                showWorkbench()
            } else {
                showLoginScreen("styles failed. Please try again.", true)
            }
        }
    }

    fun logout() {
        with (config) {
            remove(USERNAME)
            remove(PASSWORD)
            save()
        }

        showLoginScreen("Log in as another user")
    }

    companion object {
        val USERNAME = "username"
        val PASSWORD = "password"
    }

}
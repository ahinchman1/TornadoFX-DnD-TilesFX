package com.example.demo.views

import com.example.demo.app.Styles.Companion.loginScreen
import com.example.demo.controllers.LoginController
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.util.Duration
import tornadofx.*

class LoginScreen : View("Please log in") {
    private val loginController: LoginController by inject()

    private val username = SimpleStringProperty()
    private val password = SimpleStringProperty()
    private val remember = SimpleBooleanProperty()

    override val root = form {
        addClass(loginScreen)

        fieldset {
            field("Username") {
                textfield(username) {
                    whenDocked {
                        requestFocus()
                    }
                }
            }

            field("Password") {
                passwordfield(password)
            }

            field("Remember me") {
                checkbox(property = remember)
            }

            button("Login") {
                isDefaultButton = true

                action {
                    loginController.tryLogin(
                            username.value,
                            password.value,
                            remember.value
                    )
                }
            }
        }
    }

    fun clear() {
        username.value = null
        password.value = null
        remember.value = false
    }

    fun shakeStage() {
        var x = 0
        var y = 0
        val cycleCount = 10
        val move = 10
        val keyframeDuration = Duration.seconds(0.04)

        val stage = FX.primaryStage

        val timelineX = Timeline(KeyFrame(keyframeDuration, EventHandler {
            if (x == 0) {
                stage.x = stage.x + move
                x = 1
            } else {
                stage.x = stage.x - move
                x = 0
            }
        }))

        timelineX.cycleCount = cycleCount
        timelineX.isAutoReverse = false

        val timelineY = Timeline(KeyFrame(keyframeDuration, EventHandler {
            if (y == 0) {
                stage.y = stage.y + move
                y = 1
            } else {
                stage.y = stage.y - move
                y = 0
            }
        }))

        timelineY.cycleCount = cycleCount
        timelineY.isAutoReverse = false

        timelineX.play()
        timelineY.play()
    }
}
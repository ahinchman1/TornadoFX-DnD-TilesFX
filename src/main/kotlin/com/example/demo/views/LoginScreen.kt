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

    /***** Global Variables *****/
    private val username = SimpleStringProperty()
    private val password = SimpleStringProperty()
    private val remember = SimpleBooleanProperty()

    /***** View *****/
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
                            username.valueSafe,
                            password.valueSafe,
                            remember.value
                    )
                }
            }
        }
    }

    /**
     * Permanently removes contents of text fields.
     *
     */
    fun clear() {
        username.value = null
        password.value = null
        remember.value = false
    }

    /**
     * Shakes the stage upon incorrect login
     *
     */
    fun shakeStage() {
        var moved = false
        val cycleCount = 10
        val move = 10
        val keyframeDuration = Duration.seconds(0.04)

        val stage = FX.primaryStage

        Timeline(KeyFrame(keyframeDuration, EventHandler {
            if (!moved) {
                stage.x = stage.x + move
                stage.y = stage.y + move
            } else {
                stage.x = stage.x - move
                stage.y = stage.y - move
            }
            moved = moved.not()
        })).apply {
            setCycleCount(cycleCount)
            isAutoReverse = false
            play()
        }
    }
}
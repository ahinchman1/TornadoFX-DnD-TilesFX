package com.example.demo.views

import com.example.demo.controllers.LoginController
import tornadofx.*
import javafx.application.Platform
import javafx.scene.layout.BorderPane
import javafx.scene.text.Font


class Workbench : View() {
    override val root = BorderPane()
    private val loginController: LoginController by inject()

    private val metroTileHomepages = listOf("file:/img/MetroTileHomepage.png", "file:/img/MetroTileHomepage2.png", "file:/img/MetroTileHomepage3.png",
            "file:/img/MetroTileHomepage4.png", "file:/img/MetroTileHomepage5.png", "file:/img/MetroTileHomepage6.png", "file:/img/MetroTileHomepage7.png",
            "file:/img/MetroTileHomepage8.png", "file:/img/MetroTileHomepage9.png", "file:/img/MetroTileHomepage10.png", "file:/img/MetroTileHomepage11.png",
            "file:/img/MetroTileHomepage12.png", "file:/img/MetroTileHomepage13.png", "file:/img/MetroTileHomepage14.png", "file:/img/MetroTileHomepage15.png",
            "file:/img/MetroTileHomepage16.png", "file:/img/MetroTileHomepage17.png", "file:/img/MetroTileHomepage18.png", "file:/img/MetroTileHomepage19.png",
            "file:/img/MetroTileHomepage20.png")



    init {
        title = "Secure Workbench"

        with (root) {
            setPrefSize(800.0, 600.0)

            top {

                label(title) {
                    font = Font.font(22.0)
                }
            }

            center {

                datagrid(metroTileHomepages) {

                    maxCellsInRow=3

                    cellWidth=200.0
                    cellHeight=200.0

                    cellCache {
                        imageview(it) {
                            fitWidth=200.0
                            fitHeight=200.0
                        }
                    }
                }
            }

            bottom {

                label("If you can see this, you are successfully logged in!")

                hbox {

                    button("Logout") {
                        setOnAction {
                            loginController.logout()
                        }
                    }

                    button("Exit") {
                        setOnAction {
                            Platform.exit()
                        }
                    }
                }
            }
        }
    }
}
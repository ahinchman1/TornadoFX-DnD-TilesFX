package com.example.demo.views

import com.example.demo.app.Styles.Companion.workbenchScreen
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.WorkbenchController
import tornadofx.*
import javafx.application.Platform
import javafx.scene.layout.BorderPane
import javafx.scene.text.Font


class Workbench : View() {
    override val root = BorderPane()
    private val loginController: LoginController by inject()
    private val workbenchController: WorkbenchController by inject()

    private val metroTileHomepages = listOf("/img/MetroTileHomepage.png", "/img/MetroTileHomepage2.png", "/img/MetroTileHomepage3.png",
            "/img/MetroTileHomepage4.png", "/img/MetroTileHomepage5.png", "/img/MetroTileHomepage6.png", "/img/MetroTileHomepage7.png",
            "/img/MetroTileHomepage8.png", "/img/MetroTileHomepage9.png", "/img/MetroTileHomepage10.png", "/img/MetroTileHomepage11.png",
            "/img/MetroTileHomepage12.png", "/img/MetroTileHomepage13.png", "/img/MetroTileHomepage14.png", "/img/MetroTileHomepage15.png",
            "/img/MetroTileHomepage16.png", "/img/MetroTileHomepage17.png", "/img/MetroTileHomepage18.png", "/img/MetroTileHomepage19.png",
            "/img/MetroTileHomepage20.png").observable()



    init {
        title = "Secure Workbench"

        with (root) {
            addClass(workbenchScreen)
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
                    paddingLeft=80.0
                    paddingTop=20.0
                    paddingBottom=40.0

                    pagination(Math.ceil(metroTileHomepages.size/9.0).toInt()) {
                        cellFormat{
                            imageview(it) {
                            fitWidth=200.0
                            fitHeight=200.0
                            isPreserveRatio=true
                            }
                        }
                        onUserSelect(2) {
                            workbenchController.goToGUIEditor(it)
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
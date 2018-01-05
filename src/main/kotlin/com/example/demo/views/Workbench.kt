package com.example.demo.views

import com.example.demo.app.Styles.Companion.workbenchScreen
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.WorkbenchController
import tornadofx.*
import javafx.application.Platform
import javafx.scene.text.Font


class Workbench : View() {

    private val loginController: LoginController by inject()
    private val workbenchController: WorkbenchController by inject()

    /***** Global Variables *****/
    private val metroTileHomepages = listOf("/img/MetroTileHomepage1.png", "/img/MetroTileHomepage2.png", "/img/MetroTileHomepage3.png",
            "/img/MetroTileHomepage4.png", "/img/MetroTileHomepage5.png", "/img/MetroTileHomepage6.png", "/img/MetroTileHomepage7.png",
            "/img/MetroTileHomepage8.png", "/img/MetroTileHomepage9.png", "/img/MetroTileHomepage10.png", "/img/MetroTileHomepage11.png",
            "/img/MetroTileHomepage12.png", "/img/MetroTileHomepage13.png", "/img/MetroTileHomepage14.png", "/img/MetroTileHomepage15.png",
            "/img/MetroTileHomepage16.png", "/img/MetroTileHomepage17.png", "/img/MetroTileHomepage18.png", "/img/MetroTileHomepage19.png",
            "/img/MetroTileHomepage20.png").observable()

    private val paginator = DataGridPaginator(metroTileHomepages, itemsPerPage = 9)

    /***** View *****/
    override val root = borderpane {
    title = "Secure Workbench"

        addClass(workbenchScreen)
        setPrefSize(750.0, 700.0)

        top {
            label(title) {
                font = Font.font(22.0)
            }
            menubar {
                menu("File") {
                    item("Logout").action {
                        loginController.logout()
                    }
                    item("Quit").action {
                        Platform.exit()
                    }
                }
            }
        }

        center {
            datagrid(paginator.items) {
                maxCellsInRow=3

                cellWidth=180.0
                cellHeight=180.0
                paddingLeft=80.0
                paddingTop=20.0

                cellFormat {
                    graphic = cache {
                        imageview(it) {
                            fitWidth = 180.0
                            fitHeight = 180.0
                            isPreserveRatio = true
                            center
                        }
                    }
                }
                onUserSelect(2) {
                    workbenchController.goToEditor(it)
                }
            }
        }

        bottom {
            stackpane {
                add(paginator)
                paddingBottom=25.0
            }
        }
    }

}
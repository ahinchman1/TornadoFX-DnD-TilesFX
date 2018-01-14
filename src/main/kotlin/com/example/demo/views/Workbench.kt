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
    private val metroTileHomepages = listOf("/img/Tile1.png", "/img/Tile2.png",
            "/img/Tile3.png", "/img/Tile4.png", "/img/Tile5.png",
            "/img/Tile6.png").observable()

    private val paginator = DataGridPaginator(metroTileHomepages, itemsPerPage = 4)

    /***** View *****/
    override val root = borderpane {
    title = "Secure Workbench"

        addClass(workbenchScreen)
        setPrefSize(450.0, 550.0)

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
                maxCellsInRow=2

                cellWidth=180.0
                cellHeight=180.0
                paddingLeft=30.0
                paddingTop=40.0

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
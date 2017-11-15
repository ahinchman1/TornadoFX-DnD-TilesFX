package com.example.demo.views.metroTileHomepages
import com.example.demo.app.Styles.Companion.gridSquare
import com.example.demo.app.Styles.Companion.banner_tile
import com.example.demo.app.Styles.Companion.metroTileHomepageGUI
import com.example.demo.app.Styles.Companion.module_tile
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.WorkbenchController
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import tornadofx.*

class MetroTileHomepage5 : View() {
    private val loginController: LoginController by inject()
    private val workbenchController: WorkbenchController by inject()

    override val root: BorderPane by fxml("/fxml/MetroTileHomepage5.fxml")

    private val gridSquarePane: GridPane by fxid("gridSquare")

    //import fxml modules
    private val bannerRect: Label by fxid("banner")
    private val module1Rect: Rectangle by fxid("module1")
    private val module2Rect: Rectangle by fxid("module2")
    private val module3Rect: Rectangle by fxid("module3")
    private val module4Rect: Rectangle by fxid("module4")
    private val module5Rect: Rectangle by fxid("module5")
    private val module6Rect: Rectangle by fxid("module6")
    private val module7Rect: Rectangle by fxid("module7")
    private val module8Rect: Rectangle by fxid("module8")
    private val module9Rect: Rectangle by fxid("module9")

    init {
        with(root) {
            addClass(metroTileHomepageGUI)
            setPrefSize(1000.0, 675.0)

            flowpane {
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

                gridSquarePane.addClass(gridSquare)
                bannerRect.addClass(banner_tile)
                module1Rect.addClass(module_tile)
                module2Rect.addClass(module_tile)
                module3Rect.addClass(module_tile)
                module4Rect.addClass(module_tile)
                module5Rect.addClass(module_tile)
                module6Rect.addClass(module_tile)
                module7Rect.addClass(module_tile)
                module8Rect.addClass(module_tile)
                module9Rect.addClass(module_tile)

                bannerRect.gridpaneConstraints{
                    marginTop=50.0
                    marginRight=120.0
                }

            }

            right {
                val modules = listOf("browse", "library", "featured content", "large library", "wizard",
                        "response", "messages", "build", "popular content", "announcements").observable()
                val paginator = DataGridPaginator(modules, itemsPerPage = 6)

                vbox {
                    maxWidth=300.0
                    datagrid(paginator.items) {
                        maxCellsInRow = 2

                        cellWidth = 90.0
                        cellHeight = 90.0
                        paddingTop = 20.0
                        paddingLeft = 40.0

                        cellCache {
                            alignment=Pos.CENTER_RIGHT
                            rectangle {
                                height = 90.0
                                width = 90.0
                                fill = Color.ALICEBLUE
                            }
                            label(it)
                        }
                    }
                    stackpane {
                        paddingBottom = 20.0
                        add(paginator)
                    }
                    form {
                        paddingTop = 20.0
                        paddingLeft = 20.0
                        hbox(20) {
                            fieldset("Customize Module") {
                                hbox(20) {
                                    vbox {
                                        field("Color") { textfield() }
                                        field("HoverColor") { textfield() }
                                        field("Image Source") { textfield() }
                                        field("Label") { textfield() }
                                    }
                                }
                            }
                        }
                    }
                    hbox {
                        hboxConstraints {
                            alignment = Pos.BASELINE_RIGHT
                        }
                        button("Return to Workbench") {
                            isDefaultButton = true

                            setOnAction {
                                workbenchController.returnToWorkbench(this@MetroTileHomepage5)
                            }

                            hboxConstraints {
                                marginLeftRight(10.0)
                                marginTopBottom(10.0)
                            }
                        }
                        button("Save") {
                            isDefaultButton = true

                            setOnAction {
                                // Save
                            }

                            hboxConstraints {
                                marginLeftRight(10.0)
                                marginTopBottom(10.0)
                            }
                        }
                    }
                }
            }
        }
    }
}


package com.example.demo.views
import com.example.demo.app.Styles.Companion.metroTileHomepageGUI
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.MetroTileHomepageController
import com.example.demo.controllers.WorkbenchController
import com.example.demo.model.GridInfo
import com.example.demo.model.GridScope
import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.tools.FlowGridPane
import javafx.application.Platform
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.layout.*
import javafx.scene.text.Font
import tornadofx.*

class MetroTileHomepage : View() {
    private val loginController: LoginController by inject()
    private val workbenchController: WorkbenchController by inject()
    private val controller: MetroTileHomepageController by inject()

    override val root = BorderPane()
    private val paginator = DataGridPaginator(controller.smallTiles, itemsPerPage = 8)


    init {
        with(root) {
            val gridInfo = GridInfo(controller.useTileGrid(workbenchController.metroTile))
            val grid = passGridInfo(gridInfo)
            addClass(metroTileHomepageGUI)
            setPrefSize(1000.0, 750.0)

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

                center = grid

                right {
                    vbox {
                        maxWidth = 300.0

                        drawer(side = Side.RIGHT) {
                            item("Small Modules", expanded = true) {
                                val small = FlowGridPane(2, 4).apply {
                                    hgap = 5.0
                                    vgap = 5.9
                                    padding = Insets(5.0)
                                    background = Background(BackgroundFill(Tile.BACKGROUND.invert(), CornerRadii.EMPTY, Insets.EMPTY))
                                    prefHeight = 600.0
                                    prefWidth = 300.0
                                }
                                for(tile in controller.smallTiles) {
                                    small.add(tile)
                                }
                            }
                            item("Large Modules") {
                                datagrid(paginator.items) {
                                    maxCellsInRow=2

                                    cellWidth=90.0
                                    cellHeight=90.0
                                    paddingLeft=80.0
                                    paddingTop=20.0
                                    prefHeight = 600.0
                                    prefWidth = 300.0

                                    cellFormat {
                                        it
                                    }
                                }
                            }

                        }

                        form {
                            paddingLeft = 20.0
                            hbox(20) {
                                fieldset("Customize Module") {
                                    hbox(20) {
                                        vbox {
                                            field("Color") { textfield("#fffffff") }
                                            field("HoverColor") { textfield("#fffffff") }
                                            field("Image Source") { textfield("") }
                                            field("Label") { textfield("Label") }
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
                                    workbenchController.returnToWorkbench(this@MetroTileHomepage)
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
}

private fun passGridInfo(gridInfo: GridInfo): GridPane {
    val metroScope = GridScope()
    metroScope.model.item = gridInfo
    return find<MyTiles>(metroScope).root
}

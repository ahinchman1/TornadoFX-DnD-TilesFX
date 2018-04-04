package com.example.demo.views
import com.example.demo.app.Styles
import com.example.demo.app.Styles.Companion.tileGUI
import com.example.demo.app.Styles.Companion.transparentOverlay
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.TileBuilderController
import com.example.demo.controllers.TileGUIController
import com.example.demo.controllers.WorkbenchController
import com.example.demo.model.GridInfo
import com.example.demo.model.GridInfoModel
import com.example.demo.model.GridScope
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.text.Font
import tornadofx.*

class TileGUI : View() {

    /***** Global Variables *****/
    private val loginController: LoginController by inject()
    private val tileBuilderController: TileBuilderController by inject()
    private val controller: TileGUIController by inject()

    // drag variables
    var moduleBoxItems = mutableListOf<Node>()

    /***** View *****/
    override val root = stackpane {
        setPrefSize(1000.0, 650.0)

        borderpane {
            addClass(tileGUI)
            top {
                label(title) {
                    font = Font.font(22.0)
                }
                menubar {
                    menu("File") {
                        item("Logout").action(loginController::logout)
                        item("Quit").action(Platform::exit)
                    }
                }
            }

            center(MyTiles::class)

            right {
                vbox {
                    maxWidth = 300.0
                    drawer(side = Side.RIGHT) {
                        item("Tiles", expanded = true) {
                            datagrid(tileBuilderController.tileList) {
                                maxCellsInRow = 2
                                cellWidth = 100.0
                                cellHeight = 100.0

                                paddingTop = 15.0
                                paddingLeft = 35.0
                                minWidth = 300.0

                                cellFormat {
                                    graphic = it.apply { addClass(Styles.highlightTile) }
                                }
                            }
                        }
                        item("Icons") {
                            datagrid(tileBuilderController.imageList) {
                                maxCellsInRow = 2

                                cellWidth = 100.0
                                cellHeight = 100.0
                                paddingLeft = 35.0
                                minWidth = 300.0

                                cellFormat {
                                    graphic = it
                                    style { backgroundColor += c("#222222") }
                                }
                            }
                        }
                    }

                    hbox {
                        hboxConstraints { alignment = Pos.BASELINE_RIGHT }
                        button("Return to Workbench") {
                            hboxConstraints {
                                marginLeftRight(10.0)
                                marginTop = 70.0
                            }
                        }
                    }
                }
            }
        }

        pane {
            addClass(transparentOverlay)
            isMouseTransparent = true
        }

        addEventFilter(MouseEvent.ANY, ::hoverBehavior)
        addEventFilter(MouseEvent.MOUSE_PRESSED, ::startDrag)
        addEventFilter(MouseEvent.MOUSE_DRAGGED, ::animateDrag)
        addEventFilter(MouseEvent.MOUSE_RELEASED, ::stopDrag)
        addEventFilter(MouseEvent.MOUSE_RELEASED, ::drop)
    }

    /***** Methods *****/

    private fun hoverBehavior(evt: MouseEvent) {
        controller.hoverBehavior(evt)
    }
    private fun startDrag(evt: MouseEvent) {
        controller.startDrag(evt)
    }
    private fun animateDrag(evt: MouseEvent) {
        controller.animateDrag(evt)
    }
    private fun stopDrag(evt: MouseEvent) {
        controller.stopDrag(evt)
    }
    private fun drop(evt: MouseEvent) {
        controller.drop(evt, this@TileGUI)
    }

    // init
    init {
        moduleBoxItems.addAll( tileBuilderController.tileList )
    }
}
package com.example.demo.views
import com.example.demo.app.Styles
import com.example.demo.app.Styles.Companion.tileGUI
import com.example.demo.app.Styles.Companion.transparentOverlay
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.TileBuilderController
import com.example.demo.controllers.TileGUIController
import com.example.demo.model.TileBuilder
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.text.Font
import tornadofx.*

class TileGUI : View() {

    /***** Global Variables *****/
    private val loginController: LoginController by inject()
    private val tileBuilderController: TileBuilderController by inject()
    private val controller: TileGUIController by inject()
    var module = TileBuilder()

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

                    form {
                        paddingLeft = 20.0
                        paddingTop = 20.0
                        hbox(20) {
                            fieldset("Select a tile to customize.") {
                                hbox(20) {
                                    vbox {
                                        field("Title") { textfield().bind(module.titleProperty) }
                                        field("Color") { textfield().bind(module.colorProperty) }
                                        field("HoverColor") { textfield().bind(module.hoverColorProperty) }
                                    }
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
        addEventFilter(MouseEvent.MOUSE_CLICKED, ::selectTile)
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
    private fun selectTile(evt: MouseEvent) {
        controller.selectTile(evt, module)
    }

    // init
    init {
        moduleBoxItems.addAll( tileBuilderController.tileList )
    }
}
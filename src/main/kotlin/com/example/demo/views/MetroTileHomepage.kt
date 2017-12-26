package com.example.demo.views
import com.example.demo.app.Styles
import com.example.demo.app.Styles.Companion.highlightTile
import com.example.demo.app.Styles.Companion.inflight
import com.example.demo.app.Styles.Companion.metroTileHomepageGUI
import com.example.demo.app.Styles.Companion.workAreaSelected
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.MetroTileHomepageController
import com.example.demo.controllers.PageBuilderController
import com.example.demo.controllers.WorkbenchController
import com.example.demo.model.DragTile
import com.example.demo.model.GridInfo
import com.example.demo.model.GridScope
import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.application.Platform
import javafx.geometry.Point2D
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.control.Alert
import javafx.scene.input.*
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*

class MetroTileHomepage : Fragment() {
    private val loginController: LoginController by inject()
    private val pageBuilderController: PageBuilderController by inject()
    private val workbenchController: WorkbenchController by inject()
    private val controller: MetroTileHomepageController by inject()

    private val paginator = DataGridPaginator(pageBuilderController.smallTiles, itemsPerPage = 8)
    private lateinit var gridInfo: GridInfo

    // drag variables
    private var draggingColor: Color? = null
    private var moduleboxItems = mutableListOf<Node>()
    var workArea: GridPane by singleAssign()
    private var selectedTile: Tile by singleAssign()
    private var inflightTile: Tile by singleAssign()

    override val root = borderpane {
        gridInfo = GridInfo(controller.useTileGrid(workbenchController.metroTile))
        workArea = passGridInfo(gridInfo)
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

            center = workArea.addClass(Styles.grid)

            inflightTile = TileBuilder.create()
                    .skinType(Tile.SkinType.CUSTOM)
                    .maxSize(100.0, 100.0)
                    .barBackgroundColor(Color.CHARTREUSE)
                    .build().addClass(inflight)

            inflightTile.isVisible = false
            add(inflightTile)

            right {
                vbox {
                    maxWidth = 300.0
                    drawer(side = Side.RIGHT) {
                        item("Small Modules", expanded = true) {
                            datagrid(paginator.items) {
                                maxCellsInRow = 2
                                cellWidth = 100.0
                                cellHeight = 100.0
                                paddingLeft = 35.0
                                minWidth = 300.0

                                cellFormat {
                                    graphic = cache {
                                        it
                                    }
                                    graphic.setOnMouseEntered {
                                        graphic.addClass(highlightTile)
                                    }

                                    graphic.setOnMouseExited {
                                        graphic.removeClass(highlightTile)
                                    }
                                }
                            }
                            add(paginator)
                         }

                         item("Large Modules") {
                             datagrid(paginator.items) {
                                 maxCellsInRow = 2
                                 cellWidth = 100.0
                                 cellHeight = 100.0
                                 paddingLeft = 35.0
                                 minWidth = 300.0

                                 cellFormat {
                                     graphic = cache {
                                         it
                                     }
                                 }
                             }
                             stackpane {
                                 add(paginator)
                             }
                         }
                     }

                    form {
                        paddingLeft = 20.0
                        paddingTop = 20.0
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
                            setOnAction {
                                workbenchController.returnToWorkbench(this@MetroTileHomepage)
                            }

                            hboxConstraints {
                                marginLeftRight(10.0)
                                marginBottom = 20.0
                            }
                        }
                        button("Save") {
                            isDefaultButton = true

                            setOnAction {
                                // Save
                            }

                            hboxConstraints {
                                marginLeftRight(10.0)
                                marginBottom = 20.0
                            }
                        }
                    }
                }
            }
        }

        addEventFilter(MouseEvent.MOUSE_PRESSED, ::startDrag)
        addEventFilter(MouseEvent.MOUSE_DRAGGED, ::animateDrag)
        //addEventFilter(MouseEvent.MOUSE_EXITED, ::stopDrag)
        addEventFilter(MouseEvent.MOUSE_RELEASED, ::stopDrag)
        addEventFilter(MouseEvent.MOUSE_RELEASED, ::drop)
    }

    private fun startDrag(evt : MouseEvent) {

        moduleboxItems
                .filter {
                    val mousePt : Point2D = it.sceneToLocal( evt.sceneX, evt.sceneY )
                    it.contains(mousePt)
                }
                .firstOrNull()
                .apply {
                    if( this != null ) {
                        draggingColor = Color.GRAY
                    }
                }

    }

    private fun animateDrag(evt : MouseEvent) {

        val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
        if( workArea.contains(mousePt) ) {

            // highlight the drop target (hover doesn't work)
            if( !workArea.hasClass(workAreaSelected)) {
                workArea.addClass(workAreaSelected)
            }

            // animate a rectangle so that the user can follow
            if( !inflightTile.isVisible ) {
                inflightTile.isVisible = true
            }

            inflightTile.relocate( mousePt.x, mousePt.y )
        }

    }

    private fun stopDrag(evt: MouseEvent) {
        if( workArea.hasClass(workAreaSelected ) ) {
            workArea.removeClass(workAreaSelected)
        }
        if( inflightTile.isVisible ) {
            inflightTile.isVisible = false
        }
    }

    private fun drop(evt : MouseEvent) {

        val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
        if( workArea.contains(mousePt) ) {
            if( draggingColor != null ) {
                val newTile = controller.moduleTileBuilder("module1")
                workArea.add( newTile )
                newTile.relocate( mousePt.x, mousePt.y )

                inflightTile.toFront() // don't want to move cursor tracking behind added objects
            }
        }

        draggingColor = null
    }

    private fun pickGridTile(tile: Tile, point2D: Point2D) {
        return pickGridTile(tile, point2D.x, point2D.y)
    }

    private fun pickGridTile(tile: Tile, sceneX: Double, sceneY:Double) {
        val mousePoint= Point2D(sceneX, sceneY)
        val mpLocal = workArea.sceneToLocal(mousePoint)

        val pickedTile = getPickedGridTileInfo(mpLocal)
        val gridRow: Int = (mpLocal.x/gridInfo.rows) as Int
        val gridColumn: Int = (mpLocal.y/gridInfo.columns) as Int
        val tileSpanRow: Int = (tile.width/100) as Int
        val tileSpanCol: Int = (tile.height/100) as Int

        if (gridRow <= gridInfo.rows && gridColumn <= gridInfo.columns
                && tileSpanRow == pickedTile.rowSpan && tileSpanCol == pickedTile.colSpan) {
            workArea.add(tile, gridColumn, gridRow, tileSpanRow, tileSpanCol)
        } else {
            // might just want to drop the tile somewhere in the grid instead
            alert(
                    type = Alert.AlertType.ERROR,
                    header = "Tile Error",
                    content = "Can't drop tile here"
            )
        }
    }

    private fun getPickedGridTileInfo(point2D: Point2D): DragTile {
        val gridRow: Int = (point2D.x/gridInfo.rows) as Int
        val gridColumn: Int = (point2D.y/gridInfo.columns) as Int
        var colIndex = 0
        var rowIndex = 0
        var rowSpan = 0
        var colSpan = 0

        val children = workArea.children

        for (tile in children) {
            if (GridPane.getRowIndex(tile) == gridRow && GridPane.getColumnIndex(tile) == gridColumn) {
                colIndex = GridPane.getColumnIndex(tile)
                rowIndex = GridPane.getRowIndex(tile)
                selectedTile = tile as Tile
                rowSpan = (selectedTile.width / 100.0) as Int
                colSpan = (selectedTile.height / 100.0) as Int
                break
            }
        }
        return DragTile(selectedTile, colIndex, rowIndex, colSpan, rowSpan)
    }

    init {
        moduleboxItems.addAll( pageBuilderController.smallTiles )
    }
}

private fun passGridInfo(gridInfo: GridInfo): GridPane {
    val metroScope = GridScope()
    metroScope.model.item = gridInfo
    return find<MyTiles>(metroScope).root
}
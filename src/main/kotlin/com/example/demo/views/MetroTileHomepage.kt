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
import com.example.demo.model.*
import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.application.Platform
import javafx.geometry.Point2D
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.input.*
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*
import kotlin.math.roundToInt

class MetroTileHomepage : Fragment() {
    private val loginController: LoginController by inject()
    private val pageBuilderController: PageBuilderController by inject()
    private val workbenchController: WorkbenchController by inject()
    private val controller: MetroTileHomepageController by inject()

    private val paginator = DataGridPaginator(pageBuilderController.smallTiles, itemsPerPage = 8)
    private lateinit var gridInfo: GridInfo

    val pageBuilderScope = PageBuilderScope()
    val dragTileScope = DragTileScope()

    // drag variables
    private var draggingTarget: Tile? = null
    private var moduleBoxItems = mutableListOf<Node>()
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
                            addEventFilter(MouseEvent.MOUSE_PRESSED, ::returnToWorkBench)

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

    private fun returnToWorkBench(evt: MouseEvent) {
        workbenchController.returnToWorkbench(this@MetroTileHomepage)
        evt.consume()
    }

    private fun startDrag(evt : MouseEvent) {

        val targetNode = evt.target as Node
        val topMostTarget = targetNode.findParentOfType(Tile::class)

        moduleBoxItems
                .filter {
                    val mousePt : Point2D = it.sceneToLocal( evt.sceneX, evt.sceneY )
                    it.contains(mousePt)
                }
                .firstOrNull()
                .apply {
                    if( topMostTarget != null ) {
                        println( "topMostTarget=${topMostTarget}")
                        draggingTarget = topMostTarget
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
            if( draggingTarget != null ) {
                val newModule = PageBuilder(100.0, 100.0, Color.DARKGRAY, "module1")
                pageBuilderScope.model.item = newModule

                val newTile = pageBuilderController.moduleTileBuilder(newModule)
                pickGridTile(newTile, mousePt.x, mousePt.y)

                inflightTile.toFront() // don't want to move cursor tracking behind added objects
            }
        }

        draggingTarget = null

        evt.consume()
    }

    private fun pickGridTile(tile: Tile, sceneX: Double, sceneY:Double) {
        val mousePoint= Point2D(sceneX, sceneY)
        val mpLocal = workArea.sceneToLocal(mousePoint)

        getPickedGridTileInfo(mpLocal)
        val rowOffset: Int = ((mpLocal.x - 25)/100).roundToInt() * 10
        val colOffset: Int = ((mpLocal.y - 75)/100).roundToInt() * 10
        val gridColumn: Int = ((mpLocal.x - 25 - rowOffset)/100).roundToInt()
        val gridRow: Int = ((mpLocal.y - 75 - colOffset)/100).roundToInt()
        val tileSpanRow: Int = (pageBuilderScope.model.item.width/100).roundToInt()
        val tileSpanCol: Int = (pageBuilderScope.model.item.height/100).roundToInt()

        if (gridRow <= gridInfo.rows && gridColumn <= gridInfo.columns
                && tileSpanRow == dragTileScope.model.item.rowSpan &&
                tileSpanCol == dragTileScope.model.item.colSpan &&
                draggingTarget != null) {
            workArea.add(tile, gridColumn, gridRow, tileSpanRow, tileSpanCol)
        } else {
            // might just want to drop the tile somewhere in the grid instead
            alert(
                    type = Alert.AlertType.ERROR,
                    header = "Can't drop tile here!",
                    content = "Attempted to drop Tile: \n" +
                            "    Row: " + dragTileScope.model.item.rowIndex + "\n" +
                            "    Column:" + dragTileScope.model.item.colIndex + "\n" +
                            "    Tile RowSpan: " + dragTileScope.model.item.rowSpan + "\n" +
                            "    Tile ColSpan: " + dragTileScope.model.item.colSpan + "\n" +
                            "Over Tile: \n" +
                            "    Row: " + gridRow + "\n" +
                            "    Column:" + gridColumn + "\n" +
                            "     Tile RowSpan: " +  tileSpanRow + "\n" +
                            "     Tile ColSpan: " +  tileSpanCol + "\n"
            )
        }
    }

    private fun getPickedGridTileInfo(point2D: Point2D) {
        val rowOffset: Int = ((point2D.x - 25)/100).roundToInt() * 10
        val colOffset: Int = ((point2D.y - 75)/100).roundToInt() * 10
        val gridColumn: Int = ((point2D.x - 25 - rowOffset)/100).roundToInt()
        val gridRow: Int = ((point2D.y - 75 - colOffset)/100).roundToInt()
        var colIndex = 0
        var rowIndex = 0
        var rowSpan = 0
        var colSpan = 0

        val children = workArea.children

        for (tile in children) {
            val workAreaRow = GridPane.getRowIndex(tile)
            val workAreaCol = GridPane.getColumnIndex(tile)
            if (workAreaRow == gridRow && workAreaCol == gridColumn) {
                colIndex = workAreaCol
                rowIndex = workAreaRow
                colSpan = GridPane.getColumnSpan(tile)
                rowSpan = GridPane.getRowSpan(tile)
                selectedTile = tile as Tile
                workArea.children.remove(tile)
                break
            }
        }
        if (colSpan != 0 || rowSpan != 0) {
            dragTileScope.model.item = DragTile(selectedTile, colIndex, rowIndex, colSpan, rowSpan)
        }  else {
            // might just want to drop the tile somewhere in the grid instead
            alert(
                    type = Alert.AlertType.ERROR,
                    header = "TILE ERROR",
                    content = "Can't drop a tile here."
            )
        }

    }

    init {
        moduleBoxItems.addAll( pageBuilderController.smallTiles )
    }
}

private fun passGridInfo(gridInfo: GridInfo): GridPane {
    val metroScope = GridScope()
    metroScope.model.item = gridInfo
    return find<MyTiles>(metroScope).root
}
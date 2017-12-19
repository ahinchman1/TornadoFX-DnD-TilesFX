package com.example.demo.views
import com.example.demo.app.Styles
import com.example.demo.app.Styles.Companion.highlightTile
import com.example.demo.app.Styles.Companion.metroTileHomepageGUI
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.MetroTileHomepageController
import com.example.demo.controllers.WorkbenchController
import com.example.demo.model.DragTile
import com.example.demo.model.GridInfo
import com.example.demo.model.GridScope
import eu.hansolo.tilesfx.Tile
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.geometry.Point2D
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.control.Alert
import javafx.scene.input.*
import javafx.scene.layout.*
import javafx.scene.text.Font
import tornadofx.*
import java.time.Duration






class MetroTileHomepage : Fragment() {
    private val loginController: LoginController by inject()
    private val workbenchController: WorkbenchController by inject()
    private val controller: MetroTileHomepageController by inject()
    private val paginator = DataGridPaginator(controller.smallTiles, itemsPerPage = 8)
    lateinit var tile: Tile
    lateinit var pickedTile: Tile
    lateinit var leavingTile: Tile
    private lateinit var gridInfo: GridInfo
    private lateinit var grid: GridPane
    private var offset = Point2D(0.0, 0.0)
    private var draggingTile = false

    /* save clipboard for images -- to be implemented later
    companion object {
        private var TILES: DataFormat = DataFormat("eu.hansolo.tilesfx.Tile")
    } */

    override val root = borderpane {
        val gridInfo = GridInfo(controller.useTileGrid(workbenchController.metroTile))
        grid = passGridInfo(gridInfo)
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

            center  = grid.addClass(Styles.grid)

                grid.setOnMouseExited { e ->
                    leaveGrid(e)
                }

                grid.setOnMouseClicked { e ->
                    checkReleaseOutOfGrid(e)
                }

                /* save clipboard for images -- to be implemented later
                grid.setOnDragOver { event ->
                    if (event.dragboard.hasContent(TILES)) event.acceptTransferModes(TransferMode.COPY)
                    event.consume()
                }

                grid.setOnDragDropped { event ->
                    if (event.dragboard.hasContent(TILES)) {
                        event.isDropCompleted = false
                        val db: Dragboard = event.dragboard
                        val node: Node = event.pickResult.intersectedNode
                        if (node != grid && db.hasContent(TILES) && db.getContent(TILES) is DragTile) {
                            // if there isn' a way to use this method, then I'll have to workaround
                            // with a more complicated getPosition
                            val columnIndex = GridPane.getColumnIndex(node)
                            val rowIndex = GridPane.getRowIndex(node)
                            val x = if (columnIndex == null) 0 else columnIndex
                            val y = if (rowIndex == null) 0 else rowIndex
                            dragTileScope.model.item = (db.getContent(TILES) as? DragTile)  // cast to DragTile?
                            // should there be a way to get dataformat to return dragTileScope model format?
                            grid.add(dragTileScope.model.item.tile, x, y,
                                    dragTileScope.model.item.colSpan,
                                    dragTileScope.model.item.rowSpan)
                        }
                    }
                    event.isDropCompleted = true
                    event.consume()
                } */

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
                                    graphic.setOnMousePressed { e ->
                                        tile = e.source as Tile
                                        tile.prefHeight = 100.0 // accessing tile properties are private in Tile
                                        tile.prefWidth = 100.0 // accessing tile properties are private in Tile

                                        //leavingTile = tile.graphic as Tile
                                        startDraggingTile(e)
                                    }
                                    graphic.setOnMouseDragged { e ->
                                        dragTile(e)
                                    }

                                    /* save clipboard for images -- to be implemented later
                                    graphic.setOnDragDetected { event ->
                                        startDragAndDrop(TransferMode.MOVE).apply {
                                            setContent { put(TILES, it) }
                                            event.consume()
                                        }
                                    }*/
                                }
                            }
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
    }

    private fun startDraggingTile(e: MouseEvent) {
        offset = Point2D(e.x, e.y)
        root += tile
        //leavingTile.opacity = 1.0
        //leavingTile.layoutX = tile.layoutX
        //leavingTile.layoutY = tile.layoutY

        draggingTile = true
    }

    private fun dragTile(e: MouseEvent) {
        val mousePoint = Point2D(e.x, e.y)
        val mousePointS = Point2D(e.sceneX, e.sceneY)

        if (!inGrid(mousePointS)) {
            return   // don't relocate() b/c will resize Pane
        }

        val mousePointP = tile.localToParent(mousePoint)
        val x = mousePointP.x - offset.x
        val y = mousePointP.y - offset.y
        tile.relocate(x, y)
        tile.layoutX = x + offset.x
        tile.layoutY = y + offset.y

    }

    private fun finishDraggingTile(e: MouseEvent) {
        offset = Point2D(0.0, 0.0)

        val mousePoint = Point2D(e.x, e.y)
        val mousePointScene = tile.localToScene(mousePoint)

        val timeline = Timeline()
        timeline.cycleCount = 1
        timeline.isAutoReverse = false

        pickGridTile(tile, mousePointScene)
        draggingTile = false

    }

    private fun pickGridTile(tile: Tile, point2D: Point2D) {
        return pickGridTile(tile, point2D.x, point2D.y)
    }

    private fun pickGridTile(tile: Tile, sceneX: Double, sceneY:Double) {
        val mousePoint= Point2D(sceneX, sceneY)
        val mpLocal = grid.sceneToLocal(mousePoint)

        val pickedTile = getPickedGridTileInfo(mpLocal)
        val gridRow: Int = (mpLocal.x/gridInfo.rows) as Int
        val gridColumn: Int = (mpLocal.y/gridInfo.columns) as Int
        val tileSpanRow: Int = (tile.width/100) as Int
        val tileSpanCol: Int = (tile.height/100) as Int

        if (gridRow <= gridInfo.rows && gridColumn <= gridInfo.columns
                && tileSpanRow == pickedTile.rowSpan && tileSpanCol == pickedTile.colSpan) {
            grid.add(tile, gridColumn, gridRow, tileSpanRow, tileSpanCol)
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

        val children = grid.children

        for (tile in children) {
            if (GridPane.getRowIndex(tile) == gridRow && GridPane.getColumnIndex(tile) == gridColumn) {
                colIndex = GridPane.getColumnIndex(tile)
                rowIndex = GridPane.getRowIndex(tile)
                pickedTile = tile as Tile
                rowSpan = (pickedTile.width / 100.0) as Int
                colSpan = (pickedTile.height / 100.0) as Int
                break
            }
        }

        return DragTile(pickedTile, colIndex, rowIndex, colSpan, rowSpan)
    }

    private fun inGrid(pt: Point2D): Boolean {
        val gridPt = grid.sceneToLocal(pt)
        return (gridPt.x - offset.x >= 0.0
                && gridPt.y - offset.y >= 0.0
                && gridPt.x <= grid.width
                && gridPt.y <= grid.height)
    }

    private fun checkReleaseOutOfGrid(e: MouseEvent) {
        val mousePointScene = Point2D(e.sceneX, e.sceneY)
        if (!inGrid(mousePointScene)) {
            leaveGrid(e)
            e.consume()
        } else {
            finishDraggingTile(e)
        }
    }

    private fun leaveGrid(event: MouseEvent) {
        if (draggingTile) {
            val timeline: Timeline = Timeline()

            draggingTile = false

            //timeline.keyFrames.add(KeyFrame(Duration(200.0)),
              //      KeyValue(tile.layoutXProperty(), tile.get))
        }
    }
}

private fun passGridInfo(gridInfo: GridInfo): GridPane {
    val metroScope = GridScope()
    metroScope.model.item = gridInfo
    return find<MyTiles>(metroScope).root
}



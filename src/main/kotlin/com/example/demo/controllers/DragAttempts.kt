package com.example.demo.controllers


/**
 * Previous attempts I'm afraid to let go of yet
 */

import com.example.demo.model.GridInfo
import com.example.demo.model.GridScope
import com.example.demo.views.MetroTileHomepage
import com.example.demo.views.MyTiles
import eu.hansolo.tilesfx.Tile
import javafx.event.EventHandler
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.input.DragEvent
import javafx.scene.input.Dragboard
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import tornadofx.*

/*class DragController: Controller() {
    private val workbenchController: WorkbenchController by inject()
    private val controller: MetroTileHomepageController by inject()
    private val view: MetroTileHomepage by inject()
    private val gridInfo = GridInfo(controller.useTileGrid(workbenchController.metroTile))
    val grid = passGridInfo(gridInfo)


}

private fun passGridInfo(gridInfo: GridInfo): GridPane {
    val metroScope = GridScope()
    metroScope.model.item = gridInfo
    return find<MyTiles>(metroScope).root
}*/

/*private fun drop(evt : MouseEvent) {

    println("in drop")

    val mousePt = grid.sceneToLocal( evt.sceneX, evt.sceneY )
    if( grid.contains(mousePt) ) {
        println("drop in workarea")
        if( draggingColor != null ) {
            println("exec drop")
            val tile = Tile(RECTANGLE_HEIGHT, RECTANGLE_WIDTH, draggingColor)
            workArea.add( tile )
            tile.relocate( mousePt.x, mousePt.y )
        }
    }

    draggingColor = null
}

init {
    moduleItems.addAll( modules.childrenUnmodifiable )
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
}*/

/*grid.setOnMouseExited { e ->
    leaveGrid(e)
}

grid.setOnMouseClicked { e ->
    checkReleaseOutOfGrid(e)
}

 save clipboard for images -- to be implemented later
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

/* save clipboard for images -- to be implemented later
companion object {
    private var TILES: DataFormat = DataFormat("eu.hansolo.tilesfx.Tile")
} */



/*
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
import kotlin.math.roundToInt

class MetroTileHomepage : Fragment() {
    private val loginController: LoginController by inject()
    private val pageBuilderController: PageBuilderController by inject()
    private val workbenchController: WorkbenchController by inject()
    private val controller: MetroTileHomepageController by inject()

    private val paginator = DataGridPaginator(pageBuilderController.smallTiles, itemsPerPage = 8)
    private lateinit var gridInfo: GridInfo

    private var dragTile: DragTile by singleAssign()

    // drag variables
    private var moduleBoxItems = mutableListOf<Node>()
    private var workArea: GridPane by singleAssign()
    private var inFlightTile: PageBuilder by singleAssign()

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
                        // create inflightTile
                        for (pageBuilder in pageBuilderController.smallTilesPageBuilderCollection) {
                            inFlightTile = pageBuilder
                            dragTile.tile.addClass(inflight)
                            dragTile.tile.isVisible = false
                            add(dragTile.tile)
                            if (dragTile.tile.properties  == topMostTarget.properties) {
                                passDragTileInfo(pageBuilder)

                                dragTile.rowSpan = (pageBuilder.width/100).roundToInt()
                                dragTile.colSpan = (pageBuilder.height/100).roundToInt()
                            }
                        }
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
            if( !dragTile.tile.isVisible ) {
                dragTile.tile.isVisible = true
            }

            dragTile.tile.relocate( mousePt.x, mousePt.y )
        }

    }

    private fun stopDrag(evt: MouseEvent) {
        if( workArea.hasClass(workAreaSelected ) ) {
            workArea.removeClass(workAreaSelected)
        }
        if( dragTile.tile.isVisible ) {
            dragTile.tile.isVisible = false
        }
    }

    private fun drop(evt : MouseEvent) {

        val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
        if( workArea.contains(mousePt) ) {
            if( dragTile.tile != null ) {
                val newTile = dragTile.tile

                pickGridTile(newTile, mousePt.x, mousePt.y)
                dragTile.tile.toFront() // don't want to move cursor tracking behind added objects
            }
        }

        dragTile.tile = null

        evt.consume()
    }

    private fun pickGridTile(tile: Tile, sceneX: Double, sceneY:Double) {
        val mousePoint= Point2D(sceneX, sceneY)
        val mpLocal = workArea.sceneToLocal(mousePoint)

        val selectedTile: DragTile = getPickedGridTileInfo(mpLocal)
        val rowOffset: Int = ((mpLocal.x - 25)/100).roundToInt() * 10
        val colOffset: Int = ((mpLocal.y - 75)/100).roundToInt() * 10
        val gridColumn: Int = ((mpLocal.x - 25 - rowOffset)/100).roundToInt()
        val gridRow: Int = ((mpLocal.y - 75 - colOffset)/100).roundToInt()

        if (gridRow <= gridInfo.rows && gridColumn <= gridInfo.columns
                && dragTile.rowSpan == selectedTile.rowSpan &&
                dragTile.colSpan == selectedTile.colSpan &&
                dragTile.tile != null) {
            workArea.add(tile, gridColumn, gridRow, dragTile.rowSpan, dragTile.colSpan)
        } else {
            // might just want to drop the tile somewhere in the grid instead
            alert(
                    type = Alert.AlertType.ERROR,
                    header = "Can't drop tile here!",
                    content = "Attempted to drop Tile at: \n" +
                            "    Row: " + selectedTile.rowIndex + "\n" +
                            "    Column:" + selectedTile.colIndex + "\n" +
                            "    Tile RowSpan: " + selectedTile.rowSpan + "\n" +
                            "    Tile ColSpan: " + selectedTile.colSpan + "\n" +
                            "Over Tile: \n" +
                            "    Row: " + gridRow + "\n" +
                            "    Column:" + gridColumn + "\n" +
                            "     Tile RowSpan: " +  dragTile.rowSpan + "\n" +
                            "     Tile ColSpan: " +  dragTile.colSpan + "\n"
            )
        }
    }

    private fun getPickedGridTileInfo(point2D: Point2D): DragTile {
        val rowOffset: Int = ((point2D.x - 25)/100).roundToInt() * 10
        val colOffset: Int = ((point2D.y - 75)/100).roundToInt() * 10
        val gridColumn: Int = ((point2D.x - 25 - rowOffset)/100).roundToInt()
        val gridRow: Int = ((point2D.y - 75 - colOffset)/100).roundToInt()
        var colIndex = 0
        var rowIndex = 0
        var rowSpan = 0
        var colSpan = 0
        var dropTile: DragTile by singleAssign()

        val children = workArea.children

        for (tile in children) {
            val workAreaRow = GridPane.getRowIndex(tile)
            val workAreaCol = GridPane.getColumnIndex(tile)
            if (workAreaRow == gridRow && workAreaCol == gridColumn) {
                colIndex = workAreaCol
                rowIndex = workAreaRow
                colSpan = GridPane.getColumnSpan(tile)
                rowSpan = GridPane.getRowSpan(tile)
                workArea.children.remove(tile)
                break
            }
        }
        if (colSpan != 0 || rowSpan != 0) {
            dropTile = DragTile(dragTile.tile, colIndex, rowIndex, colSpan, rowSpan, dragTile.color, dragTile.title )
        }  else {
            // might just want to drop the tile somewhere in the grid instead
            alert(
                    type = Alert.AlertType.ERROR,
                    header = "TILE ERROR",
                    content = "Can't drop a tile here."
            )
        }
        return dropTile

    }

    private fun passDragTileInfo(pageBuilder: PageBuilder) {
        val dragTileScope = DragTileScope()
        dragTileScope.model.item = dragTile
        dragTile.color = pageBuilder.tileColor
        dragTile.title = pageBuilder.title
        dragTile.tile = pageBuilderController.moduleTileBuilder(pageBuilder)
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

 */
package com.example.demo.controllers

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
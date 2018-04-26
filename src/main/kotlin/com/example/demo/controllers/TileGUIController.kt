package com.example.demo.controllers

import com.example.demo.app.Styles
import com.example.demo.model.DragTile
import com.example.demo.model.GridInfoModel
import com.example.demo.views.MyTiles
import com.example.demo.views.TileGUI
import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import tornadofx.*

class TileGUIController : Controller() {

    private lateinit var hoverTile: Tile
    private lateinit var hoverTileProperties: com.example.demo.model.TileBuilder
    private val controller: TileBuilderController by inject()
    private val workbenchController: WorkbenchController by inject()
    private val view: TileGUI by inject()
    private val myTiles: MyTiles by inject()
    private val gridInfoModel: GridInfoModel by inject()

    // drag variables
    private lateinit var dragTile: DragTile
    private var isDragAndDrop = true
    private var isImageTile = false
    private lateinit var inFlightTile: Tile
    private lateinit var inFlightTileProperties: com.example.demo.model.TileBuilder
    private lateinit var originalTileProperties: com.example.demo.model.TileBuilder


    /**
     * Grid Tile Builder, simplified tile
     *
     * @property MouseEvent evt
     * @property StackPane workArea
     * @property Tile tile
     * @property TileBuilder property
     */
    private fun addHoverTile(evt: MouseEvent, workArea: StackPane, tile: Tile, property: com.example.demo.model.TileBuilder) {

        val bounds = tile.layoutBounds
        val coordinates = tile.localToScene(bounds.minX, bounds.minY)
        val tileX = coordinates.x
        val tileY = coordinates.y

        hoverTileProperties = property.copy()
        hoverTileProperties.color = property.hoverColor
        hoverTileProperties.width = tile.widthProperty().value
        hoverTileProperties.height = tile.heightProperty().value
        hoverTileProperties.colSpan = property.colSpan
        hoverTileProperties.rowSpan = property.rowSpan
        hoverTile = controller.moduleTileBuilder(hoverTileProperties)
        workArea.children[1].add(hoverTile)
        hoverTile.isMouseTransparent = true
        hoverTile.translateX = tileX
        hoverTile.translateY = tileY
        evt.consume()
    }

    /**
     * Grid Tile Builder, simplified tile
     *
     * @property MouseEvent evt
     * @property StackPane workArea
     */
    private fun removeHoverTile(evt: MouseEvent, workArea: StackPane) {
        if (::hoverTileProperties.isInitialized) {
            workArea.children[1].getChildList()!!.remove(hoverTile)
        }
        evt.consume()
    }

    /**
     * Reproduce and remove tiles with hover colors as you hover over
     * dropped tiles
     *
     * @property MouseEvent evt
     */
    fun hoverBehavior(evt: MouseEvent) {
        for (child in myTiles.root.children) {
            if (child is Tile && child.titleProperty().value.toIntOrNull() == null && ::inFlightTileProperties.isInitialized) {
                child.setOnMouseEntered {
                    val title = child.titleProperty().value

                    // TODO: change hashmap to grid output
                    controller.createGridTilesHashMap[title]?.let {
                        inFlightTileProperties = it
                    }

                    if (title != "Hi Admin") {
                        addHoverTile(evt, view.root, child, inFlightTileProperties)
                    }
                }
                child.setOnMouseExited {
                    removeHoverTile(evt, view.root)
                }
            }
        }
    }

    /**
     * Grabs a tile and its properties to prepare for the animateDrag
     * and drop events.
     *
     * @property MouseEvent evt
     */
    fun startDrag(evt : MouseEvent) {

        val targetNode = evt.target as Node
        val tileTarget = targetNode.findParentOfType(Tile::class)

        val mousePt : Point2D = view.root.sceneToLocal( evt.sceneX, evt.sceneY )
        view.moduleBoxItems
                .filter {
                    it.contains(mousePt)
                }
                .apply {
                    // select tile from data grid
                    if (tileTarget is Tile && !myTiles.root.contains(mousePt)) {
                        val title = tileTarget.titleProperty().value
                        val color = tileTarget.backgroundColorProperty().value
                        var titleColor = Color.WHITE

                        if (color != Color.TRANSPARENT) {
                            controller.hashmap[title]?.let {
                                originalTileProperties = it
                            }
                            setProperties()
                            inFlightTileProperties.title = title
                        }
                        else {
                            inFlightTileProperties = originalTileProperties.copy()
                            inFlightTileProperties.color = color.toString()
                            inFlightTileProperties.hoverColor = color.toString()
                            inFlightTileProperties.image = title
                            titleColor = Color.TRANSPARENT
                            isImageTile = true
                        }
                        inFlightTileProperties.width = 100.0
                        inFlightTileProperties.height = 100.0
                        inFlightTile = controller.moduleTileBuilder(inFlightTileProperties, titleColor)
                        inFlightTile.isVisible = false
                        view.root.children[1].add(inFlightTile)
                        isDragAndDrop = true
                    }
                }

    }

    /**
     * Renders a tile the user can drag to the desired grid location
     *
     * @property MouseEvent evt
     */
    fun animateDrag(evt : MouseEvent) {

        val mousePt = view.root.sceneToLocal( evt.sceneX, evt.sceneY )
        val targetNode = evt.target as Node

        val tileTarget = targetNode.findParentOfType(Tile::class)

        if (tileTarget != null) {
            val tileTargetParent = tileTarget.parent
            if( view.root.contains(mousePt) && (tileTargetParent !is GridPane) ) {

                // animate a rectangle so that the user can follow
                if( !inFlightTile.isVisible ) {
                    inFlightTile.isVisible = true
                }
                inFlightTile.toFront()
                val widthOffset = inFlightTile.widthProperty().value/2
                val heightOffset = inFlightTile.heightProperty().value/2
                inFlightTile.relocate( mousePt.x - widthOffset, mousePt.y - heightOffset)
            }
        }
    }

    /**
     * Highlight the workArea and hide the draggingTile node
     *
     * @property MouseEvent evt
     */
    fun stopDrag(evt: MouseEvent) {
        val targetNode = evt.target as Node
        val tileTarget = targetNode.findParentOfType(Tile::class)

        if (tileTarget is Tile &&
                ::inFlightTileProperties.isInitialized  &&
                inFlightTile.isVisible ) {
            inFlightTile.isVisible = false
        }
    }

    /**
     * Compare selected dragging tile with the location of the drop
     * and render the module tile accordingly
     *
     * @property MouseEvent evt
     */
    fun drop(evt : MouseEvent, returnView: UIComponent) {

        val mousePt = myTiles.root.sceneToLocal( evt.sceneX, evt.sceneY )
        val targetNode = evt.target as Node
        val tileTarget = targetNode.findParentOfType(Tile::class)

        val buttonTarget = targetNode.findParentOfType(Button::class)

        if (::inFlightTileProperties.isInitialized && isDragAndDrop) {
            if (tileTarget is Tile && myTiles.root.contains(mousePt) &&
                    inFlightTileProperties.title.toIntOrNull() == null) {
                pickGridTile(mousePt.x, mousePt.y, evt)
                view.root.children[1].getChildList()!!.remove(inFlightTile)
                isDragAndDrop = false
            }
            inFlightTileProperties.width = 100.0
            inFlightTileProperties.height = 100.0
            evt.consume()
        }

        if (buttonTarget is Button && buttonTarget.textProperty().value == "Return to Workbench") {
            workbenchController.returnToWorkbench(returnView)
            evt.consume()
        }

    }

    /**
     * Compare selected dragging tile with the location of the drop
     * and render the module tile accordingly
     *
     * @property MouseEvent evt
     * @property Double sceneX
     * @property Double sceneY
     */
    private fun pickGridTile(sceneX: Double, sceneY:Double, evt: MouseEvent) {
        val mousePoint= Point2D(sceneX, sceneY)
        val mpLocal = myTiles.root.sceneToLocal(mousePoint)

        val rowOffset: Int = ((mpLocal.x - 35)/100).toInt() * 10
        val colOffset: Int = ((mpLocal.y - 85)/100).toInt() * 10
        val gridColumn: Int = ((mpLocal.x - 35 - rowOffset)/100).toInt()
        val gridRow: Int = ((mpLocal.y - 85 - colOffset)/100).toInt()

        // tileExchange is the pair the tiles, one to remove from the grid and
        // the other either the dragged tile or the resized version of the dragged tile
        val removeTile: Tile = getPickedGridTileInfo(gridRow, gridColumn, evt)
        val gridTitleTile = removeTile.titleProperty().value

        if (gridRow < gridInfoModel.item.rows &&
                gridColumn < gridInfoModel.item.columns &&
                gridTitleTile != "Hi Admin" && isDragAndDrop) {

            myTiles.root.children.remove(removeTile)
            myTiles.root.add(dragTile.tile, dragTile.colIndex, dragTile.rowIndex, dragTile.colSpan, dragTile.rowSpan)
            isImageTile = false
        }

    }

    /**
     * Compare selected dragging tile with the location of the drop
     * and render the module tile accordingly, assigns the tile to be dropped
     * to dragTile and the tile to be removed from the grid
     *
     * @property Int gridRow
     * @property Int gridColumn
     */
    private fun getPickedGridTileInfo(gridRow: Int, gridColumn: Int, evt: MouseEvent): Tile {
        var colIndex = 0
        var rowIndex = 0
        var rowSpan = 0
        var colSpan = 0
        lateinit var removeTile: Tile

        val children = myTiles.root.children

        loop@ for (gridTile in children) {
            if (gridTile is Tile) {
                rowIndex = GridPane.getRowIndex(gridTile)
                colIndex = GridPane.getColumnIndex(gridTile)
                colSpan = GridPane.getColumnSpan(gridTile)
                rowSpan = GridPane.getRowSpan(gridTile)
                removeTile = gridTile
                isDragAndDrop = false

                // detect for the possibility that the selected gridRow/gridColumn might be in
                // within a tile in w span(s) > 1
                if (rowSpan > 1 || colSpan > 1) {
                    for (i in 0..(rowSpan-1)) {
                        for (j in 0..(colSpan-1)) {
                            val compareRowIndex = rowIndex + i
                            val compareColumnIndex = colIndex + j
                            if (gridRow == compareRowIndex && gridColumn == compareColumnIndex) {
                                // resize tile
                                inFlightTileProperties.width = removeTile.widthProperty().value
                                inFlightTileProperties.height = removeTile.heightProperty().value
                                isDragAndDrop = true
                                break@loop
                            }
                        }
                    }
                } else {
                    if (rowIndex == gridRow && colIndex == gridColumn) {
                        isDragAndDrop = true
                        break@loop
                    }
                }
            }
        }

        if (inFlightTile.titleColorProperty().value  != c("0xffffffff")) {
            val image = inFlightTileProperties.image
            val title = removeTile.titleProperty().value
            if (controller.createGridTilesHashMap.containsKey(title)) {
                controller.createGridTilesHashMap[title]?.let {
                    originalTileProperties = it
                }

                setProperties()
                inFlightTileProperties.title = title
                inFlightTileProperties.image = image
            }
        }

        val draggedTile = controller.moduleTileBuilder(inFlightTileProperties)
        controller.createGridTilesHashMap.put(inFlightTileProperties.title, inFlightTileProperties.copy())
        dragTile = DragTile(draggedTile, colSpan, rowSpan, colIndex, rowIndex)

        return removeTile
    }

    fun selectTile(evt: MouseEvent, module: com.example.demo.model.TileBuilder) {
        val targetNode = evt.target as Node
        val tileTarget = targetNode.findParentOfType(Tile::class)
        val mousePt : Point2D = view.root.sceneToLocal( evt.sceneX, evt.sceneY )

        if (myTiles.root.contains(mousePt) && tileTarget!!.graphicProperty().value != null &&
                !tileTarget.hasClass(Styles.selectedTile) &&
                tileTarget.titleProperty().value.toIntOrNull() == null) {

            myTiles.root.children.forEach {
                if (it.hasClass(Styles.selectedTile)) {
                    it.removeClass(Styles.selectedTile)
                }
                if (it == tileTarget) {
                    it.addClass(Styles.selectedTile)
                }
            }

            val title: String = tileTarget.titleProperty().value

            controller.createGridTilesHashMap[title]?.let {
                module.titleProperty.set(it.title)
                module.colorProperty.set(it.hoverColor)
                module.hoverColorProperty.set(it.hoverColor)
            }
        }
    }

    private fun setProperties() {
        inFlightTileProperties = originalTileProperties.copy()
        inFlightTileProperties.color = originalTileProperties.color
        inFlightTileProperties.hoverColor = originalTileProperties.hoverColor
    }
}
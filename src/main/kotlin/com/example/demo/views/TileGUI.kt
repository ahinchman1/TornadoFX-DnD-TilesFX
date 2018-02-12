package com.example.demo.views
import com.example.demo.app.Styles
import com.example.demo.app.Styles.Companion.tileGUI
import com.example.demo.app.Styles.Companion.transparentOverlay
import com.example.demo.app.Styles.Companion.workAreaSelected
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.TileBuilderController
import com.example.demo.controllers.TileGUIController
import com.example.demo.controllers.WorkbenchController
import com.example.demo.model.DragTile
import com.example.demo.model.GridInfo
import com.example.demo.model.GridScope
import com.example.demo.model.TileBuilder
import eu.hansolo.tilesfx.Tile
import javafx.application.Platform
import javafx.geometry.Point2D
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.text.Font
import tornadofx.*
import kotlin.math.roundToInt

class TileGUI : Fragment() {

    /***** Global Variables *****/
    private val loginController: LoginController by inject()
    private val tileBuilderController: TileBuilderController by inject()
    private val workbenchController: WorkbenchController by inject()
    private val controller: TileGUIController by inject()

    private lateinit var gridInfo: GridInfo

    // drag variables
    private lateinit var dragTile: DragTile
    private var isDragAndDrop = true
    private var moduleBoxItems = mutableListOf<Node>()
    private var workArea: GridPane by singleAssign()
    private lateinit var inFlightTile: Tile
    private lateinit var inFlightTileProperties: TileBuilder

    /***** View *****/
    override val root = stackpane {
        gridInfo = GridInfo(controller.useTileGrid(workbenchController.tile))
        workArea = passGridInfo(gridInfo)
        setPrefSize(1000.0, 650.0)

        borderpane {
            addClass(tileGUI)
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
                            datagrid(tileBuilderController.tileList) {
                                maxCellsInRow = 2
                                cellWidth = 100.0
                                cellHeight = 100.0

                                paddingTop = 15.0
                                paddingLeft = 35.0
                                minWidth = 300.0

                                cellFormat {
                                    graphic = cache {
                                        it
                                    }.apply {
                                        setOnMouseEntered {
                                            addClass(Styles.highlightTile)
                                        }

                                        setOnMouseExited {
                                            removeClass(Styles.highlightTile)
                                        }
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

    /**
     * Reproduce and remove tiles with hover colors as you hover over
     * dropped tiles
     *
     * @property MouseEvent evt
     */
    private fun hoverBehavior(evt: MouseEvent) {
        for (child in workArea.children) {
            if (child is Tile && child.titleProperty().value.toIntOrNull() == null && ::inFlightTileProperties.isInitialized) {
                child.setOnMouseEntered {
                    val title = child.titleProperty().value

                    tileBuilderController.hashmap[title]?.let {
                        inFlightTileProperties = it
                    }

                    controller.addHoverTile(evt, root, child, inFlightTileProperties)
                }
                child.setOnMouseExited {
                    controller.removeHoverTile(evt, root)
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
    private fun startDrag(evt : MouseEvent) {

        val targetNode = evt.target as Node
        val tileTarget = targetNode.findParentOfType(Tile::class)
        val mousePt : Point2D = root.sceneToLocal( evt.sceneX, evt.sceneY )
        moduleBoxItems
                .filter {
                    it.contains(mousePt)
                }
                .apply {
                    // select tile from data grid
                    if (tileTarget is Tile && !workArea.contains(mousePt)) {
                        val title = tileTarget.titleProperty().value

                        tileBuilderController.hashmap[title]?.let {
                            inFlightTileProperties = it
                        }
                        inFlightTile = tileBuilderController.moduleTileBuilder(inFlightTileProperties)
                        inFlightTile.isVisible = false
                        root.children[1].add(inFlightTile)
                        isDragAndDrop = true
                    }

                    // select dropped tile in work area
                    if (tileTarget is Tile && workArea.contains(mousePt) && tileTarget.graphicProperty().value != null) {
                        // initialize values in the text fields
                    }
                }

    }

    /**
     * Renders a tile the user can drag to the desired grid location
     *
     * @property MouseEvent evt
     */
    private fun animateDrag(evt : MouseEvent) {

        val mousePt = root.sceneToLocal( evt.sceneX, evt.sceneY )
        val targetNode = evt.target as Node
        val tileTarget = targetNode.findParentOfType(Tile::class)
        if (tileTarget != null) {
            val tileTargetParent = tileTarget.parent
            if( root.contains(mousePt) && (tileTargetParent !is GridPane) ) {

                // highlight the drop target (hover doesn't work)
                if( !workArea.hasClass(workAreaSelected)) {
                    workArea.addClass(workAreaSelected)
                }

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
    private fun stopDrag(evt: MouseEvent) {
        val targetNode = evt.target as Node
        val tileTarget = targetNode.findParentOfType(Tile::class)

        if (workArea.hasClass(workAreaSelected)) {
            workArea.removeClass(workAreaSelected)
        }

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
    private fun drop(evt : MouseEvent) {

        val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
        val targetNode = evt.target as Node
        val tileTarget = targetNode.findParentOfType(Tile::class)

        val buttonTarget = targetNode.findParentOfType(Button::class)

        if (::inFlightTileProperties.isInitialized && isDragAndDrop) {
            if (tileTarget is Tile && workArea.contains(mousePt) &&
                    inFlightTileProperties.title.toIntOrNull() == null) {
                pickGridTile(mousePt.x, mousePt.y)
                root.children[1].getChildList()!!.remove(inFlightTile)

                inFlightTileProperties.width = 100.0
                inFlightTileProperties.height = 100.0
                isDragAndDrop = false
            }

        }

        if (buttonTarget is Button && buttonTarget.textProperty().value == "Return to Workbench") {
            workbenchController.returnToWorkbench(this@TileGUI)
        }

        evt.consume()
    }

    /**
     * Compare selected dragging tile with the location of the drop
     * and render the module tile accordingly
     *
     * @property MouseEvent evt
     * @property Double sceneX
     * @property Double sceneY
     */
    private fun pickGridTile(sceneX: Double, sceneY:Double) {
        val mousePoint= Point2D(sceneX, sceneY)
        val mpLocal = workArea.sceneToLocal(mousePoint)

        val rowOffset: Int = ((mpLocal.x - 35)/100).roundToInt() * 10
        val colOffset: Int = ((mpLocal.y - 85)/100).roundToInt() * 10
        val gridColumn: Int = ((mpLocal.x - 35 - rowOffset)/100).roundToInt()
        val gridRow: Int = ((mpLocal.y - 85 - colOffset)/100).roundToInt()

        // tileExchange is the pair the tiles, one to remove from the grid and
        // the other either the dragged tile or the resized version of the dragged tile
        val removeTile: Tile = getPickedGridTileInfo(gridRow, gridColumn)
        val gridTitleTile = removeTile.titleProperty().value

        if (gridRow < gridInfo.rows &&
                gridColumn < gridInfo.columns &&
                gridTitleTile != "Hi Admin" && isDragAndDrop) {

            workArea.children.remove(removeTile)
            workArea.add(dragTile.tile, dragTile.colIndex, dragTile.rowIndex, dragTile.colSpan, dragTile.rowSpan)
        }
    }

    /**
     * Compare selected dragging tile with the location of the drop
     * and render the module tile accordingly, returns the tile to be dropped
     * and the tile to be removed from the grid
     *
     * @property Int gridRow
     * @property Int gridColumn
     */
    private fun getPickedGridTileInfo(gridRow: Int, gridColumn: Int): Tile {
        var colIndex = 0
        var rowIndex = 0
        var rowSpan = 0
        var colSpan = 0
        lateinit var removeTile: Tile

        val children = workArea.children

        loop@ for (gridTile in children) {
            rowIndex = GridPane.getRowIndex(gridTile)
            colIndex = GridPane.getColumnIndex(gridTile)
            colSpan = GridPane.getColumnSpan(gridTile)
            rowSpan = GridPane.getRowSpan(gridTile)
            removeTile = gridTile as Tile
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
        val draggedTile = tileBuilderController.moduleTileBuilder(inFlightTileProperties)
        dragTile = DragTile(draggedTile, colSpan, rowSpan, colIndex, rowIndex)

        return removeTile
    }

    // init
    init {
        moduleBoxItems.addAll( tileBuilderController.tileList )
    }
}

/**
 * Render workarea by passing chosen grid information.
 *
 * @property GridInfo gridInfo
 */
private fun passGridInfo(gridInfo: GridInfo): GridPane {
    val gridScope = GridScope()
    gridScope.model.item = gridInfo
    return find<MyTiles>(gridScope).root
}
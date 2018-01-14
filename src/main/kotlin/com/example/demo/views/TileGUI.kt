package com.example.demo.views
import com.example.demo.app.Styles
import com.example.demo.app.Styles.Companion.highlightTile
import com.example.demo.app.Styles.Companion.metroTileHomepageGUI
import com.example.demo.app.Styles.Companion.transparentOverlay
import com.example.demo.app.Styles.Companion.workAreaSelected
import com.example.demo.controllers.LoginController
import com.example.demo.controllers.TileGUIController
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

    /***** Global Variables *****/
    private val loginController: LoginController by inject()
    private val pageBuilderController: PageBuilderController by inject()
    private val workbenchController: WorkbenchController by inject()
    private val controller: TileGUIController by inject()

    private lateinit var gridInfo: GridInfo

    // drag variables
    private lateinit var dropTile: DragTile
    private var moduleBoxItems = mutableListOf<Node>()
    var workArea: GridPane by singleAssign()
    private lateinit var inflightTile: Tile
    private lateinit var inflightTileProperties: PageBuilder

    /***** View *****/
    override val root = stackpane {
        gridInfo = GridInfo(controller.useTileGrid(workbenchController.metroTile))
        workArea = passGridInfo(gridInfo)
        setPrefSize(1000.0, 650.0)

        borderpane {
            addClass(metroTileHomepageGUI)
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
                                datagrid(pageBuilderController.smallTiles) {
                                    maxCellsInRow = 2
                                    cellWidth = 100.0
                                    cellHeight = 100.0

                                    paddingTop = 15.0
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
                                    marginTop = 70.0
                                }
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

        addEventFilter(MouseEvent.MOUSE_PRESSED, ::startDrag)
        addEventFilter(MouseEvent.MOUSE_DRAGGED, ::animateDrag)
        addEventFilter(MouseEvent.MOUSE_RELEASED, ::stopDrag)
        addEventFilter(MouseEvent.MOUSE_RELEASED, ::drop)
    }

    /***** Methods *****/

    /**
     * Returns to workbench, but allows you to return to the values
     * and settings cached in the grid. Asks to save should the user
     * forget to submit a desired grid  (TO BE COMPLETED).
     *
     * @param [MouseEvent] evt
     */
    private fun returnToWorkBench(evt: MouseEvent) {
        workbenchController.returnToWorkbench(this@MetroTileHomepage)
        evt.consume()
    }

    /**
     * Grabs a tile and its properties to prepare for the animateDrag
     * and drop events.
     *
     * @param [MouseEvent] evt
     */
    private fun startDrag(evt : MouseEvent) {

        var targetNode = evt.target as Node
        var tileTarget = targetNode.findParentOfType(Tile::class)
        moduleBoxItems
                .filter {
                    val mousePt : Point2D = root.sceneToLocal( evt.sceneX, evt.sceneY )
                    it.contains(mousePt)
                }
                .firstOrNull()
                .apply {
                    if(tileTarget is Tile && tileTarget!!.titleProperty().value != null) {
                        var width = tileTarget!!.widthProperty().value
                        var height = tileTarget!!.heightProperty().value
                        var tileColor = tileTarget!!.backgroundColorProperty().value
                        var title = tileTarget!!.titleProperty().value

                        inflightTileProperties = PageBuilder(width, height, tileColor, title)
                        inflightTile = pageBuilderController.moduleTileBuilder(inflightTileProperties)
                        inflightTile.isVisible = false
                        root.children[1].add(inflightTile)
                    }
                }

    }

    /**
     * Renders a tile the user can drag to the desired grid location
     *
     * @param [MouseEvent] evt
     */
    private fun animateDrag(evt : MouseEvent) {

        val mousePt = root.sceneToLocal( evt.sceneX, evt.sceneY )
        var targetNode = evt.target as Node
        var tileTarget = targetNode.findParentOfType(Tile::class)
        if (tileTarget != null) {
            var tileTargetParent = tileTarget!!.parent
            if( root.contains(mousePt) && (tileTargetParent !is GridPane) ) {

                // highlight the drop target (hover doesn't work)
                if( !workArea.hasClass(workAreaSelected)) {
                    workArea.addClass(workAreaSelected)
                }

                // animate a rectangle so that the user can follow
                if( !inflightTile.isVisible ) {
                    inflightTile.isVisible = true
                }
                inflightTile.toFront()
                val widthOffset = inflightTile.widthProperty().value/2
                val heightOffset = inflightTile.heightProperty().value/2
                inflightTile.relocate( mousePt.x - widthOffset, mousePt.y - heightOffset)
            }
        }

    }

    /**
     * Highlight the workarea and hide the draggingTile node
     *
     * @param [MouseEvent] evt
     */
    private fun stopDrag(evt: MouseEvent) {
        var targetNode = evt.target as Node
        var tileTarget = targetNode.findParentOfType(Tile::class)

        if( workArea.hasClass(workAreaSelected ) ) {
            workArea.removeClass(workAreaSelected)
        }
        if(tileTarget is Tile && (inflightTileProperties.title.toIntOrNull() != null)
                && inflightTile.isVisible ) {
            inflightTile.isVisible = false
        }
    }

    /**
     * Compare selected dragging tile with the location of the drop
     * and render the module tile accordingly
     *
     * @param [MouseEvent] evt
     */
    private fun drop(evt : MouseEvent) {

        val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
        var targetNode = evt.target as Node
        var tileTarget = targetNode.findParentOfType(Tile::class)

        if (tileTarget is Tile && workArea.contains(mousePt) &&
                inflightTileProperties.title.toIntOrNull() == null) {
            if (inflightTileProperties.tileColor != null ) {
                var dropPickedTile: Tile = pageBuilderController.moduleTileBuilder(inflightTileProperties)
                pickGridTile(evt, dropPickedTile, mousePt.x, mousePt.y)
                root.children[1].getChildList()!!.remove(inflightTile)
            }
        }

        evt.consume()
    }

    /**
     * Compare selected dragging tile with the location of the drop
     * and render the module tile accordingly
     *
     * @param [Tile] tile
     * @param [Double] sceneX
     * @param [Double] sceneY
     */
    private fun pickGridTile(evt : MouseEvent, tile: Tile, sceneX: Double, sceneY:Double) {
        val mousePoint= Point2D(sceneX, sceneY)
        val mpLocal = workArea.sceneToLocal(mousePoint)

        val rowOffset: Int = ((mpLocal.x - 25)/100).roundToInt() * 10
        val colOffset: Int = ((mpLocal.y - 85)/100).roundToInt() * 10
        val gridColumn: Int = ((mpLocal.x - 25 - rowOffset)/100).roundToInt()
        val gridRow: Int = ((mpLocal.y - 85 - colOffset)/100).roundToInt()
        val tileSpanRow: Int = (inflightTileProperties.height/100).roundToInt()
        val tileSpanCol: Int = (inflightTileProperties.width/100).roundToInt()

        // helper function
        val pickedTileInfo: Pair<Pair<Int, Int>, Tile> = getPickedGridTileInfo(gridRow, gridColumn,
                tileSpanRow, tileSpanCol)
        val pickedTileSpans: Pair<Int, Int> = pickedTileInfo.first
        val pickedGridTileRowSpan = pickedTileSpans.first
        val pickedGridTileColSpan = pickedTileSpans.second

        if (dropTile.title != null &&
                (gridRow <= gridInfo.rows  && gridRow == dropTile.rowIndex) &&
                (gridColumn <= gridInfo.columns && gridColumn == dropTile.colIndex) &&
                tileSpanRow == dropTile.rowSpan &&
                tileSpanCol == dropTile.colSpan) {

            workArea.children.remove(pickedTileInfo.second)
            workArea.add(tile, gridColumn, gridRow, tileSpanRow, tileSpanCol)

            dropTile.title = null

        } else {
            evt.consume()
            alert(
                    type = Alert.AlertType.ERROR,
                    header = "Can't drop tile here!",
                    content = "Attempted to drop Tile: \n" +
                            "    Row: " + dropTile.rowIndex + "\n" +
                            "    Column:" + dropTile.colIndex + "\n" +
                            "    Tile RowSpan: " + dropTile.rowSpan + "\n" +
                            "    Tile ColSpan: " + dropTile.colSpan + "\n" +
                            "Over Tile: \n" +
                            "    Row: " + gridRow + "\n" +
                            "    Column:" + gridColumn + "\n" +
                            "     Tile RowSpan: " +  pickedGridTileRowSpan + "\n" +
                            "     Tile ColSpan: " +  pickedGridTileColSpan + "\n" +
                            "  X: " + mpLocal.x + "\n" +
                            "  Y: " + mpLocal.y
            )
        }
    }

    /**
     * Compare selected dragging tile with the location of the drop
     * and render the module tile accordingly
     *
     * @param [Point2D] point2D
     */
    private fun getPickedGridTileInfo(gridRow: Int, gridColumn: Int,
                                      spanRow: Int, spanCol: Int): Pair<Pair<Int, Int>, Tile> {
        var colIndex = 0
        var rowIndex = 0
        var rowSpan = 0
        var colSpan = 0
        lateinit var selectedTile: Tile

        val children = workArea.children

        for (tile in children) {
            rowIndex = GridPane.getRowIndex(tile)
            colIndex = GridPane.getColumnIndex(tile)
            colSpan = GridPane.getColumnSpan(tile)
            rowSpan = GridPane.getRowSpan(tile)
            selectedTile = tile as Tile
            if (rowIndex == gridRow && colIndex == gridColumn) {
                break
            }
        }

        dropTile = DragTile(selectedTile,
                colSpan, rowSpan, colIndex, rowIndex,
                selectedTile.backgroundColorProperty().value,
                selectedTile.titleProperty().value)

        return Pair(Pair(rowSpan, colSpan), selectedTile)
    }

    // init
    init {
        moduleBoxItems.addAll( pageBuilderController.smallTiles )
    }
}

/**
 * Render workarea by passing chosen grid information.
 *
 * @param [Point2D] point2D
 */
private fun passGridInfo(gridInfo: GridInfo): GridPane {
    val metroScope = GridScope()
    metroScope.model.item = gridInfo
    return find<MyTiles>(metroScope).root
}
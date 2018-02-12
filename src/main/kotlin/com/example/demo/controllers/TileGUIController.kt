package com.example.demo.controllers

import com.example.demo.model.HomepageGridBuilder
import com.example.demo.model.TilePlacement
import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.collections.FXCollections
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane
import tornadofx.Controller
import tornadofx.add
import tornadofx.getChildList
import tornadofx.toModel

class TileGUIController : Controller() {

    private val json = resources.jsonArray("/JSON/GridInfo.json").toModel<HomepageGridBuilder>()
    lateinit var gridInfo: Pair<Pair<Int, Int>, List<TilePlacement>>
    private lateinit var hoverTile: Tile
    private lateinit var hoverTileProperties: com.example.demo.model.TileBuilder
    private val controller: TileBuilderController by inject()

    /**
     * User pickTile info to render MyTiles
     *
     * @property String image
     */
    fun useTileGrid(pickTile: Int): Pair< Pair<Int, Int>, List<TilePlacement>> {

        json.forEach {
            if (pickTile == it.grid) {
                val gridTiles = it.tiles.map {
                    TilePlacement(gridTileBuilder(it.title, it.width, it.height),
                            it.colIndex, it.rowIndex, it.colSpan, it.rowSpan)
                }.toCollection(FXCollections.observableArrayList<TilePlacement>())
                gridInfo = Pair(Pair(it.rows, it.columns), gridTiles)
            }
        }

        return gridInfo
    }

    /**
     * Grid Tile Builder, simplified tile
     *
     * @property String title
     * @property Double width
     */
    private fun gridTileBuilder(title: String, width: Double = 100.0,
                    height: Double = 100.0) : Tile {
        return TileBuilder.create()
                .skinType(Tile.SkinType.TEXT)
                .title(title)
                .maxSize(width, height)
                .roundedCorners(false)
                .build()
    }

    /**
     * Grid Tile Builder, simplified tile
     *
     * @property MouseEvent evt
     * @property StackPane workArea
     * @property Tile tile
     * @property TileBuilder property
     */
    fun addHoverTile(evt: MouseEvent, workArea: StackPane, tile: Tile, property: com.example.demo.model.TileBuilder) {

        val bounds = tile.layoutBounds
        val coordinates = tile.localToScene(bounds.minX, bounds.minY)
        val tileX = coordinates.x
        val tileY = coordinates.y

        hoverTileProperties = property
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
    fun removeHoverTile(evt: MouseEvent, workArea: StackPane) {
        if (::hoverTileProperties.isInitialized) {
            workArea.children[1].getChildList()!!.remove(hoverTile)
        }
        evt.consume()
    }
}
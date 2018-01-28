package com.example.demo.controllers

import com.example.demo.model.HomepageGridBuilder
import tornadofx.*

import com.example.demo.model.TilePlacement
import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.collections.FXCollections

class TileGUIController : Controller() {

    private val json = resources.jsonArray("/JSON/GridInfo.json").toModel<HomepageGridBuilder>()
    lateinit var gridInfo: Pair<Pair<Int, Int>, List<TilePlacement>>

    /**
     * User pickTile info to render MyTiles
     *
     * @property String image
     */
    fun useTileGrid(pickTile: Int): Pair< Pair<Int, Int>, List<TilePlacement>> {

        json.forEach {
            if (pickTile == it.grid) {
                val gridTiles = FXCollections.observableArrayList<TilePlacement>()
                it.tiles.forEach {
                    gridTiles.add(TilePlacement(gridTileBuilder(it.title, it.width, it.height),
                            it.colIndex, it.rowIndex, it.colSpan, it.rowSpan))
                }
                gridInfo = Pair(Pair(it.rows, it.columns), gridTiles)
            }
        }

        return gridInfo
    }

    /**
     * Grid Tile Builder, simplified tile
     *
     * @property String image
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


}
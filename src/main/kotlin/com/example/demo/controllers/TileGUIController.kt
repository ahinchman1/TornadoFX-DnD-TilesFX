package com.example.demo.controllers

import com.example.demo.model.HomepageGridBuilder
import tornadofx.*

import com.example.demo.model.TilePlacement
import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.collections.FXCollections

class TileGUIController : Controller() {

    val json = loadJsonArray(javaClass.getResource("/JSON/GridInfo.json")).toModel<HomepageGridBuilder>()
    // due to spacing, add 10 to height/width to account for offset grid space

    private val tilePane1Grid = Pair(4, 6)
    private val tilePane1 = listOf(
            TilePlacement(gridTileBuilder("1"), 2, 1, 1, 1),
            TilePlacement(gridTileBuilder("2"), 3, 1, 1, 1),
            TilePlacement(gridTileBuilder("3"), 2, 2, 1, 1),
            TilePlacement(gridTileBuilder("4"), 3, 2, 1, 1)
    ).observable()

    private val tilePane2Grid = Pair(4, 6)
    private val tilePane2 = listOf(
            TilePlacement(gridTileBuilder("1"), 0, 0, 1, 1),
            TilePlacement(gridTileBuilder("2"), 1, 0, 1, 1),
            TilePlacement(gridTileBuilder("3"), 2, 0, 1, 1),
            TilePlacement(gridTileBuilder("4"), 3, 0, 1, 1),
            TilePlacement(gridTileBuilder("5"), 4, 0, 1, 1),
            TilePlacement(gridTileBuilder("6"), 5, 0, 1, 1),
            TilePlacement(gridTileBuilder("7"), 0, 1, 1, 1),
            TilePlacement(gridTileBuilder("8"), 1, 1, 1, 1),
            TilePlacement(gridTileBuilder("9"), 2, 1, 1 ,1),
            TilePlacement(gridTileBuilder("10"), 3, 1, 1, 1),
            TilePlacement(gridTileBuilder("11"), 4, 1, 1, 1),
            TilePlacement(gridTileBuilder("12"), 5, 1, 1, 1),
            TilePlacement(gridTileBuilder("13"), 0, 2, 1, 1),
            TilePlacement(gridTileBuilder("14"), 1, 2, 1, 1),
            TilePlacement(gridTileBuilder("15"), 2, 2, 1, 1),
            TilePlacement(gridTileBuilder("16"), 3, 2, 1, 1),
            TilePlacement(gridTileBuilder("17"), 4, 2, 1, 1),
            TilePlacement(gridTileBuilder("18"), 5, 2, 1, 1),
            TilePlacement(gridTileBuilder("19"), 0, 3, 1, 1),
            TilePlacement(gridTileBuilder("20"), 1, 3, 1, 1),
            TilePlacement(gridTileBuilder("21"), 2, 3, 1, 1),
            TilePlacement(gridTileBuilder("22"), 3, 3, 1, 1),
            TilePlacement(gridTileBuilder("23"), 4, 3, 1, 1),
            TilePlacement(gridTileBuilder("24"), 5, 3, 1, 1)
    ).observable()

    private val tilePane3Grid = Pair(4, 6)
    private val tilePane3 = listOf(
            TilePlacement(gridTileBuilder("1"), 0, 0, 1, 1),
            TilePlacement(gridTileBuilder("2"), 1, 0, 1, 1),
            TilePlacement(gridTileBuilder("3"), 2, 0, 1, 1),
            TilePlacement(gridTileBuilder("4", 310.0), 3, 0, 3, 1),
            TilePlacement(gridTileBuilder("5"), 0, 1, 1, 1),
            TilePlacement(gridTileBuilder("6"), 0 , 2, 1, 1),
            TilePlacement(gridTileBuilder("7"), 0, 3, 1, 1),
            TilePlacement(gridTileBuilder("8", 210.0, 210.0), 1, 1, 2, 2),
            TilePlacement(gridTileBuilder("9"), 1, 3, 1 ,1),
            TilePlacement(gridTileBuilder("10"), 2, 3, 1, 1),
            TilePlacement(gridTileBuilder("11", 210.0, 210.0), 3, 1, 2, 2),
            TilePlacement(gridTileBuilder("12"), 3, 3, 1, 1),
            TilePlacement(gridTileBuilder("13"), 4, 3, 1, 1),
            TilePlacement(gridTileBuilder("14"), 5, 1, 1, 1),
            TilePlacement(gridTileBuilder("15"), 5, 2, 1, 1),
            TilePlacement(gridTileBuilder("16"), 5, 3, 1, 1)
    ).observable()

    private val tilePane4Grid = Pair(4, 6)
    private val tilePane4 = listOf(
            TilePlacement(gridTileBuilder("1"), 0, 0, 1, 1),
            TilePlacement(gridTileBuilder("2"), 1, 0, 1, 1),
            TilePlacement(gridTileBuilder("3"), 2, 0, 1, 1),
            TilePlacement(gridTileBuilder("4", 310.0), 3, 0, 3, 1),
            TilePlacement(gridTileBuilder("5", 210.0, 210.0), 0, 1, 2, 2),
            TilePlacement(gridTileBuilder("6"), 0 , 3, 1, 1),
            TilePlacement(gridTileBuilder("7"), 1, 3, 1, 1),
            TilePlacement(gridTileBuilder("8", 210.0, 210.0), 2, 1, 2, 2),
            TilePlacement(gridTileBuilder("9"), 2, 3, 1 ,1),
            TilePlacement(gridTileBuilder("10"), 3, 3, 1, 1),
            TilePlacement(gridTileBuilder("11", 210.0, 210.0), 4, 1, 2, 2),
            TilePlacement(gridTileBuilder("12"), 4, 3, 1, 1),
            TilePlacement(gridTileBuilder("13"), 5, 3, 1, 1)
    ).observable()

    private val tilePane5Grid = Pair(4, 6)
    private val tilePane5 = listOf(
            TilePlacement(gridTileBuilder("1"), 0, 0, 1, 1),
            TilePlacement(gridTileBuilder("2"), 1, 0, 1, 1),
            TilePlacement(gridTileBuilder("3"), 2, 0, 1, 1),
            TilePlacement(gridTileBuilder("4", 310.0), 3, 0, 3, 1),
            TilePlacement(gridTileBuilder("5", 310.0, 210.0), 0, 1, 3, 2),
            TilePlacement(gridTileBuilder("6"), 0, 3, 1, 1),
            TilePlacement(gridTileBuilder("7"), 1, 3, 1, 1),
            TilePlacement(gridTileBuilder("8"), 2, 3, 1, 1),
            TilePlacement(gridTileBuilder("9", 210.0, 210.0), 3, 1, 2 ,2),
            TilePlacement(gridTileBuilder("10"), 3, 3, 1, 1),
            TilePlacement(gridTileBuilder("11"), 4, 3, 1, 1),
            TilePlacement(gridTileBuilder("12"), 5, 1, 1, 1),
            TilePlacement(gridTileBuilder("13"), 5, 2, 1, 1),
            TilePlacement(gridTileBuilder("14"), 5, 3, 1, 1)
    ).observable()

    private val tilePane6Grid = Pair(4, 6)
    private val tilePane6 = listOf(
            TilePlacement(gridTileBuilder("1"), 0, 0, 1, 1),
            TilePlacement(gridTileBuilder("2"), 1, 0, 1, 1),
            TilePlacement(gridTileBuilder("3"), 2, 0, 1, 1),
            TilePlacement(gridTileBuilder("4"), 0, 1, 1, 1),
            TilePlacement(gridTileBuilder("5"), 1 , 1, 1, 1),
            TilePlacement(gridTileBuilder("6"), 0, 2, 1, 1),
            TilePlacement(gridTileBuilder("7"), 1, 2, 1, 1),
            TilePlacement(gridTileBuilder("8", 310.0), 0, 3, 3, 1),
            TilePlacement(gridTileBuilder("9", 210.0, 210.0), 2, 1, 2 ,2),
            TilePlacement(gridTileBuilder("10"), 3, 3, 1, 1),
            TilePlacement(gridTileBuilder("11"), 4, 1, 1, 1),
            TilePlacement(gridTileBuilder("12"), 5, 1, 1, 1),
            TilePlacement(gridTileBuilder("13", 210.0, 210.0), 4, 2, 2, 2),
            TilePlacement(gridTileBuilder("14", 310.0), 3, 0, 3, 1)
    ).observable()

    /**
     * User pickTile info to render MyTiles
     *
     * @param [String] image
     */
    fun useTileGrid(pickTile: Int): Pair< Pair<Int, Int>, List<TilePlacement>> {
        var gridInfo = Pair(Pair(1,1), tilePane1)

        json.forEach {
            if (pickTile == it.grid) {
                val gridTiles = FXCollections.observableArrayList<TilePlacement>()
                it.tiles.forEach {
                    gridTiles.add(TilePlacement(gridTileBuilder2(it.title, it.width, it.height),
                            it.colIndex, it.rowIndex, it.rowSpan, it.colSpan))
                }
                gridInfo = Pair(Pair(it.rows, it.columns), gridTiles)
            }
        }

        /*when (metroTile) {
            1 -> gridInfo = Pair(tilePane1Grid, tilePane1)
            2 -> gridInfo = Pair(tilePane2Grid, tilePane2)
            3 -> gridInfo = Pair(tilePane3Grid, tilePane3)
            4 -> gridInfo = Pair(tilePane4Grid, tilePane4)
            5 -> gridInfo = Pair(tilePane5Grid, tilePane5)
            6 -> gridInfo = Pair(tilePane6Grid, tilePane6)
        }*/

        return gridInfo
    }

    /* Grid tile builders*/
    /**
     * Grid Tile Builder, simplified tile
     *
     * @param [String] image
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
     * @param [String] image
     */
    private fun gridTileBuilder2(title: String, width: Double,
                                height: Double) : Tile {
        return TileBuilder.create()
                .skinType(Tile.SkinType.TEXT)
                .title(title)
                .maxSize(width, height)
                .roundedCorners(false)
                .build()
    }


}
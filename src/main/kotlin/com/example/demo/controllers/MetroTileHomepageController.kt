package com.example.demo.controllers

import com.example.demo.model.ModuleTilePlacement
import com.example.demo.views.MetroTileHomepage
import com.example.demo.views.MyTiles
import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*
import java.beans.EventHandler

class MetroTileHomepageController: Controller() {
    /** Small Modules **/
    private val module1 = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .title("module1")
            .build()

    private val module2 = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .title("module1")
            .build()

    private val module3 = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .title("module1")
            .build()

    private val module4 = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .title("module1")
            .build()

    private val module5 = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .title("module1")
            .build()

    private val module6 = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .title("module1")
            .build()

    private val module7 = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .title("module1")
            .build()

    private val module8 = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .title("module1")
            .build()

    val smallTiles = listOf(module1, module2, module3, module4, module5,
            module6, module7, module8).observable()

    // use metroTile info to render MyTiles
    fun useTileGrid(metroTile: Int): Pair< Pair<Int, Int>, List<ModuleTilePlacement>> {
        var gridInfo = Pair(Pair(1,1), tilePane1)

        when (metroTile) {
            1 -> gridInfo = Pair(tilePane1Grid, tilePane1)
            2 -> gridInfo = Pair(tilePane2Grid, tilePane2)
            3 -> gridInfo = Pair(tilePane3Grid, tilePane3)
            4 -> gridInfo = Pair(tilePane4Grid, tilePane4)
            5 -> gridInfo = Pair(tilePane5Grid, tilePane5)
            6 -> gridInfo = Pair(tilePane6Grid, tilePane6)
            7 -> gridInfo = Pair(tilePane7Grid, tilePane7)
            8 -> gridInfo = Pair(tilePane8Grid, tilePane8)
        }

        return gridInfo
    }

    private val tilePane1Grid = Pair(4, 6)
    private val tilePane1 = listOf(
            ModuleTilePlacement(gridBannerBuilder(), 0, 0, 3, 1),
            ModuleTilePlacement(gridTileBuilder("1"), 0, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("2"), 1 , 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("3"), 0, 2, 1, 1),
            ModuleTilePlacement(gridTileBuilder("4"), 1, 2, 1, 1),
            ModuleTilePlacement(gridTileBuilder("5", 210.0, 210.0), 2, 1, 2 ,2),
            ModuleTilePlacement(gridTileBuilder("6"), 3, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("7"), 4, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("8"), 5, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("9", 210.0, 210.0), 4, 2, 2, 2),
            ModuleTilePlacement(gridTileBuilder("10", 310.0), 3, 0, 3, 1)
            ).observable()

    private val tilePane2Grid = Pair(4, 6)
    private val tilePane2 = listOf(
            ModuleTilePlacement(gridBannerBuilder(), 0, 0, 3, 1),
            ModuleTilePlacement(gridTileBuilder("1", 310.0), 3, 0, 3, 1),
            ModuleTilePlacement(gridTileBuilder("2"), 0, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("3"), 0 , 2, 1, 1),
            ModuleTilePlacement(gridTileBuilder("4"), 0, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("5", 210.0, 210.0), 1, 1, 2, 2),
            ModuleTilePlacement(gridTileBuilder("6"), 1, 3, 1 ,1),
            ModuleTilePlacement(gridTileBuilder("7"), 2, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("8", 210.0, 210.0), 3, 1, 2, 2),
            ModuleTilePlacement(gridTileBuilder("9"), 3, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("10"), 4, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("11"), 5, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("12"), 5, 2, 1, 1),
            ModuleTilePlacement(gridTileBuilder("13"), 5, 3, 1, 1)
    ).observable()

    private val tilePane3Grid = Pair(4, 6)
    private val tilePane3 = listOf(
            ModuleTilePlacement(gridBannerBuilder(), 0, 0, 3, 1),
            ModuleTilePlacement(gridTileBuilder("1", 310.0), 3, 0, 3, 1),
            ModuleTilePlacement(gridTileBuilder("2", 210.0, 210.0), 0, 1, 2, 2),
            ModuleTilePlacement(gridTileBuilder("3"), 0 , 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("4"), 1, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("5", 210.0, 210.0), 2, 1, 2, 2),
            ModuleTilePlacement(gridTileBuilder("6"), 2, 3, 1 ,1),
            ModuleTilePlacement(gridTileBuilder("7"), 3, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("8", 210.0, 210.0), 4, 1, 2, 2),
            ModuleTilePlacement(gridTileBuilder("9"), 4, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("10"), 5, 3, 1, 1)
    ).observable()

    private val tilePane4Grid = Pair(4, 6)
    private val tilePane4 = listOf(
            ModuleTilePlacement(gridBannerBuilder(), 0, 0, 3, 1),
            ModuleTilePlacement(gridTileBuilder("1", 310.0), 3, 0, 3, 1),
            ModuleTilePlacement(gridTileBuilder("2", 310.0, 210.0), 0, 1, 3, 2),
            ModuleTilePlacement(gridTileBuilder("3"), 0, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("4"), 1, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("5"), 2, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("6", 210.0, 210.0), 3, 1, 2 ,2),
            ModuleTilePlacement(gridTileBuilder("7"), 3, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("8"), 4, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("9"), 5, 1, 1, 1)
    ).observable()

    private val tilePane5Grid = Pair(4, 6)
    private val tilePane5 = listOf(
            ModuleTilePlacement(gridBannerBuilder("Content", 410.0), 0, 0, 4, 1),
            ModuleTilePlacement(gridTileBuilder("1"), 4, 0, 1, 1),
            ModuleTilePlacement(gridTileBuilder("2"), 5, 0, 1, 1),
            ModuleTilePlacement(gridTileBuilder("3", 410.0, 210.0), 0, 1, 4, 2),
            ModuleTilePlacement(gridTileBuilder("4"), 0, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("5"), 1, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("6"), 2, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("7"), 3, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("8", 210.0, 210.0), 4, 1, 2 ,2),
            ModuleTilePlacement(gridTileBuilder("9"), 5, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("10"), 5, 1, 1, 1)
    ).observable()

    private val tilePane6Grid = Pair(3, 3)
    private val tilePane6 = listOf(
            ModuleTilePlacement(gridBannerBuilder(), 0, 0, 3, 1),
            ModuleTilePlacement(gridTileBuilder("1"), 0, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("2"), 0, 2, 1, 1),
            ModuleTilePlacement(gridTileBuilder("3", 210.0, 210.0), 1, 1, 2, 2)
    ).observable()

    private val tilePane7Grid = Pair(4, 6)
    private val tilePane7 = listOf(
            ModuleTilePlacement(gridBannerBuilder(), 0, 0, 3, 1),
            ModuleTilePlacement(gridTileBuilder("1"), 0, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("2"), 0, 2, 1, 1),
            ModuleTilePlacement(gridTileBuilder("3"), 0 , 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("4"), 1, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("5"), 2, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("6", 210.0, 210.0), 1, 2, 2, 2),
            ModuleTilePlacement(gridTileBuilder("7"), 3, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("8"), 4, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("9", 210.0, 210.0), 3, 2, 2, 2),
            ModuleTilePlacement(gridTileBuilder("10"), 5, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("11"), 5, 2, 1, 1),
            ModuleTilePlacement(gridTileBuilder("12"), 5, 3, 1, 1),
            ModuleTilePlacement(gridTileBuilder("13", 310.0, 100.0), 3, 0, 3, 1)
    ).observable()

    private val tilePane8Grid = Pair(4, 5)
    private val tilePane8 = listOf(
            ModuleTilePlacement(gridBannerBuilder(width = 510.0), 0, 0, 3, 1),
            ModuleTilePlacement(gridTileBuilder("1"), 0, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("2"), 1, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("3"), 2, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("4", 310.0), 3, 1, 1, 1),
            ModuleTilePlacement(gridTileBuilder("5", 210.0, 210.0), 0, 2, 2, 2),
            ModuleTilePlacement(gridTileBuilder("6", 210.0, 210.0), 2, 2, 2, 2),
            ModuleTilePlacement(gridTileBuilder("7"), 4, 2, 1, 1),
            ModuleTilePlacement(gridTileBuilder("8"), 4, 3, 1, 1)
    ).observable()

    /* Grid tile builders*/
    private fun gridTileBuilder(title: String, width: Double = 100.0,
                    height: Double = 100.0) : Tile {
        return TileBuilder.create()
                .skinType(Tile.SkinType.TEXT)
                .title(title)
                .maxSize(width, height)
                .roundedCorners(false)
                .build()
    }

    private fun gridBannerBuilder(subtitle: String = "Hi, Admin", width: Double = 310.0,
                                height: Double = 100.0) : Tile {
        return TileBuilder.create()
                .skinType(Tile.SkinType.TEXT)
                .maxSize(width, height)
                .description(subtitle)
                .descriptionAlignment(Pos.BASELINE_LEFT)
                .descriptionColor(c("#7a838e"))
                .roundedCorners(false)
                .backgroundColor(Color.WHITE)
                .build()
    }

}
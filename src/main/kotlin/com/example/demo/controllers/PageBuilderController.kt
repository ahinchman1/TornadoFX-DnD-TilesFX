package com.example.demo.controllers

import com.example.demo.model.PageBuilder
import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.scene.paint.Color
import tornadofx.*

class PageBuilderController : Controller() {

    /***** Global Variables *****/
    /** Small Modules **/
    private val aBlock = PageBuilder(100.0, 100.0, Color.SIENNA, "a_block")
    private val bBlock = PageBuilder(100.0, 100.0, Color.CORAL, "b_block")
    private val cBlock = PageBuilder(100.0, 100.0, Color.BLANCHEDALMOND, "c_block")
    private val dBlock = PageBuilder(100.0, 100.0, Color.DARKORCHID, "d_block")
    private val eBlock = PageBuilder(100.0, 100.0, Color.TOMATO, "e_block")
    private val fBlock = PageBuilder(100.0, 100.0, Color.LIMEGREEN, "f_block")
    private val gBlock = PageBuilder(100.0, 100.0, Color.FIREBRICK, "g_block")
    private val hBlock = PageBuilder(100.0, 100.0, Color.FORESTGREEN, "h_block")

    val smallTiles = listOf(
            moduleTileBuilder(aBlock), moduleTileBuilder(bBlock),
            moduleTileBuilder(cBlock), moduleTileBuilder(dBlock),
            moduleTileBuilder(eBlock), moduleTileBuilder(fBlock),
            moduleTileBuilder(gBlock), moduleTileBuilder(hBlock)
            ).observable()
    
    /**
     * Create a tile using pagebuilder data
     *
     * @param [PageBuilder] module
     */
    fun moduleTileBuilder(module: PageBuilder): Tile {
        return TileBuilder.create()
                .skinType(Tile.SkinType.TEXT)
                .title(module.title)
                .maxSize(module.width, module.height)
                .backgroundColor(module.tileColor)
                .roundedCorners(false)
                .build()
    }

}
package com.example.demo.controllers

import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.scene.paint.Color
import tornadofx.*

class TileBuilderController : Controller() {

    /***** Global Variables *****/
    /** Small Modules **/
    private val aBlock = com.example.demo.model.TileBuilder(100.0, 100.0, Color.SIENNA, "a_block")
    private val bBlock = com.example.demo.model.TileBuilder(100.0, 100.0, Color.CORAL, "b_block")
    private val cBlock = com.example.demo.model.TileBuilder(100.0, 100.0, Color.BLANCHEDALMOND, "c_block")
    private val dBlock = com.example.demo.model.TileBuilder(100.0, 100.0, Color.DARKORCHID, "d_block")
    private val eBlock = com.example.demo.model.TileBuilder(100.0, 100.0, Color.TOMATO, "e_block")
    private val fBlock = com.example.demo.model.TileBuilder(100.0, 100.0, Color.LIMEGREEN, "f_block")
    private val gBlock = com.example.demo.model.TileBuilder(100.0, 100.0, Color.FIREBRICK, "g_block")
    private val hBlock = com.example.demo.model.TileBuilder(100.0, 100.0, Color.FORESTGREEN, "h_block")

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
    fun moduleTileBuilder(module: com.example.demo.model.TileBuilder): Tile {
        return TileBuilder.create()
                .skinType(Tile.SkinType.TEXT)
                .title(module.title)
                .maxSize(module.width, module.height)
                .backgroundColor(module.tileColor)
                .roundedCorners(false)
                .build()
    }

}
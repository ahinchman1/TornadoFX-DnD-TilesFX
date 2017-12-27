package com.example.demo.controllers

import com.example.demo.model.DragTileScope
import com.example.demo.model.PageBuilder
import com.example.demo.model.PageBuilderModel
import com.example.demo.model.PageBuilderScope
import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.scene.paint.Color
import tornadofx.*

class PageBuilderController : Controller() {

    /** Small Modules **/
    private val search = PageBuilder(100.0, 100.0, Color.SIENNA, "Search")
    private val track = PageBuilder(100.0, 100.0, Color.CORAL, "Track")
    private val browse = PageBuilder(100.0, 100.0, Color.BLANCHEDALMOND, "Browse")
    private val openCat = PageBuilder(100.0, 100.0, Color.DARKORCHID, "OpenCat")
    private val build = PageBuilder(100.0, 100.0, Color.TOMATO, "Build")
    private val upload = PageBuilder(100.0, 100.0, Color.LIMEGREEN, "Upload")
    private val image = PageBuilder(100.0, 100.0, Color.FIREBRICK, "Image")
    private val contacts = PageBuilder(100.0, 100.0, Color.FORESTGREEN, "Contacts")
    private val popCont = PageBuilder(100.0, 100.0, Color.LAVENDERBLUSH, "Popular Content")
    private val library = PageBuilder(100.0, 100.0, Color.LIGHTSEAGREEN, "Library")
    private val favorites = PageBuilder(100.0, 100.0, Color.BLUEVIOLET, "My Favorites")
    private val fileDest = PageBuilder(100.0, 100.0, Color.CHARTREUSE, "FileDestination")

    val smallTiles = listOf(
            moduleTileBuilder(search), moduleTileBuilder(track),
            moduleTileBuilder(browse), moduleTileBuilder(openCat),
            moduleTileBuilder(build), moduleTileBuilder(upload),
            moduleTileBuilder(image), moduleTileBuilder(contacts),
            moduleTileBuilder(popCont), moduleTileBuilder(library),
            moduleTileBuilder(favorites), moduleTileBuilder(fileDest)
            ).observable()

    fun moduleTileBuilder(module: PageBuilder): Tile {
        val PageBuilderScope = PageBuilderScope()
        val model = PageBuilderModel()
        model.item = module
        return TileBuilder.create()
                .skinType(Tile.SkinType.TEXT)
                .title(module.title)
                .maxSize(module.width, module.height)
                .backgroundColor(module.tileColor)
                .roundedCorners(false)
                .build()
    }
}
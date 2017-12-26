package com.example.demo.controllers

import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.scene.paint.Color
import tornadofx.*

class PageBuilderController : Controller() {

    /** Small Modules **/
    private val search = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .backgroundColor(Color.SIENNA)
            .title("Search")
            .build()

    private val track = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .backgroundColor(Color.CORAL)
            .title("Track")
            .build()

    private val browse = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .backgroundColor(Color.BLANCHEDALMOND)
            .title("Browse")
            .build()

    private val openCat = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .backgroundColor(Color.DARKORCHID)
            .title("Open Cat")
            .build()

    private val build = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .backgroundColor(Color.TOMATO)
            .title("Build")
            .build()

    private val upload = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .backgroundColor(Color.LIMEGREEN)
            .title("Upload")
            .build()

    private val image = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .backgroundColor(Color.FIREBRICK)
            .title("Image")
            .build()

    private val contacts = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .backgroundColor(Color.FORESTGREEN)
            .title("Contacts")
            .build()

    private val popCont = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .barBackgroundColor(Color.LAVENDERBLUSH)
            .title("Popular Content")
            .build()

    private val library = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .barBackgroundColor(Color.LIGHTSEAGREEN)
            .title("Library")
            .build()

    private val favorites = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .barBackgroundColor(Color.BLUEVIOLET)
            .title("My Favorites")
            .build()

    private val fileDest = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .maxSize(100.0, 100.0)
            .barBackgroundColor(Color.CHARTREUSE)
            .title("FileDestination")
            .build()

    val smallTiles = listOf(search, track, browse, openCat, build,
            upload, image, contacts, popCont, library, favorites,
            fileDest).observable()
}
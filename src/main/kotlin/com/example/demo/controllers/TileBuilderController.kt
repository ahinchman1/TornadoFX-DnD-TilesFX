package com.example.demo.controllers

import com.example.demo.model.TileBuilder
import eu.hansolo.tilesfx.Tile
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import tornadofx.*

class TileBuilderController : Controller() {

    /***** Global Variables *****/

    var hashmap = HashMap<String, TileBuilder>()
    var tileList = ArrayList<Tile>()
    var imageList = ArrayList<Tile>()
    var createGridTilesHashMap = HashMap<String, com.example.demo.model.TileBuilder>()
    private val modules = loadJsonArray(javaClass.getResource("/JSON/TileBuilder.json")).toModel<TileBuilder>()

    private var homeIcons= listOf("/home_icons/24-technical-support.png", "/home_icons/cloud-network.png",
            "/home_icons/customer-service.png", "/home_icons/data-analysis.png",
            "/home_icons/data-connection.png", "/home_icons/data-lock.png",
            "/home_icons/data-servers.png", "/home_icons/data-usage.png")

    init {
        renderModule()
        renderImages()
    }

    /**
     * Create a tile using PageBuilder data
     *
     */
    private fun renderModule() {
        modules.forEach {
            hashmap.put(it.title, it)
            tileList.add(moduleTileBuilder(it))
        }
    }

    /**
     * Create a tile using PageBuilder data
     *
     */
    private fun renderImages() {
        homeIcons.forEach {
            imageList.add(imageTileBuilder(it))
        }
    }

    /**
     * Create tile graphic
     *
     * @property String url
     */
    private fun createGraphic(url: String): ImageView {
        return ImageView(javaClass.getResource(url).toExternalForm()).apply {
            fitWidth = 20.0
            fitHeight = 20.0
            isPreserveRatio = true
        }.apply {
                    this.fitWidth = 50.0
                    this.fitHeight = 50.0
                    this.isPreserveRatio = true
                }
    }


    /**
     * Create a tile using PageBuilder data
     *
     * @property TileBuilder module
     */
    private fun imageTileBuilder(image: String): Tile {
        return eu.hansolo.tilesfx.TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .maxSize(100.0, 100.0)
                .minSize(100.0, 100.0)
                .graphic(createGraphic(image))
                .title(image)
                .titleColor(Color.TRANSPARENT)
                .backgroundColor(Color.TRANSPARENT)
                .roundedCorners(false)
                .build()
    }

    /**
     * Create a tile using PageBuilder data
     *
     * @property TileBuilder module
     */
    fun moduleTileBuilder(module: TileBuilder, titleColor: Color = Color.WHITE): Tile {
        val color = c(module.color)
        var title = module.title
        if (title == "Hi Admin") {
            title = ""
        }
        return eu.hansolo.tilesfx.TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .title(title)
                .titleColor(titleColor)
                .maxSize(module.width, module.height)
                .minSize(module.width, module.height)
                .graphic(createGraphic(module.image))
                .backgroundColor(color)
                .roundedCorners(false)
                .build()
    }

}
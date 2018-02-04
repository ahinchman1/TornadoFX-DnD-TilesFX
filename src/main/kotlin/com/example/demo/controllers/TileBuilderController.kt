package com.example.demo.controllers

import com.example.demo.model.TileBuilder
import com.example.demo.model.TileBuilderJSON
import eu.hansolo.tilesfx.Tile
import javafx.scene.paint.Color
import tornadofx.*

class TileBuilderController : Controller() {

    /***** Global Variables *****/

    var hashmap = HashMap<String, TileBuilder>()
    var tileList = ArrayList<Tile>()
    private val modules = loadJsonArray(javaClass.getResource("/JSON/TileBuilder.json")).toModel<TileBuilderJSON>()

    init {
        renderModule()
    }

    /**
     * Create a tile using PageBuilder data
     *
     * @property TileBuilder module
     */
    private fun renderModule() {
        modules.forEach { module ->
            val color: Color = c(module.color)
            val pageBuilder = TileBuilder(module.title, module.width,
                    module.height, color)

            hashmap.put(module.title, pageBuilder)
            tileList.add(moduleTileBuilder(pageBuilder))
        }
    }

    /**
     * Create a tile using PageBuilder data
     *
     * @property TileBuilder module
     */
    fun moduleTileBuilder(module: TileBuilder): Tile {
        return eu.hansolo.tilesfx.TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .title(module.title)
                .maxSize(module.width, module.height)
                .backgroundColor(module.color)
                .roundedCorners(false)
                .build()
    }

}
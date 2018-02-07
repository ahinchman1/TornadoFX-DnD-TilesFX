package com.example.demo.controllers

import com.example.demo.model.TileBuilder
import eu.hansolo.tilesfx.Tile
import tornadofx.*

class TileBuilderController : Controller() {

    /***** Global Variables *****/

    var hashmap = HashMap<String, TileBuilder>()
    var tileList = ArrayList<Tile>()
    private val modules = loadJsonArray(javaClass.getResource("/JSON/TileBuilder.json")).toModel<TileBuilder>()

    init {
        renderModule()
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
     * @property TileBuilder module
     */
    fun moduleTileBuilder(module: TileBuilder): Tile {
        val color = c(module.color)
        return eu.hansolo.tilesfx.TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .title(module.title)
                .maxSize(module.width, module.height)
                .minSize(module.width, module.height)
                .backgroundColor(color)
                .roundedCorners(false)
                .build()
    }

}
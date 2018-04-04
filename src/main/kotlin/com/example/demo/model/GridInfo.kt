package com.example.demo.model

import eu.hansolo.tilesfx.Tile
import eu.hansolo.tilesfx.TileBuilder
import javafx.beans.property.Property
import tornadofx.*

/***** Data classes and models intended for grid rendering *****/

class GridInfo(info: Pair<Pair<Int, Int>, List<TilePlacement>>) {

    private var coordinates by property(info.first)
    fun coordinatesProperty() = getProperty(GridInfo::coordinates)

    var rows: Int by property(info.first.first)
    var columns: Int by property(info.first.second)
    var moduleTiles by property(info.second)
}

class GridInfoModel : ItemViewModel<GridInfo>() {
    private val coordinates = bind { item?.coordinatesProperty() }

    override fun onCommit(commits: List<Commit>) {
        super.onCommit(commits)

        // The println will only be called if findChanged is not null
        commits.findChanged(coordinates)?.let { println("Grid Info changed from ${it.first} to ${it.second}")}
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }

    fun useTileGrid(grids: List<HomepageGridBuilder>, grid: Int) {
        grids.asSequence().forEach {
            if (grid == it.grid) {
                val gridTiles = it.tiles.map(::createTilePlacement)
                item = GridInfo(Pair(Pair(it.rows, it.columns), gridTiles))
            }
        }
    }

    private fun createTilePlacement(hpb: HomepageTileBuilder): TilePlacement {
        return TilePlacement(gridTileBuilder(hpb.title, hpb.width, hpb.height),
                hpb.colIndex, hpb.rowIndex, hpb.colSpan, hpb.rowSpan)
    }

    /**
     * Grid Tile Builder, simplified tile
     *
     * @property String title
     * @property Double width
     */
    private fun gridTileBuilder(title: String, width: Double = 100.0, height: Double = 100.0) : Tile
            = TileBuilder.create()
            .skinType(Tile.SkinType.TEXT)
            .title(title)
            .maxSize(width, height)
            .roundedCorners(false)
            .build()
}

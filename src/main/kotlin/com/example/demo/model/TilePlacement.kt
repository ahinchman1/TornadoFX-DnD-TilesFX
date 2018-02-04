package com.example.demo.model

import eu.hansolo.tilesfx.Tile
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
}

/***** Data classes and models intended for tile and grid location and properties
 *     NOTE: revisit models for refactoring *****/
class TilePlacement(tile: Tile, colIndex: Int, rowIndex: Int, colSpan: Int, rowSpan: Int) {
    var tile: Tile by property(tile)
    fun tileProperty() = getProperty(TilePlacement::tile)

    var colIndex: Int by property(colIndex)
    fun colIndexProperty() = getProperty(TilePlacement::colIndex)

    var rowIndex: Int by property(rowIndex)
    fun rowIndexProperty() = getProperty(TilePlacement::rowIndex)

    var colSpan: Int by property(colSpan)
    fun colSpanProperty() = getProperty(TilePlacement::colSpan)

    var rowSpan: Int by property(rowSpan)
    fun rowSpanProperty() = getProperty(TilePlacement::rowSpan)
}

class TilePlacementModel : ItemViewModel<TilePlacement>() {
    private val tile = bind { item?.tileProperty()  }
    private val colIndex = bind { item?.colIndexProperty() }
    private val rowIndex = bind { item?.rowIndexProperty() }
    private val colSpan = bind { item?.colSpanProperty() }
    private val rowSpan = bind { item?.rowSpanProperty() }

    override fun onCommit(commits: List<Commit>) {
        super.onCommit(commits)

        // The println will only be called if findChanged is not null
        commits.findChanged(tile)?.let { println("Module Tile changed from ${it.first} to ${it.second}")}
        commits.findChanged(colIndex)?.let { println("Column Index changed from ${it.first} to ${it.second}")}
        commits.findChanged(rowIndex)?.let { println("Row Index changed from ${it.first} to ${it.second}")}
        commits.findChanged(colSpan)?.let { println("Column Span changed from ${it.first} to ${it.second}")}
        commits.findChanged(rowSpan)?.let { println("Row Span changed from ${it.first} to ${it.second}")}
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }
}

class GridScope: Scope() {
    val model = GridInfoModel()
}

/***** Data classes and models intended for tile and grid location, saving
 *     tile objects needed for module rendering,
 *     dragging, and copying in a view  *****/

class DragTile(tile: Tile, colSpan: Int, rowSpan: Int, colIndex: Int,
               rowIndex: Int) {
    var tile by property(tile)
    fun tileProperty() = getProperty(DragTile::tile)

    var colSpan by property(colSpan)
    fun colSpanProperty() = getProperty(DragTile::colSpan)

    var rowSpan by property(rowSpan)
    fun rowSpanProperty() = getProperty(DragTile::rowSpan)

    var colIndex by property(colIndex)
    fun colIndexProperty() = getProperty(DragTile::colIndex)

    var rowIndex by property(rowIndex)
    fun rowIndexProperty() = getProperty(DragTile::rowIndex)
}

class DragTileModel : ItemViewModel<DragTile>() {
    private val tile = bind { item?.tileProperty()  }
    private val colSpan = bind { item?.colSpanProperty() }
    private val rowSpan = bind { item?.rowSpanProperty() }
    private val colIndex = bind { item?.colIndexProperty() }
    private val rowIndex = bind { item?.rowIndexProperty() }

    override fun onCommit(commits: List<Commit>) {
        super.onCommit(commits)

        // The println will only be called if findChanged is not null
        commits.findChanged(tile)?.let { println("Module Tile changed from ${it.first} to ${it.second}")}
        commits.findChanged(colSpan)?.let { println("Column Span changed from ${it.first} to ${it.second}")}
        commits.findChanged(rowSpan)?.let { println("Row Span changed from ${it.first} to ${it.second}")}
        commits.findChanged(colIndex)?.let { println("Column Index changed from ${it.first} to ${it.second}")}
        commits.findChanged(rowIndex)?.let { println("Row Index changed from ${it.first} to ${it.second}")}
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }
}
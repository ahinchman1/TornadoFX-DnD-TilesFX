package com.example.demo.model

import eu.hansolo.tilesfx.Tile
import javafx.beans.property.Property
import javafx.scene.image.Image
import javafx.scene.paint.Color
import tornadofx.*
import java.io.Serializable

/***** Data classes and models intended for grid rendering *****/

class GridInfo(info: Pair<Pair<Int, Int>, List<ModuleTilePlacement>>) {
    private var info by property(info)

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
class ModuleTilePlacement(tile: Tile, colIndex: Int, rowIndex: Int, colSpan: Int, rowSpan: Int) {
    var tile: Tile by property(tile)
    fun tileProperty() = getProperty(ModuleTilePlacement::tile)

    var colIndex: Int by property(colIndex)
    fun colIndexProperty() = getProperty(ModuleTilePlacement::colIndex)

    var rowIndex: Int by property(rowIndex)
    fun rowIndexProperty() = getProperty(ModuleTilePlacement::rowIndex)

    var colSpan: Int by property(colSpan)
    fun colSpanProperty() = getProperty(ModuleTilePlacement::colSpan)

    var rowSpan: Int by property(rowSpan)
    fun rowSpanProperty() = getProperty(ModuleTilePlacement::rowSpan)
}

class ModuleTilePlacementModel : ItemViewModel<ModuleTilePlacement>() {
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

/***** Data classes and models intended for tile and grid location and properties
 *     NOTE: revisit models for refactoring *****/
class DragTile(tile: Tile, colSpan: Int, rowSpan: Int, colIndex: Int,
               rowIndex: Int, color: Color, title: String): Serializable {
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

    var color by property(color)
    fun colorProperty() = getProperty(DragTile::color)

    var title by property(title)
    fun titleProperty() = getProperty(DragTile::title)
}

/***** Data classes and models intended for tile and grid location and properties
 *     NOTE: revisit models for refactoring *****/
class DragTileModel : ItemViewModel<DragTile>() {
    private val tile = bind { item?.tileProperty()  }
    private val colSpan = bind { item?.colSpanProperty() }
    private val rowSpan = bind { item?.rowSpanProperty() }
    private val colIndex = bind { item?.colIndexProperty() }
    private val rowIndex = bind { item?.rowIndexProperty() }
    private val color = bind { item?.colorProperty() }
    private val title = bind { item?.titleProperty() }

    override fun onCommit(commits: List<Commit>) {
        super.onCommit(commits)

        // The println will only be called if findChanged is not null
        commits.findChanged(tile)?.let { println("Module Tile changed from ${it.first} to ${it.second}")}
        commits.findChanged(colSpan)?.let { println("Column Span changed from ${it.first} to ${it.second}")}
        commits.findChanged(rowSpan)?.let { println("Row Span changed from ${it.first} to ${it.second}")}
        commits.findChanged(colIndex)?.let { println("Column Index changed from ${it.first} to ${it.second}")}
        commits.findChanged(rowIndex)?.let { println("Row Index changed from ${it.first} to ${it.second}")}
        commits.findChanged(color)?.let { println("Color changed from ${it.first} to ${it.second}")}
        commits.findChanged(title)?.let { println("Title changed from ${it.first} to ${it.second}")}
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }
}

class DragTileScope: Scope() {
    val model = DragTileModel()
}
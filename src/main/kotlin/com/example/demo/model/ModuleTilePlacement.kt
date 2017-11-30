package com.example.demo.model

import eu.hansolo.tilesfx.Tile
import javafx.beans.property.Property
import tornadofx.*

class GridInfo(info: Pair<Pair<Int, Int>, List<ModuleTilePlacement>>) {
    var info by property(info)
    fun infoProperty() = getProperty(GridInfo::info)

    var coordinates by property(info.first)
    fun coordinatesProperty() = getProperty(GridInfo::coordinates)

    var rows by property(info.first.first)
    fun rowsProperty() = getProperty(GridInfo::rows)

    var columns by property(info.first.second)
    fun columnsProperty() = getProperty(GridInfo::columns)

    var moduleTiles by property(info.second)
    fun moduleTilesProperty() = getProperty(GridInfo::moduleTiles)
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

class ModuleTilePlacement(tile: Tile, colIndex: Int, rowIndex: Int, colSpan: Int, rowSpan: Int) {
    var tile by property(tile)
    fun tileProperty() = getProperty(ModuleTilePlacement::tile)

    var colIndex by property(colIndex)
    fun colIndexProperty() = getProperty(ModuleTilePlacement::colIndex)

    var rowIndex by property(rowIndex)
    fun rowIndexProperty() = getProperty(ModuleTilePlacement::rowIndex)

    var colSpan by property(colSpan)
    fun colSpanProperty() = getProperty(ModuleTilePlacement::colSpan)

    var rowSpan by property(rowSpan)
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

class ModuleTileScope: Scope() {
    val model = ModuleTilePlacementModel()
}
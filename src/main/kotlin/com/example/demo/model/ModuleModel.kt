package com.example.demo.model

import eu.hansolo.tilesfx.Tile
import javafx.beans.property.Property
import tornadofx.*
import java.io.Serializable

// Not sure if I'm using this at all
class ModuleModel: ItemViewModel<Tile>() {
    // Module Base
    var moduleSkin = bind(Tile::skinProperty)
    var moduleBackground = bind(Tile::backgroundColorProperty)
    var moduleBorderColor = bind(Tile::borderColorProperty)
    var moduleMaxHeight = bind(Tile::maxHeightProperty)
    var moduleMaxWidth = bind(Tile::maxWidthProperty)

    // Module Label
    var moduleTitle = bind(Tile::titleProperty)
    var moduleDescriptor = bind(Tile::descriptionProperty)
    var moduleTitleColor = bind(Tile::titleColorProperty)
    var moduleDescriptorColor = bind(Tile::descriptionColorProperty)
    var moduleTitleAlignment = bind(Tile::titleAlignmentProperty)
    var moduleDescriptorAlignment = bind(Tile::descriptionAlignmentProperty)

    // Module Image
    var moduleImage = bind(Tile::graphicProperty)

    // Module Features
    var moduleMap = bind(Tile::mapProviderProperty)
    var moduleBarChartItem = bind(Tile::addBarChartItem)
    var moduleBarColor = bind(Tile::barColorProperty)
    var moduleBackgroundBarColor = bind(Tile::barBackgroundColorProperty)

    override fun onCommit(commits: List<Commit>) {
        super.onCommit(commits)

        // The println will only be called if findChanged is not null
        commits.findChanged(moduleSkin)?.let { println("Module Skin changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleBackground)?.let { println("Module Background changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleMaxHeight)?.let { println("Module MaxHeight changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleMaxWidth)?.let { println("Module MaxWidth changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleBorderColor)?.let { println("Module Skin changed from ${it.first} to ${it.second}")}

        commits.findChanged(moduleTitle)?.let { println("Module Title changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleTitleColor)?.let { println("Module TitleColor changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleTitleAlignment)?.let { println("Module TitleAlignment changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleDescriptor)?.let { println("Module Descriptor changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleDescriptorColor)?.let { println("Module DescriptorColor changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleDescriptorAlignment)?.let { println("Module DescriptorAlignment changed from ${it.first} to ${it.second}")}

        commits.findChanged(moduleImage)?.let { println("Module Image changed from ${it.first} to ${it.second}")}

        commits.findChanged(moduleMap)?.let { println("Module Map changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleBarChartItem)?.let { println("Module BarChartItem changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleBarColor)?.let { println("Module BarColor changed from ${it.first} to ${it.second}")}
        commits.findChanged(moduleBackgroundBarColor)?.let { println("Module BackgroundBarColor changed from ${it.first} to ${it.second}")}
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }

    class ModuleTileScope: Scope() {
        val model = ModuleModel()
    }
}
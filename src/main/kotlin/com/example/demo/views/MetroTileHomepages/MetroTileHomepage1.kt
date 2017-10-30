package com.example.demo.views.MetroTileHomepages

import com.example.demo.app.Styles.Companion.metroTileHomepageGUI
import tornadofx.*

class MetroTileHomepage1 : View("My View") {
    override val root = borderpane()

    init {
        with(root) {
            addClass(metroTileHomepageGUI)
            setPrefSize(750.0, 700.0)
        }
    }

}

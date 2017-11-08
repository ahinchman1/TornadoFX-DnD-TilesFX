package com.example.demo.app

import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    // holds class-level properties that can easily be retrieved
    companion object {
        val loginScreen by cssclass()
        val workbenchScreen by cssclass()
        val metroTileHomepageGUI by cssclass()
        val GUI by cssclass()
        val metroRect by cssclass()
    }

    // apply styling to classes
    init {
        reloadStylesheetsOnFocus()
        select(loginScreen) {
            padding = box(15.px)
            vgap = 7.px
            hgap = 10.px
        }

        select(workbenchScreen) {
            backgroundColor += Color.rgb(34,34,34)
        }

        select(metroTileHomepageGUI) {
            backgroundColor += Color.rgb(34,34,34)
        }

        select(GUI) {
            backgroundColor += Color.WHITE
        }

        select(metroRect) {
            borderColor += box(Color.BLACK)
        }
    }
}
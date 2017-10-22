package com.example.demo.app

import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet() {
    // holds class-level properties that can easily be retrieved
    companion object {
        val loginScreen by cssclass()
        val workbenchScreen by cssclass()
    }

    // apply styling to classes
    init {
        select(loginScreen) {
            padding = box(15.px)
            vgap = 7.px
            hgap = 10.px
        }

        select(workbenchScreen) {

        }
    }
}
package com.example.demo.views

import com.example.demo.controllers.LoginController
import tornadofx.*
import javafx.application.Platform
import javafx.scene.layout.BorderPane
import javafx.scene.text.Font


class Workbench : View() {
    override val root = BorderPane()
    private val loginController: LoginController by inject()



    private val kitties = listOf("https://www.colourbox.com/preview/2129303-cat-sleeping-by-sofa-kuzia-senior-cat-12-y-o.jpg",
            "https://1.bp.blogspot.com/-Q8bzpaeF4MI/U1xRQzc6iTI/AAAAAAAAEt8/3kLRKFOXc08/s1600/Cat.jpg",
            "https://i.pinimg.com/736x/5e/c9/7a/5ec97a6d548d57caafdd586b22390709--white-fluffy-kittens-fluffy-cat.jpg",
            "https://static.boredpanda.com/blog/wp-content/uploads/2017/09/animals-with-unusual-fur-markings-22-59ae8ed6d2edd__605.jpg",
            "https://i.ytimg.com/vi/tntOCGkgt98/maxresdefault.jpg",
            "https://peopledotcom.files.wordpress.com/2017/08/cat-money.jpg?crop=0px%2C0px%2C2000px%2C1051px&resize=1200%2C630",
            "https://d2wq73xazpk036.cloudfront.net/media/27FB7F0C-9885-42A6-9E0C19C35242B5AC/1510A438-083C-4E6B-804E297DCFC7427F/thul-6b0f599e-82d1-5051-a045-fc0648ae0996.jpg?response-content-disposition=inline",
            "https://peopledotcom.files.wordpress.com/2017/09/cat-named-dog-1.jpg?crop=0px%2C0px%2C2000px%2C1335.1648351648px&resize=728%2C486",
            "https://www.petfinder.com/wp-content/uploads/2012/11/99233806-bringing-home-new-cat-632x475.jpg",
            "http://www.sciencemag.org/sites/default/files/styles/article_main_large/public/still-no-logo.jpg?itok=CHQ16GAg")

    init {
        title = "Secure Workbench"

        with (root) {
            setPrefSize(800.0, 600.0)

            top {

                label(title) {
                    font = Font.font(22.0)
                }
            }

            center {

                datagrid(kitties) {

                    maxCellsInRow=3

                    cellWidth=200.0
                    cellHeight=200.0

                    cellCache {
                        imageview(it) {
                            fitWidth=200.0
                            fitHeight=200.0
                        }
                    }
                }
            }

            bottom {

                label("If you can see this, you are successfully logged in!")

                hbox {

                    button("Logout") {
                        setOnAction {
                            loginController.logout()
                        }
                    }

                    button("Exit") {
                        setOnAction {
                            Platform.exit()
                        }
                    }
                }
            }
        }
    }
}
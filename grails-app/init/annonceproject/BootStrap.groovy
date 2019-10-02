package annonceproject

import mbds_2019_2020.Annonce
import mbds_2019_2020.Illustration
import mbds_2019_2020.User

class BootStrap {

    def init = { servletContext ->
        def userInstance = new User(username: "Imen",
                password: "password",
                thumbnail: new Illustration(filename: "apple-touch-icon-retina.png"))
                .save(flush: true, failOnError: true)
        (1..5).each {
            userInstance.addToAnnonces(
                    new Annonce(
                            title: "title",
                            description: "description",
                            validTill: new Date(),
                            state: Boolean.TRUE)
                            .addToIllustrations(new Illustration(filename: "advancedgrails.svg"))
                            .addToIllustrations(new Illustration(filename: "favicon.ico"))
                            .addToIllustrations(new Illustration(filename: "apple-touch-icon-retina.png"))
            )
        }
        userInstance.save(flush:true, failOnError:true)
        if(!userInstance.save(flush:true)){
            println "error l'or de la savegarde file Boostrap"
        }
    }
    def destroy = {
    }
}
package mbds_2019_2020

import grails.validation.ValidationException
import org.apache.commons.lang.RandomStringUtils

import static org.apache.commons.lang.RandomStringUtils.*
import static org.springframework.http.HttpStatus.*

class IllustrationController {

    IllustrationService illustrationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond illustrationService.list(params), model:[illustrationCount: illustrationService.count()]
    }

    def show(Long id) {
        respond illustrationService.get(id)
    }

    def create() {
        respond new Illustration(params)
    }

    def save(Illustration illustration) {
        if (illustration == null) {
            notFound()
            return
        }

        try {
            illustrationService.save(illustration)
            println "creation avec succes"
        } catch (ValidationException e) {
            respond illustration.errors, view:'create'
            println "creation failed"
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'illustration.label', default: 'Illustration'), illustration.id])
                redirect illustration
            }
            '*' { respond illustration, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond illustrationService.get(id)
    }

    def upload() {

                def file = request.getFile('filename')
                if(file == null || file.empty) {
                    flash.message = "File cannot be empty"
                } else {
                    int randomStringLength = 5
                    String charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()
                    String randomString = random(randomStringLength, charset.toCharArray())
                    def illustrationInstance = new Illustration()
                    illustrationInstance.filename = file.originalFilename +randomString
                    illustrationInstance.save()
                    file.transferTo(new File('C:/Users/imen/nouveauDossier/GestionAnnonces/grails-app/assets/importedImages/'+ illustrationInstance.filename))

        }
        redirect (action:'index')
    }

    def update(Illustration illustration) {
        if (illustration == null) {
            notFound()
            return
        }

        try {
            illustrationService.save(illustration)
        } catch (ValidationException e) {
            respond illustration.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'illustration.label', default: 'Illustration'), illustration.id])
                redirect illustration
            }
            '*'{ respond illustration, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        illustrationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'illustration.label', default: 'Illustration'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'illustration.label', default: 'Illustration'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

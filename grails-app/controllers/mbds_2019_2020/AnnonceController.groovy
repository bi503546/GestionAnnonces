package mbds_2019_2020

import grails.validation.ValidationException
import java.text.SimpleDateFormat
import static org.apache.commons.lang.RandomStringUtils.random
import static org.springframework.http.HttpStatus.*

class AnnonceController {

    AnnonceService annonceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond annonceService.list(params), model:[annonceCount: annonceService.count()]
    }

    def show(Long id) {
        respond annonceService.get(id)
    }

    def create() {
        respond new Annonce(params)
    }

    def checkvalidity(Long id){

        Annonce annoncecurrent = Annonce.findById(id)
        def vt = annoncecurrent.validTill
        def date = new Date()
        //Return Value − The value 0 if the argument Date is equal to this Date;
        // a value less than 0 if this Date is before the Date argument;
        // and a value greater than 0 if this Date is after the Date argument.
        if (vt.compareTo(sdf)<0)
                {
                    annoncecurrent.state = false
        } else {

        }
    }


    def save(Annonce annonce) {
        if (annonce == null) {
            notFound()
            return
        }

        def file = request.getFiles('illu')

        file.each {
            if (it == null || it.empty) {
                flash.message = "File cannot be empty"
            } else {
                int randomStringLength = 4
                String charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()
                String randomString = random(randomStringLength, charset.toCharArray())
                it.transferTo(new File('C:/Users/DELL/Desktop/GestionAnnonces/grails-app/assets/importedImages/' + it.originalFilename + randomString))
                annonce.addToIllustrations(new Illustration(filename: it.originalFilename + randomString))
            }
        }


        try {
            annonceService.save(annonce)
        } catch (ValidationException e) {
            respond annonce.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'annonce.label', default: 'Annonce'), annonce.id])
                redirect annonce
            }
            '*' { respond annonce, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond annonceService.get(id)
    }

    def update(Annonce annonce) {
        if (annonce == null) {
            notFound()
            return
        }

        try {
            annonceService.save(annonce)
        } catch (ValidationException e) {
            respond annonce.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'annonce.label', default: 'Annonce'), annonce.id])
                redirect annonce
            }
            '*'{ respond annonce, [status: OK] }
        }
    }

    def deletefromillustrations(){

        def illustrationId = params.param2
        def annonceId = params.param1

        def annonceInstance = Annonce.get(annonceId)
        def IllustrationInstance = Illustration.get(illustrationId)

        annonceInstance.removeFromIllustrations(IllustrationInstance)
        annonceInstance.save(flush:true)

        IllustrationInstance.delete(flush:true)

    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        annonceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'annonce.label', default: 'Annonce'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'annonce.label', default: 'Annonce'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

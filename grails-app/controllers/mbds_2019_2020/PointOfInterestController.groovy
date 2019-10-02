package mbds_2019_2020

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PointOfInterestController {

    PointOfInterestService pointOfInterestService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pointOfInterestService.list(params), model:[pointOfInterestCount: pointOfInterestService.count()]
    }

    def show(Long id) {
        respond pointOfInterestService.get(id)
    }
    def editFeaturedImage(Long id) {
        PointOfInterest pointOfInterest = pointOfInterestService.get(id)
        if (!pointOfInterest) {
            notFound()
            return
        }
        [pointOfInterest: pointOfInterest]
    }
    def create() {
        respond new PointOfInterest(params)
    }

    def save(PointOfInterest pointOfInterest) {
        if (pointOfInterest == null) {
            notFound()
            return
        }

        try {
            pointOfInterestService.save(pointOfInterest)
        } catch (ValidationException e) {
            respond pointOfInterest.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pointOfInterest.label', default: 'PointOfInterest'), pointOfInterest.id])
                redirect pointOfInterest
            }
            '*' { respond pointOfInterest, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond pointOfInterestService.get(id)
    }

    def uploadFeaturedImage(FeaturedImageCommand cmd) {

        if (cmd.hasErrors()) {
            respond(cmd, model: [pointOfInterest: cmd], view: 'editFeaturedImage')
            return
        }

        PointOfInterest pointOfInterest = uploadPointOfInterestFeaturedImageService.uploadFeatureImage(cmd)

        if (pointOfInterest == null) {
            notFound()
            return
        }

        if (pointOfInterest.hasErrors()) {
            respond(pointOfInterest, model: [pointOfInterest: pointOfInterest], view: 'editFeaturedImage')
            return
        }

        Locale locale = request.locale
        flash.message = crudMessageService.message(CRUD.UPDATE, domainName(locale), pointOfInterest.id, locale)
        redirect pointOfInterest
    }
    def update(PointOfInterest pointOfInterest) {
        if (pointOfInterest == null) {
            notFound()
            return
        }

        try {
            pointOfInterestService.save(pointOfInterest)
        } catch (ValidationException e) {
            respond pointOfInterest.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pointOfInterest.label', default: 'PointOfInterest'), pointOfInterest.id])
                redirect pointOfInterest
            }
            '*'{ respond pointOfInterest, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pointOfInterestService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pointOfInterest.label', default: 'PointOfInterest'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointOfInterest.label', default: 'PointOfInterest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

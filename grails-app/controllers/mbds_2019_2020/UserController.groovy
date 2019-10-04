package mbds_2019_2020

import grails.validation.ValidationException

import static org.apache.commons.lang.RandomStringUtils.random
import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model: [userCount: userService.count()]
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {
        if (user == null) {
            notFound()
            return
        }
        def file = request.getFile('file')
        if (file == null || file.empty) {
            flash.message = "File cannot be empty"
        } else
        {
            int randomStringLength = 4
            String charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()
            String randomString = random(randomStringLength, charset.toCharArray())
            file.transferTo(new File('C:/Users/DELL/Desktop/GestionAnnonces/grails-app/assets/importedImages/' + file.originalFilename + randomString))
            user.thumbnail = new Illustration(filename : file.originalFilename + randomString)
        }

            try {
                userService.save(user)
            } catch (ValidationException e) {
                respond user.errors, view: 'create'
                return
            }

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                    redirect user
                }
                '*' { respond user, [status: CREATED] }
            }
        }

    def edit(Long id) {
        respond userService.get(id)

    }

    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
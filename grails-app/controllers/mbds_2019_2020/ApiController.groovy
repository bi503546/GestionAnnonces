package mbds_2019_2020

import grails.converters.JSON
import grails.converters.XML
import java.text.SimpleDateFormat
class ApiController {
    AnnonceService annonceService
    def pattern = "dd-MM-yyyy"

    def annonce() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def annonce = Annonce.get(params.id)
                println(annonce)
                if (!annonce)
                    return response.status = 404
                response.withFormat {
                    json { render annonce as JSON }
                    xml { render annonce as XML }
                }
                break
            case "PUT":
                if (!params.id)
                    return response.status = 400
                    def annonce = Annonce.get(params.id)
                if (!annonce)
                    return response.status = 404
                //request.SON
                if (!request.JSON.title || !request.JSON.description || !request.JSON.validTill || !request.JSON.state || !request.JSON.dateCreated)
                    return response.status = 400
                annonce.dateCreated = new SimpleDateFormat(pattern).parse(request.JSON.dateCreated)
                annonce.validTill = new SimpleDateFormat(pattern).parse(request.JSON.validTill)
                annonce.state = new Boolean(request.JSON.state)
                annonce.description = request.JSON.description
                annonce.title = request.JSON.title
                annonceService.save(annonce)
                return response.status = 200
                break
            case "PATCH" :
                if (!params.id)
                    return response.status = 400
                def annonce = Annonce.get(params.id)
                if (!annonce)
                    return response.status = 404
                if(request.JSON.dateCreated)
                    annonce.dateCreated = new SimpleDateFormat(pattern).parse(request.JSON.dateCreated)
                if(request.JSON.validTill)
                    annonce.validTill = (new SimpleDateFormat(pattern).parse(request.JSON.validTill))
                if(request.JSON.state)
                    annonce.state = new Boolean(request.JSON.state)
                if(request.JSON.description)
                    annonce.description = request.JSON.description
                if(request.JSON.title)
                    annonce.title = request.JSON.title
                annonceService.save(annonce)
                return response.status = 200
            case "DELETE" :
                if (!params.id)
                    return response.status = 400
                def annonce = Annonce.get (params.id)
                 if (!annonce)
                    return response.status = 404
                annonce.delete(flush: true)
                return response.status = 200
            default :
                return response.status = 405 //405 Method Not Allowed
                break
        }
        return response.status = 406 //406 Not Acceptable
    }

    def annonces() {
        switch(request.getMethod()) {
            case "GET" :
                def annonces = Annonce.getAll()
                if (!annonces)
                    return response.status = 404
                response.withFormat {
                    json { render annonces as JSON }
                    xml { render annonces as XML }
                }
                break
            case "POST" :
                if(!request.JSON.title || !request.JSON.description || !request.JSON.validTill)
                    return response.status = 404
                def newAnnonce = new Annonce(
                        title: request.JSON.title,
                        description: request.JSON.description,
                        validTill: new SimpleDateFormat(pattern).parse(request.JSON.validTill),
                        dateCreated: new Date(),
                        state: Boolean.TRUE
                )
                if(params.userId){
                    newAnnonce.author = User.get(request.JSON.userId)
                }
                annonceService.save(newAnnonce)
                return response.status = 201
                break
            default :
                return response.status = 405
                break
        }
        return response.status = 406
    }
}
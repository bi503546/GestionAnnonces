package mbds_2019_2020

import grails.gorm.services.Service

@Service(Annonce)
interface AnnonceService {

    Annonce get(Serializable id)

    List<Annonce> list(Map args)

    Long count()

    void delete(Serializable id)

    Annonce save(Annonce annonce)

}
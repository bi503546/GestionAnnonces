package mbds_2019_2020

import grails.gorm.services.Service

@Service(PointOfInterest)
interface PointOfInterestService {

    PointOfInterest get(Serializable id)

    List<PointOfInterest> list(Map args)

    Long count()

    void delete(Serializable id)

    PointOfInterest save(PointOfInterest pointOfInterest)

    PointOfInterest updateFeaturedImageUrl(Serializable id, Long version, String featuredImageUrl)

}
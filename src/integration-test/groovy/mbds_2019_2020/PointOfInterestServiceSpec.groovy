package mbds_2019_2020

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PointOfInterestServiceSpec extends Specification {

    PointOfInterestService pointOfInterestService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PointOfInterest(...).save(flush: true, failOnError: true)
        //new PointOfInterest(...).save(flush: true, failOnError: true)
        //PointOfInterest pointOfInterest = new PointOfInterest(...).save(flush: true, failOnError: true)
        //new PointOfInterest(...).save(flush: true, failOnError: true)
        //new PointOfInterest(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //pointOfInterest.id
    }

    void "test get"() {
        setupData()

        expect:
        pointOfInterestService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PointOfInterest> pointOfInterestList = pointOfInterestService.list(max: 2, offset: 2)

        then:
        pointOfInterestList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pointOfInterestService.count() == 5
    }

    void "test delete"() {
        Long pointOfInterestId = setupData()

        expect:
        pointOfInterestService.count() == 5

        when:
        pointOfInterestService.delete(pointOfInterestId)
        sessionFactory.currentSession.flush()

        then:
        pointOfInterestService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PointOfInterest pointOfInterest = new PointOfInterest()
        pointOfInterestService.save(pointOfInterest)

        then:
        pointOfInterest.id != null
    }
}

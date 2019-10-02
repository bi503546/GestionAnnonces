package mbds_2019_2020

class PointOfInterest {
    String name
    String featuredImageUrl // <1>

    static constraints = {
        featuredImageUrl nullable: true
    }

}

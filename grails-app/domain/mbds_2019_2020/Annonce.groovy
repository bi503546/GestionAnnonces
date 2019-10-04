package mbds_2019_2020

class Annonce {
    String title
    String description
    Date dateCreated
    Date validTill
    Boolean state = Boolean.FALSE

    static belongsTo=[author: User]

    static hasMany=[illustrations: Illustration]

    static constraints = {
        title blank: false, nullable: false
        description blank: false, nullable: false
        validTill blank: false
    }

}
package mbds_2019_2020

class User {
    String username
    String password
    Date dateCreated
    Date lastUpdated
    Illustration thumbnail

    static hasMany = [annonces: Annonce]

    static constraints = {
        username blank:false, nullable :false, size:4..20
        password password:true, blank:false, nullable: false, size:5..20
        thumbnail nullable: false
        annonces nullable: true

    }


}

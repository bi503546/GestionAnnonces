package mbds_2019_2020

class MyJob {

    static triggers = {
        simple repeatInterval: 9000
    }

    void execute() {
        print "Ce message chaque 5 seconde"
            def annonces = Annonce.getAll()
        annonces.each{
            if(it.validTill.before(new Date())){
                it.state = Boolean.FALSE
            }
            it.save(flush:true)
        }

        }

}
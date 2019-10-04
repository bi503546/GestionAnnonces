package mbds_2019_2020

class MyJob {

    static triggers = {
        simple repeatInterval: 5000
    }

    Annonce annonce

    void execute() {
        print "Ce message chaque 5 seconde"
    }
}
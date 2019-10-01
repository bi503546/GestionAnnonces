package mbds_2019_2020


class Illustration {
    String filename

    static constraints = {
        filename blank: false, nullable: false
    }


    String toString(){
        return filename
    }

}

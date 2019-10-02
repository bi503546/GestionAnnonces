package mbds_2019_2020

import org.hibernate.validator.internal.metadata.facets.Cascadable
import org.hibernate.validator.internal.metadata.facets.Validatable
import org.springframework.web.multipart.MultipartFile

class FeaturedImageCommand implements Validatable{
    MultipartFile featuredImageFile
    Long id
    Integer version

    static constraints = {
        id nullable: false
        version nullable: false
        featuredImageFile  validator: { val, obj ->
            if ( val == null ) {
                return false
            }
            if ( val.empty ) {
                return false
            }

            ['jpeg', 'jpg', 'png'].any { extension -> // <1>
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }

    @Override
    Iterable<Cascadable> getCascadables() {
        return null
    }
}

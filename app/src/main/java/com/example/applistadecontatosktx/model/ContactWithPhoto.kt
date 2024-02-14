package com.example.applistadecontatosktx.model

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class ContactWithPhoto(

    @Embedded var contact: Contact,

    @Relation(parentColumn = "photoId", entityColumn = "id")
    var photo: Photo
) : Serializable

/**
 *
 * We can use @Embedded and create a ready-to-use object with its loaded relationships.
 * This object will already have data saved as internal attributes as long as they have a relationship.
 *
 * To work with this approach, we just need to remember two things:
 *
 *  @Embedded: Which main object, the object that has references to other entities;
 *  @Relation: It forms a link between two entities that relate to each other, it contains two essential attributes:
 *
 *      parentColumn -> represents which entity field associated with @Embedded indicates relationship with secondary entity
 *      entityColumn -> represents which identity column for secondary entity
 *
 *
 *
 *
 * Podemos usar o @Embedded e criar um objeto já pronto para uso com suas relações carregadas viewModel.
 * Esse objeto já terá os dados guardados como atributos internos desde que possuam uma relação.
 *
 * Para trabalharmos essa abordagem basta lembrarmos de duas coisas:
 *
 *  @Embedded: Qual objeto principal, o objeto que possui as referencias a outra entidade;
 *  @Relation: Faz a ligação entre duas entidades que possuem relações umas com as outras, possui dois atributos essenciais:
 *      parentColumn -> representa qual o campo da entidade associada ao @Embedded sinaliza relação com a entidade secundaria
 *      entityColumn -> representa qual a coluna identidade da entidade secundaria
 *
 * **/
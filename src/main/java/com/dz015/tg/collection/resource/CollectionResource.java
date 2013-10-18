package com.dz015.tg.collection.resource;

import com.dz015.tg.collection.model.TGCollection;
import com.dz015.tg.collection.service.CollectionService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/collection/{id}")
public class CollectionResource {

    @Inject
    private CollectionService dataService;

    @GET
    @Produces("application/json")
    public TGCollection getCollection(@PathParam("id") long id) {
        TGCollection collection = dataService.getCollection(id);
        if (collection == null) {
            throw new WebApplicationException(404);
        }
        return collection;
    }

}

package com.dz015.tg.collection.resource;

import com.dz015.tg.collection.service.CollectionService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/collection/{id}")
public class CollectionResource {

    @Inject
    private CollectionService dataService;

    @GET
    public String getCollection(@PathParam("id") long id) {
        return dataService.getCollection(id).getName();
    }

}

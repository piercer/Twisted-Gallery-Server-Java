package com.dz015.tg.resource;

import com.dz015.tg.service.data.TGDataService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/collection/{id}")
public class CollectionResource {

    @Inject
    private TGDataService dataService;

    @GET
    public String getCollection(@PathParam("id") long id) {
        return dataService.getCollection(id).getName();
    }

}

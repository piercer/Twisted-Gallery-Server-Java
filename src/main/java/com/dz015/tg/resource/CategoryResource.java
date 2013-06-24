package com.dz015.tg.resource;

import com.dz015.tg.service.data.TGDataService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/category/{id}")
public class CategoryResource {

    @Inject
    private TGDataService dataService;

    @GET
    public String getCategory(@PathParam("id") long id) {
        return dataService.getCategory(id).getName();
    }

}

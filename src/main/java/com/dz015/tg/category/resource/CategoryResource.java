package com.dz015.tg.category.resource;

import com.dz015.tg.category.service.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/category/{id}")
public class CategoryResource {

    @Inject
    private CategoryService dataService;

    @GET
    public String getCategory(@PathParam("id") long id) {
        return dataService.getCategory(id).getName();
    }

}

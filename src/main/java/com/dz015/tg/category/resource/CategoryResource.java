package com.dz015.tg.category.resource;

import com.dz015.tg.category.model.TGCategory;
import com.dz015.tg.category.service.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/category/{id}")
public class CategoryResource {

    @Inject
    private CategoryService dataService;

    @GET
    @Produces("application/json")
    public TGCategory getCategory(@PathParam("id") long id) {
        TGCategory category = dataService.getCategory(id);
        if (category == null) {
            throw new WebApplicationException(404);
        }
        return category;
    }

}

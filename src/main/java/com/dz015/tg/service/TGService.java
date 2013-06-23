package com.dz015.tg.service;

import com.dz015.tg.service.data.TGDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Service
@Path("/gallery")
public class TGService {

    @Autowired
    private TGDataService dataService;

    @GET
    @Path("/collection/{id}")
    public String getCollection(@PathParam("id") long id) {
        return dataService.getCollection(id).getName();
    }

    @GET
    @Path("/category/{id}")
    public String getCategory(@PathParam("id") long id) {
        return dataService.getCategory(id).getName();
    }
}

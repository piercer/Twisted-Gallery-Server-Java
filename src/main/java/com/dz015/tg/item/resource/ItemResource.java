package com.dz015.tg.item.resource;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Path("/image/{id}/{width}X{height}")
@Produces("image/jpeg")
public class ItemResource {

    @GET
    public BufferedImage getFullImage(@PathParam("id") int id, @PathParam("width") int width, @PathParam("height") int height) {

        BufferedImage image;

        try {
            image = ImageIO.read(new URL("file:///Users/conrad/Documents/fitear2/DSC04525.jpg"));
        }
        catch (IOException e) {
            throw new WebApplicationException(404);
        }

        //ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //try {
        //    ImageIO.write(image, "png", baos);
        //} catch (IOException e) {
        //    throw new WebApplicationException(404);
        // }
        //byte[] imageData = baos.toByteArray();

        // uncomment line below to send non-streamed
        // return Response.ok(imageData).build();

        // uncomment line below to send streamed
        return image;
    }

}

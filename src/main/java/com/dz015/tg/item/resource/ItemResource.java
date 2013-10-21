package com.dz015.tg.item.resource;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Path("/image/{id}/{width}X{height}")
@Produces("image/jpeg")
public class ItemResource {

    @GET
    public BufferedImage getImage(@PathParam("id") int id, @PathParam("width") int width, @PathParam("height") int height) {

        final BufferedImage original;
        final BufferedImage resized = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = resized.createGraphics();

        try {
            original = ImageIO.read(new URL("file:///Users/conrad/Documents/WeddingPhotos/DSC03626.jpg"));
            graphics2D.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
            graphics2D.drawImage(original,0,0,width,height,null);
            graphics2D.dispose();
        }
        catch (IOException e) {
            throw new WebApplicationException(404);
        }

        return resized;
    }

}

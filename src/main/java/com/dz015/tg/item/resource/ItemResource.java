package com.dz015.tg.item.resource;

import com.dz015.tg.item.model.TGItem;
import com.dz015.tg.item.service.ItemService;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Path("/item/{id}/{width}X{height}")
@Produces("image/jpeg")
public class ItemResource {

    @Inject
    ItemService itemService;

    @GET
    public BufferedImage getImage(@PathParam("id") long id, @PathParam("width") int width, @PathParam("height") int height) {

        final BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        try {
            TGItem imageItem = itemService.getItem(id);
            final BufferedImage original = ImageIO.read(new URL("file://" + imageItem.getPath()));
            final Graphics2D graphics2D = resized.createGraphics();
            graphics2D.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            graphics2D.drawImage(original, 0, 0, width, height, null);
            graphics2D.dispose();
        }
        catch (IOException e) {
            throw new WebApplicationException(404);
        }

        return resized;
    }

}

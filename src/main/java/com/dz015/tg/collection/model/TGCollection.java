package com.dz015.tg.collection.model;

import com.dz015.tg.item.model.TGItem;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="collection")
public class TGCollection {

    private final String name;
    private final String description;
    private final long id;
    private final int nItems;
    private final int previewId;
    private final ArrayList<TGItem> items;


    private TGCollection(CollectionBuilder builder) {
        id = builder.id;
        name = builder.name;
        description = builder.description;
        nItems = builder.nItems;
        previewId = builder.previewId;
        items = builder.items;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getnItems() {
        return nItems;
    }

    public String getDescription() {
        return description;
    }

    public int getPreviewId() {
        return previewId;
    }

    public ArrayList<TGItem> getItems() {
        return items;
    }

    public static class CollectionBuilder {

        private final long id;
        private final String name;
        private String description;
        private int nItems;
        private int previewId;
        private ArrayList<TGItem> items;

        public CollectionBuilder(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public CollectionBuilder description(String val) {
            description = val;
            return this;
        }

        public CollectionBuilder nItems(int val) {
            nItems = val;
            return this;
        }

        public CollectionBuilder previewId(int val) {
            previewId = val;
            return this;
        }

        public CollectionBuilder items(ArrayList<TGItem> val) {
            items = val;
            return this;
        }

        public TGCollection build() {
            return new TGCollection(this);
        }
    }
}

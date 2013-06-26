package com.dz015.tg.collection.service;

import com.dz015.tg.collection.model.TGCollection;

public class DummyCollectionService implements CollectionService {

    @Override
    public TGCollection getCollection(long collectionId) {
        return null;//new TGCollection(collectionId);
    }

}

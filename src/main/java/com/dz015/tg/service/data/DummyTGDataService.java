package com.dz015.tg.service.data;

import com.dz015.tg.model.TGCategory;
import com.dz015.tg.model.TGCollection;

public class DummyTGDataService implements TGDataService {
    @Override
    public TGCollection getCollection(long collectionId) {
        return new TGCollection(collectionId);
    }

    @Override
    public TGCategory getCategory(long categoryId) {
        return new TGCategory(categoryId);
    }
}

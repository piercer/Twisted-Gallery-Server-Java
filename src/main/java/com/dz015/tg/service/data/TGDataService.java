package com.dz015.tg.service.data;

import com.dz015.tg.category.model.TGCategory;
import com.dz015.tg.collection.model.TGCollection;

public interface TGDataService {

    TGCollection getCollection(long collectionId);
    TGCategory getCategory(long categoryId);

}

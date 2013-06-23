package com.dz015.tg.service.data;

import com.dz015.tg.model.TGCategory;
import com.dz015.tg.model.TGCollection;

public interface TGDataService {

    TGCollection getCollection(long collectionId);
    TGCategory getCategory(long categoryId);

}

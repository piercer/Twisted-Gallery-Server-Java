package com.dz015.tg.category.service;

import com.dz015.tg.category.model.TGCategory;

public class DummyCategoryService implements CategoryService {

    @Override
    public TGCategory getCategory(long categoryId) {
        return null;//new TGCategory(categoryId);
    }

}

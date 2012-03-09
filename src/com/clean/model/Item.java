package com.clean.model;

import com.j256.ormlite.field.DatabaseField;

public class Item {

    @DatabaseField(generatedId = true)
    public int _id;
    
    public static final String NAME_FIELD_NAME = "name";
    @DatabaseField(canBeNull = false, columnName = NAME_FIELD_NAME)
    public String name;

    public static final String IS_ACTIVE_FIELD_NAME = "is_active";
    @DatabaseField(canBeNull = true, columnName = IS_ACTIVE_FIELD_NAME)
    public Boolean is_active;
    
    public static final String ITEM_ORDER="item_order";
    @DatabaseField(canBeNull = false,columnName = ITEM_ORDER)
    public int order;
    
    public static final String 	PARENT_ID="parent_id";
    @DatabaseField(canBeNull = false,columnName = PARENT_ID)
    public int parentId;
    
    public static final String CHILDREN_COUNT="children_count";
    @DatabaseField(canBeNull = false,columnName = CHILDREN_COUNT)
    public int childrenNum;
}

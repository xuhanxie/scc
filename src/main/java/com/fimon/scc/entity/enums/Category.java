package com.fimon.scc.entity.enums;

/*
Intended to let category be enum types,
but when frontend send integers to backend,
we need to manually implement functions to map them to enum types
so current we just use integer types
the same case with itemstatus
 */
public enum Category {

    UNCATEGORIZED,

    FOOD,

    CLOTHES,

    BOOK,

    ELECTRONICS,

    // more ...

}

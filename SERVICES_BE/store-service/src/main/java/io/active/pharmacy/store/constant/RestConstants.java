package io.active.pharmacy.store.constant;

import io.active.pharmacy.store.dto.ListRequest;

public class RestConstants {

    public static final String PAGE_INDEX = "index";
    public static final String PAGE_INDEX_DEFAULT = "0";

    public static final String PAGE_SIZE = "size";
    public static final String PAGE_SIZE_DEFAULT = "10";

    public static ListRequest defaultListRequest = new ListRequest();
}

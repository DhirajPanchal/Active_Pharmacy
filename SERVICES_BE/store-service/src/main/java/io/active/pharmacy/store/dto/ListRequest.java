package io.active.pharmacy.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListRequest {

    private Map<String, String> sort = new HashMap<>();

    private List<FilterItem> filter = new ArrayList<>();

    private boolean onlyActive = false;

}


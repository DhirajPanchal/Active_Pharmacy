package io.active.pharmacy.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListRequest {

    private Map<String, String> sort = new HashMap<>();

    private Map<String, String> filter = new HashMap<>();

    private boolean onlyActive = false;

}
package io.active.pharmacy.store.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListResponse<E> {

    private List<E> list;

    private Long count;

    public ListResponse(List<E> list, Long count) {
        this.list = list;
        this.count = count;
    }
}
package io.active.pharmacy.store.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListResponse<E> {

    private List<E> content;

    private Long count;

    public ListResponse(List<E> content, Long count) {
        this.content = content;
        this.count = count;
    }
}
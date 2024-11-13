package io.active.pharmacy.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterItem{
    private String field;
    private String operator;
    private String value;

    @Override
    public String toString() {
        return "FilterItem{" +
                "field='" + field + '\'' +
                ", operator='" + operator + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}


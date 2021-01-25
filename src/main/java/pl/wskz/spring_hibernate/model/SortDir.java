package pl.wskz.spring_hibernate.model;

import org.springframework.data.domain.Sort;

public enum SortDir {
    ASC(Sort.Direction.ASC),
    DESC(Sort.Direction.DESC);

    private final Sort.Direction sortDirection;

    SortDir(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }
}

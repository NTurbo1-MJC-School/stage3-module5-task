package com.mjc.school.controller;

import java.util.List;

public interface BaseController<T, R, K> {

    List<R> readAll(String sort, int page);

    R readById(K id);

    R create(T createRequest);

    R update(K id, T updateRequest);

    void deleteById(K id);
}

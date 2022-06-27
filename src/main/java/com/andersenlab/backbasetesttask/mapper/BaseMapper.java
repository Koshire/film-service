package com.andersenlab.backbasetesttask.mapper;

public interface BaseMapper<M, D> {

    M mapToModel(D dto);
    D mapToDto(M model);
}

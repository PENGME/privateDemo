package com.pm.demo.service;

import com.pm.demo.entity.Execel;

import java.util.List;

public interface ExecelService {
    List<Execel> findAll();
    int add(Execel execel);
    int del();
}

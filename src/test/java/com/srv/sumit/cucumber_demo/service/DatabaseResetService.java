package com.srv.sumit.cucumber_demo.service;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabaseResetService {
    private final EntityManager entityManager;

    public DatabaseResetService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void resetEmployeeTable() {
        entityManager.createNativeQuery("ALTER TABLE employee ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }
}

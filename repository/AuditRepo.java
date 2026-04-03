package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.AuditLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepo extends JpaRepository<AuditLogs ,Long> {
}

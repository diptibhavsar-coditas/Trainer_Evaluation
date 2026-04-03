package com.example.Ecommerce.aspect;

import com.example.Ecommerce.entity.AuditLogs;
import com.example.Ecommerce.repository.AuditRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuditAspect {

    private final AuditRepo auditLogRepository;

    @AfterReturning("execution(* com.example.ecommerce.service.OrderService.placeOrder(..))")
    public void auditOrderCreation(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        Long userId = (Long) args[0];

        AuditLogs auditLog = AuditLogs.builder()
                .userId(userId)
                .action("ORDER_CREATED")
                .methodName(joinPoint.getSignature().getName())
                .timestamp(LocalDateTime.now())
                .build();

        auditLogRepository.save(auditLog);

        log.info("Audit log saved for order creation. User ID: {}", userId);
    }

    @AfterReturning("execution(* com.example.ecommerce.service.impl.ProductServiceImpl.updateProduct(..))")
    public void auditProductUpdate(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        Long userId = (Long) args[0];

        AuditLogs auditLog = AuditLogs.builder()
                .userId(userId)
                .action("PRODUCT_UPDATED")
                .methodName(joinPoint.getSignature().getName())
                .timestamp(LocalDateTime.now())
                .build();

        auditLogRepository.save(auditLog);

        log.info("Audit log saved for product update. Admin ID: {}", userId);
    }

    @AfterReturning("execution(* com.example.ecommerce.service.OrderService.cancelOrder(..))")
    public void auditOrderCancellation(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        Long userId = (Long) args[0];
        Long orderId = (Long) args[1];

        AuditLogs auditLog = AuditLogs.builder()
                .userId(userId)
                .action("ORDER_CANCELLED")
                .methodName(joinPoint.getSignature().getName() + " - Order ID: " + orderId)
                .timestamp(LocalDateTime.now())
                .build();

        auditLogRepository.save(auditLog);

        log.info("Audit log saved for order cancellation. User ID: {}, Order ID: {}",
                userId, orderId);
    }
}
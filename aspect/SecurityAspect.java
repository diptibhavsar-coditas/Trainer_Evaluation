package com.example.Ecommerce.aspect;

import com.example.Ecommerce.exception.UnAuthorizedAccessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SecurityAspect {

    @Before("execution(* com.example.ecommerce.service.OrderService.placeOrder(..))")
    public void validateUserBeforeOrder(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        Long userId = (Long) args[0];

        if (userId == null || userId <= 0) {
            throw new  UnAuthorizedAccessException("Invalid user. Please login first.");
        }

        log.info("User validation successful for placing order. User ID: {}", userId);
    }

    @Before("execution(* com.example.ecommerce.service.impl.ProductServiceImpl.updateProduct(..))")
    public void validateAdminBeforeProductUpdate(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        Long userId = (Long) args[0];
        String role = (String) args[1];

        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new UnAuthorizedAccessException("Only admin can update product.");
        }

        log.info("Admin validation successful. Admin ID: {}", userId);
    }

    @Before("execution(* com.example.ecommerce.service.impl.OrderServiceImpl.cancelOrder(..))")
    public void validateUserBeforeCancelOrder(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        Long userId = (Long) args[0];
        Long orderId = (Long) args[1];

        if (userId == null || orderId == null) {
            throw new  UnAuthorizedAccessException("Invalid cancel request.");
        }

        log.info("Validation successful for cancelling order. User ID: {}, Order ID: {}",
                userId, orderId);
    }
}

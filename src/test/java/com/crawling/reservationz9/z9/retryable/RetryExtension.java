package com.crawling.reservationz9.z9.retryable;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.lang.reflect.InvocationTargetException;

public class RetryExtension implements TestExecutionExceptionHandler {
    private final int retryCount = 1000;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) {
        for (int i = 1; i <= retryCount; i++) {
            try {
                context.getTestMethod().ifPresent(method -> {
                    try {
                        method.invoke(context.getTestInstance().orElseThrow());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
                
                return; // 성공 시 종료
            } catch (Exception t) {
                System.out.println(context.getDisplayName() + " 실패 - 재시도 중: " + i);
                if (i == retryCount) {
                    throw t; // 재시도 후에도 실패하면 예외 던짐
                }
            }
        }
    }
}

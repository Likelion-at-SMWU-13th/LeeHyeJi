package com.likelion.banking.integration;

import com.likelion.banking.domain.Account;
import com.likelion.banking.exception.AccountNotFoundException;
import com.likelion.banking.repository.AccountRepository;
import com.likelion.banking.service.TransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;

/**
 * TransferService의 통합 테스트
 * 
 * 단위 테스트와의 차이점:
 * - @SpringBootTest로 스프링 컨텍스트가 실행됨
 * - @Autowired로 실제 빈을 주입받음
 * - @MockBean으로 스프링 컨텍스트의 빈을 Mock으로 대체
 * 
 * 목표:
 * - 스프링 컨텍스트 내에서 테스트 수행
 * - @Transactional 등 스프링 기능이 정상 작동하는지 확인
 */
@SpringBootTest
class TransferServiceIntegrationTests {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private TransferService transferService;

    /**
     * 실습 2-1: 스프링 통합 테스트
     * 
     * 단위 테스트와 동일한 시나리오지만,
     * 이번에는 스프링 컨텍스트가 실행된 상태에서 테스트합니다.
     */
    @Test
    @DisplayName("통합 테스트: 스프링 컨텍스트 내에서 계좌 이체가 수행된다")
    void transferMoneyIntegrationTest() {
        // Given
        Account sender = new Account(1L, "John", new BigDecimal(1000));
        Account receiver = new Account(2L, "Jane", new BigDecimal(1000));

        given(accountRepository.findById(1L))
                .willReturn(Optional.of(sender));
        given(accountRepository.findById(2L))
                .willReturn(Optional.of(receiver));

        // When
        transferService.transferMoney(1L, 2L, new BigDecimal(100));

        // Then
        verify(accountRepository).changeAmount(1L, new BigDecimal(900));
        verify(accountRepository).changeAmount(2L, new BigDecimal(1100));
    }

    /**
     * 과제 1: 발신인 계좌를 찾을 수 없는 경우
     */
    @Test
    @DisplayName("예외 플로우: 발신인 계좌를 찾을 수 없으면 예외가 발생한다")
    void moneyTransferSenderAccountNotFoundIntegrationTest() {
        // Given
        given(accountRepository.findById(999L))
                .willReturn(Optional.empty());

        // When & Then
        assertThrows(AccountNotFoundException.class, () -> {
            transferService.transferMoney(999L, 2L, new BigDecimal(100));
        });

        verify(accountRepository, never())
                .changeAmount(anyLong(), any(BigDecimal.class));
    }

    /**
     * 과제 2: 수취인 계좌를 찾을 수 없는 경우
     */
    @Test
    @DisplayName("예외 플로우: 수취인 계좌를 찾을 수 없으면 예외가 발생한다")
    void moneyTransferReceiverAccountNotFoundIntegrationTest() {
        // Given
        Account sender = new Account(1L, "John", new BigDecimal(1000));

        given(accountRepository.findById(1L))
                .willReturn(Optional.of(sender));

        given(accountRepository.findById(999L))
                .willReturn(Optional.empty());

        // When & Then
        AccountNotFoundException exception = assertThrows(
                AccountNotFoundException.class,
                () -> transferService.transferMoney(1L, 999L, new BigDecimal(100))
        );

        assertTrue(exception.getMessage().contains("Receiver"));
        verify(accountRepository, never())
                .changeAmount(anyLong(), any(BigDecimal.class));
    }
}
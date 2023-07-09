package com.example.loans.repository;

import com.example.loans.model.Loan;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaAttributeConverter<Loan, Integer> {

    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);
}

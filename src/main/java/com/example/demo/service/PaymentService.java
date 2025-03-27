package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
    
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
    
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    
    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment existingPayment = getPaymentById(id);
        if(existingPayment != null) {
            existingPayment.setAmount(updatedPayment.getAmount());
            existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
            existingPayment.setPaymentStatus(updatedPayment.getPaymentStatus());
            existingPayment.setPaymentDate(updatedPayment.getPaymentDate());
            existingPayment.setBooking(updatedPayment.getBooking());
            return paymentRepository.save(existingPayment);
        }
        return null;
    }
    
    public boolean deletePayment(Long id) {
        Payment payment = getPaymentById(id);
        if(payment != null) {
            paymentRepository.delete(payment);
            return true;
        }
        return false;
    }
}

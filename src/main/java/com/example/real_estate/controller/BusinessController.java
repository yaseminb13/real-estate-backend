package com.example.real_estate.controller;

import com.example.real_estate.model.Business;
import com.example.real_estate.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/businesses")
@CrossOrigin(origins = "*") // tarayıcıdan erişim için (güvenli)
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    // Tüm işletmeleri listele
    @GetMapping
    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    // ID’ye göre işletme getir
    @GetMapping("/{id}")
    public Optional<Business> getBusinessById(@PathVariable Long id) {
        return businessRepository.findById(id);
    }

    // Yeni işletme ekle
    @PostMapping
    public Business createBusiness(@RequestBody Business business) {
        return businessRepository.save(business);
    }

    // Var olan işletmeyi güncelle
    @PutMapping("/{id}")
    public Business updateBusiness(@PathVariable Long id, @RequestBody Business updatedBusiness) {
        return businessRepository.findById(id)
                .map(business -> {
                    business.setName(updatedBusiness.getName());
                    business.setAuthorizedPerson(updatedBusiness.getAuthorizedPerson());
                    business.setAddress(updatedBusiness.getAddress());
                    business.setPhone(updatedBusiness.getPhone());
                    business.setFax(updatedBusiness.getFax());
                    return businessRepository.save(business);
                })
                .orElseGet(() -> {
                    updatedBusiness.setId(id);
                    return businessRepository.save(updatedBusiness);
                });
    }

    // İşletme sil
    @DeleteMapping("/{id}")
    public void deleteBusiness(@PathVariable Long id) {
        businessRepository.deleteById(id);
    }
}

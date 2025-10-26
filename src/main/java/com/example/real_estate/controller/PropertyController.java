package com.example.real_estate.controller;

import com.example.real_estate.model.Business;
import com.example.real_estate.model.Property;
import com.example.real_estate.repository.BusinessRepository;
import com.example.real_estate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Property> getPropertyById(@PathVariable Long id) {
        return propertyRepository.findById(id);
    }

    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyRepository.save(property);
    }

    @PostMapping("/with-business/{businessId}")
    public Property createPropertyWithBusiness(@PathVariable Long businessId, @RequestBody Property property) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        property.setBusiness(business);
        return propertyRepository.save(property);
    }

    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable Long id, @RequestBody Property updatedProperty) {
        return propertyRepository.findById(id)
                .map(property -> {
                    property.setTitle(updatedProperty.getTitle());
                    property.setType(updatedProperty.getType());
                    property.setCity(updatedProperty.getCity());
                    property.setDistrict(updatedProperty.getDistrict());
                    property.setPrice(updatedProperty.getPrice());
                    property.setArea(updatedProperty.getArea());
                    property.setBusiness(updatedProperty.getBusiness());
                    return propertyRepository.save(property);
                })
                .orElseGet(() -> {
                    updatedProperty.setId(id);
                    return propertyRepository.save(updatedProperty);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable Long id) {
        propertyRepository.deleteById(id);
    }
}


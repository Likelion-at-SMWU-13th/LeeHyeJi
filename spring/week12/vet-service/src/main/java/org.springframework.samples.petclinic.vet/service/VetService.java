package org.springframework.samples.petclinic.vet.service;

import org.springframework.samples.petclinic.vet.db.VetRepository;
import org.springframework.samples.petclinic.vet.model.Vet;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class VetService {

    private final VetRepository vets;

    public VetService(VetRepository vets) {
        this.vets = vets;
    }

    public Collection<Vet> findAll() {
        return vets.findAll();
    }
}
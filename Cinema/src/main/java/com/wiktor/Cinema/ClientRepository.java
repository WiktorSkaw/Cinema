package com.wiktor.Cinema;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    public Client findByName(String name);
}

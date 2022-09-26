package org.launchcode.animalroster.data;

import org.launchcode.animalroster.models.Handler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandlerRepository extends CrudRepository<Handler, Integer> {
}

package edu.scau.tijian.repository;

import edu.scau.tijian.entity.PhoneCodePair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneCodePairRepository
        extends CrudRepository<PhoneCodePair, String> {}

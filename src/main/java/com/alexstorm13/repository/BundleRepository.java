package com.alexstorm13.repository;

import com.alexstorm13.entity.Bundle;
import com.alexstorm13.entity.Software;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
public interface BundleRepository extends CrudRepository<Bundle, Long> {
}

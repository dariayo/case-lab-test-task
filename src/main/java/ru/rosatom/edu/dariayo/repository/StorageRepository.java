package ru.rosatom.edu.dariayo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosatom.edu.dariayo.entity.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {

}
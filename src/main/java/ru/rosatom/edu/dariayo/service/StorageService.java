package ru.rosatom.edu.dariayo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import ru.rosatom.edu.dariayo.entity.Storage;
import ru.rosatom.edu.dariayo.repository.StorageRepository;

@Service
public class StorageService {
    @Autowired
    private StorageRepository storageRepository;

    public Storage saveFile(Storage storage){
        return storageRepository.save(storage);
    }

    public Optional<Storage> getFile(Long id){
        return storageRepository.findById(id);
    }

    public List<Storage> getAllFiles(Pageable pageable){
        return storageRepository.findAll(pageable).getContent();
    }

}

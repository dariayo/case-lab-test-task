package ru.rosatom.edu.dariayo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rosatom.edu.dariayo.entity.Storage;
import ru.rosatom.edu.dariayo.service.StorageService;

@RestController
@RequestMapping("/api/files")
public class StorageController {
    @Autowired
    private StorageService service;

    /**
     * create file with params: file, title, description, creationDate
     * 
     * @param storage
     * @return
     */
    @PostMapping
    public ResponseEntity<Long> createFile(@RequestBody Storage storage) {
        Storage savedFile = service.saveFile(storage);
        return ResponseEntity.ok(savedFile.getId());

    }

    /**
     * get file by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Storage> getFile(@PathVariable("id") Long id) {
        Optional<Storage> storage = service.getFile(id);
        return storage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * get all files with paging
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Storage>> getAllFiles(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("creationDate").descending());
        return ResponseEntity.ok(service.getAllFiles(pageRequest));
    }
}

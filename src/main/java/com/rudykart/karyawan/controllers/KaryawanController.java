package com.rudykart.karyawan.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rudykart.karyawan.dto.DataResponse;
import com.rudykart.karyawan.dto.KaryawanDto;
import com.rudykart.karyawan.dto.PagingResponse;
import com.rudykart.karyawan.entities.Karyawan;
import com.rudykart.karyawan.services.KaryawanService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/karyawan")
public class KaryawanController {
    private final KaryawanService karyawanService;

    public KaryawanController(KaryawanService karyawanService) {
        this.karyawanService = karyawanService;
    }

    @GetMapping("/{pageSize}/{pageNo}")
    public ResponseEntity<PagingResponse<Karyawan>> findAllKaryawanWithPage(@PathVariable int pageNo,
            @PathVariable int pageSize) {
        return ResponseEntity.ok().body(karyawanService.findAllKaryawanWithPage(pageNo, pageSize));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Karyawan>> saveKaryawan(@RequestBody @Valid KaryawanDto karyawanDto) {
        return ResponseEntity.status(201).body(karyawanService.saveKaryawan(karyawanDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Karyawan>> upadateKaryawan(@PathVariable Long id,
            @RequestBody @Valid KaryawanDto karyawanDto) {
        return ResponseEntity.status(200).body(karyawanService.updateKaryawan(id, karyawanDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteKaryawan(@PathVariable Long id) {
        return new ResponseEntity<>(karyawanService.deleteKaryawan(id), HttpStatus.OK);
    }
}

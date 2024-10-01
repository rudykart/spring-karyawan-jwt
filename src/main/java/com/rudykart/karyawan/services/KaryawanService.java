package com.rudykart.karyawan.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rudykart.karyawan.dto.DataResponse;
import com.rudykart.karyawan.dto.KaryawanDto;
import com.rudykart.karyawan.dto.PagingResponse;
import com.rudykart.karyawan.entities.Karyawan;
import com.rudykart.karyawan.entities.User;
import com.rudykart.karyawan.repositories.KaryawanRepository;
import com.rudykart.karyawan.repositories.UserRepository;

@Service
public class KaryawanService {

    private final KaryawanRepository karyawanRepository;
    private final UserRepository userRepository;

    public KaryawanService(KaryawanRepository karyawanRepository, UserRepository userRepository) {
        this.karyawanRepository = karyawanRepository;
        this.userRepository = userRepository;
    }

    public PagingResponse<Karyawan> findAllKaryawanWithPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Karyawan> karyawan = karyawanRepository.findAllKaryawanWithPage(pageable);
        List<Karyawan> listOfMaterial = karyawan.getContent();

        PagingResponse<Karyawan> response = new PagingResponse<>(200, listOfMaterial,
                karyawan.getNumber(), karyawan.getSize(),
                karyawan.getTotalElements(), karyawan.getTotalPages(), karyawan.isLast());
        return response;
    }

    public DataResponse<Karyawan> saveKaryawan(KaryawanDto karyawanDto) {
        Karyawan karyawan = new Karyawan();
        karyawan.setName(karyawanDto.getName());
        karyawan.setJabatan(karyawanDto.getJabatan());
        karyawan.setAlamat(karyawanDto.getAlamat());

        if (karyawanDto.getUserId() != null) {
            User user = userRepository.findById(karyawanDto.getUserId()).orElseThrow();
            karyawan.setUser(user);
        }
        return new DataResponse<>(201, karyawanRepository.save(karyawan));
    }

    public DataResponse<Karyawan> updateKaryawan(Long id, KaryawanDto karyawanDto) {
        Karyawan karyawan = karyawanRepository.findById(id).orElseThrow();
        karyawan.setName(karyawanDto.getName());
        karyawan.setJabatan(karyawanDto.getJabatan());
        karyawan.setAlamat(karyawanDto.getAlamat());
        if (karyawanDto.getUserId() != null) {
            User user = userRepository.findById(karyawanDto.getUserId()).orElseThrow();
            karyawan.setUser(user);
        }
        return new DataResponse<>(200, karyawanRepository.save(karyawan));
    }

    public DataResponse<Map<String, String>> deleteKaryawan(Long id) {
        Karyawan karyawan = karyawanRepository.findById(id).orElseThrow();

        Map<String, String> data = new HashMap<String, String>();
        data.put("status", "Deleted");
        data.put("name", karyawan.getName());

        karyawanRepository.deleteById(id);
        return new DataResponse<>(200, data);
    }
}

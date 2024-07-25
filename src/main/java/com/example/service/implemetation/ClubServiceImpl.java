package com.example.service.implemetation;

import com.example.exception.ClubNotFoundException;
import com.example.persistence.entity.ClubEntity;
import com.example.persistence.repository.ClubRepository;
import com.example.presentation.dto.ClubDTO;
import com.example.service.interfaces.IClubService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements IClubService {

    private final ModelMapper modelMapper;
    private final ClubRepository clubRepository;

    @Override
    public List<ClubDTO> findAll() {
        return this.clubRepository.findAll().stream().map(entity -> this.modelMapper.map(entity,ClubDTO.class)).toList();
    }

    @Override
    public ClubDTO findById(Long id) {
        ClubEntity clubEntity = this.clubRepository.findById(id).orElseGet(ClubEntity::new);
        return this.modelMapper.map(clubEntity,ClubDTO.class);
    }

    @Override
    public ClubDTO save(ClubDTO clubDTO) {
        ClubEntity clubEntity = this.modelMapper.map(clubDTO,ClubEntity.class);
        ClubEntity clubSaved = this.clubRepository.save(clubEntity);
        return this.modelMapper.map(clubSaved,ClubDTO.class);
    }

    @Override
    public ClubDTO updateClub(Long id, ClubDTO clubDTO) {
        ClubEntity currentClubEntity = this.clubRepository
                .findById(id).orElseThrow(()->new ClubNotFoundException("Usuario con id " + id + " no existe."));
        currentClubEntity.setName(clubDTO.getName());
        currentClubEntity.setCountry(clubDTO.getCountry());

        ClubEntity clubUpdated = this.clubRepository.save(currentClubEntity);
        return this.modelMapper.map(clubUpdated,ClubDTO.class);
    }

    @Override
    public String deleteClub(Long id) {
        ClubEntity currentClubEntity = this.clubRepository
                .findById(id).orElseThrow(()->new ClubNotFoundException("Usuario con id " + id + " no existe."));
        this.clubRepository.delete(currentClubEntity);
        return "Usuario eliminado correctamente.";
    }
}

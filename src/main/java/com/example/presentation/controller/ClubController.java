package com.example.presentation.controller;

import com.example.presentation.dto.ClubDTO;
import com.example.service.interfaces.IClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
@RequiredArgsConstructor
@Tag(name = "Controlador de clubes", description = "Controlador CRUD de clubes")
public class ClubController {

    private final IClubService clubService;

    // Find All
    @GetMapping("/findAll")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ClubDTO>> findAll(){
        return new ResponseEntity<>(this.clubService.findAll(), HttpStatus.OK);
    }

    // Find By Id
    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(
            method = "GET",
            summary = "Obtener club por Id",
            description = "Obtiene los datos de un club brindado un ID como parametro en la petición",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID del club a solicitar datos",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Obtencion de datos del club del ID brindado",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ClubDTO.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<ClubDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.clubService.findById(id), HttpStatus.OK);
    }

    // Save
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ClubDTO> save(@RequestBody ClubDTO clubDTO){
        return new ResponseEntity<>(this.clubService.save(clubDTO), HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ClubDTO> save(@PathVariable Long id, @RequestBody ClubDTO clubDTO){
        return new ResponseEntity<>(this.clubService.updateClub(id, clubDTO), HttpStatus.CREATED);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(this.clubService.deleteClub(id), HttpStatus.OK);
    }

}

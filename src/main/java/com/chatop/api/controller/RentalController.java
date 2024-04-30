package com.chatop.api.controller;

import com.chatop.api.dto.DtoConverter;
import com.chatop.api.dto.RentalDTO;
import com.chatop.api.model.Rental;
import com.chatop.api.model.Rentals;
import com.chatop.api.model.ResponseMessage;
import com.chatop.api.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/rentals")
public class RentalController {

    @Value("${picture.file.path}")
    private String pictureFileDirPath;

    @Value("${angular.file.path}")
    private String pictureFileGetPath;

    @Autowired
    private RentalService rentalService;

    @GetMapping("")
    public ResponseEntity<Rentals> getAll() {
        Rentals rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @PostMapping("")
    public ResponseEntity<?> createRental(
            @RequestParam("name") String name,
            @RequestParam("surface") int surface,
            @RequestParam("price") long price,
            @RequestParam("picture") MultipartFile pictureFile,
            @RequestParam("description") String description

    ) {
        if (pictureFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage(" empty"));
        }
        try {
            String urlToSave = createUrlOfPicture(pictureFile);

            RentalDTO rentalDTO = new RentalDTO(name, surface, price, urlToSave, description);
            rentalService.createRental(rentalDTO);

            return ResponseEntity.ok(new ResponseMessage("Rental created !"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
    }

    /**
     * A file send when posting a rental will be saved in a folder with a unique filename
     * the path to the location of the file will be saved in the database
     * To avoid issue right now, the file is saved in the angular assets/pictures/ folder
     * @param pictureFile
     * @return the path to the file
     * @throws IOException
     */
    private String createUrlOfPicture(MultipartFile pictureFile) throws IOException {
        // unique file name
        String fileName = System.currentTimeMillis() + "_" + pictureFile.getOriginalFilename();
        Path filePath = Paths.get(pictureFileDirPath, fileName);

        // save file in directory
        Files.copy(pictureFile.getInputStream(), filePath);

        return pictureFileGetPath + fileName;
    }

    @GetMapping("{id}")
    public ResponseEntity<Rental> getRental(@PathVariable int id) throws Exception {
        Rental rental = rentalService.getRentalById(id);
        return ResponseEntity.ok(rental);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseMessage> updateRental(@PathVariable("id") final int id, @RequestBody RentalDTO rentalDTO) throws Exception {



        return ResponseEntity.ok(new ResponseMessage("Rental Updated !"));
    }
}

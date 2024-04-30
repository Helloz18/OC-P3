package com.chatop.api.controller;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.dto.UpdateRentalDTO;
import com.chatop.api.model.Rentals;
import com.chatop.api.model.ResponseMessage;
import com.chatop.api.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@Tag(name = "Rental controller", description = "Controller used to manage rentals. A connected user can " +
        "gets the list of all rentals, " +
        "create a rental, " +
        "update a rental link to his account, " +
        "get a rental by its id.")
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
    @Operation(summary = "Get the list of all rentals stored in the database.",
            description = "The list returns RentalDTO objects." )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rentals, that represents a list of RentalDTO."),
            @ApiResponse(responseCode = "401", description = "")
    })
    public ResponseEntity<Rentals> getAll() {
        Rentals rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @PostMapping(value="", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create a rental that will be saved in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental created"),
            @ApiResponse(responseCode = "401", description = "")
    })
    public ResponseEntity<?> createRental(
            @Parameter(description = "name", example="My rental")
            @RequestParam("name") String name,
            @Parameter(description = "surface", example="80")
            @RequestParam("surface") int surface,
            @Parameter(description = "price", example="500")
            @RequestParam("price") long price,
            @Parameter(description = "picture")
            @RequestParam("picture")  MultipartFile pictureFile,
            @Parameter(description = "description", example="Long text describing the rental.")
            @RequestParam("description") String description

    ) {
        if (pictureFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
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
    @Operation(summary = "Get a rental by its id.",
            description = "The object returned is a RentalDTO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "a RentalDTO.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RentalDTO.class))),
            @ApiResponse(responseCode = "401", description = "")
    })

    public ResponseEntity<RentalDTO> getRental(@PathVariable int id) throws Exception {
        RentalDTO rentalDTO = rentalService.getRentalById(id);
        return ResponseEntity.ok(rentalDTO);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update an existing rental with name, surface, price and description",
            description = "The picture cannot be changed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated",
                         content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "401", description = "")
    })

    public ResponseEntity<?> updateRental(
            @Parameter(description = "id of the rental", example="1")
            @PathVariable("id") final int id,
            @Parameter(description = "name", example="My rental")
            @RequestParam("name") String name,
            @Parameter(description = "surface", example="80")
            @RequestParam("surface") int surface,
            @Parameter(description = "price", example="500")
            @RequestParam("price") long price,
            @Parameter(description = "description", example="Long text describing the rental.")
            @RequestParam("description") String description
    ) {
        try {
            UpdateRentalDTO updateRentalDTO = new UpdateRentalDTO(name, surface, price, description);
            rentalService.updateRental(id, updateRentalDTO);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
        return ResponseEntity.ok(new ResponseMessage("Rental Updated !"));
    }
}

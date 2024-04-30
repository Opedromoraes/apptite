package com.dev.apptite.api.controller.restaurante;

import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.request.RestauranteUpdateRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.exceptions.dto.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

//@Tag(name = "Restaurante")
//@RestController()
//@RequestMapping(value = "/restaurantes")
//@Validated
@RestController
@Tag(name = "Restaurantes")
@RequestMapping(value = "/restaurantes")
@Validated
public interface IRestauranteController {

    @Operation(
            summary = "Criar Restaurante",
            description = "Endpoint responsável por criar um novo restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Restaurante criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = RestauranteResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @PostMapping
    @ResponseStatus(CREATED)
    ResponseEntity<RestauranteResponse> create(@Valid @RequestBody RestauranteRequest restauranteRequest);

    @Operation(
            summary = "Buscar Restaurante",
            description = "Endpoint responsável por buscar um restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Restaurante encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = RestauranteResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping
    @ResponseStatus(OK)
    ResponseEntity<List<RestauranteResponse>> findAll();



    @Operation(
            summary = "Atualizar Restaurante",
            description = "Endpoint responsável por atualizar um restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Restaurante atualizado com sucesso.",
                            content = @Content(schema = @Schema(implementation = RestauranteResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @PutMapping("{id}")
    @ResponseStatus(OK)
    ResponseEntity<RestauranteResponse> update(@Valid @RequestBody RestauranteUpdateRequest request, @PathVariable Long id);

    @Operation(
            summary = "Buscar restaurante por id",
            description = "Endpoint responsável por buscar um restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Restaurante encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = RestauranteResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("{id}")
    @ResponseStatus(OK)
    ResponseEntity<RestauranteResponse> findById(@PathVariable Long id);

    @Operation(
            summary = "Deletar Restaurante",
            description = "Endpoint responsável por deletar um cliente",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Restaurante deletado com sucesso."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable Long id);

}

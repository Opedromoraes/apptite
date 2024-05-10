package com.dev.apptite.api.controller.categoria;

import com.dev.apptite.api.controller.categoria.request.CategoriaRequest;
import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
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

@RestController
@Tag(name = "Categorias")
@RequestMapping(value = "/categorias")
@Validated
public interface ICategoriaController {

    @Operation(
            summary = "Criar Categoria",
            description = "Endpoint responsável por criar um novo categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Categoria criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = CategoriaResponse.class))),
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
    ResponseEntity<CategoriaResponse> create(@Valid @RequestBody CategoriaRequest categoriaRequest);

    @Operation(
            summary = "Buscar Categoria",
            description = "Endpoint responsável por buscar um categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = CategoriaResponse.class))),
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
    ResponseEntity<List<CategoriaResponse>> findAll();

    @Operation(
            summary = "Buscar categoria por id",
            description = "Endpoint responsável por buscar uma categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = CategoriaResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Categoria não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("{id}")
    @ResponseStatus(OK)
    ResponseEntity<CategoriaResponse> findById(@PathVariable Long id);

    @Operation(
            summary = "Buscar categoria por id do Restaurante",
            description = "Endpoint responsável por buscar categorias de um Restaurante",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = CategoriaResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Categoria não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("restaurante/{idRestaurante}")
    @ResponseStatus(OK)
    ResponseEntity<List<CategoriaResponse>> findByIdRestaurante(@PathVariable Long idRestaurante);

    @Operation(
            summary = "Deletar Categoria",
            description = "Endpoint responsável por deletar um cliente",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Categoria deletado com sucesso."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Categoria não encontrado.",
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

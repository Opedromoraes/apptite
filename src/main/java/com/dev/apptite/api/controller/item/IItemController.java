package com.dev.apptite.api.controller.item;

import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
import com.dev.apptite.api.controller.item.request.ItemRequest;
import com.dev.apptite.api.controller.item.response.ItemResponse;
import com.dev.apptite.domain.exceptions.dto.ErrorDTO;
import com.dev.apptite.domain.utils.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@Tag(name = "Itens")
@RequestMapping(value = "/itens")
@Validated
public interface IItemController {

    @Operation(
            summary = "Criar Item",
            description = "Endpoint responsável por criar um novo item",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Item criado com sucesso.",
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
    ResponseEntity<ItemResponse> create(@Valid @RequestBody ItemRequest itemRequest);

    @Operation(
            summary = "Buscar item por id",
            description = "Endpoint responsável por buscar um item",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = CategoriaResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("{id}")
    @ResponseStatus(OK)
    ResponseEntity<ItemResponse> findById(@PathVariable Long id);

    @Operation(
            summary = "Deletar Item",
            description = "Endpoint responsável por deletar um item",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Item deletado com sucesso."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable Long id);

    @Operation(
            summary = "Consultar item paginado",
            description = "Endpoint responsável por buscar um item paginado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = ItemResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping
    @ResponseStatus(OK)
    ResponseEntity<PageResponse<ItemResponse>> findAllPaginated(
            @ParameterObject @RequestParam(defaultValue = "0") @Min(0) int page,
            @ParameterObject @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Long idCategoria);
}

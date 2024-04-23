package com.dev.apptite.api.controller.restaurante;

import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.exceptions.ErroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
                            content = @Content(schema = @Schema(implementation = ErroDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErroDTO.class)))
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
                            content = @Content(schema = @Schema(implementation = ErroDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErroDTO.class)))
            })
    @GetMapping
    @ResponseStatus(OK)
    RestauranteResponse findAll();

//    @Operation(
//            summary = "Criar Cliente",
//            description = "Endpoint responsável por criar um novo cliente",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "201",
//                            description = "Cliente criado com sucesso.",
//                            content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
//                    @ApiResponse(
//                            responseCode = "422",
//                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
//                            content = @Content(schema = @Schema(implementation = ErrorObject.class))),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "Ocorreu um erro inesperado.",
//                            content = @Content(schema = @Schema(implementation = ErrorObject.class)))
//            })
//    @PostMapping
//    @ResponseStatus(CREATED)
//    ClienteResponse create(@Valid @RequestBody ClienteRequest clienteRequest);
//    @Operation(
//            summary = "Buscar Cliente",
//            description = "Endpoint responsável por buscar um cliente",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Cliente encontrado com sucesso.",
//                            content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
//                    @ApiResponse(
//                            responseCode = "404",
//                            description = "Cliente não encontrado.",
//                            content = @Content(schema = @Schema(implementation = ErrorObject.class))),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "Ocorreu um erro inesperado.",
//                            content = @Content(schema = @Schema(implementation = ErrorObject.class)))
//            })
//    @GetMapping
//    @ResponseStatus(OK)
//    ClienteResponse findAll();
//    @Operation(
//            summary = "Atualizar Cliente",
//            description = "Endpoint responsável por atualizar um cliente",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Cliente atualizado com sucesso.",
//                            content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
//                    @ApiResponse(
//                            responseCode = "404",
//                            description = "Cliente não encontrado.",
//                            content = @Content(schema = @Schema(implementation = ErrorObject.class))),
//                    @ApiResponse(
//                            responseCode = "422",
//                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
//                            content = @Content(schema = @Schema(implementation = ErrorObject.class))),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "Ocorreu um erro inesperado.",
//                            content = @Content(schema = @Schema(implementation = ErrorObject.class)))
//            })
//    @PutMapping(value = "/{id}")
//    @ResponseStatus(OK)
//    ClienteRequest update(@Valid @RequestBody ClienteRequest clienteRequest,@PathVariable Long id);
//    @Operation(
//            summary = "Deletar Cliente",
//            description = "Endpoint responsável por deletar um cliente",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "204",
//                            description = "Cliente deletado com sucesso."
//                    ),
//                    @ApiResponse(
//                            responseCode = "404",
//                            description = "Cliente não encontrado.",
//                            content = @Content(schema = @Schema(implementation = ErrorObject.class))),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "Ocorreu um erro inesperado.",
//                            content = @Content(schema = @Schema(implementation = ErrorObject.class)))
//            })
//    @DeleteMapping(value = "/{id}")
//    @ResponseStatus(NO_CONTENT)
//    ClienteRequest delete(@PathVariable Long id);
}

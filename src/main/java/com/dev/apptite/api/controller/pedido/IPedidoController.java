//package com.dev.apptite.api.controller.pedido;
//
//import com.dev.apptite.api.controller.cardapio.request.CardapioRequest;
//import com.dev.apptite.api.controller.cardapio.response.CardapioResponse;
//import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
//import com.dev.apptite.api.controller.pedido.request.PedidoRequest;
//import com.dev.apptite.api.controller.pedido.response.PedidoResponse;
//import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
//import com.dev.apptite.domain.exceptions.dto.ErrorDTO;
//import com.dev.apptite.domain.utils.PageResponse;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Min;
//import org.springdoc.api.annotations.ParameterObject;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import static org.springframework.http.HttpStatus.*;
//
//@RestController
//@Tag(name = "Pedidos")
//@RequestMapping(value = "/pedi")
//@Validated
//public interface IPedidoController {
//
//        @Operation(
//                summary = "Criar Pedidos",
//                description = "Endpoint responsável por criar um novo pedido",
//                responses = {
//                        @ApiResponse(
//                                responseCode = "201",
//                                description = "Pedido criado com sucesso.",
//                                content = @Content(schema = @Schema(implementation = PedidoResponse.class))),
//                        @ApiResponse(
//                                responseCode = "422",
//                                description = "Requisição possui pelo menos um valor faltante ou inválido.",
//                                content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
//                        @ApiResponse(
//                                responseCode = "500",
//                                description = "Ocorreu um erro inesperado.",
//                                content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
//                })
//        @PostMapping
//        @ResponseStatus(CREATED)
//        ResponseEntity<PedidoResponse> create(@Valid @RequestBody PedidoRequest pedidoRequest);
//
//        @Operation(
//                summary = "Buscar pedido por id",
//                description = "Endpoint responsável por buscar um pedido",
//                responses = {
//                        @ApiResponse(
//                                responseCode = "200",
//                                description = "Pedido encontrado com sucesso.",
//                                content = @Content(schema = @Schema(implementation = PedidoResponse.class))),
//                        @ApiResponse(
//                                responseCode = "404",
//                                description = "Pedido não encontrado.",
//                                content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
//                        @ApiResponse(
//                                responseCode = "500",
//                                description = "Ocorreu um erro inesperado.",
//                                content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
//                })
//        @GetMapping("{id}")
//        @ResponseStatus(OK)
//        ResponseEntity<PedidoResponse> findById(@PathVariable Long id);
//
//        @Operation(
//                summary = "Deletar Pedido",
//                description = "Endpoint responsável por deletar um pedido",
//                responses = {
//                        @ApiResponse(
//                                responseCode = "204",
//                                description = "Pedido deletado com sucesso."
//                        ),
//                        @ApiResponse(
//                                responseCode = "404",
//                                description = "Pedido não encontrado.",
//                                content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
//                        @ApiResponse(
//                                responseCode = "500",
//                                description = "Ocorreu um erro inesperado.",
//                                content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
//                })
//        @DeleteMapping(value = "/{id}")
//        @ResponseStatus(NO_CONTENT)
//        ResponseEntity<Void> delete(@PathVariable Long id);
//
//        @Operation(
//                summary = "Consultar pedido paginado",
//                description = "Endpoint responsável por buscar um pedido paginado",
//                responses = {
//                        @ApiResponse(
//                                responseCode = "200",
//                                description = "Pedido encontrado com sucesso.",
//                                content = @Content(schema = @Schema(implementation = PedidoResponse.class))),
//                        @ApiResponse(
//                                responseCode = "404",
//                                description = "Cardápio não encontrado.",
//                                content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
//                        @ApiResponse(
//                                responseCode = "500",
//                                description = "Ocorreu um erro inesperado.",
//                                content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
//                })
//        @GetMapping
//        @ResponseStatus(OK)
//        ResponseEntity<PageResponse<PedidoResponse>> findAllPaginated(
//                @ParameterObject @RequestParam(defaultValue = "0") @Min(0) int page,
//                @ParameterObject @RequestParam(defaultValue = "10") @Min(1) int size,
//                @RequestParam(required = false) String nome,
//                @RequestParam(required = false) Long idRestaurante,
//                @RequestParam(required = false) String nomeRestaurante);
//}

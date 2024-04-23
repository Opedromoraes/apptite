package com.dev.apptite.service;

import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.RestauranteEntity;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestauranteService {

    private final RestauranteMapper mapper;
    private final RestauranteRepository repository;

    public RestauranteDTO salvar(RestauranteDTO restauranteDTO) {

        RestauranteEntity restauranteEntity = mapper.dtoToEntity(restauranteDTO);
        RestauranteEntity restaurante = repository.save(restauranteEntity);
        RestauranteDTO restauranteDtoSalvo = mapper.entityToDTO(restaurante);

        return restauranteDtoSalvo;
    }


//    public ClienteDTO salvar(ClienteDTO clienteDTO) {
//        Cliente cliente = new Cliente();
//        cliente.setEmail(clienteDTO.getEmail());
//        cliente.setNome(clienteDTO.getNome());
//
//        Cliente clienteSalvo = repository.save(cliente);
//
//        ClienteDTO clienteDTOSalvo = new ClienteDTO();
//        clienteDTOSalvo.setId(clienteSalvo.getId());
//        clienteDTOSalvo.setNome(clienteSalvo.getNome());
//        clienteDTOSalvo.setEmail(clienteSalvo.getEmail());
//        clienteDTOSalvo.setUpdatedAt(clienteSalvo.getUpdatedAt());
//        clienteDTOSalvo.setUpdatedBy(clienteSalvo.getUpdatedBy());
//        clienteDTOSalvo.setCreatedAt(clienteSalvo.getCreatedAt());
//        clienteDTOSalvo.setCreatedBy(clienteSalvo.getCreatedBy());
//
//        return clienteDTOSalvo;
//    }
}

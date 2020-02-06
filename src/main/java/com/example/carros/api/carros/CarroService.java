package com.example.carros.api.carros;

import com.example.carros.api.infra.exception.ObjectNotFOundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> getCarros(Pageable pageable) {
        return carroRepository.findAll(pageable).stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
    }

    public CarroDTO getCarroById(Long id) {
        return carroRepository.findById(id).map(CarroDTO::create).orElseThrow(() -> new ObjectNotFOundException("Carro não encontrado"));
    }


    public List<CarroDTO> getCarroByTipo(String tipo, Pageable pageable) {
        return carroRepository.findByTipo(tipo, pageable).stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
    }

    public CarroDTO save(Carro carro) {
        Assert.isNull(carro.getId(), "Nao foi possivel inserir registro");
        return CarroDTO.create(carroRepository.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");
        if (Objects.nonNull(getCarroById(id))) {
            Carro db = new Carro();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            carroRepository.save(db);

            return CarroDTO.create(db);
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        carroRepository.deleteById(id);
    }
}

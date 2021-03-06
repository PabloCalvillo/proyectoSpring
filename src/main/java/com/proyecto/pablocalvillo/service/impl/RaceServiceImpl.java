package com.proyecto.pablocalvillo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyecto.pablocalvillo.converter.RaceConverter;
import com.proyecto.pablocalvillo.entity.Race;
import com.proyecto.pablocalvillo.model.RaceModel;
import com.proyecto.pablocalvillo.repository.QueryDSLRace;
import com.proyecto.pablocalvillo.repository.RaceJpaRepository;
import com.proyecto.pablocalvillo.service.RaceService;

@Service
public class RaceServiceImpl implements RaceService {
	
	@Autowired
	@Qualifier("raceJpaRepository")
	private RaceJpaRepository raceJpaRepository;
	
	@Autowired
	@Qualifier("queryDSLRace")
	private QueryDSLRace queryDSLRace;
	
	@Autowired
	@Qualifier("raceConverter")
	private RaceConverter raceConverter;

	@Override
	public List<RaceModel> listAllRaces() {
		List<RaceModel> racesModel = raceJpaRepository.findAll().stream()
				.map(race -> raceConverter.entity2model(race)).collect(Collectors.toList());
		return racesModel;
	}

	@Override
	public Race addRace(RaceModel raceModel) {
		return raceJpaRepository.save(raceConverter.model2entity(raceModel));
	}

	@Override
	public int removeRace(int id) {
		raceJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Race updateRace(RaceModel raceModel) {
		return raceJpaRepository.save(raceConverter.model2entity(raceModel));
	}

	@Override
	public Race findById(int id) {
		return raceJpaRepository.findById(id);
	}

	@Override
	public Race findByNombre(String name) {
		return raceJpaRepository.findByNombre(name);
	}

	@Override
	public Race findByNombreAndId(String nombre, int id) {
		return queryDSLRace.findByNombreAndId(nombre, id);
	}	
}

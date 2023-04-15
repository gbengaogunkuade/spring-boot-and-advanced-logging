package com.ogunkuade.engineers.service;


import com.ogunkuade.engineers.entity.Engineer;
import com.ogunkuade.engineers.exception.EngineerAlreadyExistsException;
import com.ogunkuade.engineers.exception.EngineerDoesNotExistsException;
import com.ogunkuade.engineers.record.EngineerRequestRecord;
import com.ogunkuade.engineers.record.EngineerResponseRecord;
import com.ogunkuade.engineers.repository.EngineerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EngineerService {

    Logger logger = LoggerFactory.getLogger(EngineerService.class);

    private final EngineerRepository engineerRepository;

    public EngineerService(EngineerRepository engineerRepository) {
        this.engineerRepository = engineerRepository;
    }


    public EngineerResponseRecord createEngineer(EngineerRequestRecord engineerRequestRecord){
        if(engineerRepository.existsByName(engineerRequestRecord.name())){
            logger.warn("ENGINEER WITH SAME NAME ALREAY EXISTS");
            throw new EngineerAlreadyExistsException(String.format("Engineer with the name %s already exists", engineerRequestRecord.name()));
        }
        Engineer engineer = new Engineer();
        engineer.setName(engineerRequestRecord.name());
        engineer.setEmail(engineerRequestRecord.email());
        engineer.setCompany(engineerRequestRecord.company());
        Engineer savedEngineer = engineerRepository.save(engineer);
        EngineerResponseRecord engineerResponseRecord = new EngineerResponseRecord(
                savedEngineer.getId(),
                savedEngineer.getName(),
                savedEngineer.getEmail(),
                savedEngineer.getCompany()
        );
        return engineerResponseRecord;
    }



    public EngineerResponseRecord updateEngineer(EngineerRequestRecord engineerRequestRecord, Long id){
        Engineer existingEngineer = engineerRepository.findById(id).orElseThrow(() -> new EngineerDoesNotExistsException(String.format("Engineer with the id %d does not exists", id)));
        existingEngineer.setName(engineerRequestRecord.name());
        existingEngineer.setEmail(engineerRequestRecord.email());
        existingEngineer.setCompany(engineerRequestRecord.company());
        Engineer savedEngineer = engineerRepository.save(existingEngineer);
        EngineerResponseRecord engineerResponseRecord = new EngineerResponseRecord(
                savedEngineer.getId(),
                savedEngineer.getName(),
                savedEngineer.getEmail(),
                savedEngineer.getCompany()
        );
        logger.info(String.format("ENGINEER WITH AN ID %d SUCCESSFULLY UPDATED", id));
        return engineerResponseRecord;
    }



    public List<EngineerResponseRecord> getAllEngineers(){
        List<Engineer> engineerList = engineerRepository.findAll();
        List<EngineerResponseRecord> engineerResponseRecordList = new ArrayList<>();
        for(Engineer engineer : engineerList){
            EngineerResponseRecord engineerResponseRecord = new EngineerResponseRecord(
                    engineer.getId(),
                    engineer.getName(),
                    engineer.getEmail(),
                    engineer.getCompany()
            );
            engineerResponseRecordList.add(engineerResponseRecord);
        }
        logger.info("ALL ENGINEERS SUCCESSFULLY FETCHED FROM DATABASE");
        return engineerResponseRecordList;
    }



    public EngineerResponseRecord getEngineer(Long id){
        Engineer existingEngineer = engineerRepository.findById(id).orElseThrow(() -> new EngineerDoesNotExistsException(String.format("Engineer with the id %d does not exists", id)));
        EngineerResponseRecord engineerResponseRecord = new EngineerResponseRecord(
                existingEngineer.getId(),
                existingEngineer.getName(),
                existingEngineer.getEmail(),
                existingEngineer.getCompany()
        );
        logger.info(String.format("ENGINEER WITH AN ID %d SUCCESSFULLY FETCHED FROM DATABASE", id));
        return engineerResponseRecord;
    }



    public String deleteEngineer(Long id){
        Engineer existingEngineer = engineerRepository.findById(id).orElseThrow(() -> new EngineerDoesNotExistsException(String.format("Engineer with the id %d does not exists", id)));
        engineerRepository.delete(existingEngineer);
        logger.info(String.format("ENGINEER WITH AN ID %d SUCCESSFULLY DELETED FROM DATABASE", id));
        return String.format("Engineer with the id %d successfully deleted from database", id);
    }



}

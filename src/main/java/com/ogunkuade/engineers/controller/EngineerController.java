package com.ogunkuade.engineers.controller;


import com.ogunkuade.engineers.record.EngineerRequestRecord;
import com.ogunkuade.engineers.record.EngineerResponseRecord;
import com.ogunkuade.engineers.service.EngineerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/engineers")
public class EngineerController {


    private final EngineerService engineerService;

    public EngineerController(EngineerService engineerService) {
        this.engineerService = engineerService;
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public EngineerResponseRecord creatingEngineer(@RequestBody EngineerRequestRecord engineerRequestRecord){
        return engineerService.createEngineer(engineerRequestRecord);
    }


    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public EngineerResponseRecord updatingEngineer(@RequestBody EngineerRequestRecord engineerRequestRecord, @PathVariable Long id){
        return engineerService.updateEngineer(engineerRequestRecord, id);
    }


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EngineerResponseRecord> getttingAllEngineers(){
        return engineerService.getAllEngineers();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EngineerResponseRecord getttingEngineer(@PathVariable Long id){
        return engineerService.getEngineer(id);
    }


    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deletingEngineer(@PathVariable Long id){
        return engineerService.deleteEngineer(id);
    }





}

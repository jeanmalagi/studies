package controller;

import model.Agendamento;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class BarberShopAgenda {

    private Map<Integer, Agendamento> agendamentos = new HashMap<>();
    private int nextId = 1;

    // Agendar cliente
    @PostMapping
    public ResponseEntity<String> agendarCliente(@RequestBody Agendamento agendamento) {
        agendamento.setId(nextId++);
        agendamentos.put(agendamento.getId(), agendamento);
        return new ResponseEntity<>("Agendamento realizado com sucesso!", HttpStatus.CREATED);
    }

    // Confirmar agendamento
    @PutMapping("/{id}")
    public ResponseEntity<String> confirmarAgendamento(@PathVariable("id") int id) {
        Agendamento agendamento = agendamentos.get(id);
        if (agendamento != null) {
            agendamento.setConfirmacao(true);
            return new ResponseEntity<>("Agendamento confirmado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Agendamento não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    // Consultar agendamento
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> consultarAgendamento(@PathVariable("id") int id) {
        Agendamento agendamento = agendamentos.get(id);
        if (agendamento != null) {
            return new ResponseEntity<>(agendamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BarberShopAgenda.class, args);
    }
}

package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.service.CardValidationService;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardRepository cardRepository;



    @Autowired
    public CardController(CardRepository cardRepository){
        this.cardRepository=cardRepository;

    }

    @GetMapping
    public List<Card> getAll(){
        return cardRepository.findAll();
    }
   @GetMapping("/byColor/{color}")
    public List<Card> getByColor(@PathVariable("color") String color){

        return cardRepository.findByColor(color);
   }
   @PostMapping
   public Card createCard(@RequestBody Card card){

        return cardRepository.save(card);
   }
   @PutMapping("/")
    public Card updateCard(@RequestBody Card card){
        return cardRepository.update(card);
   }
   @DeleteMapping("/{id}")
    public Card deleteCard(@PathVariable("id") long id){
        CardValidation.idExist(id);
        return cardRepository.remove(id);
   }
   @GetMapping("/byValue/{value}")
    public List<Card> getByValue(@PathVariable("value") Integer value){
        return cardRepository.findByValue(value);
   }
    @GetMapping("/byType/{type}")
    public List<Card> getByType(@PathVariable("type") String type){
        return cardRepository.findByType(type);
    }

}

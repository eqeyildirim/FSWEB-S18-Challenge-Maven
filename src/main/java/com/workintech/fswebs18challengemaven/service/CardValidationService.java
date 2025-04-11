package com.workintech.fswebs18challengemaven.service;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CardValidationService {
    public void validateCard(Card card){
        if(card==null){
            throw new CardException("Card cannot be null! ", HttpStatus.BAD_REQUEST);
        }
        validateJokerCard(card);
        validateValueAndType(card);
    }

    private void validateValueAndType(Card card) {
       if(card.getValue() != null && card.getType()!=null){
           throw new CardException("Card cannot be have both value and type",HttpStatus.BAD_REQUEST);
       }
       if(card.getValue()==null&&card.getType()==null){
           throw new CardException("Card must have either value or type",HttpStatus.BAD_REQUEST);
       }
    }

    private void validateJokerCard(Card card) {
        if(card.getType() == Type.JOKER && (card.getValue()!=null||card.getColor()!=null) ){
            throw new CardException("Joker cards cannot have value or color",HttpStatus.BAD_REQUEST);
        }

    }

}

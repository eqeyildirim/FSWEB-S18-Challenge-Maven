package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CardValidation {
    public static void colorExist(String color) {
        if(color.isEmpty()){
            throw new CardException("Card is null or empty!"+color, HttpStatus.NOT_FOUND);
        }
    }


    public static void idExist(long id) {
        if(id<=0){
            throw new CardException("id cannot less than zero"+id,HttpStatus.BAD_REQUEST);
        }
    }
}

package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;
@Slf4j
@Repository
public class CardRepositoryImpl implements CardRepository{

    private final EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Card save(Card card) {
        log.info("save started");
        entityManager.persist(card);
        log.info("save success ended");
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> cardColorQuery=entityManager.createQuery("SELECT c FROM Card c WHERE c.color ILIKE CONCAT('%',:color,'%'",Card.class);
        cardColorQuery.setParameter("color",color);

        List<Card> cards = cardColorQuery.getResultList();

        if (cards.isEmpty()) {
            throw new CardException("No cards found with color: " + color, HttpStatus.NOT_FOUND);
        }

        return cards;
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query =entityManager.createQuery("SELECT c FROM Card c",Card.class);
        return query.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> valueQuery = entityManager.createQuery("SELECT c FROM Card c WHERE c.value=:value", Card.class);
        valueQuery.setParameter("value",value);
        return valueQuery.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> typedQuery=entityManager.createQuery("SELECT c FROM Card c WHERE c.type ILIKE ('%',:type,'%'",Card.class);
        typedQuery.setParameter("type",type);
        return typedQuery.getResultList();
    }
    @Transactional
    @Override
    public Card update(Card card) {
        return entityManager.merge(card);
    }
    @Transactional
    @Override
    public Card remove(long id) {
        Card willBeDeletedCard=entityManager.find(Card.class,id);
        entityManager.remove(willBeDeletedCard);
        return willBeDeletedCard;
    }
}

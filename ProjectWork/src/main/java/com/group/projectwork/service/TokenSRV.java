package com.group.projectwork.service;

import com.group.projectwork.entity.Token;
import com.group.projectwork.exception.TokenExpiredException;
import com.group.projectwork.repository.TokenDB;

import java.time.Duration;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenSRV {

    @Autowired
    TokenDB db;

    public Token updToken(Token token) {
    	token.setIns(new Date(System.currentTimeMillis()));
    	return this.db.save(token);
    }
    
    public Token getByValue(String tokenValue) throws TokenExpiredException {
    	var opt = this.db.findByValore(tokenValue);
    	if(opt.isEmpty())
    		throw new EntityNotFoundException();
    	
    	var token = opt.get();
    	
    	Date date = new Date(System.currentTimeMillis());
    	var duration = Duration.between(token.getIns().toInstant(),date.toInstant());
    	if(duration.abs().getSeconds()/60 > 15)
    		throw new TokenExpiredException();
    	
    	return this.updToken(token);
    }
}

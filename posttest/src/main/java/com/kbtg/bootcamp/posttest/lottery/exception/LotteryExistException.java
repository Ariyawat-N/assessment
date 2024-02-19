package com.kbtg.bootcamp.posttest.lottery.exception;

public class LotteryExistException extends RuntimeException{
    public LotteryExistException(String message){
        super(message);
    }
}

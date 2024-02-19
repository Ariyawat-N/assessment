package com.kbtg.bootcamp.posttest.lottery.exception;

public class LotteryDuplicateException extends RuntimeException{
    public LotteryDuplicateException(String message){
        super(message);
    }
}

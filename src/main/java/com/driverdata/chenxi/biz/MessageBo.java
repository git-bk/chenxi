package com.driverdata.chenxi.biz;

import java.util.List;

import com.driverdata.chenxi.dto.MessageDto;
import com.driverdata.chenxi.exception.ChanceValidateException;

public interface MessageBo {

    public String send(MessageDto message) throws Exception;

    MessageDto addMessage(MessageDto mes) throws ChanceValidateException;

    List<MessageDto> findMessageList(MessageDto messageDto) throws ChanceValidateException;
}

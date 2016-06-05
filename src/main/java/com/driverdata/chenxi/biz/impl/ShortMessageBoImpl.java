package com.driverdata.chenxi.biz.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.driverdata.chenxi.biz.MessageBo;
import com.driverdata.chenxi.dal.mapper.MessageDoMapperExt;
import com.driverdata.chenxi.dal.model.MessageDo;
import com.driverdata.chenxi.dal.model.MessageDoExample;
import com.driverdata.chenxi.dal.model.MessageDoExample.Criteria;
import com.driverdata.chenxi.dal.plugin.Page;
import com.driverdata.chenxi.dto.MessageDto;
import com.driverdata.chenxi.enums.YesOrNo;
import com.driverdata.chenxi.exception.ChanceValidateException;
import com.driverdata.chenxi.util.StringUtil;

public class ShortMessageBoImpl implements MessageBo {

    @Autowired
    private MessageDoMapperExt messageDoMapperExt;
    @Value("#{configProperties['sms_sign']}")
    private String             sms_sign;
    @Value("#{configProperties['sms_url']}")
    private String             sms_url;
    @Value("#{configProperties['sms_name']}")
    private String             sms_name;
    @Value("#{configProperties['sms_pwd']}")
    private String             sms_pwd;

    public String getRandomCode() {
        int i = (int) (Math.random() * 1000000);
        return new Integer(i).toString();
    }

    @Override
    public String send(MessageDto message) throws Exception {

        StringBuffer sb = new StringBuffer(sms_url);
        sb.append("?name=");
        sb.append(sms_name);
        sb.append("&pwd=");
        sb.append(sms_pwd);
        sb.append("&mobile=");
        sb.append(message.getReceiverAddress());
        sb.append("&content=");
        sb.append(URLEncoder.encode(message.getContent(), "UTF-8"));
        sb.append("&stime=");
        sb.append("&sign=");
        sb.append(URLEncoder.encode(sms_sign, "UTF-8"));
        sb.append("&type=pt&extno=");
        URL url = new URL(sb.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        InputStream is = url.openStream();

        String returnStr = convertStreamToString(is);
        return returnStr;
    }

    private String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb1.toString();
    }

    @Override
    public MessageDto addMessage(MessageDto mes) throws ChanceValidateException {
        if (mes == null) {
            throw new ChanceValidateException("addMessage-001", "通知参数不能为空");
        }
        if (StringUtil.isBlank(mes.getReceiver())) {
            throw new ChanceValidateException("addMessage-002", "接收人不能为空");
        }
        if (StringUtil.isBlank(mes.getBusinessType())) {
            throw new ChanceValidateException("addMessage-003", "通知类型不能为空");
        }
        if (StringUtil.isBlank(mes.getMessageType())) {
            throw new ChanceValidateException("addMessage-004", "通知方式不能为空");
        }
        if (mes.getProjectId() == null) {
            throw new ChanceValidateException("addMessage-005", "项目id不能为空");
        }
        if (StringUtil.isBlank(mes.getContent())) {
            throw new ChanceValidateException("addMessage-006", "通知内容不能为空");
        }
        if (StringUtil.isBlank(mes.getReceiverAddress())) {
            throw new ChanceValidateException("addMessage-007", "接收地址不能为空");
        }
        MessageDo messageDo = new MessageDo();
        BeanUtils.copyProperties(mes, messageDo);
        messageDoMapperExt.insertSelective(messageDo);
        mes.setId(messageDo.getId());
        return mes;
    }

    /*
     * (non-Javadoc)
     * @see com.driverdata.chenxi.biz.MessageBo#findMessageList(com.driverdata.chenxi.dto.MessageDto)
     */
    @Override
    public List<MessageDto> findMessageList(MessageDto messageDto) throws ChanceValidateException {
        if (messageDto == null) {
            throw new ChanceValidateException("findMessageList-001", "查询通知参数不能为空");
        }
        List<MessageDto> messageListDto = new ArrayList<MessageDto>();
        MessageDoExample example = new MessageDoExample();
        example.setOrderByClause("gmt_create desc");
        example.setPage(new Page(0, 100000));
        Criteria criteria = example.createCriteria();
        if (messageDto.getProjectId() != null) {
            criteria.andProjectIdEqualTo(messageDto.getProjectId());
        }
        if (StringUtil.isNotBlank(messageDto.getBusinessType())) {
            criteria.andBusinessTypeEqualTo(messageDto.getBusinessType());
        }
        if (StringUtil.isNotBlank(messageDto.getMessageType())) {
            criteria.andMessageTypeEqualTo(messageDto.getMessageType());
        }
        if (StringUtil.isNotBlank(messageDto.getTitle())) {
            criteria.andTitleEqualTo(messageDto.getTitle());
        }
        if (StringUtil.isNotBlank(messageDto.getReceiverAddress())) {
            criteria.andReceiverAddressEqualTo(messageDto.getReceiverAddress());
        }
        criteria.andIsDeletedEqualTo(YesOrNo.NO.getValue());
        List<MessageDo> messageList = messageDoMapperExt.selectByExample(example);
        if (messageList == null || messageList.isEmpty()) {
            return messageListDto;
        }
        for (MessageDo messageDo : messageList) {
            MessageDto mesDto = new MessageDto();
            BeanUtils.copyProperties(messageDo, mesDto);
            messageListDto.add(mesDto);
        }
        return messageListDto;
    }
}

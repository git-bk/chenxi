package com.bk.chenxi.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bk.chenxi.biz.ImageBo;
import com.bk.chenxi.biz.UserBo;
import com.bk.chenxi.dal.mapper.RoleDoMapperExt;
import com.bk.chenxi.dal.mapper.UserDoMapperExt;
import com.bk.chenxi.dal.mapper.UserRoleDoMapperExt;
import com.bk.chenxi.dal.plugin.Page;
import com.bk.chenxi.dto.ImageDto;
import com.bk.chenxi.dto.MessageDto;
import com.bk.chenxi.dto.UserDto;
import com.bk.chenxi.enums.FileType;
import com.bk.chenxi.enums.RoleType;
import com.bk.chenxi.exception.ChanceValidateException;
import com.bk.chenxi.model.User;
import com.bk.chenxi.util.ShortMessageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson.JSON;
import com.bk.chenxi.dal.model.UserDo;
import com.bk.chenxi.dal.model.UserDoExample;
import com.bk.chenxi.dal.model.UserRoleDo;

public class UserBoImpl implements UserBo {

    private final Logger                      logger    = LoggerFactory.getLogger(getClass());

    private static final Integer              VALIDTIME = 60 * 5 * 1000;

    @Autowired
    private UserDoMapperExt userDoMapperExt;

    @Autowired
    private RoleDoMapperExt roleDoMapperExt;

    @Autowired
    private UserRoleDoMapperExt userRoleDoMapperExt;

    @Autowired
    private ImageBo imageBo;

    @Override
    public void sendMobileValidateCode(String mobile) throws ChanceValidateException {
        String code = ShortMessageService.getRandomCode();
        // 发送短信
        try {
            MessageDto message = new MessageDto();
            message.setReceiverAddress(mobile);
            message.setContent(" 验证码： " + code);
        } catch (Exception e) {
            throw new ChanceValidateException("sendMobileValidateCode-001", "发送短信失败");
        }
    }

    @Override
    public UserDo findUserbyAccount(String account) {
        UserDoExample example = new UserDoExample();
        example.createCriteria().andAccountEqualTo(account).andIsDeletedEqualTo("n");
        List<UserDo> rslist = userDoMapperExt.selectByExample(example);
        return rslist != null && rslist.size() > 0 ? rslist.get(0) : null;
    }

    @Override
    public void registerUser(String account, String password, String validateCode, String type)
                                                                                               throws ChanceValidateException {
        if (!isCodeMatches(account, validateCode)) {
            throw new ChanceValidateException("registerUser-001", "验证码不匹配");
        }
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            throw new ChanceValidateException("registerUser-002", "账户或密码为空");
        }
        if (findUserbyAccount(account) != null) {
            throw new ChanceValidateException("registerUser-003", "用户已存在");
        }

        UserDo user = new UserDo();
        user.setAccount(account);
        user.setPhone(account);
        user.setPassword(password);
        user.setIsDeleted("n");
        user.setType(StringUtils.isBlank(type) ? "1" : type);
        userDoMapperExt.insertSelective(user);
        roleAuthorize(RoleType.ROLE_USER.getValue(), account);
    }

    @Override
    public void ChancePassword(String account, String password, String validateCode) throws ChanceValidateException {
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            throw new ChanceValidateException("ChancePassword-002", "新账号或新密码为空");
        }
        UserDo user = findUserbyAccount(account);
        if (user == null) {
            throw new ChanceValidateException("ChancePassword-003", "账号尚未注册");
        }
        if (!isCodeMatches(account, validateCode)) {
            throw new ChanceValidateException("ChancePassword-001", "验证码错误");
        }
        UserDo user_ = new UserDo();
        user_.setId(user.getId());
        user_.setPassword(password);
        userDoMapperExt.updateByPrimaryKeySelective(user_);
    }

    private boolean isCodeMatches(String account, String validateCode) {
        return true;
    }

    @Override
    public void roleAuthorize(Integer roleId, String account) throws ChanceValidateException {
        UserRoleDo record = new UserRoleDo();
        record.setRoleId(roleId);
        UserDo user = findUserbyAccount(account);
        if (user != null) {
            record.setUserId(user.getId());
            userRoleDoMapperExt.insertSelective(record);
        } else {
            throw new ChanceValidateException("roleAuthorize-005", "用户不存在");
        }
    }

    @Override
    public UserDo getLocalUser() {
        // 当前登录用户
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDo user = findUserbyAccount(userDetails.getUsername());
        return user;
    }

    @Override
    public UserDto getLocalUserDto() throws ChanceValidateException {
        // 当前登录用户
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDo user = findUserbyAccount(userDetails.getUsername());
        if (user != null && user.getId() != null) {
            // 用户详情对象
            User param = (User) userDetails;
            UserDto userDto = findUserById(user.getId());
            if (userDto != null) {
                userDto.setRoles(param.getRoles());
            }
            return userDto;
        }
        return null;
    }

    @Override
    public Integer modifyUser(UserDto user) throws ChanceValidateException {
        if (user == null || user.getId() == null) {
            throw new ChanceValidateException("modifyUser-001", "参数对象为空");
        }
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(user, userDo);
        int rs = userDoMapperExt.updateByPrimaryKeySelectiveExt(userDo);
        // 更换头像
        if (user.getNewImageId() != null) {
            // 删除原有头像
            if (user.getImageId() != null) {
                imageBo.removeImageById(user.getImageId());
            }
            // 关联新头像
            ImageDto imageDto = new ImageDto();
            imageDto.setId(user.getNewImageId());
            imageDto.setAssociateId(user.getId());
            imageBo.modifyImage(imageDto);
        }
        return rs;
    }

    @Override
    public UserDto findUserById(Integer id) throws ChanceValidateException {
        if (id == null || id <= 0) {
            logger.info("findUserById.UBI001");
            throw new ChanceValidateException("findUserById-001", "用户id为空");
        }
        UserDo userDo = new UserDo();
        userDo = userDoMapperExt.selectByPrimaryKey(id);
        if (userDo == null) {
            return null;
        }
        UserDto user = new UserDto();
        BeanUtils.copyProperties(userDo, user);
        ImageDto imageDto = new ImageDto();
        imageDto.setAssociateId(id);
        imageDto.setType(FileType.user_logo.name());
        List<ImageDto> imagelist = imageBo.findImageByDto(imageDto);
        if (imagelist != null && imagelist.size() > 0) {
            user.setHeadPortrait(imagelist.get(0));
        }
        return user;
    }

    /*
	 * 
	 */
    @Override
    public List<User> findUsersByUser(User User) throws ChanceValidateException {
        if (User == null) {
            throw new ChanceValidateException("findUsersByUser-001", "参数对象不能为空"); // 参数对象不能为空
        }
        UserDoExample example = new UserDoExample();
        example.setOrderByClause("id desc");
        example.setPage(new Page(0, 1000000000));
        UserDoExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(User.getAccount())) {
            criteria.andAccountEqualTo(User.getAccount());
        }
        if (StringUtils.isNotBlank(User.getIntro())) {
            criteria.andIntroEqualTo(User.getIntro());
        }
        if (StringUtils.isNotBlank(User.getRealname())) {
            criteria.andRealnameEqualTo(User.getRealname());
        }
        if (StringUtils.isNotBlank(User.getSignature())) {
            criteria.andSignatureEqualTo(User.getSignature());
        }
        if (StringUtils.isNotBlank(User.getType())) {
            criteria.andTypeEqualTo(User.getType());
        }
        if (StringUtils.isNotBlank(User.getWeibo())) {
            criteria.andWeiboEqualTo(User.getWeibo());
        }
        if (StringUtils.isNotBlank(User.getWeixin())) {
            criteria.andWeixinEqualTo(User.getWeixin());
        }
        List<UserDo> list = userDoMapperExt.selectByExample(example);
        List<User> result = null;
        if (list != null && !list.isEmpty()) {
            result = new ArrayList<User>();
            for (UserDo tempDo : list) {
                User tempDto = new User();
                BeanUtils.copyProperties(tempDo, tempDto);
                result.add(tempDto);
            }
        }
        return result;
    }

    /*
	 * 
	 */
    @Override
    public User newUser(User User) throws ChanceValidateException {
        logger.info("开始执行：UserBo.newUser");
        logger.info("  newUser-参数： User=" + JSON.toJSONString(User));
        if (User == null) {
            throw new ChanceValidateException("newUser-001", "参数对象为空");
        }
        if (StringUtils.isBlank(User.getAccount())) {
            throw new ChanceValidateException("newUser-002", "账号不能为空");
        }
        if (StringUtils.isBlank(User.getType())) {
            throw new ChanceValidateException("newUser-003", "用户类型不能为空");
        }
        UserDo userTemp = findUserbyAccount(User.getAccount());
        if (userTemp != null) {
            throw new ChanceValidateException("newUser-004", "该账号已被注册");
        }
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(User, userDo);
        userDoMapperExt.insertSelective(userDo);
        User.setId(userDo.getId());
        return User;
    }

    @Override
    public Integer removeUserById(Integer id) throws ChanceValidateException {
        if (id == null) {
            throw new ChanceValidateException("removeUserById-001", "用户id不能为空");
        }
        UserDo userDo = new UserDo();
        userDo.setId(id);
        int rs = userDoMapperExt.deleteByPrimaryKey(userDo);
        return rs;
    }

}

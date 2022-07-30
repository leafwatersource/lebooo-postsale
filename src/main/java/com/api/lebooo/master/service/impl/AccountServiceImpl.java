package com.api.lebooo.master.service.impl;

import com.api.lebooo.dao.AccountMapper;
import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.master.service.AccountService;
import com.api.lebooo.model.Account;
import com.api.lebooo.model.AccountExample;
import com.api.lebooo.utils.JedisPoolUtils;
import com.api.lebooo.utils.ReferenceUtil;
import com.api.lebooo.utils.TokenUtil;
import com.api.lebooo.utils.encryptTools.AESUtils;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022-05-31 15:59
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private JedisPoolUtils jedisPoolUtils;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> accountList(AccountExample accountExample) {
        return accountMapper.selectByExample(accountExample);
    }

    @Override
    public ResultEntity loginAccount(String account, String password) {

        ResultEntity resultEntity = new ResultEntity();
        try {
            AccountExample accountExample = new AccountExample();
            accountExample.or().andIsDelEqualTo(0).andIsStatusEqualTo(0).andAccountNameEqualTo(account);
            List<Account> accountList = accountMapper.selectByExample(accountExample);
            if (accountList.size() == 0) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("该账号未注册，请重新输入");
                return resultEntity;
            }
            if (accountList.size() > 1) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_RANGE.getCode());
                resultEntity.setMsg("该账号未注册，请重新输入");
                return resultEntity;
            }
            Map<String,Object> map = new HashMap<>();
            Account ac= accountList.get(0);
            if (StringUtils.isBlank(ac.getPassword())) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("账号或密码错误，请重新输入");
                return resultEntity;
            }
            if (StringUtils.isBlank(ac.getSalt())) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("账号或密码错误，请重新输入");
                return resultEntity;
            }
            password = DigestUtils.md5Hex(password+ac.getSalt());
            if (!password.equals(ac.getPassword())) {
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("账号或密码错误，请重新输入");
                return resultEntity;
            }
            if (StringUtils.isNotBlank(ac.getToken())) {
                jedisPoolUtils.del(ReferenceUtil.TOKENKEYPC+ac.getToken());
            }

            String token = TokenUtil.getInstance().generateTokenUUID();
            Account accountObj = new Account();
            accountObj.setId(ac.getId());
            accountObj.setToken(token);
            accountObj.setNewLogin(new Date());
            accountMapper.updateByPrimaryKeySelective(accountObj);

            map.put("accessToken", token);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            resultEntity.setData(map);

            jedisPoolUtils.setex(ReferenceUtil.TOKENKEYPC+token, ReferenceUtil.SECONDSPC, String.valueOf(ac.getId()));
        }catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }

    @Override
    public Account getAccount(Long accountId) {
        return accountMapper.selectByPrimaryKey(accountId);
    }

    @Override
    public ResultEntity saveAccount(Account newAccount, Account acc) {

        ResultEntity resultEntity = new ResultEntity();
        try {
            AccountExample accountExample = new AccountExample();
            accountExample.or().andIsDelEqualTo(0).andAccountNameEqualTo(newAccount.getAccountName());
            List<Account> accountList = accountMapper.selectByExample(accountExample);
            if (accountList.size() > 0){
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                return resultEntity;
            }

            Account account = new Account();
            account.setAccountName(newAccount.getAccountName());
            String salt= AESUtils.generateSalt();
            account.setPassword(DigestUtils.md5Hex(newAccount.getPassword() + salt));
            account.setSalt(salt);
            account.setNickname(newAccount.getNickname());
            account.setTelephone(newAccount.getTelephone());
            account.setIsStatus(newAccount.getIsStatus());
            account.setAccountType(1);
            account.setUpdateAccountId(acc.getId());
            account.setCreateAccountId(acc.getId());
            Date date = new Date();
            account.setCreateTime(date);
            account.setUpdateTime(date);
            accountMapper.insertSelective(account);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            return resultEntity;
        } catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
            return resultEntity;
        }

    }


    public static void main(String[] args) {

//        AESUtils.encrypt(password + "&" + account.getSalt())
//        String salt=AESUtils.generateSalt();
        String salt="RUNaBbUKARQ=";
        String md5=DigestUtils.md5Hex("123456");

      System.out.println("=========={2}"+md5);
//      System.out.println("=========={2}"+DigestUtils.md5Hex(md5+salt));
    }


}



package com.api.lebooo.master.console.controller;

import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.master.service.AccountService;
import com.api.lebooo.model.Account;
import com.api.lebooo.model.AccountExample;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Date 2022-05-31 15:59
 */
@Slf4j
@Controller
@RequestMapping(value = "/console")
public class AccountConsoleController {

    @Autowired
    private AccountService accountService;

    /**
     * 登录
     */
    @RequestMapping(value = "/login/account")
    @ResponseBody
    public ResultEntity loginAccount(@RequestBody String json) {
        ResultEntity resultEntity = new ResultEntity();
        JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
        log.info("/login/account {}"+jsonStr);
        String account = jsonStr.get("account").getAsString();
        if (StringUtils.isBlank(account)){
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("输入有误，请检查");
            return resultEntity;
        }
        String password = jsonStr.get("password").getAsString();
        if (StringUtils.isBlank(password)){
            resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
            resultEntity.setMsg("输入有误，请检查");
            return resultEntity;
        }
        resultEntity = (accountService.loginAccount(account,password));
        log.info("/login/account resultEntity{}"+resultEntity);
        return resultEntity;
    }

    /**
     * 创建新用户
     */
    @RequestMapping(value = "/save/account")
    @ResponseBody
    public ResultEntity saveAccount(@RequestAttribute String json, @RequestAttribute Account account) {

        ResultEntity resultEntity = new ResultEntity();
        try {
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
            String accountName = jsonStr.get("accountName").getAsString();
            if (StringUtils.isBlank(accountName)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("输入有误，请检查");
                return resultEntity;
            }
            String password = jsonStr.get("password").getAsString();
            if (StringUtils.isBlank(password)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("输入有误，请检查");
                return resultEntity;
            }
            String nickname = jsonStr.get("nickname").getAsString();
            if (StringUtils.isBlank(nickname)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("输入有误，请检查");
                return resultEntity;
            }
            String telephone = jsonStr.get("telephone").getAsString();
            if (StringUtils.isBlank(telephone)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("输入有误，请检查");
                return resultEntity;
            }
            String accountType = jsonStr.get("accountType").getAsString();
            if (StringUtils.isBlank(accountType)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("输入有误，请检查");
                return resultEntity;
            }
            String isStatus = jsonStr.get("isStatus").getAsString();
            if (StringUtils.isBlank(isStatus)){
                resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
                resultEntity.setMsg("输入有误，请检查");
                return resultEntity;
            }
            Account newAccount = new Account();
            newAccount.setAccountName(accountName);
            newAccount.setPassword(password);
            newAccount.setNickname(nickname);
            newAccount.setTelephone(telephone);
            newAccount.setAccountType(Integer.parseInt(accountType));
            newAccount.setIsStatus(Integer.parseInt(isStatus));
            resultEntity = accountService.saveAccount(newAccount,account);

        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return resultEntity;
    }

    /**
     * 客服列表
     */
    @RequestMapping(value = "/guestList/account")
    @ResponseBody
    public ResultEntity guestList(){

        ResultEntity resultEntity = new ResultEntity();
        try {
            AccountExample accountExample = new AccountExample();
            accountExample.or().andAccountTypeEqualTo(1);
            List<Account> accountList = accountService.accountList(accountExample);
            List<Map<String,Object>> list = new ArrayList<>();
            if (accountList.size() == 0){
                list = Collections.emptyList();
                resultEntity.setData(list);
                resultEntity.setCode(CodeEnum.SUCCESS.getCode());
                return resultEntity;
            }
            for (Account account : accountList) {
                Map<String,Object> map = new HashMap<>();
                map.put("accountId",account.getId());
                map.put("accountName",account.getAccountName());
                list.add(map);
            }
            resultEntity.setData(list);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            return resultEntity;
        } catch (Exception e) {
            e.printStackTrace();
            resultEntity.setCode(CodeEnum.ERROR.getCode());
        }
        return resultEntity;
    }
}

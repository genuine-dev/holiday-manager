package jp.co.genuine.hm.repository.user;

import org.apache.ibatis.annotations.Mapper;

import jp.co.genuine.hm.model.user.UserAccount;

@Mapper
public interface UserAccountRepository {
	UserAccount findOne (String accountId);
}

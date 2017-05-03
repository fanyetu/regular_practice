package cn.fanyetu.design.behavior.state.simple;

/**
 * 透支状态，具体状态类
 * <p>
 * Created by zhanghaonan on 2017/5/3.
 */
public class OverdraftState extends AccountState {

	public OverdraftState(Account account) {
		this.account = account;
	}

	public OverdraftState(AccountState accountState) {
		this.account = accountState.account;
	}

	@Override
	public void stateCheck() {
		if (this.account.getBalance() > 0) {
			account.setState(new NormalState(this));
		} else if (this.account.getBalance() <= -2000) {
			account.setState(new RestrictedState(this));
		}
	}
}

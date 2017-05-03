package cn.fanyetu.design.behavior.state.simple;

/**
 * 受限状态，具体状态类
 * <p>
 * Created by zhanghaonan on 2017/5/3.
 */
public class RestrictedState extends AccountState {

	public RestrictedState(Account account) {
		this.account = account;
	}

	public RestrictedState(AccountState accountState) {
		this.account = accountState.account;
	}

	@Override
	public void withdraw(double amount) {
		System.out.println("账户受限，不能取款");
	}

	@Override
	public void stateCheck() {
		if (this.account.getBalance() > 0) {
			this.account.setState(new NormalState(this));
		} else if (this.account.getBalance() > -2000) {
			this.account.setState(new OverdraftState(this));
		}
	}
}

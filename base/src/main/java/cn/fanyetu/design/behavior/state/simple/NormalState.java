package cn.fanyetu.design.behavior.state.simple;

/**
 * 正常状态，充当具体状态类
 * <p>
 * Created by zhanghaonan on 2017/5/3.
 */
public class NormalState extends AccountState {

	public NormalState(Account account) {
		this.account = account;
	}

	public NormalState(AccountState accountState) {
		this.account = accountState.account;
	}

	@Override
	public void computeInterest() {
		System.out.println("正常状态，无需支付利息。");
	}

	//状态转换
	@Override
	public void stateCheck() {
		if (account.getBalance() > -2000 && account.getBalance() <= 0) {
			account.setState(new OverdraftState(this));
		} else if (account.getBalance() <= -2000) {
			account.setState(new RestrictedState(this));
		}
	}
}

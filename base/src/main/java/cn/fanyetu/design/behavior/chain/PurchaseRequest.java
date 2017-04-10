package cn.fanyetu.design.behavior.chain;

/**
 * 采购单，请求类
 * <p>
 * Created by Administrator on 2017/4/10.
 */
public class PurchaseRequest {

    private double amount;//采购金额
    private int num;//采购单号
    private String purpose;//采购目的

    public PurchaseRequest(double amount, int num, String purpose) {
        this.amount = amount;
        this.num = num;
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}

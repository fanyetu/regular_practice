package cn.fanyetu.design.behavior.chain;

/**
 * 董事会，具体的处理类
 *
 * Created by Administrator on 2017/4/10.
 */
public class Congress extends Approver {

    Congress(String name) {
        super(name);
    }

    /**
     * 处理请求
     * @param request
     */
    @Override
    public void processRequest(PurchaseRequest request) {
        System.out.println("召开董事会审批采购单：" + request.getNum()
                + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");        //处理请求
    }
}

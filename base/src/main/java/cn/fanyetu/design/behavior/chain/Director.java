package cn.fanyetu.design.behavior.chain;

/**
 * 主任类，具体的请求处理类
 * <p>
 * Created by Administrator on 2017/4/10.
 */
public class Director extends Approver {

    Director(String name) {
        super(name);
    }

    /**
     * 具体的请求处理方法
     *
     * @param request
     */
    @Override
    public void processRequest(PurchaseRequest request) {
        //如果采购的金额在5000元以下，主任处理
        if (request.getAmount() < 5000) {
            System.out.println("主任" + this.name + "审批采购单：" + request.getNum()
                    + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");  //处理请求
        } else {
            System.out.println("主任" + this.name + "转发请求");
            this.successor.processRequest(request);//转发请求
        }
    }
}

package cn.fanyetu.design.behavior.chain;

/**
 * 董事长类，具体的请求处理类
 *
 * Created by Administrator on 2017/4/10.
 */
public class President extends Approver {

    President(String name) {
        super(name);
    }

    /**
     * 具体请求处理方法
     * @param request
     */
    @Override
    public void processRequest(PurchaseRequest request) {
        //如果采购的金额在50000元以下，董事长处理
        if (request.getAmount() < 50000) {
            System.out.println("董事长" + this.name + "审批采购单：" + request.getNum()
                    + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");  //处理请求
        }else{
            System.out.println("董事长" + this.name + "转发请求");
            this.successor.processRequest(request);//转发请求
        }
    }
}

package cn.fanyetu.design.behavior.chain;

/**
 * 审批者类，抽象处理类
 *
 * Created by Administrator on 2017/4/10.
 */
public abstract class Approver {

    protected Approver successor;//定义后继对象
    protected String name;

    Approver(String name) {
        this.name = name;
    }

    //设置后继处理对象
    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    /**
     * 抽象请求处理方法
     * @param request
     */
    public abstract void processRequest(PurchaseRequest request);
}

package cn.fanyetu.design.behavior.chain;

/**
 * 测试职责链模式
 *
 * Created by Administrator on 2017/4/10.
 */
public class Client {

    public static void main(String[] args) {
        //创建请求处理对象
        Approver approver1,approver2,approver3,approver4;
        approver1 = new Director("林华华");
        approver2 = new VicePresident("侯亮平");
        approver3 = new President("李达康");
        approver4 = new Congress("董事会");

        //创建职责链（创建职责链的工作在客户端中进行）
        approver1.setSuccessor(approver2);
        approver2.setSuccessor(approver3);
        approver3.setSuccessor(approver4);

        //创建采购单，调用职责链
        PurchaseRequest pr1 = new PurchaseRequest(4500,10001,"购买倚天剑");
        approver1.processRequest(pr1);

        PurchaseRequest pr2 = new PurchaseRequest(8000,10002,"购买《葵花宝典》");
        approver1.processRequest(pr2);

        PurchaseRequest pr3 = new PurchaseRequest(30000,10003,"购买《金刚经》");
        approver1.processRequest(pr3);

        PurchaseRequest pr4 = new PurchaseRequest(800000,10004,"购买桃花岛");
        approver1.processRequest(pr4);
    }
}

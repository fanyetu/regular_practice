/**
 * Created by zhanghaonan on 2017/6/22.
 */
{
    //数组解构赋值
    let a,b,c;
    [a,b] = [1,2];
    console.log(a,b);
}

{
    //解构赋值的特性，扩展对象
    let a,b,rest;
    [a,b,...rest] = [1,2,3,4,5,6];
    console.log(a,b,rest);
}

{
    //对象解构赋值
    let a,b;
    ({a,b}={a:1,b:2});
    console.log(a,b);
}
{
    //数组解构赋值
    let a,b,c,d;
    [a,b,c=3,d] = [1,2];//设置变量的默认值
    console.log(a,b,c,d);
}

{
    //解构赋值的小案例1
    let a = 1;
    let b = 2;
    [a,b] = [b,a];//通过解构赋值实现变量的交换
    console.log(a,b);
}

{
    function f() {
        return [1,2];
    }
    let a,b;
    [a,b] = f();//使用解构赋值同时获取多个值
    console.log(a,b);
}

{
    function f() {
        return [1,2,3,4,5];
    }
    let a,b;
    [a,,,b] = f();//只接收我们关心的值，但是我觉得并没有什么卵用
    console.log(a,b);
}

{
    function f() {
        return [1,2,3,4,5];
    }
    let a,b;
    [a,...b] = f();//不知道函数返回的数组长度，我只关心第一个，剩下的直接放到一个数组里面
    console.log(a,b);
}

{
    let o = {p:42,q:true};
    let {q,p} = o;//是以key的方式去匹配的
    console.log(p,q);
}

{
    //这里其实是使用了es6种对象的简写模式，左侧的对象等于{a:a=10,b:b=10}
    //对象解构赋值的时候右侧的对象是和左侧的key进行匹配的。
    let {a=10,b=5} = {a:4};//对象解构赋值的默认值
    console.log(a,b);
}

{
    //对象嵌套的解构赋值
    let metaData = {
        title:'abc',
        test:[{
            title:'test1',
            desc:'testdesc'
        }]
    }

    let {title:enTitle,test:[{title:cnTitle}],desc:testDesc='ceshidesc'} = metaData;
    console.log(enTitle,cnTitle,testDesc);
}
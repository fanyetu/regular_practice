/**
 * Created by zhanghaonan on 2017/6/22.
 */

function letTest() {
    for (let i=0;i<10;i++){
        console.log(i);
    }
    // console.log(i);
}

function constTest() {
    const PI = 3.14;
    // PI = 8;
    console.log(PI)

    const b = {
        m:1
    }
    b.n = 2;
    console.log(b);
}

letTest();
constTest();

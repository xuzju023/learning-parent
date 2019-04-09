package lambda.recursion.fibonacci;

import lambda.recursion.IntCall;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 15:14 2019/4/9
 */
public class RecursiveFibonacci {
    static IntCall fib;

    RecursiveFibonacci() {
        fib = n -> n == 0 ? 0 :
                n == 1 ? 1 :
                        fib.call(n - 1) + fib.call(n - 2);
    }

    int fibonacci(int n) {
        return fib.call(n);
    }

    public static void main(String[] args) {
        RecursiveFibonacci rf = new RecursiveFibonacci();
        for (int i = 0; i <= 10; i++) {
            System.out.println(rf.fibonacci(i));
        }
        System.out.println(fib.call(-1));
        fib = x -> x == 0 ? 0 : x == 1 ? 1 : fib.call(x - 1) + fib.call(x - 2);
    }
}

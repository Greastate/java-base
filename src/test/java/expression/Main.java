package expression;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by leiguowei on 2018/1/22
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        Expression expression = new Expression("1+20*(3-1*9)");

        System.out.println(expression.getOperandList());
        System.out.println(expression.getOperatorList());
        System.out.println(expression.getComputeResult());
    }
}
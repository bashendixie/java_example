package com.home.skydance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SkydanceApplicationTests {

    @Test
    void contextLoads() {

        sumOfDiv(16);
    }


    static int sumOfDiv(int x)
    {
        // 1 is a proper divisor
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(x); i++)
        {
            if (x % i == 0)
            {
                sum += i;

                // To handle perfect squares
                if (x / i != i)
                    sum += x / i;
            }
        }
        return sum;
    }
}

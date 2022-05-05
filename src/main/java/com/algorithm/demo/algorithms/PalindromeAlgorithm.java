package com.algorithm.demo.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文算法
 */
public class PalindromeAlgorithm {

    /**
     * 判断是否为回文
     * @param text
     * @return
     */
    public static boolean is_or_not_palindrome(String text)
    {
        int len = text.length(); //计算输入字符串的长度；
        for(int i = 0; i < (len / 2); i++) //只需要判断前一半（len/2）长度就好了
        {
            if(text.charAt(i) != text.charAt(len - 1 - i)) //判断是否为回文数；
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 寻找字符串内的所有回文1
     * @param text
     * @return
     */
    public static List<String> find_all_palindrome_string1(String text)
    {
        // 用于存储所有的回文
        List<String> temps = new ArrayList<>();

        int len = text.length();
        for (int i = 0; i < len; i++)
        {
            for (int j = i + 1; j < len; j++)
            {
                String temp = text.substring(i, j);
                if (temp.length()>1 && is_or_not_palindrome(temp) == true)
                    temps.add(temp);
            }
        }

        return temps;
    }

    /**
     * 寻找字符串内的所有回文2
     * @param s
     * @return
     */
    public static List<String> find_all_palindrome_string2(String s)
    {
        List<String> list = new ArrayList<String>();

        // moving the pivot from starting till end of the string
        for (float pivot = 0; pivot < s.length(); pivot += .5) {

            // set radius to the first nearest element
            // on left and right
            float palindromeRadius = pivot - (int)pivot;

            // if the position needs to be compared has an element
            // and the characters at left and right matches
            while ((pivot + palindromeRadius) < s.length()
                    && (pivot - palindromeRadius) >= 0
                    && s.charAt((int)(pivot - palindromeRadius))
                    == s.charAt((int)(pivot + palindromeRadius))) {

                list.add(s.substring((int)(pivot - palindromeRadius),
                        (int)(pivot + palindromeRadius + 1)));

                // increasing the radius by 1 to point to the
                // next elements in left and right
                palindromeRadius++;
            }
        }

        return list;
    }

    /**
     * 寻找所有的回文字符串，写法1，没有考虑abba这种情况
     * @param text
     * @return
     */
    public static String find_max_string_1(String text)
    {
        // 分割字符串
        String[] strings = text.split("");
        // 用于存储所有的回文
        List<String> temps = new ArrayList<>();
        //找到所有的回文中间的字符的下标
        for(int i=0; i<text.length()-2; i++)
        {
            byte type = -1;

            if(strings[i].equals(strings[i+2])) {
                type = 1;
            }
            if(strings[i].equals(strings[i+1])) {
                type = 2;
            }

            if(type>-1)
            {
                int index = (type==1) ? i+1 : i;
                int radis = (index<=strings.length/2) ? index : (strings.length - index - 1);

                //根据最长回文半径进行循环，判断是否回文，如果是，则加入到list内
                boolean symmetry = false;
                int min = -1, max = -1;
                for(int p=0, k=index-radis; k<index+radis; k++, p++)
                {
                    if(k == index) break;
                    int cha = (index>strings.length/2) ? p : k;

                    symmetry = strings[k].equals(strings[index+radis-cha + ((type==2)?1:0)]);
                    if(min==-1 && max==-1)
                    {
                        min = k;
                        max = index+radis-cha + 1 + ((type==2)?1:0);
                    }
                    if(symmetry == false) min = max = -1;
                }
                if(symmetry) temps.add(text.substring(min, max));
            }
        }

        if(temps.size()==0)
        {
            return "";
        }
        else
        {
            return temps.get(0);
        }
    }

    /**
     * 寻找最长的回文字符串，写法1
     * @param text
     * @return
     */
    public static String find_max_string_3(String text)
    {
        // 分割字符串
        String[] t = text.split("");
        String s1 = "$";
        for (int i = 0; i < t.length; i++)
        {
            s1 += "#" + t[i];
        }
        // 尾部再加上字符@，变为奇数长度字符串
        s1 += "#@";
        t = s1.split("");

        // 第二步：计算数组p、起始索引、最长回文半径
        int n = t.length;
        // p数组
        int[] p = new int[n];
        int id = 0, mx = 0;
        // 最长回文子串的长度
        int maxLength = -1;
        // 最长回文子串的中心位置索引
        int index = 0;
        for (int j = 1; j < n - 1; j++)
        {
            // 参看前文第五部分
            p[j] = mx > j ? Math.min(p[2 * id - j], mx - j) : 1;
            // 向左右两边延伸，扩展右边界
            while (t[j + p[j]] == t[j - p[j]])
            {
                p[j]++;
            }
            // 如果回文子串的右边界超过了mx，则需要更新mx和id的值
            if (mx < p[j] + j)
            {
                mx = p[j] + j;
                id = j;
            }
            // 如果回文子串的长度大于maxLength，则更新maxLength和index的值
            if (maxLength < p[j] - 1)
            {
                // 参看前文第三部分
                maxLength = p[j] - 1;
                index = j;
            }
        }
        // 第三步：截取字符串，输出结果
        // 起始索引的计算参看前文第四部分
        int start = (index - maxLength) / 2;
        System.out.println((text.substring(start, maxLength)));
        return "";
    }

    /**
     * 寻找所有的回文字符串，写法2
     * @param text
     * @return
     */
    public static String find_max_string_2(String text)
    {
        // 声明用于存储回文中间的字符的下标
        List<Integer> centers = new ArrayList<>();
        // 分割字符串
        String[] strings = text.split("");
        //找到所有的回文中间的字符的下标
        for(int i=0; i<text.length()-2; i++)
        {
            if(strings[i].equals(strings[i+2]))
            {
                centers.add(i+1);
            }
        }

        // 用于存储所有的回文
        List<String> temps = new ArrayList<>();
        for(int i=0; i<centers.size(); i++)
        {
            //判断回文中间的字符串的下标的位置，并确定对应最长回文的半径
            int index = centers.get(i);
            int radis = (index<=strings.length/2) ? index : (strings.length - index - 1);

            //根据最长回文半径进行循环，判断是否回文，如果是，则加入到list内
            boolean symmetry = false;
            int min = -1, max = -1;
            for(int p=0, k=index-radis; k<index+radis; k++, p++)
            {
                if(k == index) break;
                int cha = (index>strings.length/2) ? p : k;
                symmetry = strings[k].equals(strings[index+radis-cha]);
                if(min==-1 && max==-1)
                {
                    min = k;
                    max = index+radis-cha+1;
                }
                if(symmetry == false) min = max = -1;
            }
            if(symmetry) temps.add(text.substring(min, max));
        }

        if(temps.size()==0)
        {
            return "";
        }
        else
        {
            return temps.get(0);
        }
    }
}

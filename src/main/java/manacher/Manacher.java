package manacher;

public class Manacher {

    /**
     * 处理原始字符串使之更方便操作
     * @param str
     * @return
     */
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    /**
     * 返回str的最长回文子串的长度
     * @param str
     * @return
     */
    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] curArr = new int[charArr.length];
        int C = -1;
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
            //i在回文外部，直接扩
            if(i>=R){
                curArr[i]=1;
                //尝试向右扩一个，看看是否满足以第i个为中心的回文
                while (i+curArr[i]<charArr.length&&i-curArr[i]>-1){
                    //满足则当前回文右边界+1
                    if (charArr[i + curArr[i]] == charArr[i - curArr[i]]){
                        curArr[i]++;
                    }
                    else {
                        break;
                    }
                }
                //满足条件则设置回文中心和右边界
                C=i;
                R=i+curArr[i];
                max = Math.max(max, curArr[i]);
            }//i在回文右边界内
            else{
                //当前回文半径至少是R-i与对应点回文半径的最小值
                //这里就是马拉车算法减少时间复杂度的原因
                curArr[i]=Math.min(curArr[2 * C - i], R - i);
                //尝试进行扩充，判断回文长度能不能再长一点
                while (i + curArr[i] < charArr.length && i - curArr[i] > -1) {
                    if (charArr[i + curArr[i]] == charArr[i - curArr[i]]){
                        curArr[i]++;
                    }
                    else {
                        break;
                    }
                }
            }

            //统计回文半径
            if (i + curArr[i] > R) {
                R = i + curArr[i];
                C = i;
            }
            max = Math.max(max, curArr[i]);
        }
        return max - 1;
    }

    /**
     * 是上一个方法的改进版，
     * 返回str的最长回文子串的长度
     * @param str
     * @return
     */
    public static int maxLcpsLength2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        //回文半径数组
        int[] curArr = new int[charArr.length];
        //当前回文中心的索引
        int C = -1;
        //当前回文右边界
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
            //i'的回文到r的距离，哪个小哪个就是回文的区域
            //2*index-i是当前索引关于回文中心的对称点
            curArr[i] = R > i ? Math.min(curArr[2 * C - i], R - i) : 1;
            //要检验的区域没越界，且当前索引对应回文的左边边界也没有越界
            while (i + curArr[i] < charArr.length && i - curArr[i] > -1) {
                //扩充之后的左右两个值相等，回文半径+1
                //利用之前求出的半径加速判断
                if (charArr[i + curArr[i]] == charArr[i - curArr[i]]){
                    curArr[i]++;
                }
                else {
                    break;
                }
            }
            //统计回文半径
            if (i + curArr[i] > R) {
                R = i + curArr[i];
                C = i;
            }
            max = Math.max(max, curArr[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength2(str1));
    }
}

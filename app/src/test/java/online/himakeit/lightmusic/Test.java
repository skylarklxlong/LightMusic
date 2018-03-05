package online.himakeit.lightmusic;

/**
 * @author：LiXueLong
 * @date：2018/2/27
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class Test {

    private long[] a;
    private int size; //数组的大小
    private int nElem; //数组中有多少项

    /**
     * 二分查找
     *
     * @param searchNum
     * @return
     */
    public int binarySearch(long searchNum) {
        int lower = 0;
        int upper = nElem - 1;
        int curr;
        while (true) {
            curr = (lower + upper) / 2;
            if (a[curr] == searchNum) {
                return curr;
            } else if (lower > upper) {
                return -1;
            } else {
                if (a[curr] < searchNum) {
                    lower = curr + 1;
                } else {
                    upper = curr - 1;
                }
            }
        }
    }

    /**
     * 选择排序
     * <p>
     * 算法规则： 将待排序集合(0...n)看成两部分，在起始状态中，一部分为(k..n)的待排序
     * unsorted集合，另一部分为(0...k)的已排序sorted集合,在待排序集合中挑选出最小元素并
     * 且记录下标i，若该下标不等于k，那么 unsorted[i] 与 sorted[k]交换 ，一直重复这个过
     * 程，直到unsorted集合中元素为空为止。
     *
     * @param args
     */
    public static void selectionSort(int[] args) {
        int len = args.length;
        for (int i = 0, k = 0; i < len; i++, k = i) {
            //找到最小的一个
            for (int j = i + 1; j < len; j++) {
                if (args[j] < args[k]) {
                    k = j;
                }
            }
            if (i != k) {
                int tmp = args[i];
                args[i] = args[k];
                args[k] = tmp;
            }
        }
    }

    /**
     * 冒泡排序
     * <p>
     * 算法规则： 由于算法每次都将一个最大的元素往上冒，我们可以将待排序集合(0...n)看成
     * 两部分，一部分为(k..n)的待排序unsorted集合，另一部分为(0...k)的已排序sorted集合，
     * 每一次都在unsorted集合从前往后遍历，选出一个数，如果这个数比其后面的数大，则进
     * 行交换。完成一轮之后，就肯定能将这一轮unsorted集合中最大的数移动到集合的最后，
     * 并且将这个数从unsorted中删除，移入sorted中。
     *
     * @param args
     */
    public void bubbleSort(int[] args) {
        //第一层循环从数组的最后往前遍历
        for (int i = args.length - 1; i > 0; --i) {
            //这里循环的上界是 i - 1，在这里体现出 “将每一趟排序选出来的最大的数从sorted中移除”
            for (int j = 0; j < i; j++) {
                //保证在相邻的两个数中比较选出最大的并且进行交换(冒泡过程)
                if (args[j] > args[j + 1]) {
                    int temp = args[j];
                    args[j] = args[j + 1];
                    args[j + 1] = temp;
                }
            }
        }
    }

    private void swap(int[] args, int fromIndex, int toIndex) {
        args[fromIndex] = args[toIndex];
    }

    public int dividerAndChange(int[] args, int start, int end) {
        //标准值
        int pivot = args[start];
        while (start < end) {
            // 从右向左寻找，一直找到比参照值还小的数值，进行替换
            // 这里要注意，循环条件必须是 当后面的数 小于 参照值的时候
            // 我们才跳出这一层循环
            while (start < end && args[end] >= pivot)
                end--;
            if (start < end) {
                swap(args, start, end);
                start++;
            }
            // 从左向右寻找，一直找到比参照值还大的数组，进行替换
            while (start < end && args[start] < pivot)
                start++;
            if (start < end) {
                swap(args, end, start);
                end--;
            }
        }
        args[start] = pivot;
        return start;
    }

    /**
     * 快速排序
     * <p>
     * 算法规则： 本质来说，快速排序的过程就是不断地将无序元素集递归分割，一直到所有
     * 的分区只包含一个元素为止。
     * 由于快速排序是一种分治算法，我们可以用分治思想将快排分为三个步骤：
     * 1.分：设定一个分割值，并根据它将数据分为两部分
     * 2.治：分别在两部分用递归的方式，继续使用快速排序法
     * 3.合：对分割的部分排序直到完成
     *
     * @param args
     * @param start
     * @param end
     */
    public void quickSort(int[] args, int start, int end) {
        //当分治的元素大于1个的时候，才有意义
        if (end - start > 1) {
            int mid = 0;
            mid = dividerAndChange(args, start, end);
            // 对左部分排序
            quickSort(args, start, mid);
            // 对右部分排序
            quickSort(args, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] test = {5, 1, 3, 2, 4};
        selectionSort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}

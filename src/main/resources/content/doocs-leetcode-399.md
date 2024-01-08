#归并排序，JavaScript版的好像是不稳定的

Owner: doocs

Repo: leetcode

Labels: 

## Colin3191 (20 May 2021)

归并排序，JavaScript版的好像是不稳定的

## Colin3191 (20 May 2021)

function merge(arr1, arr2) {
    let arr = [];
    while (arr1.length && arr2.length) {
        if (arr1[0] < arr2[0]) {
            arr.push(arr1.shift());
        } else {
            arr.push(arr2.shift());
        }
    }
    return [...arr, ...arr1, ...arr2];

}
这里在arr1和arr2都只有一个元素且它们的值相等的情况下，返回的新数组中它们的顺序是交换了的。

## yanglbme (20 May 2021)

应该是 `if (arr1[0] <= arr2[0])` 吧

## yanglbme (20 May 2021)

@Colin3191 已更正，谢谢反馈


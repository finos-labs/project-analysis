#Solved

Owner: doocs

Repo: leetcode

Labels: 

## Erica-Iris (13 Dec 2021)

[HERE](https://doocs.github.io/leetcode/#/./basic/sorting/BubbleSort/README)
It should be like this
```Java
    private static void bubbleSort(int[] nums) {
        boolean hasChange = **false**;
        for (int i = 0, n = nums.length; i < n - 1 && hasChange; ++i) {
            hasChange = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    hasChange = true;
                }
            }
        }
    }
```

## yanglbme (13 Dec 2021)

Hi @Erica-Iris 

if `hasChange` is false, it will never go into the outer for loop.

## Erica-Iris (13 Dec 2021)

> 



> Hi @Erica-Iris
> 
> if `hasChange` is false, it will never go into the outer for loop.

I'm sorry that I was too anxious and didn't look carefully, which caused trouble to everyone.

## Abraham-80tut (13 Dec 2021)

> [HERE](https://doocs.github.io/leetcode/#/./basic/sorting/BubbleSort/README)
> It should be like this
> ```Java
>     private static void bubbleSort(int[] nums) {
>         boolean hasChange = **false**;
>         for (int i = 0, n = nums.length; i < n - 1 && hasChange; ++i) {
>             hasChange = false;
>             for (int j = 0; j < n - i - 1; ++j) {
>                 if (nums[j] > nums[j + 1]) {
>                     swap(nums, j, j + 1);
>                     hasChange = true;
>                 }
>             }
>         }
>     }
> ```




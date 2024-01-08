#144. 先序遍历二叉树

Owner: MisterBooo

Repo: LeetCodeAnimation

Labels: 

## parap1uie-s (23 Apr 2019)

用栈解决的思路没问题，但先入栈右节点，再处理左节点，思路有点拧

不符合先序遍历，根-左-右，的习惯

建议改成如下形式，参考代码：

```
class Solution:
    def preorderTraversal(self, root: TreeNode) -> List[int]:
        if not root: return []
        
        ans = []
        stack = []
        
        current_root = root
        # 当前节点不为none或者栈里有节点
        while current_root or stack:
            # 节点入栈，保留当前节点值，向左迭代
            if current_root is not None:
                stack.append(current_root)
                ans.append(current_root.val)
                current_root = current_root.left
            # 当前节点的左子树遍历完成，遍历当前节点的右子树
            else:
                current_root = stack.pop().right
        
        return ans
```

## MisterBooo (02 May 2019)

收到~，我后期重新做一个不拧把的动画，感谢


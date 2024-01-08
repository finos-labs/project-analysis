#Incorrect (?) time complexities for Heap operations

Owner: kdn251

Repo: interviews

Labels: 

## yangshun (05 Sept 2017)

## Access and Search

I was wondering why access and search are both `O(lgn)` time complexity? Taking the diagram on your README for example, finding a particular value (for example 2), will involve searching through every node in the Heap. Therefore I think access and search should be both `O(n)`.

## Remove Max / Min

As pointed out in #10, remove max / min should be `O(lgn)` and not `O(1)`.

I can submit a PR to fix the above issues if you like 😄 


## changyujiang (15 Sept 2017)

If access by index, it can be done within O(1). 

## yangshun (16 Sept 2017)

What does access mean actually? I take it to mean accessing a particular value, like in a BST example, accessing a value is O(lgn). To access a particular value in a Heap you would have to first search for it, which is O(n) for non-max/min elements.

## kdn251 (16 Sept 2017)

Hi sorry for the late response guys...
By access, I'm assuming that you're using a heap correctly. Therefore, accessing the min or max value will be O(1)  due to the properties of a heap. Search should be lg(n) as you can discard half of each subtree as you traverse the heap again due to its invariants.

## yangshun (16 Sept 2017)

@kdn251 Thanks for your response! My reply as follows:

> By access, I'm assuming that you're using a heap correctly. Therefore, accessing the min or max value will be O(1) due to the properties of a heap. 

Sure, then I think it should be called Access Max/Min just like in Remove Max/Min. And Access Max/Min should be O(1).

> Search should be lg(n) as you can discard half of each subtree as you traverse the heap again due to its invariants.

What are we searching for here? The only time a "traversal" sort of operation is in bubbling up after an insertion and percolating down after a deletion of the root. Search doesn't really apply to heaps.

Hence these are my proposals:

- Change "Access" to "Access Max/Min" and its time complexity `O(1)`
- Remove "Search"
- Remove "Remove"
- Change "Remove Max/Min" time complexity to `O(log(n))`

Would be happy to create a PR for the updated proposal!

## changyujiang (17 Sept 2017)

Thanks for your reply. Heap is not completed ordered, though. When search for a item, we cannot simply discard half of it but traverse all of items, which is O(n). 

## yangshun (17 Sept 2017)

> Clearly the O(1) refers to getting the top element of either a max or min heap respectively, not an arbitrary element 

Sure, but in the README, the access/search for a heap is written as O(lg(n)) not O(1).

> because heaps serve a specific purpose.... Learn your data structures.

I think the point here is to let people revise/learn the data structures through this repository.

## EugenHotaj (17 Sept 2017)

> Sure, but in the README, the access/search for a heap is written as O(lg(n)) not O(1).

Ah you're right, completely misunderstood you. Sorry about that. Reopening for visibility. 



## yangshun (17 Sept 2017)

Would you like me to submit a PR to address this? Would be glad to do so (:

## EugenHotaj (17 Sept 2017)

Sure, but @kdn251 will have to approve it. 

## kdn251 (17 Sept 2017)

Thanks @EugenHotaj and I see what you mean @yangshun I'll merge your PR now. Thanks for the contribution!


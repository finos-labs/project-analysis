#Add algorithm to solve the Steiner tree problem.

Owner: williamfiset

Repo: Algorithms

Labels: Graph theory 

## williamfiset (24 Oct 2017)



## micahstairs (25 Oct 2017)

I can do this one! But how do we handle dependencies on other algorithms? For example, this snippet depends on Floyd-Warshall.

## williamfiset (25 Oct 2017)

I always include all the dependencies someone needs inside the one file for the particular algorithm I'm developing even if it's a lot of duplication. I feel like this is the best solution so that all algorithms work out of the box.

## micahstairs (25 Oct 2017)

I guess the only issue is if we find a bug or something in the
Floyd-Warshall snippet and then have to change it in a lot of spots.

On Wed, 25 Oct 2017 at 13:20 William Fiset <notifications@github.com> wrote:

> I always include all the dependencies someone needs inside the one file
> for the particular algorithm I'm developing even if it's a lot of
> duplication. I feel like this is the best solution so that all algorithms
> work out if the box.
>
> —
> You are receiving this because you commented.
>
>
> Reply to this email directly, view it on GitHub
> <https://github.com/williamfiset/Algorithms/issues/28#issuecomment-339386481>,
> or mute the thread
> <https://github.com/notifications/unsubscribe-auth/AG5X_WK2DuSI5qmqvpj_t7e4gs74dhznks5sv1-5gaJpZM4QD3MJ>
> .
>


## williamfiset (25 Oct 2017)

We could make the whole project use packages/imports but then each snippet no longer works on its own. A hacky way to avoid this would be to somehow automatically copy the dependencies into the file by running a code auto generation tool. That does seems like a lot of work ATM though so I would hold off.

## williamfiset (23 Nov 2017)

@micahstairs this is done, correct?

## micahstairs (24 Nov 2017)

Correct!


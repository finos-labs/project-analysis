#Enhancement: THIS naming removal step

Owner: skylot

Repo: jadx

Labels: 

## JoshZA (25 Jun 2014)

Could you please add a pass to optimize class calls from 'classname' to 'this' instead

class Something {
    int a;
    private void example() {
         Something something = this;
         something.whatever();
         something.a = 123;
    }
}

Would be nicer if it showed:

this.whatever();
this.a = 123;

etc


## JoshZA (28 Jun 2014)

Works great thanks!



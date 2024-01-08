#A trouble in case of the same names of a constructor and a method in a class

Owner: skylot

Repo: jadx

Labels: 

## barabanshek (18 Aug 2014)

Thank you for a brilliant app! 
But using it I've found some error.
If a class contents a constructor with the same name as a one of the class's methods, it 
causes an error after decompilation a .dex file due to exchanging of the method's name from "Name" to "Name_", meanwhile the method is called from another class using it's actual name "Name".

Example.
The class Bug has a constructor named "Bug", of cource, and a method named "Bug" too.
![origin_source](https://cloud.githubusercontent.com/assets/8015047/3948980/0ad5e1f0-26af-11e4-9dd8-1b0172bfb1a4.png)
After decompilation it contents a method named "Bug_" instead of "Bug".
![bug_source_after_decompilation](https://cloud.githubusercontent.com/assets/8015047/3948979/0ad2dcee-26af-11e4-9108-d81f8c0b2f65.png)
But in the main method it's being called as "Bug".
![main_source_after_decompilation](https://cloud.githubusercontent.com/assets/8015047/3948981/0add616e-26af-11e4-93af-10b90a9b3fdb.png)


## skylot (18 Aug 2014)

Thanks! I didn't ever know that java compiler allow such method naming.
And funny that lines of code I removed for fix this issue was in jadx from the beginning, I found that commit and it was created **2 years ago!** (2012-08-11) and first jadx commit was in 2012-08-06. :)


## barabanshek (19 Aug 2014)

Thanks! Now it works perfect!



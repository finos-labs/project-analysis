#Abort HTTP connection

Owner: eugenp

Repo: tutorials

Labels: 

## gvsrini (23 May 2015)

I was looking at this example - http://www.baeldung.com/httpclient-cancel-request.

I do not seem to understand how the abort will work when the execute method has NOT yet returned. Please check once again. The abort will only help **ignore** the response **after response is received**. That would be more an **ignore** than an **abort**


## eugenp (05 Jun 2015)

Well, it depends how you define the concept and semantics of abort. My thinking on this is - regardless of how we define it ourselves, the library has the reference definition and since the API is named "abort" - I think calling it the same is appropriate. Hope that helps. Cheers, 
Eugen. 



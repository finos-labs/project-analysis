#Question: How would I export it to an exe?

Owner: NationalSecurityAgency

Repo: ghidra

Labels: Reason: Duplicate Type: Question 

## Aholicknight (06 Mar 2019)

I want to export the program back into a `.exe` after done editing it. How would I do that? 
![image](https://user-images.githubusercontent.com/7843719/53855589-dd79cc00-3f93-11e9-8b1c-f7554a43afac.png)


## rszibele (06 Mar 2019)

You need to select `Binary` as the export format which recreates the executable. You can also press F1 anywhere which opens up the help with the current context.

## Aholicknight (06 Mar 2019)

@rszibele Thanks for your help but when I export it as a .bin the file output is this:
![image](https://user-images.githubusercontent.com/7843719/53895397-98878100-3ff7-11e9-9b48-26a106f06be7.png)
and the file does not output onto the Desktop.

## SmurfRP (06 Mar 2019)

Oi! Why are you trying to decompile citizen hack? Lmaooooooooo chm_client is very very familiar.

## Aholicknight (06 Mar 2019)

@SmurfRP Wanted to see how it works. Now, how would I make it back to an `.exe` after editing it?

## rszibele (06 Mar 2019)

I'm unable to reproduce the saving issue. After importing an executable on Windows and exporting it, it does save the executable where I wanted it to save it. Ghirda automatically adds ".bin" to the file name, which you will have to remove to be able to run it.
![image](https://user-images.githubusercontent.com/1387224/53898897-ebc9f580-4030-11e9-8532-3d05bbb93353.png)

Windows is currently also affected by this other bug https://github.com/NationalSecurityAgency/ghidra/issues/19 so you _must_ import the executable in the "Raw Binary" format to be able to run the exported executable afterwards. It's possible that something is blocking Ghirda from saving on your end, check the Ghirda logs for any errors, which should be located at:

`C:\Users\{USERNAME}\.ghirda\.ghirda-9.0\application.log`

## Kushagra3911 (20 Mar 2022)

Select the PE option,PE stands for Portable Executable

## 20urc3 (10 Jul 2023)

Latest ghidra version only refer to those export func:
![image](https://github.com/NationalSecurityAgency/ghidra/assets/94982366/4425d977-50be-499d-b915-619e8604bf52)
No PE or Binary option


## ryanmkurtz (10 Jul 2023)

This is currently done though the "Original File" exporter.  It is no longer restricted to PE and ELF.

## 20urc3 (10 Jul 2023)

> This is currently done though the "Original File" exporter. It is no longer restricted to PE and ELF.

The Original File exporter isn't working for me on latest version for me. It pop-up the export Windows in front of the export summary, in a infinite loop.  I'm forced to cancel to stop the loop, the Original File isn't modified.

![image](https://github.com/NationalSecurityAgency/ghidra/assets/94982366/a813eb27-e7a3-4170-8c37-cd81fd8956c3)


## ryanmkurtz (10 Jul 2023)

Ghidra 10.3.1?

## 20urc3 (10 Jul 2023)

Yes @ryanmkurtz 


## ryanmkurtz (10 Jul 2023)

So you import your binary, then then immediately export Original File and it freaks out?  

## 20urc3 (10 Jul 2023)

- Import PE
- Patch an instruction 
- Export Program > Original File
- Freaks out and loop as shown above

## ryanmkurtz (10 Jul 2023)

I cannot reproduce.  Can you please make a new issue, attaching your binary and exact instructions on how to reproduce? 

## 20urc3 (10 Jul 2023)

The issue reside in patching instruction without respecting the right offset i guess. I've fixed my patched and now it exports. Maybe try to replace a "PUSH EBP" by a "JMP [ADDRESS]" with a too large instruction size, it overlap on the next instruction then messed up the export on Orginal File.

Still tho imo it should display error message, not loop

## ryanmkurtz (10 Jul 2023)

Did you spill over into a relocation? 


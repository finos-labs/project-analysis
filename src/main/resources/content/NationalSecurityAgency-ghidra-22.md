#A lot of Bad instructions

Owner: NationalSecurityAgency

Repo: ghidra

Labels: Type: Bug 

## progmboy (06 Mar 2019)

**Describe the bug**
A lot of normal instructions are recognized as **Bad instructions**

The follow is one of them

**Bad instructions** From 1c002cff2 to 1c002cffd 
```
       1c002cfcf 48  8b  0d       MOV        hWnd ,qword ptr [ghsemDynamicModeChange ]         = ??
                 92  49  1b  00
       1c002cfd6 45  8b  e8       MOV        R13D ,flags
       1c002cfd9 48  89  74       MOV        qword ptr [RSP  + local_1a0 ],RSI
                 24  68
       1c002cfde 4c  8b  e2       MOV        R12 ,hrgnClip
       1c002cfe1 48  89  75  90    MOV        qword ptr [RBP  + local_178 ],RSI
       1c002cfe5 89  74  24  58    MOV        dword ptr [RSP  + local_1b0 ],ESI
       1c002cfe9 89  74  24  60    MOV        dword ptr [RSP  + local_1a8 ],ESI
       1c002cfed 48  85  c9       TEST       hWnd ,hWnd
       1c002cff0 74  0c           JZ         LAB_1c002cffe
       1c002cff2 48              ??         48h    H
       1c002cff3 ff              ??         FFh
       1c002cff4 15              ??         15h
       1c002cff5 8f              ??         8Fh
       1c002cff6 63              ??         63h    c
       1c002cff7 1d              ??         1Dh
       1c002cff8 00              ??         00h
       1c002cff9 0f              ??         0Fh
       1c002cffa 1f              ??         1Fh
       1c002cffb 44              ??         44h    D
       1c002cffc 00              ??         00h
       1c002cffd 00              ??         00h
```

the pesudo-code is:
```
  if (_ghsemDynamicModeChange != 0) {
                    /* WARNING: Bad instruction - Truncating control flow here */
    halt_baddata();
  }
```


**To Reproduce**
Steps to reproduce the behavior:
1. Analyze the win32kbase.sys file at the last version windows 10(rs5)
2. Go to function _GetDCEx you will see it


**Expected behavior**
Like ida's resoult:
```
.text:00000001C002CFCF 48 8B 0D 92 49 1B 00                    mov     rcx, cs:ghsemDynamicModeChange
.text:00000001C002CFD6 45 8B E8                                mov     r13d, r8d
.text:00000001C002CFD9 48 89 74 24 68                          mov     [rsp+200h+var_198], rsi
.text:00000001C002CFDE 4C 8B E2                                mov     r12, rdx
.text:00000001C002CFE1 48 89 75 90                             mov     [rbp+100h+var_170], rsi
.text:00000001C002CFE5 89 74 24 58                             mov     [rsp+200h+var_1A8], esi
.text:00000001C002CFE9 89 74 24 60                             mov     [rsp+200h+var_1A0], esi
.text:00000001C002CFED 48 85 C9                                test    rcx, rcx
.text:00000001C002CFF0 74 0C                                   jz      short loc_1C002CFFE
.text:00000001C002CFF2 48 FF 15 8F 63 1D 00                    call    cs:__imp_ExEnterPriorityRegionAndAcquireResourceShared
.text:00000001C002CFF9 0F 1F 44 00 00                          nop     dword ptr [rax+rax+00h]
```

presudo code:
```
if ( ghsemDynamicModeChange != 0 )
    ExEnterPriorityRegionAndAcquireResourceShared();
```


**Screenshots**
None

**Environment (please complete the following information):**
 - OS: windows 10 RS5 64bits version
 - Version: ghidra_9.0_PUBLIC_20190228



## JustasMasiulis (06 Mar 2019)

Pretty much unusable for any sort of windows binary RE. KernelBase.dll is full of functions with unrecognized instructions.

## nneonneo (06 Mar 2019)

Have you tried the "Aggressive Instruction Finder" experimental analysis tool?

## JustasMasiulis (06 Mar 2019)

> Have you tried the "Aggressive Instruction Finder" experimental analysis tool?

Didn't really seem to help.

## LapisLazulis (06 Mar 2019)

Can confirm it happens too, and Agressive Instruction Finder didn't help too.

## AttePalmr (06 Mar 2019)

I believe it happens due to the disassembler determining that the code will not be executed. You can select the bytes which are "??", right click and click Disassemble or just press D. 

## x5engine (06 Mar 2019)

Did you get the issue on Linux?

## mumbel (06 Mar 2019)

Looks like REX is broken for call atleast

## progmboy (07 Mar 2019)

> I believe it happens due to the disassembler determining that the code will not be executed. You can select the bytes which are "??", right click and click Disassemble or just press D.

some instructions like "48 FF 15 C9 FA 1F 00                    call    cs:__imp_ObReferenceObjectByPointer" can not be recognized . press D is not work

## progmboy (07 Mar 2019)

I find the 4 types of instructions that can not be unrecognized.

##  1, 48 FF 15 ? ? ? ? call:cs:xxxx
All of "48 FF 15 ? ? ? ?" instructions will be recognized as bad instructions. "press D" is not useful.

##  2,cfg functions "__guard_dispatch_icall_fptr"
```
1c00030fb ff  15  17       CALL       qword ptr [__guard_dispatch_icall_fptr ]         undefined _guard_dispatch_icall()
            07  20  00                                                                   -> _guard_dispatch_icall
                        -- Flow Override: CALL_RETURN (COMPUTED_CALL_TERMINATOR)
1c0003101 84              ??         84h
1c0003102 c0              ??         C0h
1c0003103 b8              ??         B8h
1c0003104 0d              ??         0Dh
```

ghidra mark the "__guard_dispatch_icall_fptr" is noreturn
```
                             noreturn  undefined  _guard_dispatch_icall ()
             undefined         AL:1           <RETURN>
                             _guard_dispatch_icall_nop
                             _guard_dispatch_icall
       1c00bf290 ff  e0           JMP        RAX
```
So the function flow will be break at after calling "_guard_dispatch_icall", this type can be fix use "press D"

## 3.Some function call other function which contains "48 ff 15 xxx":
If the target function contain "48 ff 15 xxx" the function will be marked with  noreturn. 
for example：
```
                             *************************************************************
                             * public: __cdecl EnterLeaveCrit::EnterLeaveCrit(void) __  ..
                             *************************************************************
                             noreturn  void  __thiscall  EnterLeaveCrit (EnterLeaveCrit 
       1c00bdc5c 48  89  5c       MOV        qword ptr [RSP  + local_res8 ],RBX
                 24  08
       1c00bdc61 48  89  74       MOV        qword ptr [RSP  + local_res10 ],RSI
                 24  10
       1c00bdc66 57              PUSH       RDI
       1c00bdc67 48  83  ec  50    SUB        RSP ,0x50
       1c00bdc6b 48  8b  05       MOV        RAX ,qword ptr [__security_cookie ]               = 00002B992DDFA232h
                 1e  04  12  00
       1c00bdc72 48  33  c4       XOR        RAX ,RSP
       1c00bdc75 48  89  44       MOV        qword ptr [RSP  + local_10 ],RAX
                 24  48
       1c00bdc7a 48  8b  f1       MOV        RSI ,this
       1c00bdc7d 48              ??         48h    H
       1c00bdc7e ff              ??         FFh
       1c00bdc7f 15              ??         15h
       1c00bdc80 b4              ??         B4h
```

```
       1c0002c7d 49  8d  4b  10    LEA        RCX =>local_res10 ,[R11  + 0x10 ]
       1c0002c81 e8  d6  af       CALL       EnterLeaveCrit::EnterLeaveCrit                   void EnterLeaveCrit(EnterLeaveCr
                 0b  00
                             -- Flow Override: CALL_RETURN (CALL_TERMINATOR)
       1c0002c86 48              ??         48h    H
       1c0002c87 8d              ??         8Dh
       1c0002c88 8c              ??         8Ch
       1c0002c89 24              ??         24h    $
       1c0002c8a 80              ??         80h

```

## 4, try/exception block

ghidra couldn't recognized try/except block.
ghidra's result:
```
       1c0003bb5 e8  46  b8       CALL       memcpy                                           void * memcpy(void * _Dst, void 
                 0b  00
       1c0003bba eb  0c           JMP        LAB_1c0003bc8
       1c0003bbc 8b              ??         8Bh
       1c0003bbd f8              ??         F8h
       1c0003bbe 48              ??         48h    H
       1c0003bbf 8b              ??         8Bh
       1c0003bc0 74              ??         74h    t
       1c0003bc1 24              ??         24h    $
       1c0003bc2 50              ??         50h    P
       1c0003bc3 48              ??         48h    H
       1c0003bc4 8b              ??         8Bh
       1c0003bc5 5c              ??         5Ch    \
       1c0003bc6 24              ??         24h    $
       1c0003bc7 58              ??         58h    X

```

ida's result:
```
.text:00000001C0003E6E EB 16                                   jmp     short loc_1C0003E86
.text:00000001C0003E6E                         ;   } // starts at 1C0003E3A
.text:00000001C0003E70                         ; ---------------------------------------------------------------------------
.text:00000001C0003E70                         ;   __except(1) // owned by 1C0003E3A
.text:00000001C0003E70 8B D8                                   mov     ebx, eax
.text:00000001C0003E72 48 8B 7C 24 60                          mov     rdi, [rsp+48h+arg_10]
.text:00000001C0003E77 48 8B 74 24 58                          mov     rsi, [rsp+48h+arg_8]
.text:00000001C0003E7C 44 8B 44 24 50                          mov     r8d, [rsp+48h+arg_0]
.text:00000001C0003E81 4C 8B 74 24 68                          mov     r14, [rsp+48h+arg_18]
.text:00000001C0003E86
```

## nsadeveloper789 (07 Mar 2019)

OK. So a lot is collecting on this ticket. Good finds! I've worked out a solution for the 48 FF 15 ..... Try adding the following to ```ia.sinc``` at line 2712. I can't guarantee it's totally correct, but here it is:

```
:CALL rm64      is vexMode=0 & addrsize=2 & opsize=2 & byte=0xff; rm64 & reg_opcode=2 ...   { push88(&:8 inst_next); call [rm64]; }
```

I've updated the FAQ with some information about fixing missing or bad instructions in our Sleigh files. Have a look for documentation and a Sleigh debugging script.

## progmboy (08 Mar 2019)

> OK. So a lot is collecting on this ticket. Good finds! I've worked out a solution for the 48 FF 15 ..... Try adding the following to `ia.sinc` at line 2712. I can't guarantee it's totally correct, but here it is:
> 
> ```
> :CALL rm64      is vexMode=0 & addrsize=2 & opsize=2 & byte=0xff; rm64 & reg_opcode=2 ...   { push88(&:8 inst_next); call [rm64]; }
> ```
> I've updated the FAQ with some information about fixing missing or bad instructions in our Sleigh files. Have a look for documentation and a Sleigh debugging script.

It's work!!!! Thanks bro.

## sebsebmc (13 Mar 2019)

Not sure if this is the right place to report this, but it looks like ghidra doesn't recognize a multi-byte NOP instructions on x86.
So far I found that `0f 1f 00    nop    DWORD PTR [rax+0x0]` is not recognized

## nneonneo (13 Mar 2019)

If you’re referring to the bits of padding code between functions, Ghidra
doesn’t bother to parse them since they can’t be executed.
On Wed, Mar 13, 2019 at 11:32 AM Sebastian C <notifications@github.com>
wrote:

> Not sure if this is the right place to report this, but it looks like
> ghidra doesn't recognize a multi-byte NOP instructions on x86.
> So far I found that 0f 1f 00 nop DWORD PTR [rax+0x0] is not recognized
>
> —
> You are receiving this because you commented.
> Reply to this email directly, view it on GitHub
> <https://github.com/NationalSecurityAgency/ghidra/issues/22#issuecomment-472550253>,
> or mute the thread
> <https://github.com/notifications/unsubscribe-auth/AAEmuVYqTEoOlYuZ0Awis4lK1w6M1IbJks5vWUQsgaJpZM4bgI9q>
> .
>


## sebsebmc (13 Mar 2019)

Ah, I was not aware of this feature. You're right, this op is not reachable.

## recvfrom (13 Mar 2019)

I confirmed that `0f 1f 00` is correctly disassembled into a NOP (for x86)

Below is a list of NOP instructions that objdump decodes into NOPs but that Ghidra can't disassemble (the list may not be complete, and tries to only show one instruction per instruction class):
```
0f 18 20             	nop/reserved BYTE PTR [eax]
0f 18 28             	nop/reserved BYTE PTR [eax]
0f 18 30             	nop/reserved BYTE PTR [eax]
0f 18 38             	nop/reserved BYTE PTR [eax]
0f 19 00             	nop    DWORD PTR [eax]
0f 19 c0             	nop    eax
0f 1a c0             	nop    eax
0f 1b c0             	nop    eax
0f 1c 00             	nop    DWORD PTR [eax]
0f 1c 08             	nop    DWORD PTR [eax]
0f 1c 10             	nop    DWORD PTR [eax]
0f 1c 18             	nop    DWORD PTR [eax]
0f 1c 20             	nop    DWORD PTR [eax]
0f 1c 28             	nop    DWORD PTR [eax]
0f 1c 30             	nop    DWORD PTR [eax]
0f 1c 38             	nop    DWORD PTR [eax]
0f 1c c0             	nop    eax
0f 1d 00             	nop    DWORD PTR [eax]
0f 1d c0             	nop    eax
0f 1e 00             	nop    DWORD PTR [eax]
0f 1e c0             	nop    eax
66 0f 1e c0          	nop    ax
f2 0f 1c 00          	repnz nop DWORD PTR [eax]
f2 0f 1e c0          	nop    eax
f3 0f 1b c0          	nop    eax
f3 0f 1c 00          	repz nop DWORD PTR [eax]
f3 0f 1e c0          	nop    eax
f3 0f 1e d0          	nop    eax
f3 0f 1e d8          	nop    eax
f3 0f 1e e0          	nop    eax
f3 0f 1e e8          	nop    eax
f3 0f 1e f0          	nop    eax
f3 0f 1e f8          	nop    eax
f3 0f 1e f9          	nop    ecx
f3 0f 1e fc          	nop    esp
f3 0f 1e fd          	nop    ebp
f3 0f 1e fe          	nop    esi
f3 0f 1e ff          	nop    edi
```
I can share a test binary with these instructions (and the ones from https://github.com/NationalSecurityAgency/ghidra/issues/53#issuecomment-470682674) if that'd be helpful

## emteere (18 Mar 2019)

There is an update to the x86 in the works to fill out the identified missing instructions in this and other issues (#197, #215).  Thanks for all the feedback!

These aren't finally tested, but if the issues are causing your analysis problems you can try them.
Replace 
[x86_changes.zip](https://github.com/NationalSecurityAgency/ghidra/files/2977930/x86_changes.zip)

the attached files in Ghidra/Processors/x86/data/languages.

## Cgettys (19 Mar 2019)

I'm pretty sure there are some significant unhandled SSE instructions as well. 
E.g. this instruction:
       14107663e c4              ??         C4h
       14107663f e2              ??         E2h
       141076640 f1              ??         F1h
       141076641 a9              ??         A9h
       141076642 1d              ??         1Dh
       141076643 d9              ??         D9h
       141076644 4f              ??         4Fh    O
       141076645 47              ??         47h    G
       141076646 00              ??         00h
I try to stay away from low level SSE & AVX instructions though for my sanity, so I can't tell you what the opcode is.


## Cgettys (19 Mar 2019)

The above instruction is vfmadd213sd xmm3,xmm1,QWORD PTR [rip+0x474fd9] (thanks zbe on Discord)

## saruman9 (24 Mar 2019)

@emteere 
> These aren't finally tested, but if the issues are causing your analysis problems you can try them.

`:CMP spec_rm8,imm8       is vexMode=0 & (addrsize=0 | addrsize=1) & (byte=0x80 | byte=0x82); spec_rm8 & reg_opcode=7 ...; imm8        { subflags(  spec_rm8,imm8 ); local tmp =   spec_rm8 -   imm8; resultflags(tmp); }
`
didn't work for:
`80 38 00                      CMP        byte ptr [RAX], 0x0`

After deleting `(addrsize=0 | addrsize=1)` everything is fine.

Also for `AND` instruction.


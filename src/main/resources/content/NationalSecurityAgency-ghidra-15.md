#pdb execution failed

Owner: NationalSecurityAgency

Repo: ghidra

Labels: Type: Bug 

## qianyimao (06 Mar 2019)

PDB Execution failure of pdb.exe.
This was likely caused by severe execution failure which can occur if executed
on an unsupported platform. It may be neccessary to rebuild the PDB executable
for your platform (see Ghidra/Features/PDB/src).
ghidra.app.util.bin.format.pdb.PdbException: PDB Execution failure of pdb.exe.
This was likely caused by severe execution failure which can occur if executed
on an unsupported platform. It may be neccessary to rebuild the PDB executable
for your platform (see Ghidra/Features/PDB/src).
	at ghidra.app.util.bin.format.pdb.PdbParserNEW.verifyPdbSignature(PdbParserNEW.java:568)
	at ghidra.app.util.bin.format.pdb.PdbParserNEW.processPdbContents(PdbParserNEW.java:549)
	at ghidra.app.util.bin.format.pdb.PdbParserNEW.parse(PdbParserNEW.java:149)
	at pdb.LoadPdbTask$1.analysisWorkerCallback(LoadPdbTask.java:59)
	at ghidra.app.plugin.core.analysis.AutoAnalysisManager$AnalysisWorkerCommand.applyTo(AutoAnalysisManager.java:1683)
	at ghidra.app.plugin.core.analysis.AutoAnalysisManager$AnalysisTaskWrapper.run(AutoAnalysisManager.java:685)
	at ghidra.app.plugin.core.analysis.AutoAnalysisManager.startAnalysis(AutoAnalysisManager.java:785)
	at ghidra.app.plugin.core.analysis.AutoAnalysisManager.startAnalysis(AutoAnalysisManager.java:664)
	at ghidra.app.plugin.core.analysis.AutoAnalysisManager.startAnalysis(AutoAnalysisManager.java:629)
	at ghidra.app.plugin.core.analysis.AnalysisBackgroundCommand.applyTo(AnalysisBackgroundCommand.java:62)
	at ghidra.framework.plugintool.mgr.BackgroundCommandTask.run(BackgroundCommandTask.java:101)
	at ghidra.framework.plugintool.mgr.ToolTaskManager.run(ToolTaskManager.java:315)
	at java.base/java.lang.Thread.run(Thread.java:834)

---------------------------------------------------

## computerline1z (06 Mar 2019)

Need rebuild pdb project in Ghidra\Features\PDB for your platform, and register msdia, read the PDB document for more details :D

## qianyimao (07 Mar 2019)

i have fixed this, thanks


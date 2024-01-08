#jadx-gui: Save all code is different

Owner: skylot

Repo: jadx

Labels: 

## jpstotz (24 Apr 2014)

The code created by the "Save all" function is different from the code displayed in jadx-gui:

The method parameter names are not correctly named. Example:

jadx-gui shows:

public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            progress = (progress / 1000) \* 1000;
...

"Save all" creates:

public void onProgressChanged(SeekBar r1_SeekBar, int r2i, boolean r3z) {
    if (fromUser) {
        int progress = (progress / 1000) \* 1000;



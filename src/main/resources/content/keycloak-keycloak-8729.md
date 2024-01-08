#kc.sh ignores all values passed to --users flag when exporting

Owner: keycloak

Repo: keycloak

Labels: kind/bug area/dist/quarkus status/rejected 

## DanielJoyce (08 Nov 2021)

### Describe the bug

`./kc.sh export --file phylum_realm.json --users skip  --profile dev` exports users, even though they should be skipped

I've tried `--users=skip` and `--users SKIP` as well.


### Version

15.0.2

### Expected behavior

Users should be skipped when exporting

### Actual behavior

Users are not skipped

### How to Reproduce?

_No response_

### Anything else?

_No response_

## pedroigor (08 Nov 2021)

@DanielJoyce The single file exporter should always export users. If you want to skip users you should use the dir provider by using `--dir` instead of `--file`.

```
./kc.sh --dir /tmp/export-dir --users realm_file
```

I think the help message is misleading and not 100% accurate. We should be fixing that. Looks like our documentation is also misleading ...

## pedroigor (15 Dec 2021)

@DanielJoyce Does the information helps to close this issue?

## pedroigor (04 Jan 2022)

@DanielJoyce Thanks, feel free to re-open if there is something else to discuss.

## mhagnumdw (28 Mar 2023)


> @DanielJoyce The single file exporter should always export users. If you want to skip users you should use the dir provider by using `--dir` instead of `--file`.
> 
> ```
> ./kc.sh --dir /tmp/export-dir --users realm_file
> ```
> 
> I think the help message is misleading and not 100% accurate. We should be fixing that. Looks like our documentation is also misleading ...

This doesn't work in Keycloak 21. You forgot the `--export` parameter, I put it in and it didn't work either.

Other attempts I made:

```bash
kc.sh export --dir /tmp/export-dir --users realm_file
```
Problem: users are exported.

```bash
kc.sh export --dir /tmp/export-dir --users skip
```
Problem: `ERROR [org.keycloak.quarkus.runtime.cli.ExecutionExceptionHandler] (main) ERROR: java.lang.IllegalArgumentException: Type specified for TypedQuery [java.lang.Integer] is incompatible with query return type [class java.lang.Long]`

What I found most intuitive according to the doc:

```bash
kc.sh export --file /tmp/keycloak-export.json --users skip
```
Problem: users are exported.



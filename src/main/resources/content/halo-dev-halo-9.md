#These are two stored xss vulnerability

Owner: halo-dev

Repo: halo

Labels: kind/bug 

## Insh3ll (11 May 2018)

## The first place

> The front comment `commentAuthor` field is not strictly filtered

### Vulnerability code
> FrontCommentController.java
```
comment.setCommentAuthorEmail(comment.getCommentAuthorEmail().toLowerCase());
comment.setPost(post);
comment.setCommentDate(new Date());
comment.setCommentAuthorIp(HaloUtil.getIpAddr(request));
comment.setIsAdmin(0);
commentService.saveByComment(comment);
```

### Payload
`a" onclick="alert(/xss/)`

-----
# Second place

> When login failed at the background, the failed login username and password are written to the log without xss filtering, and displayed on the background home page, resulting in storage xss vulnerability.

### Vulnerability code
> AdminController.java
```
try {
            User aUser = userService.findUser();
            ...
        } catch (Exception e) {
            Integer errorCount = userService.updateUserLoginError();
            if (errorCount >= 5) {
                userService.updateUserLoginEnable("false");
            }
            userService.updateUserLoginLast(new Date());
            logsService.saveByLogs(new Logs(LogsRecord.LOGIN, LogsRecord.LOGIN_ERROR + "[" + loginName + "," + loginPwd + "]", HaloUtil.getIpAddr(request), new Date()));
            log.error("登录失败！：{0}", e.getMessage());
        }
```

### Payload
```
loginName=admin&loginPwd=admin<a href="javascript:alert(/xss/);">xss</a>
```


## ruibaby (11 May 2018)

OK, I know, but where are you from?
I am considering whether to do i18n.

## Insh3ll (14 May 2018)

I am Chinese

## monty8800 (14 May 2018)

@Insh3ll 。。。。俩中国人，在这撇英文

## ruibaby (14 May 2018)

@monty8800 哈哈哈哈哈


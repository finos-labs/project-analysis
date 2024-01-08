#mysql 编码参数去除charsetNumber，根据connectionCharset设置的编码即可

Owner: alibaba

Repo: canal

Labels: feature 

## agapple (18 Feb 2013)

v1.0.0的版本中，设定编码需要同时设置charsetNumber和connectionCharset，charsetNumber主要是和mysql进行handshark时，指定character_set_results的返回编码。connectionCharset指定为客户端的解析编码



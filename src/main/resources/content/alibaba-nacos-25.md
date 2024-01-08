#BeatProcessor's run method can be never executed

Owner: alibaba

Repo: nacos

Labels: 

## wei7202839 (02 Aug 2018)

executeService是个singleThreadExecutor,BeatProcessor内部while (true)自旋
BeatTask的run方法是永远没有机会执行的,这个是故意的呢还是设计错误?
```
class BeatProcessor implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    for (Map.Entry<String, BeatInfo> entry : dom2Beat.entrySet()) {
                        BeatInfo beatInfo = entry.getValue();
                        executorService.schedule(new BeatTask(beatInfo), 0, TimeUnit.MILLISECONDS);
                        LogUtils.LOG.info("BEAT", "send beat to server: ", beatInfo.toString());
                    }

                    TimeUnit.MILLISECONDS.sleep(clientBeatInterval);
                } catch (Exception e) {
                    LogUtils.LOG.error("CLIENT-BEAT", "Exception while scheduling beat.", e);
                }
            }
        }
    }

    class BeatTask implements Runnable {
        BeatInfo beatInfo;

        public BeatTask(BeatInfo beatInfo) {
            this.beatInfo = beatInfo;
        }

        @Override
        public void run() {
            Map<String, String> params = new HashMap<String, String>(2);
            params.put("beat", JSON.toJSONString(beatInfo));
            params.put("dom", beatInfo.getDom());

            try {
                String result = serverProxy.callAllServers(UtilAndComs.NACOS_URL_BASE + "/api/clientBeat", params);
                JSONObject jsonObject = JSON.parseObject(result);

                if (jsonObject != null) {
                    clientBeatInterval = jsonObject.getLong("clientBeatInterval");

                }
            } catch (Exception e) {
                LogUtils.LOG.error("CLIENT-BEAT", "failed to send beat: " + JSON.toJSONString(beatInfo), e);
            }
        }
    }
```

## nkorange (02 Aug 2018)

@wei7202839 It is a bug as you said. Are you interested in creating a PR to fix it?

## nkorange (03 Aug 2018)

Fix it at [https://github.com/alibaba/nacos/commit/a8ead77f6fb6603f4aa6c734c0751c0138697e60](url)


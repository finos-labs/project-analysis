#Http connection work improvements

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## nostra13 (13 Dec 2011)

May be this code will be useful:

``` java
public static void setClient() {
        HttpParams params = new BasicHttpParams();
        // Turn off stale checking. Our connections break all the time anyway,
        // and it's not worth it to pay the penalty of checking every time.
        HttpConnectionParams.setStaleCheckingEnabled(params, false);
        // Default connection and socket timeout of 10 seconds. Tweak to taste.
        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
        HttpConnectionParams.setSoTimeout(params, 10 * 1000);
        HttpConnectionParams.setSocketBufferSize(params, 8192);

        // Don't handle redirects -- return them to the caller. Our code
        // often wants to re-POST after a redirect, which we must do ourselves.
        HttpClientParams.setRedirecting(params, false);
        // Set the specified user agent and register standard protocols.
        HttpProtocolParams.setUserAgent(params, "bigbuzzy business");
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

        ClientConnectionManager manager = new ThreadSafeClientConnManager(params, schemeRegistry);

        AndroidApplication.client = new DefaultHttpClient(manager, params);
    }

    //И загрузку через него (нет сети - берем из кеша иначе грузим):
    public static String retrieve(String url, boolean cache) {
        StringBuilder sb = new StringBuilder();
        sb.append(AndroidApplication.DOMEN);
        sb.append(url);
        url = sb.toString();
        Log.d("retrieve", url);
        String md5 = Md5.md5(url);
        File casheDir = AndroidApplication.cacheDir;//context.getCacheDir();

        File f = null;
        if (casheDir != null && cache) {
            f = new File(casheDir, md5);
            final long time = new Date().getTime() / 1000;
            if (f.exists()) {
                if ((f.lastModified() / 1000 + 600) > time) {
                    return readFile(f);
                }
            }
        }

        HttpGet getRequest = new HttpGet(url);

        try {
            HttpResponse getResponse = AndroidApplication.getClient().execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                Log.e("statusCode", statusCode + "");
            }

            HttpEntity getResponseEntity = getResponse.getEntity();

            if (getResponseEntity != null && statusCode == HttpStatus.SC_OK) {
                String s = EntityUtils.toString(getResponseEntity);
                if (statusCode != HttpStatus.SC_OK && f != null) {
                    BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(f));
                    os.write(s.getBytes());
                    os.close();
                }
                return s;
            }
        } catch (IOException e) {
            getRequest.abort();
            if (f != null) {
                if (f.exists()) {
                    return readFile(f);
                }
            }
            Log.e("NetUtils error", e.toString());
        }
        return null;
    }

    public static String readFile(File file) {
        String data = null;
        try {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            char[] inputBuffer;
            inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            data = new String(inputBuffer);
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
```


## nostra13 (25 Jun 2012)

I have HttpClientImageDownloader for work with network using HttpClient. Also URLConnection is preferable. So this code won't be used in library.



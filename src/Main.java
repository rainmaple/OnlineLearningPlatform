import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aliyun.signature.Signature;
import com.aliyun.signature.The;
import com.user.util.GetUTCTimeUtil;

/**
 * This <tt>Main</tt> class demonstrates how to:
 * <ul>
 *     <li>Build and get an immutable api signature.</li>
 *     <li>Build and compose a request url to invoke remote api.</li>
 * </ul>
 *
 * @since 05/06/2017
 * @author Harbor Luo
 */
public class Main {
    private final static Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {

        final String URL = "https://api.aliyun.com/?q=&key=";

        /*
         * Demo_01: Build an immutable signature instance with url and print out;
         * The signature's toString() function will invoke signature's get() function.
         */
        out("output: " +
                Signature.newBuilder()
                    .method(The.HTTP.GET.method())
                    .url(URL)
                    .secret(The.API.secret())
                    .build()
        );

        /*
         * Demo_02: Build a real signature and compose a request url to invoke DescribeRegions api call;
         */
        
        String UTCTimeStr = GetUTCTimeUtil.getUTCTimeStr();
        final String ACTION = "DescribeLiveStreamsPublishList&DomainName=live.iotesta.cn&StartTime=2017-07-25T03:30:50Z&EndTime=2017-07-30T03:30:50Z&Timestamp="+UTCTimeStr;
        final String API_URL = The.API.build(ACTION);

        out(httpGet(Signature.newBuilder()
                        .method(The.HTTP.GET.method())
                        .url(API_URL)
                        .secret(The.API.secret())
                        .build()
                        .compose())
        );
    }

    private static String httpGet(String url) throws IOException {
    	/*
         * Read and covert a inputStream to a String.
         * Referred this: 
         * http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
         */
    	out(url);
        @SuppressWarnings("resource")
		Scanner s = new Scanner(new URL(url).openStream(), The.utf_8()).useDelimiter("\\A");
        try {
            String resposne = s.hasNext() ? s.next() : "true";
            return resposne;
        } finally {
            s.close();
        }
    }

    private static void out(String newLine) {
        LOG.log(Level.INFO, newLine);
    }
}

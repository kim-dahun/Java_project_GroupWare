package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PapagoTranslator {
    public static void main(String[] args) {
        String clientId = "flFAVxeyy8Umaq5ZLVJ6"; // 발급받은 클라이언트 ID
        String clientSecret = "aLdnwIHZxc"; // 발급받은 클라이언트 시크릿

        try {
            String text = URLEncoder.encode("나는 한국에서 제일 잘 나가는 가수다", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            // 번역할 텍스트를 요청 파라미터에 설정
            String postParams = "source=ko&target=en&text=" + text;
            con.setDoOutput(true);
            con.getOutputStream().write(postParams.getBytes());

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { // 성공적으로 API를 호출한 경우
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생한 경우
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            
            
            
            // 번역 결과 출력
            System.out.println(response);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}